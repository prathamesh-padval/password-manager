package com.dev.manager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;
import com.dev.manager.service.UserService;
import com.dev.manager.util.AppConstants;
import com.dev.manager.util.LoggerMsgSequence;
import com.dev.manager.util.LoggingParams;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@PostMapping(value = "/register")
	public ResponseEntity<?> login(@RequestBody UserMaster input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), AppConstants.ADD_USER,
				"Request Landed on Controller");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		try {

			String resp = userService.register(input);
			System.out.println(resp);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(LoggerMsgSequence.getMsg(logParams), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody Input input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), AppConstants.ADD_USER,
				"Request Landed on Controller");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		try {

			boolean resp = userService.validateCreds(input);
			System.out.println(resp);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(LoggerMsgSequence.getMsg(logParams), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
