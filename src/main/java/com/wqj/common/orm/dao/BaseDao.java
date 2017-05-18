package com.wqj.common.orm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.jdbc.core.CallableStatementCallback;

import com.wqj.common.bean.Page;
import com.wqj.common.orm.PropertyFilter;
import com.wqj.common.orm.entity.BaseEntity;
public interface BaseDao<T extends BaseEntity> {

//=========================================增===================================================
	
	//根据对象保存
	void save(T entity);
	
//=========================================删===================================================	
	
	//根据对象删除
	void delete(T entity);
	
	//根据id删除
	void delete(Serializable id);
	
//=========================================改===================================================		
	
	//根据对象更新
	void update(T entity);
	void merge(T entity);
	void saveOrUpdate(T entity);
//=========================================查===================================================	
	
	//根据id查询
	List<T> findAll();
	
	//根据id查询
	T get(Serializable id);
	
	//根据某个属性的值进行查询
	List<T> findDatas(String propertyName, Object value);
	
	//根据map进行查询
	List<T> findDatas(Map<String, Object> map);
	
	//根据detachedCriteria查询
	List<T> findDatas(DetachedCriteria detachedCriteria);
	
//=========================================分页查询===================================================	
	List<T> findPage(Page page);
	List<T> findPage(DetachedCriteria detachedCriteria, Page page);
	List<T> findDatas(String propertyName, Object value, Page page);
	List<T> findPage(Map<String, Object> map,Page page);  //根据map进行查询
	List<T> findPage(final Page page, final List<PropertyFilter> filters);
	
	
//=============================================sql============================================
  int createSql(String sql);
  
  public <T>T prepareCall(String callString, CallableStatementCallback<T> action) ;
 
	/** ------------------------------------- Helper -------------------------------------	 **/
	
	//创建DetachedCriteria对象
	DetachedCriteria createDetachedCriteria();
	
	public Session getSession();
}