package com.lzx.demo.javascript;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.sun.script.javascript.RhinoScriptEngine;

public class JavaScriptDemo {
	public static void main(String[] args) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Reader r=new InputStreamReader(ClassLoader.getSystemResourceAsStream("js.js"));
		engine.eval(r);
		//获取javascript的变量
		System.out.println(engine.get("sum"));
		RhinoScriptEngine en=(RhinoScriptEngine) engine;
		//调用javascript的方法并传值
		System.out.println(en.invokeFunction("add", 1,2));
	}
}
