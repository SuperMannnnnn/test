package tarena.day1702;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
	private String f;

	public Formula(String f) {
		super();
		this.f = f.trim();
	}

	public class DieDaiQi {
		int from;
		Matcher m = 
		 Pattern.compile("\\d+(\\.\\d+)?|[+\\-*/]")
		        .matcher(f);
		
		public boolean hasNext() {
			return from < f.length();
		}
		
		public String next() {
			m.find();
			String s = m.group();
			from = m.end();
			return s;
		}
	}
	
	
	/*
	 * ËãÊ½ÇóÖµ 
	 */
	public double eval() {
		LinkedList<Double> list1 = new LinkedList<>();
		LinkedList<String> list2 = new LinkedList<>();
		
		DieDaiQi d = new DieDaiQi();
		while(d.hasNext()) {
			String s = d.next();
			if(s.matches("\\d+(\\.\\d+)?")) {
				list1.add(Double.parseDouble(s));
			} else if(s.matches("[+\\-]")){
				list2.add(s);
			} else {
				double a = list1.removeLast();
				double b = Double.parseDouble(d.next());
				double c = jiSuan(a, s, b);
				list1.add(c);
			}
		}
		
		///
		
		while(list2.size() != 0) {
			double a = list1.removeFirst();
			double b = list1.removeFirst();
			String s = list2.removeFirst();
			double c = jiSuan(a, s, b);
			list1.addFirst(c);
		}
		
		return list1.get(0);
	}


	private double jiSuan(
			double a, 
			String s,
			double b) {
		
		BigDecimal c = BigDecimal.valueOf(0);
		BigDecimal bd1 = BigDecimal.valueOf(a);
		BigDecimal bd2 = BigDecimal.valueOf(b);
		
		// "+" --> '+'
		switch(s.charAt(0)) {
		case '+': c=bd1.add(bd2); break;
		case '-': c=bd1.subtract(bd2); break;
		case '*': c=bd1.multiply(bd2); break;
		case '/': c=bd1.divide(
		   bd2,10,BigDecimal.ROUND_HALF_UP); break;
		}
		return c.doubleValue();
		
	}
	
	
}






