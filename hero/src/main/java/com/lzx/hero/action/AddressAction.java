package com.lzx.hero.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzx.hero.action.base.BaseAction;
import com.lzx.hero.service.ITbHeroService;
import com.lzx.hero.service.ITbResultService;

@Namespace("/address")
public class AddressAction extends BaseAction{
	private String jsonStr;
	@Autowired
	private ITbHeroService heroService;
	@Autowired
	private ITbResultService resultService;
	@Action(value="address",results={
			@Result(location="/WEB-INF/view/address.ftl")
	})
	public String  address()throws Exception {
		setRequest("heroList", heroService.findAllOrderAddress());
		return SUCCESS;
	}
	@Action("save")
	public String save()throws Exception {
		try {
			ObjectMapper mapper=new ObjectMapper();
			List<Map> list=mapper.readValue(jsonStr, List.class);
			heroService.updateAddressSec(list);
			setJSON("success", "调整站位成功");
		} catch (Exception e) {
			setJSON("error", "调整站位失败");
			e.printStackTrace();
		}
		return JSON;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	
}
