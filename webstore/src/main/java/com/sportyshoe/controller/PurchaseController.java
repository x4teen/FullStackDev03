package com.sportyshoe.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoe.bean.Purchase;
import com.sportyshoe.service.PurchaseService;
 
@Controller
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@RequestMapping(value = "/completePurchase", method = RequestMethod.GET)
	    public String memberpurchases(ModelMap map, HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  return "login";
		  }
		  String userId = (String) session.getAttribute("user_id");
		  
		  String result = purchaseService.completePurchase(userId, request);
		  request.setAttribute("message", result);
		  
	       return "receipt"; 
	    }		  

	@RequestMapping(value = "/purchaseToday")
		public String purchaseToday(HttpServletRequest request) {
			Date date = new Date();
			System.out.println("Inside purchase ");
			List<Purchase> purchaseList = new ArrayList<Purchase>();
			purchaseList = purchaseService.findPurchaseByDate(date, request);
			request.setAttribute("purchaseList", purchaseList);
			return "displayPurchase";
		}


}
