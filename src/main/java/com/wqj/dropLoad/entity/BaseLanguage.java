package com.wqj.dropLoad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wqj.common.orm.entity.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "fornow_base_language")
public class BaseLanguage extends BaseEntity {
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "language_id")
	private Long languageId;
	@Column(name = "language_name")
	private String languageName;
	
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
}

