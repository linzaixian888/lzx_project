package com.lzx.demo.rsa;

public class RSADemo {
	public static void main(String[] args) throws Exception {
		RSAUtil rsaUtil=new RSAUtil();
		System.out.println(rsaUtil.getPrivateKey());
		System.out.println(rsaUtil.getPublicKey());
		System.out.println(rsaUtil.decrypt(rsaUtil.encrypt("中国人abc")));
	}
}
