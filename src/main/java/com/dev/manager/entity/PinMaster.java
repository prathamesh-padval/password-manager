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

@Entity
@Table(name = "pin_master")
public class PinMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pin_id" , length = 6)
	private Integer pinId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserMaster user;

	@Column(name = "user_pin")
	private String userPin;

	public Integer getPinId() {
		return pinId;
	}

	public void setPinId(Integer pinId) {
		this.pinId = pinId;
	}

	public UserMaster getUser() {
		return user;
	}

	public void setUser(UserMaster user) {
		this.user = user;
	}

	public String getUserPin() {
		return userPin;
	}

	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}

}
