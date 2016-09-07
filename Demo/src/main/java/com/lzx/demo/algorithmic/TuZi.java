package com.lzx.demo.algorithmic;

import java.util.Scanner;

/**
 * 有一对兔子，从出生后第3个月起每个月都生一对兔子，
 * 小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，
 * 问每个月的兔子总数为多少？
 * 程序分析： 兔子的规律为数列1,1,2,3,5,8,13,21....
 * @author lzx
 *
 */
public class TuZi {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int month=input.nextInt();
		System.out.println(f(month));
	}
	/**
	 * 获取第几个月的免子总数
	 * @param month
	 * @return
	 */
	public static int f(int month){
		if(month==1||month==2){
			return 1;
		}
		return f(month-1)+f(month-2);
	}
}
