package com.sportyshoe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoe.bean.Product;
import com.sportyshoe.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService ps;
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String homePage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Product> pList = ps.findAllProducts();
		System.out.println(pList);
		request.setAttribute("pList", pList);
		
		if (session.getAttribute("cartItems") == null) {
			session.setAttribute("cartTotal", 0);
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String addProduct(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_role = "";
		
		if (session.getAttribute("user_role") != null) {
			user_role = (String)session.getAttribute("user_role");
		} else {
			return "login";
		}
		
		if (user_role.matches("admin")) {
			return "addProduct";
		} else {
			return "login";
		}
		
	}
	
	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String adminHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_role = "";
		
		if (session.getAttribute("user_role") != null) {
			user_role = (String)session.getAttribute("user_role");
		} else {
			return "login";
		}
		
		if (user_role.matches("admin")) {
			List<Product> pList = ps.findAllProducts();
			request.setAttribute("pList", pList);
			return "adminHome";
		} else {
			return "login";
		}
		
	}
	
	@RequestMapping(value = "/getProductDetails", method = RequestMethod.GET)
	public String getProductDetails(@RequestParam String id, HttpServletRequest request) {
		int pid = Integer.parseInt(id);
		Product item = ps.findProduct(pid);
		request.setAttribute("item", item);
		System.out.println(item);
		return "productDetails";
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam String id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_role = "";
		
		if (session.getAttribute("user_role") != null) {
			user_role = (String)session.getAttribute("user_role");
		} else {
			return "login";
		}
		
		if (user_role.matches("admin")) {
			int pid = Integer.parseInt(id);
			String result = ps.deleteProduct(pid);
			request.setAttribute("message", result);
			return "adminHome";
		} else {
			return "login";
		}
		
		
	}
	
	
	@RequestMapping(value = "/storeProduct", method = RequestMethod.POST)
	public String storeProduct(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_role = "";
		
		if (session.getAttribute("user_role") != null) {
			user_role = (String)session.getAttribute("user_role");
		} else {
			return "login";
		}
		
		if (user_role.matches("admin")) {
			Product item = new Product();
			item.setId(Integer.parseInt(request.getParameter("id")));
			item.setProductName(request.getParameter("productName"));
			item.setProductManufacturer(request.getParameter("productBrand"));
			item.setProductCategory(request.getParameter("productCategory"));
			item.setProductDescription(request.getParameter("productDescription"));
			
			boolean isFemale = request.getParameter("gender").matches("female");
			System.out.println(request.getParameter("gender") +" : " + isFemale);
			item.setProductGender(isFemale);
			
			item.setProductSize(Double.parseDouble(request.getParameter("productSize")));
			item.setListPrice(Double.parseDouble(request.getParameter("listPrice")));
			
			String result = ps.updateProduct(item);
			request.setAttribute("message", result);
			
			return "adminHome";
		} else {
			return "login";
		}
		
		
		
	}
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProduct(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String user_role = "";
		
		if (session.getAttribute("user_role") != null) {
			user_role = (String)session.getAttribute("user_role");
		} else {
			return "login";
		}
		
		if (user_role.matches("admin")) {
			Product item = new Product();
			item.setId(Integer.parseInt(request.getParameter("id")));
			item.setProductName(request.getParameter("productName"));
			item.setProductManufacturer(request.getParameter("productBrand"));
			item.setProductCategory(request.getParameter("productCategory"));
			item.setProductDescription(request.getParameter("productDescription"));
			
			boolean isFemale = request.getParameter("gender").matches("female");
			item.setProductGender(isFemale);
			
			item.setProductSize(Double.parseDouble(request.getParameter("productSize")));
			item.setListPrice(Double.parseDouble(request.getParameter("listPrice")));
			
			String result = ps.updateProduct(item);
			request.setAttribute("message", result);
			
			return "adminHome";
		} else {
			return "login";
		}
		
		
	}

}
