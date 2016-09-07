package com.lzx.util.cache;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractMemoryCache<T> {
	private Map<String, T> cache;
	//当前容量
	private long size=0;
	//最大内存容量为1Mb,相当于1*1024*1024
	private long maxSize=1<<10<<10;
	public AbstractMemoryCache(){
		initCache(false);
	}
	public AbstractMemoryCache(long maxSize,boolean accessOrder){
		this.maxSize=maxSize;
		initCache(accessOrder);
	}
	/**
	 * 初始化缓存容器
	 */
	private void initCache(boolean accessOrder){
		cache=Collections.synchronizedMap(new LinkedHashMap<String, T>(16,0.75f,accessOrder));
	}
	/**
	 * 取出缓存
	 * @param id
	 * @return
	 */
	public T get(String id){
		try {
			if(cache.containsKey(id)){
				return cache.get(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 放进缓存池
	 * @param id
	 * @param t
	 */
	public synchronized void  put(String id,T t){
		if(cache.containsKey(id)){
			size-=getSizeByByte(cache.get(id));
		}
		cache.put(id, t);
		size+=getSizeByByte(t);
		checkSize();
	}
	/**
	 * 检查缓存池
	 */
	private synchronized void checkSize(){
		if(size>maxSize){
			Iterator<Entry<String, T>> it=cache.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, T> entry=it.next();
				size-=getSizeByByte(entry.getValue());
				it.remove();
				if(size<maxSize){
					break;
				}
			}
			
		}
		print("当前容量为：---"+size);
	}
	/**
	 * 清空缓存池
	 */
	public void clean(){
		cache.clear();
	}
	private void print(String message){
		System.out.println(message);
	}
	public abstract long getSizeByByte(T t);
}
