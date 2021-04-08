package appl_alg_interpreter;

import java.util.ArrayList;
import java.util.Hashtable;

public class Function {
	final String name;
	final ArrayList<String> formal_params;
	private final Expression body;
	
	public Function(String name, ArrayList<String> formal_params, Expression body) {
		this.name = name;
		this.formal_params = formal_params;
		this.body = body;
	}

	public Object execute(Hashtable<String,Object> params) throws Exception {
		
		if (Program.printFunctions && !Program.ignoreFunctions.contains(name)) {
			String printText = "\t" + name + "(";
			boolean first = true;
			for (String key : formal_params) {
				if (first) {
					printText += key + ": " + params.get(key);
					first = false;
				} else {
					printText += ", " + key + ": " + params.get(key);
				}
			}
			printText += ")";
			
			System.out.println(printText);
		}
		
		if (Program.countFunctions) {
			Integer count = Program.countedFunctions.get(name);
			if (count == null) {
				count = 0;
			}
			++count;
			Program.countedFunctions.put(name, count);
		}
		
		return body.evaluate(params);
	}
} 
