package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public class ListExpression extends Expression {
	
	ArrayList<Expression> elements;

	public ListExpression(int line_number, String filename, ArrayList<Expression> elements) {
		super(line_number, filename);
		this.elements = elements;
	}

	public static Expression Create(ProgramScanner psc) throws Exception {
		if (psc.nextTokenOrNull("\\]") != null) {  // empty list []
			return new ListExpression(psc.lineNumber, psc.filename, new ArrayList<Expression>());
		} else {   // initialized list e.g. [1,2,3]
			ArrayList<Expression> elements = new ArrayList<Expression>();
			Expression expr = Expression.Create(psc, true);
			elements.add(expr);
			while (true) {
				if (psc.nextTokenOrNull("\\]") != null) 
					break;
				else {
					psc.nextToken(",");
					expr = Expression.Create(psc, true);
					elements.add(expr);
				}
			}
			return new ListExpression(psc.lineNumber, psc.filename, elements);
		}
	}

	public Object evaluate(Hashtable<String, Object> params) throws Exception {
		ArrayList result = new ArrayList();
		for (int i = 0; i < elements.size(); i++) {
			result.add(i, elements.get(i).evaluate(params));
		}
		return result;
	}
}
