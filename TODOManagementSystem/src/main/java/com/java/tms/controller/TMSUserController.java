package com.java.tms.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.tms.model.TODO;
import com.java.tms.model.User;
import com.java.tms.repository.TODORepository;
import com.java.tms.repository.UserRepository;
import com.java.tms.service.TODOService;



@Controller
@RequestMapping("/TMS")
public class TMSUserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TODORepository todoRepository;
	@Autowired
	private TODOService todoService;
	
	@RequestMapping("/index")
	public String dashBoard(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("UserName "+userName);
		User user = this.userRepository.getUserByUserName(userName);
		System.out.println("user "+user);
		System.out.println("user id "+user.getId());
		model.addAttribute("user",user);
		System.out.println("==================++++++++++Before getAllTODOs method++++++++++++++==============");
		List<TODO> todoList = todoService.getAllTODOs();
		List<TODO> todoObjList = new ArrayList<>();
		for(int i=0;i<todoList.size();i++) {
			TODO todoObj = todoList.get(i);
			System.out.println(todoObj.getUserName());
			if(todoObj.getUserName() == user.getUserName()) {
				System.out.println("Entered in todoObjList");
				todoObjList.add(todoObj);
			}
		}
		if(todoObjList!= null) {
			model.addAttribute("TODOList", todoObjList);
		}else {
			model.addAttribute("TODOList", new TODO());
		}
		
		return "TODOTask";
	}
	
	@GetMapping("/newTask")
	public String newTask(Model model, Principal principal) {
		try {
			System.out.println("!!!!!====INSIDE newTask =========!!!!!!!!!");
			TODO todo = new TODO();
			String userName = principal.getName();
			System.out.println("UserName "+userName);
			User user = this.userRepository.getUserByUserName(userName);
			model.addAttribute("TODO",todo);
			model.addAttribute("user",user);
			System.out.println("!!!!!====END newTask =========!!!!!!!!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AddTask";
		
	}
	
	@PostMapping("/addTask")
	public String AddNewTask(@ModelAttribute("TODO") TODO todo,Model model, Principal principal) {
		System.out.println("!!!!!====INSIDE addNewTask =========!!!!!!!!!");
		try {
			String userName = principal.getName();
			User user = this.userRepository.getUserByUserName(userName);
			todo.setTaskStatus(false);
			todo.setUserName(user.getUserName());
//			System.out.println(todo.getDescription()+ ' '+ todo.getDate()+ ' ' +
//					todo.getTaskName() + ' ' +todo.gettId()+ ' '+todo.getUserName());
			System.out.println("LocalDateTime : "+LocalDateTime.now());
			todo.setLastUpdateddate(LocalDateTime.now());
			todoRepository.save(todo);
			model.addAttribute("user",user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		return "redirect:/TMS/index";
	}
	
	@GetMapping("/updateTODOTask/{tId}")
	public String updateTask(@PathVariable Long tId, Model model,Principal principal) {
		try {
			String userName = principal.getName();
			User user = this.userRepository.getUserByUserName(userName);
			model.addAttribute("user",user);
			model.addAttribute("TODO", todoService.getTODObyId(tId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "UpdateTODOTask";
	}
	
	@PostMapping("/updateTODOTask/{tId}")
	public String UpdateTodoTask(@PathVariable Long tId, @ModelAttribute("TODO") TODO todo, Model model, Principal 
			principal) {
		try {
			String userName = principal.getName();
			User user = this.userRepository.getUserByUserName(userName);
			model.addAttribute("user",user);
			System.out.println("taskStatus : "+todo.isTaskStatus());
			TODO existingTask = todoService.getTODObyId(tId);
			existingTask.settId(tId);
			existingTask.setTaskName(todo.getTaskName());
			existingTask.setDate(todo.getDate());
			existingTask.setDescription(todo.getDescription());
			existingTask.setLastUpdateddate(LocalDateTime.now());
			existingTask.setTaskStatus(todo.isTaskStatus());
			todoService.updateTODO(existingTask);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/TMS/index";
	}
	
	@GetMapping("/deleteTask/{tId}")
	public String deleteTask(@PathVariable Long tId,Model model, Principal principal) {
		try {
			String userName = principal.getName();
			User user = this.userRepository.getUserByUserName(userName);
			model.addAttribute("user",user);
			todoService.deteleTODObyId(tId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/TMS/index";
	}
}
