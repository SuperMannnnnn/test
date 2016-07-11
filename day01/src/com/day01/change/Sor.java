package com.day01.change;

public class Sor {
	public static void main(String[] args){
		int number[] ={80,65,76,99,83,54,92,87,74,62};
		for(int i = 0;i<number.length;i++){
			for(int j = i+1;j<number.length;j++){
				if(number[i]<number[j]){
					int temp = number[i];
					number[i]=number[j];
					number[j]=temp;
				}
			}
		}
		int i = 0;
		System.out.println(number[i]);
		}
	}


