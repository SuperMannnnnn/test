package tarena.day0801;

import android.graphics.Color;

/*
 * ��װ
 * 
 * ���ֵ�Ͳ��ص��������ݡ����ƴ��룬
 * ��װ��һ�������
 */
public class FlashLight {
    int color = Color.WHITE;
    boolean on;//false
    
    public FlashLight() {
    }
    public FlashLight(int color) {
    	//�ֲ��������������ͬ��ʱ��
    	//����ʹ�� this.xxx ��ʽ
    	//�����ó�Ա����
    	this.color = color;
    }
    
    public void turnOn() {
    	on = true;
    }
    public void turnOff() {
    	on = false;
    }
}




