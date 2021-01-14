package com.dev.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.manager.entity.PwdEntries;
import com.dev.manager.model.Input;
import com.dev.manager.service.EntriesService;

@RestController
@RequestMapping("/api")
public class PwdRecordsController {

	@Autowired
	EntriesService service;

	@PostMapping(value = "/add-entry")
	public ResponseEntity<?> addEntry(Input input) {
		try {
			String resp = service.addRecord(input);
			System.out.println(resp);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/fetch-entries")
	public ResponseEntity<?> fetchEntries(Input input) {
		try {
			List<PwdEntries> resp = service.fetchRecords(input);
			System.out.println(resp);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
