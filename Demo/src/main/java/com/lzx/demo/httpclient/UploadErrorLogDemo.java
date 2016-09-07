package com.lzx.demo.httpclient;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class UploadErrorLogDemo {
	public static String invokeUrl="http://172.16.2.103:8080/BDMbsecsvr/invoke/invoke.do";
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		HttpClientUtil util=new HttpClientUtil();
		Map dataMap=new HashMap();
		dataMap.put("phone_factory", "1");
		dataMap.put("phone_model", "2");
		dataMap.put("phone_os", "3");
		dataMap.put("cpu_model", "4");
		dataMap.put("memory_size", "5");
		dataMap.put("app_version", "6");
		dataMap.put("imei", "7");
		Map params=new HashMap();
		params.put("cmd", "log_feedback");
		params.put("data", mapper.writeValueAsString(dataMap));
		params.put("files", new File("d:/test.txt"));
		System.out.println(util.doPostString(UploadErrorLogDemo.invokeUrl, params));
		
	}
}
