package com.lzx.hero.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzx.hero.bean.TbHero;
import com.lzx.hero.bean.TbResult;
import com.lzx.hero.dao.TbResultDao;
import com.lzx.hero.dao.base.Page;
import com.lzx.hero.service.base.BaseService;

@Service
public class TbResultService extends BaseService<TbResult,Integer> implements ITbResultService{
	@Autowired
	private TbResultDao tbResultDao;
	@Autowired
	private ITbHeroService heroService;
	@Autowired
	public void setBaseDao(TbResultDao tbResultDao) {
		super.setBaseDao(tbResultDao);
	}
	
	private List<TbHero> filter(String str,List<TbHero> heroList){
		List<TbHero> returnList=new ArrayList<TbHero>();
		for(TbHero hero:heroList){
			String idStr="["+hero.getId()+"]";
			if(str.indexOf(idStr)!=-1){
				returnList.add(hero);
			}
		}
		return returnList;
		
	}
	private String forEach(String[] strs,String open,String close,String separator){
		StringBuffer sb=new StringBuffer();
		sb.append(open);
		int length=strs.length;
		for(int i=0;i<length;i++){
			sb.append(strs[i]);
			if(i!=length-1){
				sb.append(separator);
			}
		}
		sb.append(close);
		return sb.toString();
	}

	@Override
	public List<Map> getAllResultByError(String ids,Page page) {
		List<TbHero> heroList=heroService.findAllOrderAddress();
		List<TbResult> resultList=null;
		if(StringUtils.isNotBlank(ids)){
			String[] array=ids.split(",");
			String str=forEach(array, "%[", "]%", "]%%[");
			resultList=tbResultDao.getByError(str,page);
		}else{
			resultList=tbResultDao.getByError(null,page);
		}
	    List<Map> returnList=new ArrayList<Map>();
	    for(TbResult result:resultList){
	        Map returnMap=new HashMap();
	        returnMap.put("success", filter(result.getSuccess(), heroList));
	        returnMap.put("error", filter(result.getError(),  heroList));
	        returnMap.put("percentage", result.getPercentage());
	        returnMap.put("id", result.getId());
	        returnList.add(returnMap);
	    }
		return returnList;
	}

	@Override
	public long countAllResultByError(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] array=ids.split(",");
			String str=forEach(array, "%[", "]%", "]%%[");
			return tbResultDao.countByError(str);
		}else{
			return tbResultDao.countByError(null);
		}
	}

	@Override
	public boolean exist(TbResult result) {
		long size=0;
		if(result.getId()!=null){
			size=tbResultDao.countBySuccessAndError(result.getSuccess(), result.getError(),result.getId());
		}else{
			size=tbResultDao.countBySuccessAndError(result.getSuccess(), result.getError());
		}
		if(size>0){
			return true;
		}
		return false;
	}

} 
