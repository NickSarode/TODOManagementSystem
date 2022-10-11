package com.java.tms.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.java.tms.model.TODO;

@Service
public interface TODOService {
	
	
	List<TODO> getAllTODOs();
	
	TODO saveTODO(TODO todo);
	
	TODO getTODObyId(Long id);
	
	void deteleTODObyId(Long id);
	
	TODO updateTODO(TODO todo);
}
