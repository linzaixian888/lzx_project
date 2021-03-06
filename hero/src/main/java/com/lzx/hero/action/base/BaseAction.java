package com.lzx.hero.action.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.ServletActionContext;

import com.lzx.hero.dao.base.Page;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("default")
public class BaseAction extends ActionSupport{
	private String flag;
	private Page page;
	public String JSON="json";
	private Map<String, Object> result=new HashMap<String, Object>();
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	public void setRequest(String key,Object value){
		getRequest().setAttribute(key, value);
	}
	public Object getRequest(String key){
		return getRequest().getAttribute(key);
	}
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	public void setSession(String key,Object value){
		getSession().setAttribute(key, value);
	}
	public Object getSession(String key){
		return getSession().getAttribute(key);
	}
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setJSON(String key,Object value){
		result.put(key, value);
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
