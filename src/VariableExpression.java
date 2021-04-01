package appl_alg_interpreter;

import java.util.Hashtable;

public class VariableExpression extends Expression {

	String name;

	public VariableExpression(int line_number, String filename, String name) {
		super(line_number, filename);
		this.name = name;
	}

	public static Expression Create(ProgramScanner psc, String name) throws Exception {
		return new VariableExpression(psc.lineNumber, psc.filename, name);
	}

	public Object evaluate(Hashtable<String, Object> params) throws Exception {
		Object result = null;

		result = params.get(name);
		if (result == null) {
			String message = getErrorMessageHeader() + "missing parameter '" + name + "'.";
			throw new Exception(message);
		}
		if (result instanceof String) 
			result = formatNumber(((String) result));
		return result;
	}
}
