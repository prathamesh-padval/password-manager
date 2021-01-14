package com.dev.manager.service;

import java.util.List;

import com.dev.manager.entity.PwdEntries;
import com.dev.manager.model.Input;

public interface EntriesService {

	String addRecord(Input input);

	List<PwdEntries> fetchRecords(Input input);
}