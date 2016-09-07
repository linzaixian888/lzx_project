package com.bluedon.bsmon.util;


import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;

public class BlueDonDemo {
	private static String name="林再贤";
	private static String path="d:/";
	private static int month=7;
	public static void main(String[] args) throws Exception {
		String before=null;
		String after=null;
		//初始化时间
		Calendar calendar=Calendar.getInstance();
		if(month!=0){
			calendar.set(Calendar.MONTH, month-1);	
		}
		calendar.set(Calendar.DATE, 1);
		before=TimeUtil.dateFormat(calendar.getTime());
		calendar.add(calendar.MONTH, 1);
		calendar.add(calendar.DATE, -1);
		after=TimeUtil.dateFormat(calendar.getTime());
		Map<String,Integer> cache=new HashMap<String, Integer>();
		ObjectMapper mapper=new ObjectMapper();
		HttpClientUtil util=new HttpClientUtil();
		Map params=new HashMap();
		params.put("client_language", "zh-cn");
		params.put("finnger10", "");
		params.put("finnger9", "");
		params.put("login_type", "pwd");
		params.put("password", "bluedonrd");
		params.put("template10	", "");
		params.put("template9", "");
		params.put("username", "yanfa02");
		util.doPost("http://113.108.195.66:38080/accounts/login/",params);
//		-------------------------------------------------
//		params.clear();
//		params.put("UserID__PIN__contains", 1253);
//		params.put("o", "-TTime,UserID__PIN");
//		params.put("stamp", new Date().getTime());
//		params.put("TTime__gte", "2016-01-01 00:00:00");
//		params.put("TTime__lte", "2016-02-01 00:00:00");
//		params.put("l", 10);
//		params.put("mnp", 50);
//		String jsonString=util.doPostString("http://113.108.195.66:38080/data/iclock/Transaction/", params);
//		Pattern p=Pattern.compile("data:([\\s\\S]+),\nrecord_count");
//	    Matcher m= p.matcher(jsonString);
//	    if(m.find()){
//	    	System.out.println(m.group(1));
//	    	List list=mapper.readValue(m.group(1), List.class);
//	    	for(int i=0;i<list.size();i++){
//	    		List childList=(List) list.get(i);
//	    		if(childList.size()>0){
//	    			Integer userID=Integer.parseInt( childList.get(0).toString());
//	    			String oneName=childList.get(2).toString();
//	    			params.clear();
//	    			params.put("UserID__in", userID);
//	    			params.put("all", "True");
//	    			params.put("checktime__range", "(\""+before+"\",\""+after+"\")");
//	    			params.put("fields", "UserID.PIN,UserID.EName,checktime,CheckType,NewType");
//	    			params.put("filecode", "gb18030");
//	    			params.put("format", "xls");
//	    			params.put("model", "AttRecAbnormite");
//	    			params.put("o", "-pk");
//	    			params.put("reportname", "统计结果详情");
//	    			params.put("stamp", System.currentTimeMillis());
//	    			params.put("t", "1");
//	    			params.put("txtviewname", "");
//	    			HttpResponse response=util.doGet("http://113.108.195.66:38080/api/att/AttRecAbnormite/", params);
//	    			InputStream is=response.getEntity().getContent();
//	    			String index=getIndex(cache, oneName);
//	    			if(month>0){
//	    				index=index+"-"+month+"月";
//	    			}
//	    			copy(is, new FileOutputStream(new File(path, oneName+index+".xls")));
//	    		}
//	    	}
//	    }
//		-------------------------------------------------
//		params.clear();
//		params.put("l", "15");
//		params.put("mnp", "50");
//		params.put("o", "PIN,DeptID__code");
//		params.put("q", name);
//		params.put("stamp", System.currentTimeMillis());
//		String jsonString=util.doPostString("http://113.108.195.66:38080/data/personnel/Employee/", params);
//		Pattern p=Pattern.compile("data:([^_]+),\nrecord_count");
//	    Matcher m= p.matcher(jsonString);
//	    if(m.find()){
//	    	List list=mapper.readValue(m.group(1), List.class);
//	    	for(int i=0;i<list.size();i++){
//	    		List childList=(List) list.get(i);
//	    		System.out.println(childList);
//	    		if(childList.size()>0){
//	    			Integer userID=Integer.parseInt( childList.get(0).toString());
//	    			String oneName=childList.get(2).toString();
//	    			params.clear();
//	    			params.put("UserID__in", userID);
//	    			params.put("all", "True");
//	    			params.put("checktime__range", "(\""+before+"\",\""+after+"\")");
//	    			params.put("fields", "UserID.PIN,UserID.EName,checktime,CheckType,NewType");
//	    			params.put("filecode", "gb18030");
//	    			params.put("format", "xls");
//	    			params.put("model", "AttRecAbnormite");
//	    			params.put("o", "-pk");
//	    			params.put("reportname", "统计结果详情");
//	    			params.put("stamp", System.currentTimeMillis());
//	    			params.put("t", "1");
//	    			params.put("txtviewname", "");
//	    			HttpResponse response=util.doGet("http://113.108.195.66:38080/api/att/AttRecAbnormite/", params);
//	    			InputStream is=response.getEntity().getContent();
//	    			String index=getIndex(cache, oneName);
//	    			if(month>0){
//	    				index=index+"-"+month+"月";
//	    			}
//	    			copy(is, new FileOutputStream(new File(path, oneName+index+".xls")));
//	    		}
//	    	}
//	    }
	}
	private static String getIndex(Map<String,Integer> cache,String oneName){
		Integer index=cache.get(oneName);
		if(index==null){
			cache.put(oneName, 0);
			return "";
		}else{
			index++;
			cache.put(oneName, index);
			return index.toString();
		}
		
	}
	private static void copy(InputStream is,OutputStream os) throws IOException{
		try {
			int len=0;
			byte[] buff=new byte[1024];
			while((len=is.read(buff))!=-1){
				os.write(buff, 0, len);
			}
		} catch (IOException e) {
			throw e;
		}finally{
			close(is);
			close(os);
		}
	}
	private static void close(Closeable closeable){
		if(closeable!=null){
			try {
				closeable.close();
			} catch (IOException e) {
			}
		}
	}
}
