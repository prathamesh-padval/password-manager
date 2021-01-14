package com.dev.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.manager.entity.UserMaster;

@Repository
public interface UserRepo extends JpaRepository<UserMaster, Integer> {

	@Query("SELECT a FROM UserMaster a WHERE a.userName=:userName AND a.userPass=:password")
	UserMaster validate(@Param("userName") String userName, @Param("password") String password);

	public UserMaster findByUserName(String userName);
}
