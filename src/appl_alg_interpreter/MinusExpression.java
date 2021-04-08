package appl_alg_interpreter;

import java.util.Hashtable;

public class MinusExpression extends Expression {
	
	Expression nbexpr;

	public MinusExpression(int line_number, String filename, Expression nbexpr) {
		super(line_number, filename);
		this.nbexpr = nbexpr;
	}

	public Object evaluate(Hashtable<String, Object> params) throws Exception {
		
		Object result = nbexpr.evaluate(params);
		
		if (!(result instanceof String && ((String)result).matches(NUMBER))) {
			String message = getErrorMessageHeader() + "negative value of non-number expression is impossible.";
			throw new Exception(message);
		}	
		double d = Double.parseDouble((String) result);
		d = -d;
		result = (new Double(d)).toString();
		
		return result;
	}
}
