package com.java.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.java.tms.config.TMSUserDetails;
import com.java.tms.model.User;
import com.java.tms.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserName :"+username);
		List<User> userall = userRepository.findAll();
		for(int i=0;i<userall.size();i++) {
			System.out.println("user :"+ userall.get(i));
		}
		User user = userRepository.getUserByUserName(username);
		System.out.println("user :"+user);
		if(user==null) {
			throw new UsernameNotFoundException("Unable to Find User!!");
		}
		TMSUserDetails tmsUserDetails = new TMSUserDetails(user);
		return tmsUserDetails;
	}

}
