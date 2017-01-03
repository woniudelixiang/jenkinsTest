package com.wqj.common.orm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.wqj.common.bean.Page;
import com.wqj.common.orm.PropertyFilter;
import com.wqj.common.orm.PropertyFilter.MatchType;
import com.wqj.common.orm.entity.BaseEntity;
import com.wqj.common.util.LoggerUtils;
import com.wqj.common.util.ReflectionUtils;
import com.wqj.common.util.ValidateUtils;

public class HibernateDaoSupport<T extends BaseEntity> extends BaseDaoSupport<T> {

	@Override
	public List<T> findPage(Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		return findPage(detachedCriteria, page);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findPage(DetachedCriteria detachedCriteria, Page page) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if (page.isAutoCount()) {
			page.setTotalCount(countCriteriaResult(criteria));
		}
		setPageParameterToCriteria(criteria, page);
		page.setDatas(criteria.list());
		return (List<T>) page.getDatas();
	}

	/**
	 * 设置结果集和排序
	 * 
	 * @param criteria
	 * @param page
	 * @return
	 */
	protected Criteria setPageParameterToCriteria(final Criteria criteria, final Page page) {
		Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");
		criteria.setFirstResult(page.getBeginIndex());
		criteria.setMaxResults(page.getPageSize());
		if (page.isOrderBySetted()) {
			if (Page.ASC.equals(page.getOrder())) {
				criteria.addOrder(Order.asc(page.getOrderBy()));
			} else {
				criteria.addOrder(Order.desc(page.getOrderBy()));
			}
		}
		return criteria;
	}

