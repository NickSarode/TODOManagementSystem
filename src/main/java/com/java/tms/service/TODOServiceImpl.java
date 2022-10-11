package com.java.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.tms.model.TODO;
import com.java.tms.repository.TODORepository;

@Service
public class TODOServiceImpl implements TODOService{
	
	private TODORepository todoRepository;
	
	
	
	public TODOServiceImpl(TODORepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}


	public TODO saveTODOTask(TODO todo) {
		return todoRepository.save(todo);
	}


	@Override
	public TODO saveTODO(TODO todo) {
		return todoRepository.save(todo);
	}


	@Override
	public List<TODO> getAllTODOs() {
		
		return todoRepository.findAll();
	}


	@Override
	public TODO getTODObyId(Long id) {
		return todoRepository.findById(id).get();
	}


	@Override
	public void deteleTODObyId(Long id) {
		 todoRepository.deleteById(id);
		
	}


	@Override
	public TODO updateTODO(TODO todo) {
		
		return todoRepository.save(todo);
	}
}
