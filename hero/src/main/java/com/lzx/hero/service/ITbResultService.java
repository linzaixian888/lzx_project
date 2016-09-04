package com.lzx.hero.service;

import java.util.List;
import java.util.Map;

import com.lzx.hero.bean.TbResult;
import com.lzx.hero.dao.base.Page;
import com.lzx.hero.service.base.IBaseService;

public interface ITbResultService extends IBaseService<TbResult,Integer>{
	public List<Map> getAllResultByError(String ids,Page page);
	public long countAllResultByError(String ids);
	public boolean exist(TbResult result);
}
