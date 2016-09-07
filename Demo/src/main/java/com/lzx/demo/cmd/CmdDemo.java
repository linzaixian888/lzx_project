package com.lzx.demo.cmd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdDemo {
	public static void main(String[] args) throws Exception {
//		execute();
		String flag="abc";
		System.out.println(Boolean.valueOf(flag));
	}
	public static void execute() throws Exception{
		Process p=Runtime.getRuntime().exec("cmd /c \"d:/upgrade.bat\"");
		InputStream is=p.getInputStream();
		String str;
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"gbk"));
		while((str=br.readLine())!=null){
			System.out.println(str);
		}
		is=p.getErrorStream();
		br = new BufferedReader(new InputStreamReader(is,"gbk"));
		while((str=br.readLine())!=null){
			System.out.println(str);
		}
	}
	public static void text() throws Exception{
		Process p=Runtime.getRuntime().exec("cmd /c dir");
		InputStream is=p.getInputStream();
		String str;
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"gbk"));
		while((str=br.readLine())!=null){
			System.out.println(str);
		}
		is=p.getErrorStream();
		br = new BufferedReader(new InputStreamReader(is,"gbk"));
		while((str=br.readLine())!=null){
			System.out.println(str);
		}
	}
}
