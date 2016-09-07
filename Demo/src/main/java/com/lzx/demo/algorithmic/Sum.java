package com.lzx.demo.algorithmic;

import java.util.Scanner;

/**
 * 求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。例如2+22+222+2222+22222(此时共有5个数相加)，几个数相加有键盘控制。
 * @author lzx
 *
 */
public class Sum {
	//数字为2 
	private static int shuzi=2;
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int size=input.nextInt();
		int sum=0;
		for(int i=1;i<=size;i++){
			sum+=getShuzi(shuzi, i);
		}
		System.out.println("和为:"+sum);
	}
	/**
	 * 获取某个数字在某个位数的大小,如2的3位时为222
	 * @param shuzi
	 * @param size
	 * @return
	 */
	public static int getShuzi(int shuzi,int size){
		if(size==1){
			return shuzi;
		}
		return getShuzi(shuzi, size-1)*10+shuzi;
	}
}

