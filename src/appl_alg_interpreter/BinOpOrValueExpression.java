package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public class BinOpOrValueExpression extends Expression {
	
	// binary operator or single value
	String value;
	Expression left;
	Expression right;
	
	public BinOpOrValueExpression(int line_number, String filename, String value, Expression left, Expression right) {
		super(line_number, filename);
		if (value.matches("'[^']*'")) {
			value = value.replace("NEWLINE", "\n");
			value = value.replace("TAB", "\t");
		}
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public static Expression Create(ProgramScanner psc, String value) throws Exception {
		return new BinOpOrValueExpression(psc.lineNumber, psc.filename, value, null, null);
	}

	public Object evaluate(Hashtable<String,Object> params) throws Exception {
		Object result = null;
		try {
			if (left == null && right == null) {
				result = formatNumber(value);
			} else {
				Object l = left.evaluate(params);
				Object r = right.evaluate(params);
				if (!value.equals("+")) {           // '+' makes sense with all operands
					if (! (l instanceof String)) {
						String message = getErrorMessageHeader() + "binary operation '" + value + "' with non-elementary type as left operand."; 
						throw new Exception(message);
					}
					if (! (r instanceof String)) {
						String message = getErrorMessageHeader() + "binary operation '" + value + "' with non-elementary type as right operand."; 
						throw new Exception(message);
					}
				}
				
				if (value.equals("+")) {
					if (l instanceof ArrayList && r instanceof ArrayList) {
						((ArrayList) l).addAll((ArrayList) r);
						result = l;
					}
					else if (l instanceof ArrayList && !(r instanceof ArrayList)) {
						((ArrayList) l).add(r);
						result = l;
					}
					else if (!(l instanceof ArrayList) && r instanceof ArrayList) {
						((ArrayList) r).add(0, l);
						result = r;
					}
					// no operand is an array
					else if (((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = ((String)r).substring(1,((String)r).length()-1);
						result = "'" + sl + sr + "'";
					} 
					else if (((String)l).matches("'[^']*'") && !((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = (String)r;
						result = "'" + sl + sr + "'";
					}
					else if (!((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = (String)l;
						String sr = ((String)r).substring(1,((String)r).length()-1);
						result = "'" + sl + sr + "'";
					}
					else {
						double dl = Double.parseDouble((String)l);
						double dr = Double.parseDouble((String)r);
						result = formatNumber((new Double(dl + dr)).toString());
					}
				}
				if (value.equals("-")) {
					double dl = Double.parseDouble((String)l);
					double dr = Double.parseDouble((String)r);
					result = formatNumber((new Double(dl - dr)).toString());
				}
				if (value.equals("*")) {
					double dl = Double.parseDouble((String)l);
					double dr = Double.parseDouble((String)r);
					result = formatNumber((new Double(dl * dr)).toString());
				}
				if (value.equals("^")) {
					double dl = Double.parseDouble((String)l);
					double dr = Double.parseDouble((String)r);
					result = formatNumber((new Double(Math.pow(dl, dr))).toString());
				}
				if (value.equals("/")) {
					double dl = Double.parseDouble((String)l);
					double dr = Double.parseDouble((String)r);
					if (dr == 0.0) {
						String message = getErrorMessageHeader() + "division by zero."; 
						throw new Exception(message);
					}
					result = formatNumber((new Double(dl / dr)).toString());
				}
				if (value.equals("//")) {
					double dl = Double.parseDouble((String)l);
					double dr = Double.parseDouble((String)r);
					if (dr == 0.0) {
						String message = getErrorMessageHeader() + "division by zero."; 
						throw new Exception(message);
					}
					result = formatNumber((new Double((int)(0.0000001 + (dl/dr)))).toString());
				}
				if (value.equals("%")) {  // attention: force numbers to be integers
					double ll = (int) Math.floor(0.0000001 + Double.parseDouble((String)l));
					double lr = (int) Math.floor(0.0000001 + Double.parseDouble((String)r));
					result = formatNumber((new Double((int)(0.0000001 + (ll%lr)))).toString());
				}
				if (value.equals("=")) {
					if (((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = ((String)r).substring(1,((String)r).length()-1);;
						result = (new Boolean(sl.equals(sr))).toString();
					} else if (((String)l).matches("true|false") && ((String)r).matches("true|false")) {
						boolean bl = Boolean.parseBoolean((String)l);
						boolean br = Boolean.parseBoolean((String)r);
						result = (new Boolean(bl == br)).toString();
					} else if (((String)l).matches(NUMBER) && 
							((String)r).matches(NUMBER)) {
						double dl = Double.parseDouble((String)l);
						double dr = Double.parseDouble((String)r);
						result = (new Boolean(dl == dr)).toString();
					}
				}
				if (value.equals("!=")) {
					if (((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = ((String)r).substring(1,((String)r).length()-1);;
						result = (new Boolean(!(sl.equals(sr)))).toString();
					} else if (((String)l).matches("true|false") && ((String)r).matches("true|false")) {
						boolean bl = Boolean.parseBoolean((String)l);
						boolean br = Boolean.parseBoolean((String)r);
						result = (new Boolean(bl != br)).toString();
					} else if (((String)l).matches(NUMBER) && 
							((String)r).matches(NUMBER)) {
						double dl = Double.parseDouble((String)l);
						double dr = Double.parseDouble((String)r);
						result = (new Boolean(dl != dr)).toString();
					}
				}
				if (value.equals("and")) {
					boolean bl = Boolean.parseBoolean((String)l);
					boolean br = Boolean.parseBoolean((String)r);
					result = (new Boolean(bl && br)).toString();
				}
				if (value.equals("or")) {
					boolean bl = Boolean.parseBoolean((String)l);
					boolean br = Boolean.parseBoolean((String)r);
					result = (new Boolean(bl || br)).toString();
				}
				if (value.equals("<")) {
					if (((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = ((String)r).substring(1,((String)r).length()-1);;
						result = new Boolean(sl.compareTo(sr)<0).toString();
					} else if (((String)l).matches(NUMBER) && 
							((String)r).matches(NUMBER)) {
						double dl = Double.parseDouble((String)l);
						double dr = Double.parseDouble((String)r);
						result = (new Boolean(dl < dr)).toString();
					}
				}
				if (value.equals("<=")) {
					if (((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = ((String)r).substring(1,((String)r).length()-1);;
						result = new Boolean(sl.compareTo(sr)<=0).toString();
					} else if (((String)l).matches(NUMBER) && 
							((String)r).matches(NUMBER)) {
						double dl = Double.parseDouble((String)l);
						double dr = Double.parseDouble((String)r);
						result = (new Boolean(dl <= dr)).toString();
					}
				}
				if (value.equals(">")) {
					if (((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = ((String)r).substring(1,((String)r).length()-1);;
						result = new Boolean(sl.compareTo(sr)>0).toString();
					} else if (((String)l).matches(NUMBER) && 
							((String)r).matches(NUMBER)) {
						double dl = Double.parseDouble((String)l);
						double dr = Double.parseDouble((String)r);
						result = (new Boolean(dl > dr)).toString();
					}
				}
				if (value.equals(">=")) {
					if (((String)l).matches("'[^']*'") && ((String)r).matches("'[^']*'")) {
						String sl = ((String)l).substring(1,((String)l).length()-1);
						String sr = ((String)r).substring(1,((String)r).length()-1);;
						result = new Boolean(sl.compareTo(sr)>=0).toString();
					} else if (((String)l).matches(NUMBER) && 
							((String)r).matches(NUMBER)) {
						double dl = Double.parseDouble((String)l);
						double dr = Double.parseDouble((String)r);
						result = (new Boolean(dl >= dr)).toString();
					}
				}
			}
		} catch (Exception e) {
			if (e.getMessage().startsWith("Runtime error in ")) {
				throw e;
			} else {
				String message = getErrorMessageHeader() + "problems in evaluating binary operator expression '" + this.value + "'."; 
				throw new Exception(message);
			}
		}
		return result;
	}
}




