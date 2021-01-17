package com.dev.manager.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.manager.dao.PwdDao;
import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;
import com.dev.manager.service.UserService;
import com.dev.manager.util.EncryptionUtil;
import com.dev.manager.util.LoggerMsgSequence;
import com.dev.manager.util.LoggingParams;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PwdDao dao;

	@Autowired
	EncryptionUtil encryptor;

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public boolean validateCreds(Input input, String requestType) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), requestType, "Performing Operations");

		logger.info(LoggerMsgSequence.getMsg(logParams));
		UserMaster master = dao.validate(input, requestType);
		if (master != null) {
			logParams.setMsg("Validation Success");
			logger.info(LoggerMsgSequence.getMsg(logParams));

			return true;
		} else {
			logParams.setMsg("Validation Failure");
			logger.error(LoggerMsgSequence.getMsg(logParams));

			return false;
		}

	}

	@Override
	public String register(Input input, String requestType) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), requestType, "Performing Operations");

		logger.info(LoggerMsgSequence.getMsg(logParams));

		String userPin = input.getUserPin().length() == 4 ? "00" + input.getUserPin() : input.getUserPin();

		UserMaster master = new UserMaster();

		master.setUserName(input.getUserName());
		master.setUserPass(encryptor.encrypt(input.getUserPass()));
		master.setEmailId(encryptor.encrypt(input.getEmailId()));
		master.setFirstName(encryptor.encrypt(input.getFirstName()));
		master.setLastName(encryptor.encrypt(input.getLastName()));

		return dao.registerUser(master, requestType, userPin);
	}
}
