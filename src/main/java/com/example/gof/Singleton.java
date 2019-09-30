package com.example.gof;

import java.util.Arrays;

/** 
* @date 2019-09-29 14:35:33
* @author LEI
* TODO
*/

public class Singleton {
	//1、多重判断
	private static Singleton instance1 = null;
	private Singleton() {}
	
	public static Singleton getInstance1() {
		if(instance1==null) {
			synchronized (Singleton.class) {
				if(instance1==null) {
					instance1 = new Singleton();
				}
			}
		}
		return instance1;
	}
	//2、贪吃型
	private static Singleton instance2 = new Singleton();
	public static Singleton getInstance2() {
		return instance2;
	}
	//3、懒惰型  如果不加synchronized在多线程中使用可能会创建多个实例
	private static Singleton instance3 = null;
	public static synchronized Singleton getInstance3() {
		if(instance3 == null) {
			instance3 = new Singleton();
		}
		return instance3;
	}
	//4、枚举型
	enum SingletonEnum{
		instance1,instance2
	}
	
	public static void main(String[] args) {
		System.out.println(Singleton.getInstance1()==Singleton.getInstance1());
		System.out.println(Singleton.getInstance2()==Singleton.getInstance2());
		System.out.println(Singleton.getInstance3()==Singleton.getInstance3());
		System.out.println(SingletonEnum.instance1==SingletonEnum.instance1);
		
	}
}
