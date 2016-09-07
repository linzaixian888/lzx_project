package com.lzx.demo.ehcache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheDemo {
	public static void main(String[] args) {
		//默认是类编绎路径下的ehcache.xml，自定义路径可采用构造方式
				CacheManager manager = CacheManager.create();// 默认配置文件创建
				//获得所有的cache名称
				String[] names = manager.getCacheNames();
				
				for (int i = 0; i < names.length; i++) {
					Cache cache=manager.getCache(names[i]);
					for (int j = 0; j < 6; j++) {
						//往每个cache里都放6个元素
			            Element e = new Element("key" + j, "value" + j);
			            cache.put(e);
			        }
					List<String> list=cache.getKeys();
					for (String key : list) {
						//将元素都取出来
			            Element e=cache.get(key);
			            System.out.println(e.getObjectKey());
			            System.out.println(e.getObjectValue());
			        }
				}
				manager.shutdown();
	}
}
