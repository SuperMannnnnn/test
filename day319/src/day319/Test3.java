package day319;

import java.util.Arrays;
import java.util.Random;

public class Test3 {
	public static void main(String[] args) {
		// 1 获取一个随即数组
		// 定义一个数组下标范围
		int a = 5 + new Random().nextInt(5);
		// 定义数组的取值范围
		int b[] = new int[a];
		// 获取数组的所有值
		for (int i = 0; i < a; i++) {
			b[i] = new Random().nextInt(100);
		}
		System.out.println(Arrays.toString(b));
		// 冒泡排序
		// 定义i下标起始值为0，长度为a.length 遍历
		for(int i=0;i<b.length;i++){
			// 定义另一个下标j =i+1,长度为a.length,遍历
			for(int j=i+1;j<b.length;j++){
				if(b[i]>b[j]){
					int c=b[i];
					b[i]=b[j];
					b[j]=c;
				}
			}
		}
		System.out.println(Arrays.toString(b));
		// 判断前面是否比后面的大
		// 如果大就替换

	}
}
