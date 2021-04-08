package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public class CallExpression extends Expression {
	
	String name;
	ArrayList<Expression> actual_params;
	
	public CallExpression(int line_number, String filename, String name, ArrayList<Expression> actual_params) {
		super(line_number, filename);
		this.name = name;
		this.actual_params = actual_params;
	}
	
	public static Expression Create(ProgramScanner psc, String name) throws Exception {
		ArrayList<Expression> actual_params = new ArrayList<Expression>();
		if (psc.nextTokenOrNull("\\)") == null) {
			actual_params = actualParams(psc);
			psc.nextToken("\\)");
		} 
		return new CallExpression(psc.lineNumber, psc.filename, name, actual_params);
	}

	public Object evaluate(Hashtable<String,Object> params) throws Exception {
		Object result = null;
		Function f = ApplAlgInterpreterUpstream.functions.get(name);
		
		if (f == null) {
			String message = getErrorMessageHeader() + "no definition found for function '" + name + "'.";
			throw new Exception(message);
		}
		
		ArrayList<String> formal_params = f.formal_params;
		
		if (formal_params.size() != actual_params.size()) {
			String message = getErrorMessageHeader() + "wrong number of parameters in call to function '" + name + "'.";
			throw new Exception(message);
		}
		
		Hashtable<String, Object> ht = new Hashtable<String, Object>(); 
		for (int i=0; i<formal_params.size(); i++) {
			ht.put(formal_params.get(i), actual_params.get(i).evaluate(params));
		}
		result = f.execute(ht);
		if (result instanceof String) 
			result = formatNumber(((String) result));
		return result;
	}

	private static ArrayList<Expression> actualParams(ProgramScanner psc) throws Exception {
		ArrayList<Expression> result = new ArrayList<Expression>();
		Expression expr = Expression.Create(psc, true);
		result.add(expr);
		while (true) {
			if (psc.nextTokenOrNull(",") != null) {
				expr = Expression.Create(psc, true);
				result.add(expr);
			} else
				break;
		}
		return result;
	}
}
