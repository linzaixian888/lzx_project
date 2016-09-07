package com.lzx.demo.algorithmic;

import java.util.Scanner;

/**
 * 一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高？
 * @author lzx
 *
 */
public class Qiu {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int index=input.nextInt();
		System.out.println(getHeight(index));
	}
	/**
	 * 获取第几次反弹的高度
	 * @param index
	 * @return
	 */
	public static float getHeight(int index){
		if(index==1){
			return 100/2;
		}
		return getHeight(index-1)/2;
	}
}
