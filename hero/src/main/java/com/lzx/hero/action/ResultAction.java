package com.lzx.hero.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzx.hero.action.base.BaseAction;
import com.lzx.hero.bean.TbResult;
import com.lzx.hero.service.ITbHeroService;
import com.lzx.hero.service.ITbResultService;
import com.lzx.hero.service.TbResultService;

@Namespace("/result")
public class ResultAction extends BaseAction{
	private TbResult tbResult;
	private Integer id;
	private String ids;
	private String name;
	@Autowired
	private ITbHeroService heroService;
	@Autowired
	private ITbResultService resultService;
	@Action(value="resultEdit",results={
			@Result(location="/WEB-INF/view/result_edit.ftl")
	})
	public String resultEdit()throws Exception {
		if(id!=null){
			TbResult result=resultService.get(id);
			result.setSuccess(result.getSuccess().replace("[", "").replace("]", "").replace(",", ";"));
			result.setError(result.getError().replace("[", "").replace("]", "").replace(",", ";"));
			setRequest("heroResult", result);
		}
		setRequest("heroList", heroService.findAllOrderAddress());
		return SUCCESS;
	}
	

	@Action("save")
	public String save()throws Exception {
		try {
			if(resultService.exist(tbResult)){
				setJSON("error", "该克制阵容已存在");
			}else{
				tbResult.setStime(new Date());
				resultService.saveOrUpdate(tbResult);
				setJSON("success", "保存成功");
			}
			
		} catch (Exception e) {
			setJSON("error", "增加失败:"+e.toString());
			throw e;
		}
		return JSON;
	}
	@Action("delete")
	public String delete()throws Exception {
		try {
			resultService.delete(tbResult);;
			setJSON("success", "删除成功");
		} catch (Exception e) {
			setJSON("error", "删除失败:"+e.toString());
			throw e;
		}
		return JSON;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public TbResult getTbResult() {
		return tbResult;
	}
	public void setTbResult(TbResult tbResult) {
		this.tbResult = tbResult;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	
}
