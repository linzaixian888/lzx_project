package com.lzx.util.Math;

public class Random {
	/**
	 * 返回一个包含0不包含1的随机double型数字
	 * @return
	 */
	public double getRandom(){
		return Math.random();
	}
	/**
	 * 产生一个0到max的随机整型数字（包含0，不包含max）
	 * @param max
	 * @return
	 */
	public int getRandom(int max){
		return (int)(Math.random()*max);
	}
	/**
	 * 产生一个min到max的随机整型数字(包含mix,不包含max)
	 * @param min
	 * @param max
	 * @return
	 */
	public int getRandom(int min,int max){
		return getRandom(max-min)+min;
	}
	/**
	 * 产生一个随机小写字母
	 * @return
	 */
	public char getRandomLow(){
		//a是97,z是122
		return (char)getRandom(97, 123);
	}
	/**
	 * 产生一个随机大写字母
	 * @return
	 */
	public char getRandomUp(){
		//A是65,Z是90
		return (char)getRandom(65, 91);
	}
	/**
	 * 随机产生一个字母(不包含数字)
	 * @return
	 */
	public char getRandomLetter(){
		int num=getRandom(65, 123);
		if(num<91||num>=97){
			return (char)num;
		}else{
			return getRandomLetter();
		}
	}
	/**
	 * 随机产生一位长度的字符串
	 * @return
	 */
	public String getRandomLettetAndNumber(){
		int num=getRandom(0, 123);
		if(num<10){
			return num+"";
		}else if((num>=65&&num<91)||num>=97){
			return (char)num+"";
		}else{
			return getRandomLettetAndNumber();
		}
	}
	
	/**
	 * 产生一个自定义长度的字符串
	 * @param length
	 * @return
	 */
	public String getRandomString(int length){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<length;i++){
			sb.append(getRandomLettetAndNumber());
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Random r=new Random();
		System.out.println(r.getRandomString(100));
		
	}
	
	
}
