package tarena.day1701;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("求第几个丑数:");
		int n = 
		 new Scanner(System.in).nextInt();
		
		String r = f(n);
		System.out.println(r);
	}

	private static String f(int n) {
		LinkedList<BigInteger> list3 = new LinkedList<>();
		LinkedList<BigInteger> list5 = new LinkedList<>();
		LinkedList<BigInteger> list7 = new LinkedList<>();
		
		list3.add(BigInteger.valueOf(3L));
		list5.add(BigInteger.valueOf(5L));
		list7.add(BigInteger.valueOf(7L));
		
		BigInteger r = BigInteger.valueOf(0);
		for(int i=1; i<=n; i++) {
			//取三个头部值
			BigInteger a = list3.getFirst();
			BigInteger b = list5.getFirst();
			BigInteger c = list7.getFirst();
			//得到最小值
			r = a.compareTo(b)<0?a:b;
			r = r.compareTo(c)<0?r:c;
			//最小值是哪个集合的，就从哪个集合移除
			if(r.equals(a))list3.removeFirst();
			if(r.equals(b))list5.removeFirst();
			if(r.equals(c))list7.removeFirst();
			
			list3.add(r.multiply(BigInteger.valueOf(3)));
			list5.add(r.multiply(BigInteger.valueOf(5)));
			list7.add(r.multiply(BigInteger.valueOf(7)));
		}
		
		return r.toString();
	}
}










