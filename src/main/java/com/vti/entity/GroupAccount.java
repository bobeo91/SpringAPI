package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
//@IdClass(com.vti.entity.GroupAccount2.GrAcPk.class)
@Table(name ="GroupAccount", catalog = "Testing_system_1")
public class GroupAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private GrAcPk id;
//	@Column(name="GroupID")
//	@Id
//	private short groupId;
//	@Column(name="AccountID")
//	@Id
//	private short accountId;
//	
	
	
	
	@Column(name = "JoinDate")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;
	public GroupAccount() {}
	
	public GrAcPk getId() {
		return id;
	}

	public void setId(GrAcPk id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

//	public short getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(short groupId) {
//		this.groupId = groupId;
//	}
//
//	public short getAccountId() {
//		return accountId;
//	}
//
//	public void setAccountId(short accountId) {
//		this.accountId = accountId;
//	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "GroupAccount [id=" + id + ", createDate=" + createDate + "]";
	}

//	@Override
//	public String toString() {
//		return "GroupAccount [groupId=" + groupId + ", accountId=" + accountId + ", createDate=" + createDate + "]";
//	}

	@Embeddable
	public static class GrAcPk implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "GroupID")
		private short groupId;

		@Column(name = "AccountID")
		private short accountId;

		public GrAcPk() {
		}

		public short getGroupId() {
			return groupId;
		}

		public void setGroupId(short groupId) {
			this.groupId = groupId;
		}

		public short getAccountId() {
			return accountId;
		}

		public void setAccountId(short accountId) {
			this.accountId = accountId;
		}

		@Override
		public String toString() {
			return "groupId=" + groupId + ", accountId=" + accountId ;
		}

		
	}

}
