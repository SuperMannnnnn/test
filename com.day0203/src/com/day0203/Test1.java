package com.day0203;

import java.util.Scanner;

public class Test1 {
	/*public static void main(String[] args) {
		System.out.println("±ÚßË");
		@SuppressWarnings("resource")
		int n =new Scanner(System.in).nextInt();
		int r = f(n);
		System.out.println(r);
	}
	static int f(int n){
		int count =1;
		outer:
			for(int i = 3;i<=n;i++){
				for(int j = 2;j<(Math.sqrt(i)+1);j++){
					if(i%j==0){
						continue outer;
					}
					
				} count++;
			}
		
		 return count;
	}*/
	public static void main(String[] args){
		System.out.println("wwww");
		int n = new Scanner(System.in).nextInt();
		int r = f(n);
		System.out.println(r);
	}
	static int f(int n){
		int count = 1;
		outer:
			for(int i =3;i<=n;i++){
				for(int j=2;j<Math.sqrt(i)+1;j++){
					if(i%j==0){
						continue outer;
					}
				}count++;
			}return count;
	}
	
	
	
	
	
	
	
	
	
	
}
