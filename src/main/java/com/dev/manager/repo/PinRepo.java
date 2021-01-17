package com.dev.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.manager.entity.PinMaster;
import com.dev.manager.entity.UserMaster;

@Repository
public interface PinRepo extends JpaRepository<PinMaster, Integer> {

	PinMaster findByUser(UserMaster user);

}
