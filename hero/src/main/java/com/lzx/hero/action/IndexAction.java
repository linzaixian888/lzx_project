package com.lzx.hero.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzx.hero.action.base.BaseAction;
import com.lzx.hero.service.ITbHeroService;
import com.lzx.hero.service.ITbResultService;

@Namespace("/index")
public class IndexAction extends BaseAction {
	private String ids;
	private String name;
	@Autowired
	private ITbHeroService heroService;
	@Autowired
	private ITbResultService resultService;

	@Action(value = "index", results = { @Result(location = "/WEB-INF/view/index.ftl") })
	public String index() throws Exception {
		return SUCCESS;
	}
	
	@Action("listResult")
	public String listResult() throws Exception {
		setJSON("Rows", resultService.getAllResultByError(ids, getPage()));
		setJSON("Total", resultService.countAllResultByError(ids));
		return JSON;
	}
	@Action("listHero")
	public String listHero()throws Exception {
		if(StringUtils.isNotBlank(name)){
			setJSON("Rows", heroService.findHeroByName(getPage(), name));
			setJSON("Total", heroService.countHeroByName(name));
		}else{
			setJSON("Rows", heroService.findHero(getPage()));
			setJSON("Total",heroService.countAll());
		}
		return JSON;
		
	}

	@Action(value = "about", results = { @Result(location = "/WEB-INF/view/about.ftl") })
	public String about() throws Exception {
		return SUCCESS;
	}

	@Action("test")
	public String test() throws Exception {
		setJSON("result", heroService.findAllOrderAddress());
		return JSON;
	}

	@Action("initHero")
	public String initHero() throws Exception {
		heroService.saveAllHero();
		setJSON("当前action是:", "IndexAction");
		return JSON;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
