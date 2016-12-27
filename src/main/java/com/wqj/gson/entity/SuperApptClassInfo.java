/**
 * 
 */
package com.wqj.gson.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.wqj.common.orm.entity.BaseEntity;

/**
 * 约见班型中间表
 * @author yangliwei
 * @date 2016-10-11 下午2:15:17
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "super_appt_class")
public class SuperApptClassInfo extends BaseEntity {
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "appt_class_id", columnDefinition = "bigint")
	private Long apptClassId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "class_id")
	private ClassTypeInfo classType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appt_id")
	private SuperAppointmentInfo appointment;

	public Long getApptClassId() {
		return apptClassId;
	}

	public void setApptClassId(Long apptClassId) {
		this.apptClassId = apptClassId;
	}

	public ClassTypeInfo getClassType() {
		return classType;
	}

	public void setClassType(ClassTypeInfo classType) {
		this.classType = classType;
	}

	public SuperAppointmentInfo getAppointment() {
		return appointment;
	}

	public void setAppointment(SuperAppointmentInfo appointment) {
		this.appointment = appointment;
	}
}
