package com.vti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hoang
 *
 */
@Entity
@Table(name = "CategoryQuestion", catalog = "Testing_system_1")
public class CategoryQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "CategoryID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;
	@Column(name = "CategoryName", nullable = true)
	@Enumerated(EnumType.STRING)
	private CategoryName enumName;

	public enum CategoryName {
		Java, SQL, NET, Ruby, Python, NodeJS, HTML, CSS, JavaScript;
	}

	public CategoryQuestion() {
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public CategoryName getEnumName() {
		return enumName;
	}

	public void setEnumName(CategoryName enumName) {
		this.enumName = enumName;
	}

	@Override
	public String toString() {
		return "CategoryQuestion [id=" + id + ", enumName=" + enumName + "]";
	}

	

}
