package day319;

import java.util.Arrays;
import java.util.Random;

public class Test3 {
	public static void main(String[] args) {
		// 1 ��ȡһ���漴����
		// ����һ�������±귶Χ
		int a = 5 + new Random().nextInt(5);
		// ���������ȡֵ��Χ
		int b[] = new int[a];
		// ��ȡ���������ֵ
		for (int i = 0; i < a; i++) {
			b[i] = new Random().nextInt(100);
		}
		System.out.println(Arrays.toString(b));
		// ð������
		// ����i�±���ʼֵΪ0������Ϊa.length ����
		for(int i=0;i<b.length;i++){
			// ������һ���±�j =i+1,����Ϊa.length,����
			for(int j=i+1;j<b.length;j++){
				if(b[i]>b[j]){
					int c=b[i];
					b[i]=b[j];
					b[j]=c;
				}
			}
		}
		System.out.println(Arrays.toString(b));
		// �ж�ǰ���Ƿ�Ⱥ���Ĵ�
		// �������滻

	}
}
