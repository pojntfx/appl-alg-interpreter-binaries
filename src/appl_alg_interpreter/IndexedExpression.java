package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public class IndexedExpression extends Expression {
	
	Expression root;
	ArrayList<Expression> indexes;
	
	public IndexedExpression(int line_number, String filename, Expression root, ArrayList<Expression> indexes) {
		super(line_number, filename);
		this.root = root;
		this.indexes = indexes;
	}

	public Object evaluate(Hashtable<String, Object> params) throws Exception {
		Object result = null;
		Object obj = root.evaluate(params);  // the root of index navigation
		
		if (obj == null) {
			String message = getErrorMessageHeader() + "indexed expression on null value.";
			throw new Exception(message);
		}
		if (!(obj instanceof ArrayList || (obj instanceof String && ((String)obj).matches("^'[^']*'$")))) {
			String message = getErrorMessageHeader() + "indexed expression on non-list or non-string.";
			throw new Exception(message);
		}
		for (int i = 0; i < indexes.size(); i++) {
			Object index_obj = indexes.get(i).evaluate(params);
			if (!(index_obj instanceof String) || !((String)index_obj).matches(NUMBER)) {
				String message = getErrorMessageHeader() + "index is not a number."; 
				throw new Exception(message);
			}
			int index = (int) Math.floor(0.0000001 + Double.parseDouble((String)index_obj));
			if (i < indexes.size() - 1) {                               // obj has to be a list
				if (!(obj instanceof ArrayList)) {
					String message = getErrorMessageHeader() + "too many indexes for indexed expression.";
					throw new Exception(message);
				}
				if (index < 0 || index >= ((ArrayList) obj).size()) {
					String message = getErrorMessageHeader() + "index " + index + " out of bounds.";
					throw new Exception(message);
				}
				obj = ((ArrayList) obj).get(index);
			} else if (i == indexes.size() - 1) {                                  // obj may be a list or a string
				if (!(obj instanceof ArrayList || (obj instanceof String && ((String)obj).matches("^'[^']*'$")))) {
					String message = getErrorMessageHeader() + "too many indexes for indexed expression.";
					throw new Exception(message);
				}
				if (obj instanceof ArrayList) {
					if (index < 0 || index >= ((ArrayList) obj).size()) {
						String message = getErrorMessageHeader() + "index " + index + " out of bounds.";
						throw new Exception(message);
					}
					obj = ((ArrayList) obj).get(index);
				} else {
					String s = "";
					if (((String)obj).split("'").length > 0)
					    s = ((String)obj).split("'")[1];
					if (index >= s.length() || index < 0) {
						String message = getErrorMessageHeader() + "index " + index + " out of bounds."; 
						throw new Exception(message);
					}
					obj = "'" + (new Character(s.charAt(index))).toString() + "'";
				}
			}
			result = obj;
		}
		if (result instanceof String) 
			result = formatNumber(((String) result));
		return result;
	}
}
