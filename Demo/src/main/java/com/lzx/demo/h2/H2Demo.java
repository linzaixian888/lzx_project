package com.lzx.demo.h2;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2Demo {
	private static String driver="org.h2.Driver";
	private static String url="jdbc:h2:tcp://localhost/~/test3";
	private static String user="sa";
	private static String password="";
	public static void main(String[] args) throws Exception {
		Class.forName(driver);
		 Connection conn = DriverManager.getConnection(url, user, password);
		 System.out.println(conn);
	}
}
