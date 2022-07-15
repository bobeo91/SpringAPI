package com.vti.entity;

import java.io.Serializable;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Department", catalog = "testing_system_1")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "DepartmentID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "departmentName")
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<Account> accounts;
	

	
}
