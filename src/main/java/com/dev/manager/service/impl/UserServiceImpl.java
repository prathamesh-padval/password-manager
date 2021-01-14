package com.dev.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.manager.dao.PwdDao;
import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;
import com.dev.manager.service.UserService;
import com.dev.manager.util.EncryptionUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PwdDao dao;

	@Autowired
	EncryptionUtil encryptor;

	@Override
	public boolean validateCreds(Input input) {
		UserMaster master = dao.validate(input);
		return master != null ? true : false;
	}

	@Override
	public String register(UserMaster input) {
		input.setUserPass(encryptor.encrypt(input.getUserPass()));
		input.setEmailId(encryptor.encrypt(input.getEmailId()));
		input.setFirstName(encryptor.encrypt(input.getFirstName()));
		input.setLastName(encryptor.encrypt(input.getLastName()));

		return dao.registerUser(input);
	}
}
