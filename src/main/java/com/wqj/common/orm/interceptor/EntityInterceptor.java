package com.wqj.common.orm.interceptor;

import java.io.Serializable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.joda.time.DateTime;

import com.wqj.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@SuppressWarnings("serial")
public class EntityInterceptor extends EmptyInterceptor {

	/**
	 * 如果一个对象在flush执行时被发现是脏数据，则这个函数会被调用。
	 * 可以给currentState数组赋值，这个赋值操作，即会修改持久化类对象的属性值，也会修改数据库表对应的列值。
	 * 返回 true 说明在方法体中修改了对象的属性值currentState，false说明没有修改其当前属性值。
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames,
			Type[] types) {
//		System.out.println("(*****************  onFlushDirty   ************************");
		boolean modified = false;
		if (entity instanceof BaseEntity) {
			BaseEntity BaseEntity = (BaseEntity) entity;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("lastModifyDate".equals(propertyNames[i])) {
					long  lastModifyDateTime = System.currentTimeMillis();
					currentState[i] = lastModifyDateTime;
					BaseEntity.setLastModifyDate(lastModifyDateTime);
					modified = true;
				}
				if ("lastModifyDateTime".equals(propertyNames[i])) {
					DateTime lastModifyDateTime = new DateTime();
					currentState[i] = lastModifyDateTime;
					BaseEntity.setLastModifyDateTime(lastModifyDateTime);
					modified = true;
				}
			}
		}
		return modified;
	}

	/**
	 * 在一个对象被保存之前被调用。如果给state赋值，这个值能够使用insert语句保存到数据库表中，同时也更改持久化类对象的属性值。
	 * entity：表示被操作的实体对象，
	 * id：表示类对象的主键，
	 * propertyNames：表示被操作对象的属性名数组，
	 * types：表示对应属性的类型，
	 * state：表示对应属性的属性值（currentState是当前需要被更新的值，previousState是更新前的值）。
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		System.out.println("(*****************  onSave   ************************");
		boolean modified = false;
		//判断entity是否是BaseEntity类或者子类的对象
		if (entity instanceof BaseEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createDate".equals(propertyNames[i])) {
					long createDateTime = System.currentTimeMillis();
					state[i] = createDateTime;
					modified = true;
				} else if ("lastModifyDate".equals(propertyNames[i])) {
					long  lastModifyDateTime = System.currentTimeMillis();
					state[i] = lastModifyDateTime;
					modified = true;
				}else if ("createDateTime".equals(propertyNames[i])) {
					DateTime createDateTime = new DateTime();
					state[i] = createDateTime;
					modified = true;
				} else if ("lastModifyDateTime".equals(propertyNames[i])) {
					DateTime lastModifyDateTime = new DateTime();
					state[i] = lastModifyDateTime;
					modified = true;
				}
			}
		}
		return modified;
	}

}
