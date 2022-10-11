package com.java.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.tms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u where u.userName = :userName")
	public User getUserByUserName(@Param("userName") String userName);
}
