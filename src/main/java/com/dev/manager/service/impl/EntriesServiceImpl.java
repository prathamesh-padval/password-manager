package com.dev.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.manager.dao.PwdDao;
import com.dev.manager.entity.PwdEntries;
import com.dev.manager.entity.UserMaster;
import com.dev.manager.model.Input;
import com.dev.manager.service.EntriesService;
import com.dev.manager.util.EncryptionUtil;

@Service
public class EntriesServiceImpl implements EntriesService {

	@Autowired
	PwdDao dao;

	@Autowired
	EncryptionUtil encryptor;

	@Override
	public String addRecord(Input input) {

		UserMaster master = dao.fetchUser(input.getUsername());

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
		UserMaster master = dao.fetchUser(input.getUsername());

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
