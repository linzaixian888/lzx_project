package com.lzx.demo.jsoup;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.script.javascript.RhinoScriptEngine;

public class JsoupDemo {
	public static void main(String[] args) throws Exception {
		Document d = Jsoup.parse(ClassLoader.getSystemResourceAsStream("广州市电子政务门户.htm"),"utf-8","");
		System.out.println(d.html());

	}
}
