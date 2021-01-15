package com.dev.manager.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.manager.entity.PwdEntries;
import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;
import com.dev.manager.repo.PwdEntriesRepo;
import com.dev.manager.repo.UserRepo;
import com.dev.manager.util.AppConstants;
import com.dev.manager.util.EncryptionUtil;
import com.dev.manager.util.LoggerMsgSequence;
import com.dev.manager.util.LoggingParams;

@Service
public class PwdDao {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PwdEntriesRepo entryRepo;

	@Autowired
	EncryptionUtil encryptor;

	private static final Logger logger = LogManager.getLogger(PwdDao.class);

	public UserMaster validate(Input input, String requestType) {
		LoggingParams params = new LoggingParams(input.getUserName(), requestType, "Validating Credentials");
		logger.info(LoggerMsgSequence.getMsg(params));
		return userRepo.validate(input.getUserName(), encryptor.encrypt(input.getPassword()));
	}

	public UserMaster fetchUser(String userName, String requestType) {
		LoggingParams params = new LoggingParams(userName, requestType, "Fetching user based on userName");
		logger.info(LoggerMsgSequence.getMsg(params));
		return userRepo.findByUserName(userName);
	}

	public String addEntry(PwdEntries entry, String requestType) {
		LoggingParams params = new LoggingParams(entry.getUser().getUserName(), requestType, "");
		try {
			params.setMsg("Making insert call in DB");
			logger.info(LoggerMsgSequence.getMsg(params));
			PwdEntries entryCheck = entryRepo.save(entry);

			if (entryCheck != null) {
				params.setMsg("Record added in DB");
				logger.info(LoggerMsgSequence.getMsg(params));
				return AppConstants.ADD_RECORD_SUCCESS;
			} else {
				params.setMsg("Unable to add record in DB");
				logger.error(LoggerMsgSequence.getMsg(params));
				return AppConstants.ADD_RECORD_FAILURE;
			}
		} catch (Exception e) {
			params.setMsg("Some error ocurred while adding record. " + e);
			logger.error(LoggerMsgSequence.getMsg(params));

			return null;
		}

	}

	public List<PwdEntries> fetchEntries(UserMaster master, String requestType) {
		LoggingParams params = new LoggingParams(master.getUserName(), requestType, "Finding Records");
		logger.info(LoggerMsgSequence.getMsg(params));
		return entryRepo.findByUser(master);
	}

	public PwdEntries fetchEntry(PwdEntries entry, String requestType) {
		LoggingParams params = new LoggingParams(entry.getUser().getUserName(), requestType, "Finding Record");
		logger.info(LoggerMsgSequence.getMsg(params));

		PwdEntries fetchedEntry = entryRepo.fetchEntry(entry.getUser(), entry.getSiteName());

		return fetchedEntry != null ? fetchedEntry : null;

	}

	public String registerUser(UserMaster input, String requestType) {
		LoggingParams params = new LoggingParams(input.getUserName(), requestType, "Registering User");
		logger.info(LoggerMsgSequence.getMsg(params));

		try {
			UserMaster dbUser = userRepo.save(input);

			if (null != dbUser) {
				params.setMsg("User added in DB");
				logger.info(LoggerMsgSequence.getMsg(params));
				return AppConstants.ADD_USER_SUCCESS;
			} else {
				params.setMsg("Unable to add user in DB");
				logger.error(LoggerMsgSequence.getMsg(params));
				return AppConstants.ADD_USER_FAILURE;
			}

		} catch (Exception e) {
			params.setMsg("Some error ocurred while adding user. " + e);
			logger.error(LoggerMsgSequence.getMsg(params));

			return null;
		}
	}

}
