package com.sportyshoe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.bean.CartItem;
import com.sportyshoe.bean.Purchase;
import com.sportyshoe.bean.PurchaseItem;
import com.sportyshoe.repository.PurchaseItemRepository;
import com.sportyshoe.repository.PurchaseRepository;

@Service
public class PurchaseService {
	@Autowired
	PurchaseRepository pr;
	
	@Autowired
	PurchaseItemRepository ps;
	
	
	@SuppressWarnings("unchecked")
	public String completePurchase(String userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Date date = new Date();
		
		List<CartItem> cartItems = new ArrayList<CartItem>();
		  if (session.getAttribute("cartItems") != null)
			  cartItems = (List<CartItem>) session.getAttribute("cartItems");
		  
		  double total = (Double) session.getAttribute("cartTotal");
		  
		  Purchase purchase = new Purchase();
		  
		  purchase.setDate(date);
		  purchase.setEmailId(userId);
		  purchase.setTotal(total);
		  Purchase completedPurchase = pr.save(purchase);
		  int purchaseId = completedPurchase.getId();
		  session.setAttribute("email", userId);
		  session.setAttribute("purchaseId", purchaseId);
		  session.setAttribute("date", date.toString());
		  
		  
		  List<PurchaseItem> purchaseDetails = new ArrayList<PurchaseItem>();
		  
		  for (CartItem item: cartItems) {
			  PurchaseItem pi = new PurchaseItem();
			  pi.setPurchaseId(purchaseId);
			  pi.setUserEmail(userId);
			  pi.setProductId(item.getProductId());
			  pi.setProductName(item.getProductName());
			  pi.setRate(item.getRate());
			  pi.setQty(item.getQty());
			  pi.setPrice(item.getPrice());
			  purchaseDetails.add(ps.save(pi));
		  }
		  
		  session.setAttribute("purchaseDetails", purchaseDetails);
		  session.setAttribute("cartItems", null);
		  session.setAttribute("cartTotal", 0);
		  session.setAttribute("purchaseTotal", total);
		  
		return "Purchase Confirmed";
	}
	
	public List<Purchase> findPurchaseByDate(Date date, HttpServletRequest request){
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		purchaseList = pr.findAll();
		
		return purchaseList;
		
	}

}