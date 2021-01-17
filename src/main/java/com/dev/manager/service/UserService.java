package com.dev.manager.service;

import com.dev.manager.model.Input;

public interface UserService {

	boolean validateCreds(Input input, String requestType);

	String register(Input input, String requestType);

}
