package com.sportyshoe.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.bean.User;
import com.sportyshoe.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	UserRepository ur;
	
	public String checkUser(User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = user.getEmailId();
		String pwd = user.getPwd();
		
		Optional<User> dbUser = ur.findById(email);
		if (dbUser.isPresent()) {
			String dbEmail = dbUser.get().getEmailId();
			String dbPassword = dbUser.get().getPwd();
			String dbRole = dbUser.get().getUserRole();
		
			if (dbEmail.matches(email) && dbPassword.matches(pwd)) {
				session.setAttribute("user_id", dbEmail);
				session.setAttribute("user_role", dbRole);
				return dbRole;}
			else {
				return "failure";}
		} else {
			return "failure";
		}
	}
	
	
	public String signUp(User ll) {
		Optional<User> user = ur.findById(ll.getEmailId());
		
		if (user.isPresent()) {
			return "User already exits";
		}else {
			ur.save(ll);
			return "User account created.";
		}
	}
	


}
