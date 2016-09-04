package com.lzx.hero.service.base;

import java.io.Serializable;
import java.util.List;
import com.lzx.hero.dao.base.IBaseDao;

public class BaseService<T,PK extends Serializable> implements IBaseService<T, PK>{
	private IBaseDao<T,PK> baseDao;
	
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public T get(PK pk) {
		return baseDao.get(pk);
	}

	public T load(PK pk) {
		return baseDao.load(pk);
	}

	public int save(T t) {
		return baseDao.save(t);
	}

	public void update(T t) {
		baseDao.update(t);
		
	}

	public T merge(T t) {
		return baseDao.merge(t);
	}

	public void saveOrUpdate(T t) {
		baseDao.saveOrUpdate(t);
		
	}

	public void delete(T t) {
		baseDao.delete(t);
		
	}
	
	
	public int deleteAll() {
		return baseDao.deleteAll();
		
	}

	public List<T> findAll() {
		return baseDao.findAll();
	}

	public long countAll() {
		return baseDao.countAll();
	}
	
	

}
