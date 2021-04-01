package appl_alg_interpreter;

import java.util.ArrayList;

public class GenerateData {
	public static void main(String[] args) {
		
		ArrayList a = new ArrayList();
		
		for (int i = 0; i < 100; i++) {
			int v = (int)(Math.random()*1000);
			String value = (new Integer(v)).toString(); 
			if (!a.contains(value)) {
				a.add(value);
			}
		}
		System.out.println(a);
	}
}
