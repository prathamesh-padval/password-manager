package com.dev.manager.service;

import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;

public interface UserService {

	boolean validateCreds(Input input);

	String register(UserMaster input);

}
