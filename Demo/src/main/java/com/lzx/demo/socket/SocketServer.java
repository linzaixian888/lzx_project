package com.lzx.demo.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) {
		ServerSocket ss=null;
		Socket s=null;
		try {
			ss=new ServerSocket(9999);
			s=ss.accept();
			InputStream is=s.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
