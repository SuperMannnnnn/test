package tarena.day1701;

import java.util.Iterator;
import java.util.LinkedList;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * <> ����
		 * ָ�������д�ŵ���������;
		 * ���Ͳ�֧�ֻ�������,����ʹ�ð�װ��
		 */
		LinkedList<String> list = 
		 new LinkedList<>();
		list.add("aaa");
		list.add("zzz");
		list.add("ggg");
		list.add("qqq");
		list.add("iii");
		list.add("hhh");
		list.add("hhh");
		list.add(null);
		System.out.println(list.size());
		System.out.println(list);
		list.add(0, "***");
		list.addFirst(">>>");
		list.addLast("<<<");
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		System.out.println(list.removeFirst());
		System.out.println(list.removeLast());
		System.out.println(list);
		System.out.println(list.get(3));
		System.out.println(list.contains("hhh"));
		System.out.println(list.set(5, "555"));
		System.out.println(list);
		System.out.println(list.remove(3));
		System.out.println(list);
		System.out.println(list.remove("hhh"));
		System.out.println(list);
		
		//˫�������±������Ч�ʵ�
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		//˫������������������Ч�ʸ�
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}
		
		
		
	}
}




