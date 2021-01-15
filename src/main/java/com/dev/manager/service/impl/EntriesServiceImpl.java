package com.dev.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.manager.dao.PwdDao;
import com.dev.manager.entity.PwdEntries;
import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;
import com.dev.manager.service.EntriesService;
import com.dev.manager.util.AppConstants;
import com.dev.manager.util.EncryptionUtil;
import com.dev.manager.util.LoggerMsgSequence;
import com.dev.manager.util.LoggingParams;

@Service
public class EntriesServiceImpl implements EntriesService {

	@Autowired
	PwdDao dao;

	@Autowired
	EncryptionUtil encryptor;

	private static final Logger logger = LogManager.getLogger(EntriesServiceImpl.class);

	@Override
	public String addRecord(Input input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), AppConstants.ADD_RECORD,
				"Performing Operations");

		logger.info(LoggerMsgSequence.getMsg(logParams));

		UserMaster master = dao.fetchUser(input.getUserName());

		if (null != master) {

			PwdEntries entry = new PwdEntries();

			entry.setUser(master);
			entry.setSiteId(encryptor.encrypt(input.getSiteId()));
			entry.setSiteName(encryptor.encrypt(input.getSiteName()));
			entry.setSitePwd(encryptor.encrypt(input.getSitePwd()));

			return dao.addEntry(entry);
		}

		return "Failed";
	}

	@Override
	public List<PwdEntries> fetchRecords(Input input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), AppConstants.FETCH_RECORDS,
				"Performing Operations");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		UserMaster master = dao.fetchUser(input.getUserName());

		if (null != master) {

			List<PwdEntries> dbEntries = dao.fetchEntries(master);

			List<PwdEntries> entries = new ArrayList<PwdEntries>();

			dbEntries.forEach(p -> {
				PwdEntries entry = new PwdEntries();
				entry.setPwdId(p.getPwdId());
				entry.setSiteId(encryptor.decrypt(p.getSiteId()));
				entry.setSiteName(encryptor.decrypt(p.getSiteName()));
				entry.setSitePwd(encryptor.decrypt(p.getSitePwd()));

				entries.add(entry);
			});

			return entries;
		}
		return null;
	}
}
