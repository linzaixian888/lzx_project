package com.lzx.util.sql;

public class PageUtil {
	/**
	 * 获得总页数
	 * @param count 总数
	 * @param pageSize 每页的大小
	 * @return
	 */
	public static int getTotalPage(int count,int pageSize){
		if(count%pageSize==0){
			return count/pageSize;
		}else{
			return count/pageSize+1;
		}
	}
	/**
	 * 获得当前页从第几条纪录开始(不包括该条纪录)
	 * @param pageNum 当前页数
	 * @param pageSize 每页的大小
	 * @return
	 */
	public static int getIndex(int pageNum,int pageSize){
		return (pageNum-1)*pageSize;
	}
	
	/**
	 * 获得每页的大小
	 * @param count 总数
	 * @param totalPage 总页数
	 * @return
	 */
	public static int getPageSize(int count,int totalPage){
		if(count%totalPage==0){
			return count/totalPage;
		}else{
			return count/totalPage+1;
		}
	}
	/**
	 * 获得当前页数
	 * @param index 当前索引数
	 * @param pageSize  每页的大小
	 * @return
	 */
	public static int getPageNum(int index,int pageSize){
		if(index%pageSize==0){
			return index/pageSize;
		}else{
			return index/pageSize+1;
		}
	}
	public static void main(String[] args) {
	}
	
	
}
