package com.dev.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.manager.entity.PwdEntries;
import com.dev.manager.entity.UserMaster;

@Repository
public interface PwdEntriesRepo extends JpaRepository<PwdEntries, Integer> {

	List<PwdEntries> findByUser(UserMaster user);
	
}
