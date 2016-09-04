package com.lzx.hero.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session; 

public interface IBaseService<T, PK extends Serializable> {


	public  T get(PK pk);

	public  T load(PK pk);

	public  int save(T t);

	public  void update(T t);

	public  T merge(T t);

	public  void saveOrUpdate(T t);

	public  void delete(T t);
	
	public  int deleteAll();

	public  List<T> findAll();
	
	public long countAll();
	

}