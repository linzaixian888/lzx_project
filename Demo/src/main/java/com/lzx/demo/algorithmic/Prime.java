package com.lzx.demo.algorithmic;

import java.util.Scanner;

/**
 * 判断是否素数
 * @author lzx
 *程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
 *	 		则表明此数不是素数，反之是素数。
 */
public class Prime {
	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		int i=input.nextInt();
		if(isPrime(i)){
			System.out.println("是素数");
		}else{
			System.out.println("不是素数");
		}
	}
	/**
	 * 判断某个数是否素数
	 * @param i
	 * @return
	 */
	public static boolean isPrime(int i){
		if(i==1)return false;
		for(int j=2;j<=Math.sqrt(i);j++){
			if(i%j==0){
				return false;
			}
		}
		return true;
	}
}
