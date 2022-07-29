package com.sportyshoe.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sportyshoe.bean.User;
import com.sportyshoe.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/loginForm")
	public ModelAndView loginForm(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/signIn", method=RequestMethod.POST)
	public ModelAndView signIn(HttpServletRequest req) {
		User ll = new User();
		ll.setEmailId(req.getParameter("email"));
		ll.setPwd(req.getParameter("password"));
		String result = loginService.checkUser(ll, req);
		ModelAndView mav = new ModelAndView();
		
		System.out.println("Value Returned: " + result);
		if (result.matches("failure")) {
			mav.setViewName("login");
		}else if (result.matches("user")){
			mav.setViewName("cartDetails");
		}else if (result.matches("admin")) {
			mav.setViewName("/adminHome");
		}else {
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping(value="signUp", method=RequestMethod.POST)
	public ModelAndView signUp(HttpServletRequest req) {
		Date date = new Date();
		
		User ll = new User();
		ll.setEmailId(req.getParameter("email"));
		ll.setPwd(req.getParameter("password"));
		ll.setName(req.getParameter("name"));
		ll.setAddress(req.getParameter("address"));
		ll.setUserRole("user");
		ll.setDateAdded(date);
		
		
		String result = loginService.signUp(ll);
		ModelAndView mav = new ModelAndView();
		
		System.out.println(result);
		mav.setViewName("login");
		mav.addObject("msg", result);
		
		return mav;
	}
	
	@RequestMapping(value = "/signupForm")
	public ModelAndView signupForm(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signupForm");
		return mav;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

}
