package com.java.tms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.tms.helper.Message;
import com.java.tms.model.User;
import com.java.tms.repository.UserRepository;

@Controller
public class UserRegistrationController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@RequestMapping("/")
	private String home(Model model) {
	model.addAttribute("User", new User());
		return "SignUp";
	}
	
	
	
	@GetMapping("/login")
	public String customLogin(Model model)
	{
		model.addAttribute("title","Login Page");
		return "Login";
	}
	
	
//	@PostMapping("/login")
//	private String diverttoIndex() {
//		return "TODOTask";
//	}
	
	@PostMapping("/signUp")
	private String registerUser(@ModelAttribute("User") User user, Model model, HttpSession session) {
		try {
			System.out.println(user);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole("ROLE_USER");
			User result = this.userRepository.save(user);
			System.out.println(result.getPassword());
			model.addAttribute("User", new User());
			session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("User",user);
			session.setAttribute("message", new Message("Something Went Wrong!! May be user is already registered with US","alert-danger"));
		}
		return "SignUp";
	}
}
