package com.bluedon.bsmon.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
/**
 * 一个读取全局配置的类
 * @author lzx
 *
 */
public class ConfUtil {
	private static Properties p=new Properties();
	static{
		try {
			InputStream is=ConfUtil.class.getResourceAsStream("/conf.properties");
			p.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static String getConfig(String key){
		return p.getProperty(key);
	}
	public static void main(String[] args) {
		Set<Entry<Object, Object>> set=p.entrySet();
		for(Entry e:set){
		}
		
	}
	
}
