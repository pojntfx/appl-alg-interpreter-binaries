package appl_alg_interpreter;

import java.io.File;
import java.util.Hashtable;

import appl_alg_interpreter.ApplAlgParser;

public class ApplAlgInterpreter {
	
	public static Hashtable<String,Function> functions;

	public static void main(String[] args) throws Exception {
		
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/GgT.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/MultRusse.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Hanoi.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/TextSearch3.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/IntegerToBinary.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Primes.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Sqrt.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/ChangeLetterCase.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/TextSearch.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/TextSearchBruteForce.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/BoyerMooreMatchTable.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Palindrome.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Quersumme.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/BinarySearch.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/QuickSort3.apl"));
		ApplAlgParser parser = new ApplAlgParser(new File(args[0]));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Fibonacci.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Fibonacci2.apl"));
		
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/KlausurWiSe1314.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Pali.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/KlausurWiSe1314.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Test.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Textsuche.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/FirstExamples.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/PrimesLessThan.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/PaliTest.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/TextSearchBF.apl"));
//		ApplAlgParser parser = new ApplAlgParser(new File("appl_algs/Summe.apl"));

		Program p = parser.parse();
		ApplAlgInterpreter.functions = p.functions;
		
		// These are the settings for debugging purposes
		Program.printFunctions = false;
		Program.countFunctions = true;
		Program.ignoreFunctions.add("main");
        
		// Program.ignoreFunctions.add("main");
		
		System.out.println("\nResult: " + execute(p) + "\n");
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
