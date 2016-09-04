package com.lzx.hero.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lzx.hero.util.StringPrintWriter;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor{
	private static final Logger logger=LoggerFactory.getLogger(MyInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionClassName = invocation.getAction().getClass().getName();
		String actionMethodName = invocation.getProxy().getMethod();
		logger.debug("调用了{}类中的{}方法",actionClassName,actionMethodName);
		String result=null;
		try {
			result=invocation.invoke();
			return result;
		} catch (Exception e) {
			StringPrintWriter spw=new StringPrintWriter();
			e.printStackTrace(spw);
			logger.error("{}类中的{}方法发生异常:{}",actionClassName,actionMethodName,spw.getString());
			invocation.getStack().set("exceptionStack", spw.getString());
			e.printStackTrace();
			return "error";
		}
	}
	
}
