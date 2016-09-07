package com.lzx.demo.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class NetworkDemo {

	public static void main(String[] args) {
		try {
			testNetwork();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testNetwork() throws Exception {
		System.out.println(isReachable(InetAddress.getLocalHost(), InetAddress.getByName("www.baidu.com"), 80, 100));
	}

	static boolean isReachable(InetAddress localInetAddr, InetAddress remoteInetAddr,
			int port, int timeout) {

		boolean isReachable = false;
		Socket socket = null;
		try {
			socket = new Socket();
			// 端口号设置为 0 表示在本地挑选一个可用端口进行连接
			SocketAddress localSocketAddr = new InetSocketAddress(
					localInetAddr, 0);
			socket.bind(localSocketAddr);
			InetSocketAddress endpointSocketAddr = new InetSocketAddress(
					remoteInetAddr, port);
			socket.connect(endpointSocketAddr, timeout);
			System.out.println("SUCCESS - connection established! Local: "
					+ localInetAddr.getHostAddress() + " remote: "
					+ remoteInetAddr.getHostAddress() + " port" + port);
			isReachable = true;
		} catch (IOException e) {
			System.out.println("FAILRE - CAN not connect! Local: "
					+ localInetAddr.getHostAddress() + " remote: "
					+ remoteInetAddr.getHostAddress() + " port" + port);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Error occurred while closing socket..");
				}
			}
		}
		return isReachable;
	}

}
