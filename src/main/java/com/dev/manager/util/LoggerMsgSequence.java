package com.dev.manager.util;

public class LoggerMsgSequence {

	public static String getMsg(LoggingParams logparam) {
		return String.format(getLogFormat(), logparam.getUserName(), logparam.getRequestType(), logparam.getMsg());
	}

	public static String getLogFormat() {
		return "Lp [Uid=%s, Type=%s, Msg=%s] ";
	}

}
