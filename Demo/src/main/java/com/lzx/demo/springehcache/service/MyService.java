package com.lzx.demo.springehcache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
/**
 * 缓存的key值可采用SpEL表达式
 * @author lzx
 *
 */
public class MyService {
	@Cacheable(value={"mydata"})
	public int get(){
		System.out.println("调用了");
		return 1;
	}
	@CacheEvict(value={"mydata"})
	public void clean(){
		System.out.println("清空了");
	}
}
