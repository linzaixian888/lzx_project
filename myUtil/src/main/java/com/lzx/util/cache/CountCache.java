package com.lzx.util.cache;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public  class CountCache<T> {
	private Map<String, T> cache;
	//最大容量
	private int maxCount=10;
	public CountCache(){
		this(10, false);
	}
	public CountCache(int maxCount,boolean accessOrder){
		this.maxCount=maxCount;
		initCache(accessOrder);
		print("最大容量为：---"+maxCount);
		
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
	 * 放入缓存池
	 * @param id
	 * @param t
	 */
	public synchronized void put(String id,T t){
		cache.put(id, t);
		checkSize();
	}
	private synchronized void checkSize(){
		if(cache.size()>maxCount){
			Iterator<Entry<String, T>> it=cache.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, T> entry=it.next();
				it.remove();
				if(cache.size()<maxCount){
					break;
				}
			}
		}
		print("当前容量为：---"+cache.size());
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
	public static void main(String[] args) {
		
	}
}
