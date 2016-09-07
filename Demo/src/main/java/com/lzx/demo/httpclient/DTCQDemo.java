package com.lzx.demo.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 刀塔传奇的资料下载demo
 * @author Administrator
 *
 */
public class DTCQDemo {
	private static String url="jdbc:mysql://localhost:3306/dtcq?rewriteBatchedStatements=true&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	private static String driver="com.mysql.jdbc.Driver";
	private static String username="root";
	private static String password="123456";
	public static void main(String[] args) throws Exception{
		int i=1;
		HttpClientUtil util=new HttpClientUtil();
		System.out.println(util.doGetString("http://files.qidian.com/Author6/2952453/47892433.txt",null,"gbk"));
	}
	/**
	 * 流的复制，并关掉所有输入输出流
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	private static void copy(InputStream is,OutputStream os) throws IOException{
		try {
			int len=0;
			byte[] buff=new byte[1024];
			while((len=is.read(buff))!=-1){
				os.write(buff, 0, len);
			}
		} catch (IOException e) {
			throw e;
		}finally{
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
		}
	}
}
