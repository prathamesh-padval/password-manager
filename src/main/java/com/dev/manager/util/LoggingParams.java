package com.dev.manager.util;

public class LoggingParams {

	String userName;

	String requestType;

	String msg;

	public LoggingParams() {
	}

	public LoggingParams(String userName, String requestType, String msg) {
		super();
		this.userName = userName;
		this.requestType = requestType;
		this.msg = msg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "LoggingParams [userName=" + userName + ", requestType=" + requestType + ", msg=" + msg + "]";
	}

}
