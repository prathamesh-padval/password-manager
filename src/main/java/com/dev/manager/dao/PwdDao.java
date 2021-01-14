package com.dev.manager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.manager.entity.PwdEntries;
import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;
import com.dev.manager.repo.PwdEntriesRepo;
import com.dev.manager.repo.UserRepo;
import com.dev.manager.util.EncryptionUtil;

@Service
public class PwdDao {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PwdEntriesRepo entryRepo;

	@Autowired
	EncryptionUtil encryptor;

	public UserMaster validate(Input input) {
		return userRepo.validate(input.getUsername(), encryptor.encrypt(input.getPassword()));
	}

	public UserMaster fetchUser(String userName) {
		return userRepo.findByUserName(userName);
	}

	public String addEntry(PwdEntries entry) {
		PwdEntries entryCheck = entryRepo.save(entry);

		return entryCheck != null ? "Success" : "Failed";
	}

	public List<PwdEntries> fetchEntries(UserMaster master) {
		return entryRepo.findByUser(master);
	}

	public String registerUser(UserMaster input) {

		UserMaster dbUser = userRepo.save(input);

		return null != dbUser ? "Success" : "Failed";
	}

}
