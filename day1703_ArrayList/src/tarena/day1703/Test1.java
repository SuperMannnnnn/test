package tarena.day1703;

import java.util.ArrayList;
import java.util.Iterator;

public class Test1 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(999);
		list.add(222);
		list.add(666);
		list.add(888);
		list.add(333);
		list.add(333);
		list.add(null);
		System.out.println(list.size());
		System.out.println(list);
		list.add(4,444);
		System.out.println(list);
		System.out.println(list.get(2));
		System.out.println(list.contains(666));
		System.out.println(list.set(5, 555));
		System.out.println(list);
		System.out.println(list.remove(2));
		System.out.println(
		 list.remove(Integer.valueOf(333)));
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		//迭代器遍历，自己写
		
		for(Iterator<Integer> it=list.iterator(); 
			it.hasNext(); ) {
			Integer i = it.next();
			System.out.println(i);
		}
		
		Iterator<Integer> it = list.iterator();	
		while(it.hasNext()) {
			Integer i = it.next();
			System.out.println(i);
		}
		
		
		
	}
}
