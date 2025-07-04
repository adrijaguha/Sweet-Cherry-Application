package com.capgemini.sweetcherry.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.sweetcherry.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	@Query("UPDATE UserDetails u set u.password = :newPassword where u.userId =:userId AND u.password =:oldPassword ")
	@Modifying
	@Transactional
	public void updatePassword(@Param("userId") int userId,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);

	@Query("SELECT u FROM UserDetails u where u.email=:userId and u.password=:password")
	public UserDetails login(@Param("userId") String userId,@Param("password") String password);
}