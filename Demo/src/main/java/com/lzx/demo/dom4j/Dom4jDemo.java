package com.lzx.demo.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jDemo {
	public static void main(String[] args) throws Exception {
		change();
	}
	public static void change() throws Exception{
		File f=new File("D:\\java\\workspace\\sts\\Servers\\Tomcat v6.0 Server at localhost-config\\server.xml");
		SAXReader reader=new SAXReader();
		Document d=reader.read(f);
		Element root=d.getRootElement();
		List<Element> list=root.elements();
		Element serviceElement=root.element("Service");
		Element engineElement=serviceElement.element("Engine");
		Element hostElement=engineElement.element("Host");
		hostElement.addAttribute("autoDeploy", "false");
		list=hostElement.elements();
		for(Element e:list){
			e.addAttribute("reloadable", "false");
		}
		XMLWriter writer=new XMLWriter(new FileOutputStream(f));
		writer.write(d);
	}
}
