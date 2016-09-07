package com.lzx.demo.httpclient;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneUploadDemo {
	public static String invokeUrl="http://172.16.2.40:8080/BDMbsecsvr/invoke/invoke.do";
	public static String folder="d:/test";
	public static String suffix=".zip";
	public static void main(String[] args) {
		ExecutorService pool=Executors.newFixedThreadPool(100);
		for(int i=1;i<=100;i++){
			PhoneUploadThread thread=new PhoneUploadThread("root"+i, "123456");
		    pool.execute(thread);
		}
	}
}
class PhoneUploadThread extends Thread{
	private Logger log=LoggerFactory.getLogger(PhoneUploadThread.class);
	private String userID;
	private String password;
	private HttpClientUtil util;
	public PhoneUploadThread(String userID, String password) {
		try {
			this.userID = userID;
			this.password = password;
			util=new HttpClientUtil();
			Map params=new HashMap();
			params.put("cmd", "user_login");
			params.put("data", "{\"userID\":\""+userID+"\",\"password\":\""+password+"\"}");
			util.doPostString(PhoneUploadDemo.invokeUrl, params);
			log.debug("{}登陆成功",userID);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
	}
	@Override
	public void run() {
			try {
				long before=System.currentTimeMillis();
				Map params=new HashMap();
				params.put("cmd", "upload_file");
				params.put("data", "{}");
				params.put("files", new File(PhoneUploadDemo.folder+"/"+userID+PhoneUploadDemo.suffix));
				log.debug("{}结果信息:{}",userID,util.doPostString(PhoneUploadDemo.invokeUrl, params));
				long diff=System.currentTimeMillis()-before;
				log.debug("{}上传耗时毫秒:({}),耗时秒({})",userID,diff,diff/1000.0);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.toString());
			}
	}
	
}
