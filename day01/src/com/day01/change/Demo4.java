package com.day01.change;

public class Demo4 {
	public static void main(String [] args){
		int a =3;
		int b = 4;
		int c = (a++)+(b++)-(++a)-(--b)+(--a)+(b++)+(--b)-(a--)-(++b);
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c="+c);
	}

}
