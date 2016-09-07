package com.lzx.util.dom4j;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
/**
 * dom4j对象与java对象的转换
 * @author linzx
 *
 */
public class Dom4jUtil {
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void createDocument(OutputStream os,Object obj) throws Exception{
		Document document=DocumentHelper.createDocument();
		document.add(parseBean(obj));
		XMLWriter writer=new XMLWriter(os);
		writer.write(document);
	}
	/**
	 * 将dom4j节点属性值填充在javabean中
	 * @param src
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Object fillBean(Element src,Object obj) throws Exception{
		Class<?> classType=obj.getClass();
		List<Attribute> list=src.attributes();
		Method[] methods=classType.getDeclaredMethods();
		for(Attribute attribute:list){
			String name=attribute.getName();
			String value=attribute.getValue();
			StringBuilder sb=new StringBuilder();
			sb.append("set");
			int length=name.length();
			if(length>=1){
				sb.append(name.substring(0, 1).toUpperCase());
				sb.append(name.substring(1));
			}
			String methodName=sb.toString();
			for(Method method:methods){
				if(methodName.equals(method.getName())){
					Type[] types=method.getGenericParameterTypes();
					if(types!=null&&types.length==1){
						Type type=method.getGenericParameterTypes()[0];
						if(type==Integer.TYPE||type==Integer.class){
							method.invoke(obj, Integer.parseInt(value));
						}else if(type==Boolean.TYPE||type==Boolean.class){
							method.invoke(obj, Boolean.parseBoolean(value));
						}else if(type==Date.class){
							method.invoke(obj, sdf.parse(value));
						}else if(type==String.class){
							method.invoke(obj, value);
						}
					}
				}
			}
		}
		return obj;
		
	}
	/**
	 * 解析java对象，转为化dom4j节点对象
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Element parseBean(Object bean) throws Exception{
		Class<?> classType=bean.getClass();
		Element element=DocumentHelper.createElement(classType.getSimpleName());
		Method[] methods=classType.getDeclaredMethods();
		for(Method method:methods){
			String methodName=method.getName();
			int length=methodName.length();
			if(methodName.startsWith("get")&&length>=4){
				String field=methodName.substring(3, 4).toLowerCase()+methodName.substring(4);
				Type type=method.getGenericReturnType();
				Object value=method.invoke(bean);
				if(value==null){
					continue;
				}else if(type==Date.class){
					element.addAttribute(field, sdf.format(value));
				}else if(value instanceof Collection){
					for(Object temp:(Collection)value){
						element.add(parseBean( temp));
					}
				}else{
					element.addAttribute(field, String.valueOf(value));
				}
			}
		}
		return element;
	}
	public static void main(String[] args) throws Exception {
	}
}
