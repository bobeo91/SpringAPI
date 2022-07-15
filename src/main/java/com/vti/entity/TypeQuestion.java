package com.vti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "TypeQuestion", catalog = "Testing_system_1")
public class TypeQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "TypeID")
	@Id
	@GenericGenerator(name = "typeQuestion-sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "typeQuestion_sequence"),
			@Parameter(name = "initial_value", value = "10"), @Parameter(name = "increment_size", value = "3")

	})
	@GeneratedValue(generator = "typeQuestion-sequence-generator")
	private short id;

	@Column(name = "TypeName")
	private String typeName;

	public TypeQuestion() {
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "TypeQuestion [id=" + id + ", typeName=" + typeName + "]";
	}

}
