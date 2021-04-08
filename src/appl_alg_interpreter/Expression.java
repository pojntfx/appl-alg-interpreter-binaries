package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Expression {
	static Hashtable<String, Object> priorities = new Hashtable<String, Object>();
	{
		initializeBinaryOperatorPriorities();
	}

	int line_number;
	String filename;
	
	// predefined tokens for the applicative algorithm language
	static String NAME = "[a-zA-Z_][a-zA-Z_0-9]*";
	static String NUMBER = "((-?[0-9]+(\\.[0-9]*)?)|(-?\\.[0-9]+))(E(\\-)?[0-9]+)?";
	static String VALUE = NUMBER + "|'[^']*'|true|false";
	static String BINOPERATOR = "//|!=|\\->|>=|<=|[\\+\\-\\*/%=<>]|\\^|and|or";
	
	public Expression(int line_number, String filename) {
		this.filename = filename;
		this.line_number = line_number;
	}

	public static Expression Create(ProgramScanner psc, boolean continueWithBinOp) throws Exception {
		Expression result = null;
		
		// leading "-" sign?
		String minus = psc.nextTokenOrNull("\\-");
		if (minus != null) {
			result = new MinusExpression(psc.lineNumber, psc.filename, Expression.Create(psc, false));
		} else {
			// negation of boolean expression?
			String exclamation_mark = psc.nextTokenOrNull("!");
			if (exclamation_mark != null) {
				result = new NegationExpression(psc.lineNumber, psc.filename, Expression.Create(psc, false));
			} else {	
				// value?
				String value = psc.nextTokenOrNull(VALUE);
				if (value != null) {
					result = BinOpOrValueExpression.Create(psc, value);
				} else {
					// expression in parenthesis?
					String parenthesis = psc.nextTokenOrNull("\\(");
					if (parenthesis != null) {
						result = Expression.Create(psc, true);
						psc.nextToken("\\)");
					} else {
						// if then else endif expression?
						String keyword = psc.nextTokenOrNull("if");
						if (keyword != null) {
							result = IfStatementExpression.Create(psc);
						} else {
							// list expression?
							String bracket = psc.nextTokenOrNull("\\[");
							if (bracket != null) {
								result = ListExpression.Create(psc);
							} else {
								// length?
								keyword = psc.nextTokenOrNull("length");
								if (keyword != null) {
									result = LengthExpression.Create(psc);
								} else {
									// function call or variable?
									String name = psc.nextToken(NAME);
									parenthesis = psc.nextTokenOrNull("\\(");
									if (parenthesis != null) {
										result = CallExpression.Create(psc, name);
									} else {
										result = VariableExpression.Create(psc, name);
									}
								}
							}
						}
					}
				}
			}
		}
		// check whether there are indexes ( indexed expression )
		int lnb = psc.lineNumber;
		ArrayList<Expression> indexes = checkForIndexes(psc);
		if (indexes != null) {
			result = new IndexedExpression(lnb, psc.filename, result, indexes);
		}
		// look for further binary operations if continueWithBinOp = true
		if (continueWithBinOp) {
			ArrayList<Object> boc = new ArrayList<Object>(); // B_inary O_peration C_hain
			boc.add(result);
			while (true) {
				String op = psc.nextTokenOrNull(BINOPERATOR);
				if (op != null) {
					boc.add(op);
					Expression next = Expression.Create(psc, false);
					boc.add(next);
				} else {
					break;
				}
			}
			// Binary Operation Chain ready, now create the nested expression tree
			result = createTree(psc, boc, 0, boc.size()-1);
		}
		return result;
	}
	
	private static Expression createTree(ProgramScanner psc, ArrayList arr, int lower, int upper) throws Exception {
		
		Expression result = null;
		if (lower == upper) {                       // no (more) binary operation available
			return (Expression) arr.get(lower);
		}
		int rootIndex = findRootIndex(arr, lower+1, upper-1);
		Expression left = createTree(psc, arr, lower, rootIndex-1);
		String op = (String) arr.get(rootIndex);
		Expression right = createTree(psc, arr, rootIndex+1, upper);
		if (op.equals("->")) {
			ArrayList<Expression> indexes = checkForIndexes(psc);
			if (indexes != null) {
				String message = "Parsing error in " + psc.filename + " (line " + psc.lineNumber + "): double indexing not possible (try parenthesis or use [1,2] instead of [1][2])."; 
				throw new Exception(message);
			}
			result = new ListAssignmentExpression(right.line_number, psc.filename, left, right);
		} else {
			ArrayList<Expression> indexes = checkForIndexes(psc);
			if (indexes != null) {
				Expression expr = new BinOpOrValueExpression(right.line_number, psc.filename, op, left, right);
				result = new IndexedExpression(right.line_number, psc.filename, expr, indexes);
			} else {
				result = new BinOpOrValueExpression(right.line_number, psc.filename, op, left, right);
			}
		}
		return result;
	}
	
	private static int findRootIndex(ArrayList arr, int first, int last) {
		int result = first;
		int prio = (Integer) priorities.get((String)arr.get(first));
		for (int i = first; i <= last; i=i+2) {
			if (  ((Integer) priorities.get((String)arr.get(i))) >= prio  ) {
				prio = (Integer) priorities.get((String)arr.get(i));
				result = i;
			}
		}
		return result;
	}
			
	protected static ArrayList<Expression> checkForIndexes(ProgramScanner psc) throws Exception {
		ArrayList<Expression> result = new ArrayList<Expression>();
		if (psc.nextTokenOrNull("\\[") != null) {
			while (true) {
				Expression expr = Expression.Create(psc, true);
				result.add(expr);
				String comma = psc.nextTokenOrNull(",");
				if (comma == null)
					break;
			}
			psc.nextToken("\\]");
		}
		if (result.size() == 0)
			return null;
		else
			return result;
	}
	
	private static void initializeBinaryOperatorPriorities() {
		priorities.put("^", new Integer(0));  // executed first 
		
		priorities.put("*", new Integer(1));
		priorities.put("/", new Integer(1));
		priorities.put("//", new Integer(1));
		priorities.put("%", new Integer(1));
		
		priorities.put("+", new Integer(2));
		priorities.put("-", new Integer(2));
		
		priorities.put("<", new Integer(3));
		priorities.put("<=", new Integer(3));
		priorities.put(">", new Integer(3));
		priorities.put(">=", new Integer(3));
		priorities.put("=", new Integer(3));
		priorities.put("!=", new Integer(3));
		
		priorities.put("and", new Integer(4));
		priorities.put("or", new Integer(4));

		priorities.put("->", new Integer(5));  // executed last
	}

	protected String formatNumber(String s) {
		return s.replaceAll("\\.0*$", "");
	}

	protected String getErrorMessageHeader() {
		return "Runtime error in " + filename + " (line " + line_number + "): ";
	}

	public abstract Object evaluate(Hashtable<String,Object> params) throws Exception;
}
