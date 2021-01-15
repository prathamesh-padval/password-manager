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
	public String addRecord(Input input, String requestType) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), requestType, "Performing Operations");

		logger.info(LoggerMsgSequence.getMsg(logParams));

		UserMaster master = dao.fetchUser(input.getUserName(), requestType);

		if (null != master) {

			PwdEntries entry = new PwdEntries();

			entry.setUser(master);
			entry.setSiteId(encryptor.encrypt(input.getSiteId()));
			entry.setSiteName(encryptor.encrypt(input.getSiteName()));
			entry.setSitePwd(encryptor.encrypt(input.getSitePwd()));

			PwdEntries dbEntry = dao.fetchEntry(entry, requestType);

			if (dbEntry != null) {
				logParams.setMsg("Record is present in DB, updating record");
				logger.info(LoggerMsgSequence.getMsg(logParams));

				entry.setPwdId(dbEntry.getPwdId());
				return dao.addEntry(entry, requestType);
			} else {

				logParams.setMsg("Record is not present in DB, adding record");
				logger.info(LoggerMsgSequence.getMsg(logParams));
				return dao.addEntry(entry, requestType);
			}

		}

		return "Failed";
	}

	@Override
	public List<PwdEntries> fetchRecords(Input input, String requestType) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), requestType, "Performing Operations");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		UserMaster master = dao.fetchUser(input.getUserName(), requestType);

		if (null != master) {

			List<PwdEntries> dbEntries = dao.fetchEntries(master, requestType);

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
