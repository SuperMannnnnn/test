package com.day01.change;

import java.util.Scanner;
import java.util.Scanner;

public class Dome2 {
	static int a=0;//��Ʒ����
	static double m=0;//����
	static double m1=0;//�ܼ�
	static String yes="Y";//�Ƿ����
	static int i=0;
    public static void main(String[] args) {
    
       System.out.println("��ɨ����Ʒ");
       for(;yes.equals("Y");i++){
    	//��������	
       	jz();
       }
     //����
		 System.out.println("�ܼۣ�"+m1);
		 System.out.println("��Ʒ��������"+i);
		 a=0;
		 m=0;
    }
    public static void jz(){
        System.out.print("��Ʒ����");
        Scanner sca =new Scanner(System.in);
         a=sca.nextInt();
 		System.out.println("��ӭ���ѣ���������Ʒ���ۣ�");
 		Scanner sc =new Scanner(System.in);
 		 m= sc.nextInt();
 		 m1+=m*a;
 		 System.out.println("�Ƿ�������룬��Y����N��");
 		 Scanner scy =new Scanner(System.in);
 		 yes=scy.next();
    }
	}

