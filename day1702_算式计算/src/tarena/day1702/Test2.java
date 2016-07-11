package tarena.day1702;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("À„ Ω£∫");
		String s = 
		 new Scanner(System.in).nextLine();
		
		Formula f = new Formula(s);
		double r = f.eval();
		System.out.println(r);
	}
}
