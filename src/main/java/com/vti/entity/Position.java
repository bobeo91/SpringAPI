package com.vti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vti.converter.PositionEnumConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`Position`", catalog = "testing_system_1")
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "PositionID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "PositionName")
	@Convert(converter = PositionEnumConverter.class)
	private PositionName positionName;

	
	@Override
	public String toString() {
		return "Position [id=" + id + ", positionName=" + positionName+ "]";
	}

	



}
