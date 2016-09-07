package com.lzx.demo.json;

import java.util.Date;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class JacksonDemo {
	public static void main(String[] args) throws Exception {
		//从文件读取
		ObjectMapper mapper=new ObjectMapper();
		Map map=mapper.readValue(ClassLoader.getSystemResourceAsStream("com/lzx/demo/json/json"), Map.class);
		System.out.println(map);
		//读字符串读取
		String s = "{ \"name\" : \"萧远山\", \"sex\" : \"男\", \"age\" : 23,\"address\" : \"河南郑州\"}";
		map=mapper.readValue(s, Map.class);
		System.out.println(map);
		map.put("date", new Date());
		//对象转json数据
		System.out.println(mapper.writeValueAsString(map));
		
	}
}
