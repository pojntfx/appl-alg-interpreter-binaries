package appl_alg_interpreter;

import java.io.File;
import java.util.Hashtable;

public class ApplAlgInterpreter {

	public static Hashtable<String, Function> functions;

	public static void main(String[] args) throws Exception {
		// Read config from env
		var debugParserFlag = System.getenv("IAPPL_DEBUG_PARSER");
		var printFunctionsFlag = System.getenv("IAPPL_PRINT_FUNCTIONS");
		var countFunctionsFlag = System.getenv("IAPPL_COUNT_FUNCTIONS");

		// Parse config
		var debugParser = false;
		if (debugParserFlag != null && debugParserFlag.equals("true")) {
			debugParser = true;
		}

		var printFunctions = false;
		if (printFunctionsFlag != null && printFunctionsFlag.equals("true")) {
			printFunctions = true;
		}

		var countFunctions = false;
		if (countFunctionsFlag != null && countFunctionsFlag.equals("true")) {
			countFunctions = true;
		}

		// Read the source file
		ApplAlgParser parser = new ApplAlgParser(new File(args[0]), debugParser);

		// Parse the source
		Program p = parser.parse();
		ApplAlgInterpreterUpstream.functions = p.functions;

		// These are the settings for debugging purposes
		Program.printFunctions = printFunctions;
		Program.countFunctions = countFunctions;
		Program.ignoreFunctions.add("main");

		System.out.println(execute(p));
	}

	private static Object execute(Program p) {
		Object result = null;

		try {
			result = p.execute(new Hashtable<String, Object>());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
