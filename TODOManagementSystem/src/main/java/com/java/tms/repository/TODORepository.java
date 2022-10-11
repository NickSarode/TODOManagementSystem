package com.java.tms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.java.tms.model.TODO;

@Repository
public interface TODORepository extends JpaRepository<TODO, Long>{
	
	}