	/**
	 * 总记录数
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long countCriteriaResult(final Criteria criteria) {
		CriteriaImpl impl = (CriteriaImpl) criteria;
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List<OrderEntry>) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			LoggerUtils.debug(CLASS_NAME, "countCriteriaResult[{}]", e.getMessage());
		}
		Long totalCountObject = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject : 0;
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			criteria.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			LoggerUtils.debug(CLASS_NAME, "countCriteriaResult setFieldValue [{}]", e.getMessage());
		}
		return totalCount;
	}
	
	@Override
	public List<T> findDatas(String propertyName, Object value, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		String[] array = propertyName.split("\\.");
		String associationPath = array[0];
		String alias = array[0];
		if(array.length>1){
			for(int i=1; i<array.length;i++){
				detachedCriteria.createAlias(associationPath, alias);
				alias = array[i];
				associationPath = array[i-1]+"."+alias;
			}
		}
		detachedCriteria.add(Restrictions.eq(associationPath, value));
		return findPage(detachedCriteria, page);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findPage(final Page page, final List<PropertyFilter> filters) {
		Assert.notNull(page, "page is required.");
		Assert.notNull(filters, "filters is required.");
		
		Criteria criteria = createCriteria(buildCriterionByPropertyFilter(filters));
		createAlias(criteria, filters);
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(criteria);
			page.setTotalCount(totalCount);
		}
		setPageParameterToCriteria(criteria, page);
		page.setDatas(criteria.list());
		return (List<T>) page.getDatas();
	}
	
	protected Criteria createCriteria(final Criterion... criterions) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		for (Criterion criterion : criterions) {
			detachedCriteria.add(criterion);
		}
		return detachedCriteria.getExecutableCriteria(getSession());
	}
	
	protected void createAlias(Criteria criteria, final List<PropertyFilter> filters) {
		for (PropertyFilter filter : filters) {
			for (String propertyName : filter.getPropertyNames()) {
				if (propertyName.indexOf(".") != -1) {
					String aliasPrefix = propertyName.substring(0, propertyName.lastIndexOf("."));
					String[] aliases = aliasPrefix.split("\\.");

					StringBuilder sb = new StringBuilder();
					for (String alias : aliases) {
						if (sb.length() > 0) {
							sb.append(".");
						}
						sb.append(alias);
						criteria.createAlias(sb.toString(), sb.toString().replaceAll("\\.", "_"));
					}
				}
			}
		}
	}

	
	/**
	 * 根据属性的个数组装查询条件
	 * 
	 * @param filters
	 * @return
	 */
	protected Criterion[] buildCriterionByPropertyFilter(final List<PropertyFilter> filters) {
		List<Criterion> criterions = Lists.newArrayList();
		for (PropertyFilter filter : filters) {
			if (filter.hasMultiProperties()) {
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildCriterion(param, filter.getMatchValue(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterions.add(disjunction);
			} else {
				Criterion criterion = buildCriterion(filter.getPropertyName(), filter.getMatchValue(), filter.getMatchType());
				criterions.add(criterion);
			}
		}
		return criterions.toArray(new Criterion[criterions.size()]);
	}
	
	/**
	 * 字符串target在字符串source中出现的次数
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static int findNumber(String source, String target){  
        int number = 0;  
        int i = 0;  
        while((i=source.indexOf(target, i))!=-1) {  
            number++;  
            i++;  
        }
        return number;  
    }  
	
	/**
	 * 判断字符串target在字符串source中出现的次数是否大于number次
	 * 
	 * @param source
	 * @param target
	 * @param number
	 * @return
	 */
	public static boolean isFindMore(String source, String target,int number){  
        int i = 0;  
        while((i=source.indexOf(target, i))!=-1) {  
        	if(--number<0){
        		 return true;  
        	}
            i++;  
        }
        return false;  
    } 
	
	/**
	 * 组装查询条件
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @param matchType
	 * @return
	 */
	protected Criterion buildCriterion(String propertyName, final Object propertyValue, final MatchType matchType) {
		if(isFindMore(propertyName, ".", 1)){
			int lastIndex = propertyName.lastIndexOf(".");
			String alias = propertyName.substring(0, lastIndex);
			String last = propertyName.substring(lastIndex);
			propertyName = alias.replaceAll("\\.", "_") + last;
		}
		
		/*String[] dots = propertyName.split("\\.");
		if (dots.length > 2) {
			String alias = propertyName.substring(0, propertyName.lastIndexOf("."));
			String last = propertyName.substring(propertyName.lastIndexOf("."), propertyName.length());
			propertyName = alias.replaceAll("\\.", "_") + last;
		}*/

		Criterion criterion = null;
		switch (matchType) {
		case EQ:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case LIKE:
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			break;
		case LE:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case LT:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case GE:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case GT:
			criterion = Restrictions.gt(propertyName, propertyValue);
			break;
		case NN:
			criterion = Restrictions.isNotNull(propertyName);
			break;
		case BA:
			if (propertyValue instanceof List) {
				List<?> values = (List<?>) propertyValue;
				if (values.size() == 2) {
					criterion = Restrictions.between(propertyName, values.get(0), values.get(1));
				}
			}else if (propertyValue instanceof Long[]) {
				Long[] values = (Long[]) propertyValue;
				if (values.length == 2) {
					criterion = Restrictions.between(propertyName, values[0], values[1]);
				}
			}
			else{
				System.out.println("------------------BA-------else------------------------------");
			}
			break;
		case IN:
			if(propertyValue instanceof Long){
				criterion = Restrictions.eq(propertyName, propertyValue);
			}else if (propertyValue instanceof List) {
				List<?> values = (List<?>) propertyValue;
				criterion = Restrictions.in(propertyName, values);
			}
			break;
		}
		return criterion;
	}

	@Override
	public List<T> findPage(Map<String, Object> map, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if(map!=null){
			for (Entry<String, Object> entry : map.entrySet()) {
				String propertyName = entry.getKey();
				Object value = entry.getValue();
				if(ValidateUtils.isNotEmpty(value)){
					createAlias(criteria, propertyName);
					addRestriction(criteria, propertyName, value);
				}
			}
		}
		return findPage(detachedCriteria, page);
	}
}
