package com.lzx.demo.httpclient;

import java.util.Map;
import java.util.Set;

public class SqlUtil {
	public static String getInsertSql(String tableName,Map map){
		int length=map.size();
		Object[] values=new Object[length];
		StringBuilder sb=new StringBuilder();
		Set<String> keys=map.keySet();
		int index=0;
		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" ( ");
		for(String key:keys){
			sb.append(key);
			values[index++]=map.get(key);
			if(index>=length){
				break;
			}
			sb.append(" , ");
		}
		sb.append(" ) VALUES ");
		sb.append(getInSql(length,values));
		return sb.toString();
	}
	private static String getInSql(int length,Object...values){
		StringBuffer sb=new StringBuffer();
		sb.append(" ( ");
		for(int i=0;i<length;i++){
			Object obj=values[i];
			if(obj instanceof Integer){
				sb.append(" "+obj+" ");
			}else if(obj instanceof String){
				sb.append(" '"+obj+"' ");
			}
			if(i!=length-1){
				sb.append(",");
			}
		}
		sb.append(" ) ");
		return sb.toString();
	}
	
}
