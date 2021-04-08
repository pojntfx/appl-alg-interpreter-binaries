package appl_alg_interpreter;

import java.io.File;

public class ProgramScannerTest {

	static ProgramScanner psc;

	// predefined tokens for the applicative algorithm language
	static String TYPE = "(int|float|double|string|bool)(\\[\\])*";
	static String NAME = "[a-zA-Z_][a-zA-Z_0-9]*";
	static String NUMBER = "(^|\\s+)((\\-?[0-9]+(\\.[0-9]*)?)|(-?\\.[0-9]+))($|\\s+)";
	static String STRING = "'[^']*'";
	//static String OPERATOR = ">=|<=|[\\+\\-\\*/%=<>]|and|or";

	//static String NUMBER = "((-?[0-9]+(\\.[0-9]*)?)|(-?\\.[0-9]+))(E(\\-)?[0-9]+)?";
	static String OPERATOR = "[\\+\\-\\*/]";

	
	public static void main(String[] args) {

		try {
			psc = new ProgramScanner(new File("appl_algs/Test.apl"));

			System.out.println(psc.nextToken("\\("));
			System.out.println(psc.nextToken(NUMBER));
			System.out.println(psc.nextToken(OPERATOR));
			System.out.println(psc.nextToken(NUMBER));
			System.out.println(psc.nextToken("\\)"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
