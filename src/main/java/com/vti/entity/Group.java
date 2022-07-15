package com.vti.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "`group`", catalog = "testing_system_1")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "GroupID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "GroupName")
	private String name;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
//	@CreationTimestamp
	private Date createDate;
	
	@PrePersist
	public void prePersist() {
		if (createDate==null) {
			createDate = new Date();
			// co the con nhieu dieu kien khac nua		
		}
	}
//	@ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
//	private List<Account> accounts;

	

	public Group(String name, String sdate) {
		this.name=name;
		try {
			this.createDate= new SimpleDateFormat("yyyy-MM-DDDD").parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
//	public List<Account> getAccounts() {
//		return accounts;
//	}
//
//	public void setAccounts(List<Account> accounts) {
//		this.accounts = accounts;
//	}
	
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", createDate=" + createDate + "]";
	}
	
}
