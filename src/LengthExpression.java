package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public class LengthExpression extends Expression {
	
	Expression expr;

	public LengthExpression(int line_number, String filename, Expression expr) {
		super(line_number, filename);
		this.expr = expr;
	}

	public static Expression Create(ProgramScanner psc) throws Exception {
		psc.nextToken("\\(");
		Expression expr = Expression.Create(psc, true);
		psc.nextToken("\\)");
		return new LengthExpression(psc.lineNumber, psc.filename, expr);
	}

	public Object evaluate(Hashtable<String, Object> params) throws Exception {
		
		Object e = expr.evaluate(params);
		if (!(e instanceof ArrayList || (e instanceof String && ((String)e).matches("^'[^']*'$")))) {
			String message = getErrorMessageHeader() + "trying to evaluate length of inappropriate type."; 
			throw new Exception(message);
		}
		if (e instanceof ArrayList) {
			return (new Integer(((ArrayList)e).size())).toString();
		}
		else {
			return (new Integer(((String)e).length() - 2)).toString();
		}
	}
}
