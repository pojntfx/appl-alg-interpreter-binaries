package appl_alg_interpreter;

import java.io.File;
import java.util.Scanner;

public class ProgramScanner {
	
	Scanner scFile;		// scanner for the lines
	Scanner scLine;		// scanner within a line
	String actLine;		// actual line to be examined
	
	int lineNumber = 1;
	String filename;
	
	protected String anything = ".+"; // to test emptiness of lines 
	
	public ProgramScanner(File f) throws Exception {
		this.filename = f.getPath();
		try {
			scFile = new Scanner(f);
			actLine = scFile.findInLine(anything); // the complete first line
			if (actLine != null) {
				if (actLine.matches("^#+$")) {
					actLine = "";
				} else {
					actLine = actLine.split("#")[0];  // remove comments
				}
			}
			if (actLine == null || actLine.matches("^\\s*$")) { // first line was empty 
				actLine = nextLine();
			}
			if (actLine == null || actLine.matches("^\\s*$")) { // no nonempty line -> empty file
				System.out.println("Parsing error: File " + f + " is empty.");
				System.exit(-1);
			}
			scLine = new Scanner(actLine);
		} catch (Exception e) {
			String message = "Parsing error: file " + f + " not found."; 
			throw new Exception(message);
		}
	}
	
	public String nextToken(String regex) throws Exception {
		String match = null;

		if (actLine == null || actLine.matches("^\\s*$")) {
			actLine = nextLine();			
		}
		if (actLine == null || actLine.matches("^\\s*$")) {
			if (regex.equals(ApplAlgParser.NAME))
				return "EOF";
			else {
				String message = "Scanning/Parsing error in " + filename + " (line " + lineNumber + "): unexpected end of file."; 
				throw new Exception(message);
			}
		}

		scLine = new Scanner(actLine);
		scLine.useDelimiter("NONE");
		match = scLine.findInLine("^\\s*(" + regex + ")");
		if (match != null) {
			match = match.trim();
			actLine = scLine.findInLine(anything); // Rest der Zeile
		}
		if (match == null) {
			String message = "Scanning/Parsing error in " + filename + " (line " + lineNumber + ") near " + actLine.trim(); 
			throw new Exception(message);
		}
		return match;
	}
	
	public String nextTokenOrNull(String regex) {
		String match = null;

		if (actLine == null || actLine.matches("^\\s*$")) {
			actLine = nextLine();			
		}
		if (actLine == null || actLine.matches("^\\s*$"))
			return null;

		scLine = new Scanner(actLine);
		scLine.useDelimiter("NONE");
		match = scLine.findInLine("^\\s*(" + regex + ")");
		if (match != null) {
			match = match.trim();
			actLine = scLine.findInLine(anything); // Rest der Zeile
		}
		return match;
	}
	
	protected String nextLine() {
		String newLine = null;
		while (scFile.hasNextLine()) {
			lineNumber++;
			scFile.nextLine();
			newLine = scFile.findInLine(anything);
			if (newLine != null) {
				if (newLine.matches("^#+$")) {
					newLine = "";
				} else {
					newLine = newLine.split("#")[0];  // remove comments
				}
			}
			if (newLine != null && !newLine.matches("^\\s*$")) {
				break;
			}
		}
		return newLine;
	}
}
