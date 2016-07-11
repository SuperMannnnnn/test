package com.day01.change;

import java.util.Scanner;
import java.util.Scanner;

public class Dome2 {
	static int a=0;//商品个数
	static double m=0;//单价
	static double m1=0;//总价
	static String yes="Y";//是否继续
	static int i=0;
    public static void main(String[] args) {
    
       System.out.println("请扫描商品");
       for(;yes.equals("Y");i++){
    	//继续输入	
       	jz();
       }
     //结账
		 System.out.println("总价："+m1);
		 System.out.println("产品种类数："+i);
		 a=0;
		 m=0;
    }
    public static void jz(){
        System.out.print("商品个数");
        Scanner sca =new Scanner(System.in);
         a=sca.nextInt();
 		System.out.println("欢迎消费，请输入商品单价：");
 		Scanner sc =new Scanner(System.in);
 		 m= sc.nextInt();
 		 m1+=m*a;
 		 System.out.println("是否继续输入，是Y！否N！");
 		 Scanner scy =new Scanner(System.in);
 		 yes=scy.next();
    }
	}

