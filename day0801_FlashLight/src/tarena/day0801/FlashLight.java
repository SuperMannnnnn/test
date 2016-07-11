package tarena.day0801;

import android.graphics.Color;

/*
 * 封装
 * 
 * 将手电筒相关的属性数据、控制代码，
 * 封装成一个类组件
 */
public class FlashLight {
    int color = Color.WHITE;
    boolean on;//false
    
    public FlashLight() {
    }
    public FlashLight(int color) {
    	//局部变量与参数变量同名时，
    	//可以使用 this.xxx 格式
    	//来引用成员变量
    	this.color = color;
    }
    
    public void turnOn() {
    	on = true;
    }
    public void turnOff() {
    	on = false;
    }
}




