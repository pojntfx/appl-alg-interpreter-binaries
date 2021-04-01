package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public class ListAssignmentExpression extends Expression {

	Expression new_value;
	Expression indexed_expr;

	public ListAssignmentExpression(int line_number, String filename, Expression value, Expression indexed_expr) {
		super(line_number, filename);
		this.new_value = value;
		this.indexed_expr = indexed_expr;
	}

	public Object evaluate(Hashtable<String, Object> params) throws Exception {
		Object result = null;

		if (!(indexed_expr instanceof IndexedExpression)) {
			String message = getErrorMessageHeader() + "list assignment on non-indexed expression.";
			throw new Exception(message);
		}

		ArrayList<Expression> indexes = ((IndexedExpression) indexed_expr).indexes;
		Object obj = ((IndexedExpression) indexed_expr).root.evaluate(params);
		
		result = obj;		// remember the pointer to the root object as the result
		// Now start parsing through the indexes and insert the new value at the right position

		if (obj == null) {
			String message = getErrorMessageHeader() + "list assignment on null value.";
			throw new Exception(message);
		}
		if (!(obj instanceof ArrayList || (obj instanceof String && ((String) obj).matches("^'[^']*'$")))) {
			String message = getErrorMessageHeader() + "list assignment on non-list or non-string.";
			throw new Exception(message);
		}
		
		// Ok, finally we have a list or a string, let's cast them for brevity:
		ArrayList list = null;
		String str = null;
		if (obj instanceof ArrayList) 
			list = (ArrayList) obj;
		if (obj instanceof String)
			str = (String) obj;
		
		if (str != null) {		// direct assignment to string, we have only one index
			if (indexes.size() >= 2) {
				String message = getErrorMessageHeader() + "too many indexes in string assignment.";
				throw new Exception(message);
			}
			Object index_obj = indexes.get(0).evaluate(params);
			if (!(index_obj instanceof String) || !((String) index_obj).matches(NUMBER)) {
				String message = getErrorMessageHeader() + "index is not a number.";
				throw new Exception(message);
			}
			int index = (int) Math.floor(0.0000001 + Double.parseDouble((String) index_obj));
			Object newValue = new_value.evaluate(params);
			if (!(newValue instanceof String && ((String) newValue).matches("^'[^']'$"))) { // one single character
				String message = getErrorMessageHeader() + "trying to assign non-character " + newValue + " to a string.";
				throw new Exception(message);
			}
			String add_replace = "", s = "";
			if (((String) newValue).split("'").length > 0)
				add_replace = ((String) newValue).split("'")[1];
			if (str.split("'").length > 0)
			    s = str.split("'")[1];
			if (index == s.length()) { // add letter at the end of the string
				s = s + add_replace;
				str = "'" + s + "'";
			} else { // replace letter in string
				String s1 = s.substring(0, index);
				String s2 = s.substring(index + 1);
				str = "'" + s1 + add_replace + s2 + "'";
			}
			return str;
		}
		
		// the outermost object is a list
		ArrayList memory_list = null;
		int memory_index = 0;
		
		for (int i = 0; i < indexes.size(); i++) {
			Object index_obj = indexes.get(i).evaluate(params);
			if (!(index_obj instanceof String) || !((String) index_obj).matches(NUMBER)) {
				String message = getErrorMessageHeader() + "index is not a number.";
				throw new Exception(message);
			}
			int index = (int) Math.floor(0.0000001 + Double.parseDouble((String) index_obj));
			if (index < 0 || (list != null && index > list.size()) || (str != null && index > str.length() - 2)) {
				String message = getErrorMessageHeader() + "index " + index + " of list/string assignment out of bounds.";
				throw new Exception(message);
			}
			if (i < indexes.size() - 1) {
				// must be a list, pick the next inner list/string of it
				if (index == list.size()) { // add new list at index
					list.add(new ArrayList());
				}
				Object o = list.get(index);
				if (o instanceof ArrayList) 
					list = (ArrayList) o;
				else if (o instanceof String) {  // innermost object is a string, 
					                             // check whether it is an apl-string and there is exactly one more index
					if (!((String) o).matches("^'[^']*'$") || i != indexes.size() - 2) {
						String message = getErrorMessageHeader() + "too many indexes in list assignment.";
						throw new Exception(message);
					}
					memory_index = index;        // remember the container and location
					memory_list = list;
					str = (String) o;
					list = null;
				}
			} else if (i == indexes.size() - 1) { // no more inner list referenced: insert/replace value in list or string
				Object newValue = new_value.evaluate(params);
				if (newValue instanceof String) 
					newValue = formatNumber(((String) newValue));
				if (list != null) {              // innermost obj is a list
					if (index == list.size())
						list.add(index, newValue);
					else {
						list.remove(index);
						list.add(index, newValue);
					}
				}
				if (str != null) {               // innermost obj is a string
					if (!(newValue instanceof String && ((String) newValue).matches("^'[^']'$"))) {
						String message = getErrorMessageHeader() + "trying to assign non-character " + newValue + " to string.";
						throw new Exception(message);
					}
					String add_replace = "", s = "";
					if (((String) newValue).split("'").length > 0)
						add_replace = ((String) newValue).split("'")[1];
					if (str.split("'").length > 0)
					    s = str.split("'")[1];
					if (index == s.length()) { // add letter at the end of the string
						s = s + add_replace;
						str = "'" + s + "'";
					} else { // replace letter in string
						String s1 = s.substring(0, index);
						String s2 = s.substring(index + 1);
						str = "'" + s1 + add_replace + s2 + "'";
					}
					memory_list.remove(memory_index);
					memory_list.add(memory_index, str);
				}
			}
		}
		return result; // return the pointer to the original root object
	}
}
