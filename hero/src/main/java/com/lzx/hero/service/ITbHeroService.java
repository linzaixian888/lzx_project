package com.lzx.hero.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.lzx.hero.bean.TbHero;
import com.lzx.hero.dao.base.Page;
import com.lzx.hero.service.base.IBaseService;

public interface ITbHeroService extends IBaseService<TbHero,Integer>{
	public void inserOrtUpdateAllHero(Collection<TbHero> collection);
	public TbHero getHero(String name);
	public List<TbHero> findAllOrderAddress();
	public void updateAddressSec(List<Map> allHeroList);
	public  void saveAllHero() throws Exception;
	
	public List<TbHero> findHero(Page page);
	public List<TbHero> findHeroByName(Page page,String name);
	public long countHeroByName(String name);
	
}
