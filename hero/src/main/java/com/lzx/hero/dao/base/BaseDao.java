package com.lzx.hero.dao.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
public abstract class BaseDao<T,PK extends Serializable> implements IBaseDao<T, PK> {
	private Class<T> classType;
	@Resource
	private SessionFactory sessionFactory;
	public BaseDao(){
		Type type=getClass().getGenericSuperclass();
		if(type instanceof ParameterizedTypeImpl){
			classType=(Class<T>) ((ParameterizedTypeImpl) type).getActualTypeArguments()[0];
		}
	}
	@Override
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public T get(PK pk){
		return (T) getSession().get(classType, pk);
	}
	@Override
	public T load(PK pk){
		return (T) getSession().load(classType, pk);
	}
	@Override
	public int save(T t){
		Session session=getSession();
		session.clear();
		Serializable value=session.save(t);
		session.flush();
		return (Integer) value;
	}
	@Override
	public void update(T t){
		Session session=getSession();
		session.clear();
		session.update(t);
		session.flush();
	}
	@Override
	public T merge(T t){
		return (T) getSession().merge(t);
	}
	@Override
	public void saveOrUpdate(T t){
		Session session=getSession();
		session.saveOrUpdate(t);
		session.flush();
	}
	@Override
	public void delete(T t){
		getSession().delete(t);
	}
	@Override
	public List<T> findAll(){
		String hql="from "+classType.getName();
		return queryHql(hql);
	}
	@Override
	public long countAll(){
		String hql="select count(*) from "+classType.getName();
		return countHql(hql);
	}
	@Override
	public int deleteAll(){
		String hql="delete from "+classType.getName();
		return executeHql(hql);
	}
	@Override
	public List<T> queryProperty(String key,Object value){
		StringBuilder sb=new StringBuilder();
		sb.append(" from ")
		  .append(classType.getSimpleName())
		  .append(" where ")
		  .append(key)
		  .append(" = ?");
		return queryHql(sb.toString(), value);
	}
	@Override
	public T querySingleProperty(String key,Object value){
		List<T> list=queryProperty(key, value);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<T> queryHql(QueryCallBack callBack, String hql) {
		Query query=getSession().createQuery(hql);
		if(callBack!=null){
			callBack.filterQuery(query);
		}
		return query.list();
	}
	@Override
	public List<T> queryHql(String hql, final Object... params) {
		return queryHql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
			}
		},  hql);
	}
	@Override
	public List<T> queryHql(Page page, String hql, final Object... params) {
		String sortName=page.getSortName();
		String sortType=page.getSortType();
		final Integer pageIndex=page.getPageIndex();
		final Integer pageSize=page.getPageSize();
		StringBuilder sb=new StringBuilder(hql);
		if(sortName!=null){
			sb.append(" order by "+sortName);
			if(sortType!=null){
				sb.append(" "+sortType);
			}
		}
		return queryHql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				
				if(pageIndex!=null){
					query.setFirstResult(pageIndex);
				}
				if(pageSize!=null){
					query.setMaxResults(pageSize);
				}
				
			}
		},  sb.toString());
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> queryHql(String hql, Map<String, Object> params) {
		StringBuilder sb=new StringBuilder(hql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		
		return queryHql(sb.toString(),list.toArray());
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> queryHql(Page page, String hql, Map<String, Object> params) {
		String sortName=page.getSortName();
		String sortType=page.getSortType();
		final Integer pageIndex=page.getPageIndex();
		final Integer pageSize=page.getPageSize();
		StringBuilder sb=new StringBuilder(hql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		final List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		if(sortName!=null){
			sb.append(" order by "+sortName);
			if(sortType!=null){
				sb.append(" "+sortType);
			}
		}
		return queryHql(new QueryCallBack() {
			
			public void filterQuery(Query query) {
				for(int i=0;i<list.size();i++){
					query.setParameter(i, list.get(i));
				}
				if(pageIndex!=null){
					query.setFirstResult(pageIndex);
				}
				if(pageSize!=null){
					query.setMaxResults(pageSize);
				}
			}
		}, sb.toString());
	}
	@Override
	public List querySql(QueryCallBack callBack, String sql) {
		SQLQuery query=getSession().createSQLQuery(sql);
		if(callBack!=null){
			callBack.filterQuery(query);
		}
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> querySqlPojo(String sql, final Object... params) {
		return querySql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
					((SQLQuery) query).setResultTransformer(Transformers.aliasToBean(classType));
			}
		}, sql);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Map> querySqlMap(String sql, final Object... params) {
		return querySql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
					((SQLQuery) query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			}
		}, sql);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> querySqlPojo(final Page page, String sql, final Object... params) {
		StringBuilder sb=new StringBuilder(sql);
		if(page!=null&&page.getSortName()!=null){
				sb.append(" order by "+page.getSortName());
				if(page.getSortType()!=null){
					sb.append(" ")
					  .append(page.getSortType());
				}
		}
		return querySql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				if(page!=null){
					if(page.getPageIndex()!=null){
						query.setFirstResult(page.getPageIndex());
					}
					if(page.getPageSize()!=null){
						query.setMaxResults(page.getPageSize());
					}
				}
				((SQLQuery) query).setResultTransformer(Transformers.aliasToBean(classType));
			}
		}, sb.toString());
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Map> querySqlMap(final Page page, String sql, final Object... params) {
		StringBuilder sb=new StringBuilder(sql);
		if(page!=null&&page.getSortName()!=null){
				sb.append(" order by "+page.getSortName());
				if(page.getSortType()!=null){
					sb.append(" ")
					  .append(page.getSortType());
				}
		}
		return querySql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				if(page!=null){
					if(page.getPageIndex()!=null){
						query.setFirstResult(page.getPageIndex());
					}
					if(page.getPageSize()!=null){
						query.setMaxResults(page.getPageSize());
					}
				}
				((SQLQuery) query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			}
		}, sb.toString());
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> querySqlPojo(String sql, Map<String, Object> params) {
		StringBuilder sb=new StringBuilder(sql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		
		return querySqlPojo(sb.toString(),list.toArray());
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Map> querySqlMap(String sql, Map<String, Object> params) {
		StringBuilder sb=new StringBuilder(sql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		
		return querySqlMap(sb.toString(),list.toArray());
	}
	@Override
	public List<T> querySqlPojo(Page page, String sql, Map<String, Object> params) {
		StringBuilder sb=new StringBuilder(sql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		final List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		return querySqlPojo(page, sb.toString(), list.toArray());
	}
	@Override
	public List<Map> querySqlMap(Page page, String sql, Map<String, Object> params) {
		StringBuilder sb=new StringBuilder(sql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		final List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		return querySqlMap(page, sb.toString(), list.toArray());
	}
	@Override
	public long countHql(QueryCallBack callBack,String hql){
		Query query=getSession().createQuery(hql);
		if(callBack!=null){
			callBack.filterQuery(query);
		}
		Long big=(Long) query.uniqueResult();
		return big.longValue();
	}
	@Override
	public long countHql(String hql,final Object...params){
		return countHql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
			}
		}, hql);
	}
	@Override
	public long countHql(String hql,Map<String,Object> params){
		StringBuilder sb=new StringBuilder(hql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		return countHql(sb.toString(), list.toArray());
	}
	@Override
	public long countSql(QueryCallBack callBack,String sql){
		SQLQuery query=getSession().createSQLQuery(sql);
		if(callBack!=null){
			callBack.filterQuery(query);
		}
		BigInteger big=(BigInteger) query.uniqueResult();
		return big.longValue();
	}
	@Override
	public long countSql(String sql,final Object...params){
		return countSql(new QueryCallBack() {
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
			}
		}, sql);
	}
	@Override
	public long countSql(String sql,Map<String,Object> params){
		StringBuilder sb=new StringBuilder(sql);
		Set<Entry<String, Object>> set=params.entrySet();
		Iterator<Entry<String, Object>> it=set.iterator();
		if(params!=null&&params.size()>0){
			sb.append(" where ");
		}
		List list=new ArrayList();
		while(it.hasNext()){
			Entry<String, Object> entry=it.next();
			Object value=entry.getValue();
			String key=entry.getKey();
			if(value instanceof String){
				sb.append(" "+key+" like ? ");
			}else{
				sb.append(" "+key+" = ? ");
			}
			if(it.hasNext()){
				sb.append(" and ");
			}
			list.add(entry.getValue());
		}
		return countSql(sb.toString(), list.toArray());
	}
	@Override
	public int executeHql(String hql,Object...params){
		Query query=getSession().createQuery(hql);
		for(int i=0;i<params.length;i++){
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}
	@Override
	public int executeSql(String sql,Object...params){
		Query query=getSession().createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}
	@Override
	public Object querySqlSingleValue(final String sql, final Object... params) {
		List list=querySql(new QueryCallBack() {
			@Override
			public void filterQuery(Query query) {
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
			}
		},sql);
		if(list.size()>0){
			Object obj=list.get(0);
			if(obj!=null&&obj.getClass().isArray()){
				Object[] objs=(Object[]) obj;
				if(objs.length>0){
					return objs[0];
				}
			}
			return obj;
		}
		return null;
	}

}