package com.lzx.demo.springehcache;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lzx.demo.springehcache.service.MyService;

public class SpringEhCacheDemo {
	public static void main(String[] args) {
		ApplicationContext act=new ClassPathXmlApplicationContext("spring.xml");
		MyService service=act.getBean(MyService.class);
		System.out.println(service.get());
		System.out.println(service.get());
		service.clean();
		System.out.println(service.get());
		((EhCacheCacheManager)act.getBean("cacheManager")).getCacheManager().shutdown();
	}
}
