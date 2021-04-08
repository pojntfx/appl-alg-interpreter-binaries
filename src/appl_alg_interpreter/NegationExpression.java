package appl_alg_interpreter;

import java.util.Hashtable;

public class NegationExpression extends Expression {
	
	Expression boolexpr;

	public NegationExpression(int line_number, String filename, Expression boolexpr) {
		super(line_number, filename);
		this.boolexpr = boolexpr;
	}

	public Object evaluate(Hashtable<String, Object> params) throws Exception {
		
		Object result = boolexpr.evaluate(params);
		
		if (!(result.equals("true") || result.equals("false"))) {
			String message = getErrorMessageHeader() + "negation of non-boolean expression is impossible.";
			throw new Exception(message);
		}	
		if (result.equals("true")) 
			result = "false";
		else 
			result = "true";
		
		return result;
	}
}
