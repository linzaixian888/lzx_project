package com.lzx.demo.algorithmic;
/**
 * 九九乘法表
 * @author lzx
 *
 */
public class Multiplication {
	public static void main(String[] args) {
		//j*i
		for(int i=1;i<=9;i++){
			for(int j=1;j<=9;j++){
				if(i<j)break;
				System.out.print(j+"*"+i+"="+(i*j)+"\t");
			}
			System.out.println("");
		}
	}
}
