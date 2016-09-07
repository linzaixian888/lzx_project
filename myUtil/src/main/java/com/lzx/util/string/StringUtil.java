package com.lzx.util.string;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对字符串的操作类
 * @author lzx
 *
 */
public class StringUtil {
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public String firstUp(String str){
		if(str==null||"".equals(str)){
			return str;
		}else{
			String temp=str.substring(0,1);
			return temp.toUpperCase()+str.substring(1);
		}
	}
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public String firstLow(String str){
		if(str==null||"".equals(str)){
			return str;
		}else{
			String temp=str.substring(0,1);
			return temp.toLowerCase()+str.substring(1);
		}
	}
	/**
	 * 去掉自定义标识和里面的内容
	 * @param source
	 * @param beginStr
	 * @param endStr
	 * @return
	 */
	public  String trim(String source,String beginStr,String endStr){
		if(source==null||"".equals(source)){
			return source;
		}
		int beginIndex, endIndex;
		beginIndex = source.indexOf(beginStr);
		endIndex = source.indexOf(endStr)+endStr.length()-1;
		if (beginIndex != -1 && endIndex != -1) {
			source = source.substring(0, beginIndex)
					+ source.substring(endIndex + 1);
			if (source.indexOf(beginStr) != -1 && source.indexOf(endStr) != -1) {
				source=trim(source,beginStr,endStr);
			}
		}
		return source;
		
	}
	/**
	 * 去除_符号并将除了第一个单词之外的每个首字母大写
	 * @param source
	 * @return
	 */
	public String tofield(String source){
		source=source.toLowerCase();
		if(source.indexOf("_")==-1){
			return firstLow(source);
		}else{
			StringBuffer sb=new StringBuffer();
			String[] strs=source.split("_");
			for(int i=0;i<strs.length;i++){
				if(i==0){
					sb.append(firstLow(strs[i]));
					continue;
				}
				sb.append(firstUp(strs[i]));
			}
			return sb.toString();
		}
	}
	/**
	 * 去除.符号以及之后的字符串
	 * @param str
	 * @return
	 */
	public String trimPostfix(String str){
		int index=str.lastIndexOf(".");
		if(index==-1){
			return str;
		}else{
			return str.substring(0, index);
		}
	}
	/**
	 * 对一个字符串数组进行迭代处理
	 * @param strs
	 * @param open
	 * @param close
	 * @param separator
	 * @return
	 */
	public String forEach(String[] strs,String open,String close,String separator){
		StringBuffer sb=new StringBuffer();
		sb.append(open);
		int length=strs.length;
		for(int i=0;i<length;i++){
			sb.append(strs[i]);
			if(i!=length-1){
				sb.append(separator);
			}
		}
		sb.append(close);
		return sb.toString();
	}
	/**
	 * 对一个字符串集合进行迭代处理
	 * @param strList
	 * @param open
	 * @param close
	 * @param separator
	 * @return
	 */
	public String forEach(List<String> strList,String open,String close,String separator){
		StringBuffer sb=new StringBuffer();
		sb.append(open);
		int length=strList.size();
		for(int i=0;i<length;i++){
			sb.append(strList.get(i));
			if(i!=length-1){
				sb.append(separator);
			}
		}
		sb.append(close);
		return sb.toString();
	}
	/**
	 * 取得标识字符串在某个字符串中的数量
	 * @param allStr
	 * @param tag
	 * @return
	 */
	public int getCount(String allStr, String tag){
		int count=0;
		Pattern p=Pattern.compile(tag);
		Matcher m=p.matcher(allStr);
		while(m.find()){
			count++;
		}
		return count;
	}
	/**
	 * 将字符串解析成代码字符串
	 * @param str
	 * @return
	 */
	public String parse(String str){
		int length=str.length();
		StringBuilder sb=new StringBuilder(length);
		for(int i=0;i<length;i++){
			char c=str.charAt(i);
			switch (c) {
			case '\\':
				sb.append("\\\\");
				break;
			case '\"':
				sb.append("\\\"");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
	/**
	 * 如果该路径末尾没有文件分隔符，则加上文件分隔符
	 * @param path
	 * @return
	 */
	public  String getAddPath(String path){
		boolean isAdd=true;
		String temp=path.trim();
		if(temp.endsWith("/")||temp.endsWith("\\")){
			isAdd=false;
		}
		if(isAdd){
			path=path+File.separator;
		}
		return path;
		
	}
	/**
	 * 如果文件路径有文件分隔符，则删除文件分隔符
	 * @param path
	 * @return
	 */
	public String getDelPath(String path){
		String temp=path.trim();
		if(temp.endsWith("/")||temp.endsWith("\\")){
			temp.substring(0, temp.length()-1);
		}
		return path;
	}
	/**
	 * 单个unicode转换为中文，不匹配则返回原字符串
	 * @param unicode
	 * @return
	 */
	public static String unicodeToString (String unicode){
		int length=unicode.length();
		StringBuilder sb=new StringBuilder();
		if(unicode.startsWith("\\u")&&length==6){
			return sb.append((char)Integer.parseInt(unicode.substring(2), 16)).toString();
		}
		return unicode;
		
	}
	/**
	 * 将字会串里面的unicode转换为中文
	 * @param str
	 * @return
	 */
	public static String coverUnicode(String str){
		StringBuilder sb=new StringBuilder();
		int length=str.length();
		int i = -1;  
		int pos = 0; 
		int last=-1;
		while((i=str.indexOf("\\u", pos)) != -1){
	        sb.append(str.substring(pos, i));  
	        if(i+6 <= length){  
	            pos = i+6;  
	            sb.append(unicodeToString(str.substring(i, i+6)));
	            last=pos;
	        }else{
	        	sb.append(str.substring(i));
	        	last=length;
	        }
	    } 
		if(last==-1){
			return str;
		}else if(last<length){
			sb.append(str.substring(last));
		}
		return sb.toString();  
		
	}
	public static void main(String[] args) {
		System.out.println(unicodeToString("\u82f1"));
	}
	
}
