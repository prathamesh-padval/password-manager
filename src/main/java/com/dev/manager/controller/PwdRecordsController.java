package com.dev.manager.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.manager.entity.PwdEntries;
import com.dev.manager.model.Input;
import com.dev.manager.service.EntriesService;
import com.dev.manager.util.AppConstants;
import com.dev.manager.util.LoggerMsgSequence;
import com.dev.manager.util.LoggingParams;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PwdRecordsController {

	@Autowired
	EntriesService service;

	private static final Logger logger = LogManager.getLogger(PwdRecordsController.class);

	@PostMapping(value = "/add-record")
	public ResponseEntity<?> addEntry(@RequestBody Input input) {

		String requestType = AppConstants.ADD_RECORD;
		LoggingParams logParams = new LoggingParams(input.getUserName(), requestType, "Request Landed on Controller");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		try {
			String resp = service.addRecord(input, requestType);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(LoggerMsgSequence.getMsg(logParams), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/fetch-records")
	public ResponseEntity<?> fetchEntries(@RequestBody Input input) {
		String requestType = AppConstants.FETCH_RECORDS;
		LoggingParams logParams = new LoggingParams(input.getUserName(), requestType, "Request Landed on Controller");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		logParams.setMsg(input.getUserName());
		logger.info(LoggerMsgSequence.getMsg(logParams));
		try {
			List<PwdEntries> resp = service.fetchRecords(input, requestType);

			if (resp != null) {
				logParams.setMsg("Returning records");
				logger.info(LoggerMsgSequence.getMsg(logParams));
			}

			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(LoggerMsgSequence.getMsg(logParams), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
