package day0331;

public class Test1 {
	public static void main(String[] args) {
		
		int []a={1,2,3,4,5}; 
		int b[]={4,5,5};
		b=chang(a); 
		b[1]=7; 
		System.out.println(a[1]);
		} 
	
	public static int[] chang(int a[]){ 
		a[1]=6; 
		return a; 
		} 
}
