package com.wqj.common.orm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.Subcriteria;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.wqj.common.orm.entity.BaseEntity;

@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public abstract class BaseDaoSupport<T extends BaseEntity> implements BaseDao<T> {

	protected String CLASS_NAME = getClass().getName();
	
//=========================================增===================================================
	
	/**
	 * 根据实体增加
	 */
	@Override
	@Transactional
	public void save(T entity) {
		getSession().save(entity);
	}

//=========================================删===================================================	
	
	/**
	 * 根据实体删除
	 */
	@Override
	@Transactional
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	/**
	 * 根据id删除
	 */
	@Override
	@Transactional
	public void delete(Serializable id) {
		T entity = get(id);
		if (entity == null) {
			return;
		}
		getSession().delete(entity);
	}

//=========================================改===================================================	
	/**
	 * 根据实体更新
	 */
	@Override
	@Transactional
	public void update(T entity) {
		getSession().update(entity);
	}
	
	@Override
	public void merge(T entity) {
		getSession().merge(entity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

//=========================================查===================================================
	
	/**
	 * 查询所有的 
	 */
	@Override
	public List<T> findAll() {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
		List<T> list = criteria.list();
		return list;
	}

	/**
	 * 根据主键查询
	 */
	@Override
	public T get(Serializable id) {
		return (T) getSession().get(getEntityClass(), id);
	}
	
	/**
	 * 根据一个属性值查询 【支持多表关联】
	 */
	@Override
	public List<T> findDatas(String propertyName, Object value) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
		String[] array = propertyName.split("\\.");
		String associationPath = array[0];
		String alias = array[0];
		if(array.length>1){
			for(int i=1; i<array.length;i++){
				criteria.createAlias(associationPath, alias);
				alias = array[i];
				associationPath = array[i-1]+"."+alias;
			}
		}
		criteria.add(Restrictions.eq(associationPath, value));
		return criteria.list();
	}
	
	/**
	 * 根据多个属性值查询 【支持多表关联】
	 */
	@Override
	public List<T> findDatas(Map<String, Object> map) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
		for (Entry<String, Object> entry : map.entrySet()) {
			String propertyName = entry.getKey();
			Object value = entry.getValue();
			createAlias(criteria, propertyName);
			addRestriction(criteria, propertyName, value);
		}
		return criteria.list();
	}
	
	/**
	 * 根据多个属性值查询 【支持多表关联】
	*/
	/*@Override
	public List<T> findDatas(Map<String, Object> map) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
		addRestriction(criteria, map);
		createAlias(criteria, map);
		return criteria.list();
	}*/
	
	@Override
	public List<T> findDatas(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}
	
	/** ------------------------------------- Helper -------------------------------------	 **/
	
	public Class<T> getEntityClass() {
		try {
			return (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			return (Class<T>) getEntity(getClass().getSuperclass().getGenericInterfaces());
		}
	}

	protected Class<?> getEntity(Type[] types) {
		Class<?> entityClass = null;
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
			} else if (type instanceof Class) {
				entityClass = getEntity(((Class<?>) type).getGenericInterfaces());
			}
		}
		return entityClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 创建DetachedCriteria实例
	 */
	@Override
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}
	
	//添加查询条件
	public void addRestriction(Criteria criteria, Map<String, Object> map){
		for (Entry<String, Object> entry : map.entrySet()) {
			String propertyName = entry.getKey();
			Object value = entry.getValue();
			addRestriction(criteria, propertyName, value);
		}
	}
	
	//添加查询条件
	public void addRestriction(Criteria criteria, String propertyNames, Object value){
		int lastIndex  = propertyNames.lastIndexOf(".");
		if (lastIndex!=-1) {
			String alias = propertyNames.substring(0, lastIndex);
			String last = propertyNames.substring(lastIndex);
			propertyNames = alias.replaceAll("\\.", "_") + last;
		}
		criteria.add(Restrictions.eq(propertyNames, value));
	}
	
	//创建别名
	public void createAlias(Criteria criteria, Map<String, Object> map) {
		for (Entry<String, Object> entry : map.entrySet()) {
			String propertyName = entry.getKey();
			createAlias(criteria, propertyName);
		}
	}
	
	//创建别名
	public void createAlias(Criteria criteria, String propertyNames) {
		int lastIndex  = propertyNames.lastIndexOf(".");
		if (lastIndex != -1) {
			String aliasPrefix = propertyNames.substring(0, lastIndex);
			String[] propertyNameArray = aliasPrefix.split("\\.");

			StringBuilder sb = new StringBuilder();
			for (String propertyName : propertyNameArray) {
				if (sb.length() > 0) {
					sb.append(".");
				}
				sb.append(propertyName);
				
				String associationPath = sb.toString();
				String alias = associationPath.replaceAll("\\.", "_");
				//判断别名对否存在
				boolean aliasExits = existAlias(criteria, alias);
				if(!aliasExits){
					criteria.createAlias(associationPath, alias);
					System.out.println(associationPath + " , " + alias);
				}
			}
		}
	}
	
	/**
	 * 判断别名是否存在[http://ju.outofmemory.cn/entry/156068]
	 * 
	 * @param criteria
	 * @param alias
	 * @return
	 */
	private boolean existAlias(Criteria criteria, String alias) {
		Iterator<Subcriteria> it = ((CriteriaImpl) criteria).iterateSubcriteria();
		while (it.hasNext()) {
			Subcriteria sub = (Subcriteria) it.next();
			if (alias.equals(sub.getAlias())) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	@Transactional
	public int createSql(String sql) {
		 return getSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override
	@Transactional
	public <T>T prepareCall(String callString, CallableStatementCallback<T> action) {
		return getJdbcTemplate().execute(callString,action);
	}
	
	/** ------------------------------------- IOC -------------------------------------	 **/

	private SessionFactory sessionFactory;

	/**
	 * 当Service实例化的时候调用该方法注入setSessionFactory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("************* setSessionFactory 注入 ***************");
	}
	
	
	protected JdbcTemplate jdbcTemplate = null;

	public JdbcTemplate getJdbcTemplate() {
		  return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		System.out.println("************* setJdbcTemplate 注入 ***************");
		this.jdbcTemplate = jdbcTemplate;
	}
}
