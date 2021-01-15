package com.dev.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.manager.entity.PwdEntries;
import com.dev.manager.entity.UserMaster;

@Repository
public interface PwdEntriesRepo extends JpaRepository<PwdEntries, Integer> {

	List<PwdEntries> findByUser(UserMaster user);

	@Query("SELECT a FROM PwdEntries a WHERE a.user=:user AND a.siteName=:siteName")
	PwdEntries fetchEntry(@Param("user") UserMaster user, @Param("siteName") String siteName);

}
