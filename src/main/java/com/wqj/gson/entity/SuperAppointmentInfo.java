package com.wqj.gson.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.google.common.collect.Lists;
import com.wqj.common.annotation.JsonCycleFilter;
import com.wqj.common.orm.entity.BaseEntity;

/**
 * 约见表
 * @author yangliwei
 * @date 2016-10-11 下午2:15:17
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "super_appointment")
public class SuperAppointmentInfo extends BaseEntity{
	
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "appt_id", columnDefinition = "bigint")
	private Long apptId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private Students students;

	// 签约班型
	@JsonCycleFilter
	@OneToMany(mappedBy="appointment", fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	/**
	 * 这里注意OneToMany默认的加载方式是赖加载。
	 * javax.persistence.CascadeType 属性说明
	 * PERSIST  ： 只有A类新增时，会级联B对象新增。若B对象在数据库存在则抛异常
	 * MERGE    ： 指A类新增或者变化，会级联B对象（新增或者变化）
	 * REMOVE   ：只有A类删除时，会级联删除B类； 
	 * REFRESH  ：级联刷新，当多个用户同时作操作一个实体，为了用户取到的数据是实时的，在用实体中的数据之前就可以调用一下refresh()方法！
	 * DETACH   ：
	 * ALL      ： 包含以上所有级联属性
	 */
	// @Where(clause="DEL_FLAG=1") //代表只取未删除的数据；
	// @OrderBy(clause="CREATED_DATE asc") 
	private List<SuperApptClassInfo> SuperApptClasss = Lists.newArrayList();
	
	public Long getApptId() {
		return apptId;
	}

	public void setApptId(Long apptId) {
		this.apptId = apptId;
	}

	public Students getStudent() {
		return students;
	}

	public void setStudent(Students students) {
		this.students = students;
	}

	public List<SuperApptClassInfo> getSuperApptClasss() {
		return SuperApptClasss;
	}

	public void setSuperApptClasss(List<SuperApptClassInfo> superApptClasss) {
		SuperApptClasss = superApptClasss;
	}
}
