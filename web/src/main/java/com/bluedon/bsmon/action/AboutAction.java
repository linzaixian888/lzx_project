package com.bluedon.bsmon.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.bluedon.bsmon.action.base.BaseAction;

@Namespace("/about")
public class AboutAction extends BaseAction{
	@Action(value="about",results={
			@Result(location="/WEB-INF/view/about.ftl")
	})
	public String about() throws Exception {
		return SUCCESS;
	}
	@Action("testJSON")
	public String testJSON() throws Exception {
		setJSON("当前action是:", "HelloAction");
		return JSON;
	}
	
}
