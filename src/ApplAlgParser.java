package appl_alg_interpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

public class ApplAlgParser {
	
	// predefined tokens for the applicative algorithm language
	static String NAME = "[a-zA-Z_][a-zA-Z_0-9]*";
	static String NONEMPTY_STRING = "'[^']+'";

	ProgramScanner psc;
	File file;

	public ApplAlgParser (File file) throws Exception {
		this.file = file;
		psc = new ProgramScanner(file);
	}
	
	public Program parse() {
		Program p = null;
		try {
			p = program();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		System.out.println("Parsing " + file + " done.");
		return p;
	}
	
	Program program() throws Exception {
		Hashtable<String,Function> functions = new Hashtable<String,Function>();
		String main = null;
		
		ArrayList<File> files = usages();
		
		while (true) {
			Function f = function();
			if (f == null) 
				break;
			if (functions.containsKey(f.name)) {
				String message = "Parsing error in " + file + ": duplicated function '" + f.name + "'."; 
				throw new Exception(message);
			}
			functions.put(f.name,f);
			if (main == null)
				main = f.name;
		}
		
		Program p = new Program(main, functions, files);
		p.parseFiles();		
		return p;
	}
	
	ArrayList<File> usages() throws Exception {
		ArrayList<File> result = new ArrayList<File>();
		while (true) {
			String keyword = psc.nextTokenOrNull("use");
			if (keyword != null) {
				String s = psc.nextToken(NONEMPTY_STRING);
				s = s.substring(1, s.length()-1);
				File f = new File(s);
				result.add(f);
			} else
				break;
			
		}			
		return result;
	}
	
	Function function() throws Exception {
		String name = psc.nextToken(NAME);
		if (name.equals("EOF"))
			return null;
		psc.nextToken("\\(");
		ArrayList<String> params = new ArrayList<String>();
		if (psc.nextTokenOrNull("\\)") == null) {  // parameter there
			params = formalParams();
			psc.nextToken("\\)");
		}
		psc.nextToken("=");
		Expression body = Expression.Create(psc, true);

		return new Function(name, params, body);
	}

	ArrayList<String> formalParams() throws Exception {
		ArrayList<String> result = new ArrayList<String>();
		String name = psc.nextToken(NAME);
		result.add(name);
		
		while (true) {
			if (psc.nextTokenOrNull(",") != null) {
				name = psc.nextToken(NAME);
				result.add(name);
			} else
				break;
		}
		return result;
	}
}