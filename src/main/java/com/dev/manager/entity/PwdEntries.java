package com.dev.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pwd_entries")
public class PwdEntries {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pwd_id")
	private Integer pwdId;

	@Column(name = "site_name")
	private String siteName;

	@Column(name = "site_user_name")
	private String siteUserName;

	@Column(name = "site_pwd")
	private String sitePwd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private UserMaster user;

	public Integer getPwdId() {
		return pwdId;
	}

	public void setPwdId(Integer pwdId) {
		this.pwdId = pwdId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteUserName() {
		return siteUserName;
	}

	public void setSiteUserName(String siteUserName) {
		this.siteUserName = siteUserName;
	}

	public String getSitePwd() {
		return sitePwd;
	}

	public void setSitePwd(String sitePwd) {
		this.sitePwd = sitePwd;
	}

	public UserMaster getUser() {
		return user;
	}

	public void setUser(UserMaster user) {
		this.user = user;
	}

}
