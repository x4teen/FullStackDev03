package com.sportyshoe.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoe.bean.CartItem;
import com.sportyshoe.bean.Product;
import com.sportyshoe.service.ProductService;
 
@Controller
public class CartController {

	@Autowired
	private ProductService productService; 

//	@Autowired
//	private PurchaseService purchaseService;
//	
//	@Autowired
//	private PurchaseItemService purchaseItemService; 


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showCart", method = RequestMethod.GET)
	    public String showCart(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before adding items to cart");
		  } else {
			  // if cart is already in session then retrieve it else create a new cart list and 
			  // save it to session
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cartItems") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cartItems");
			  
			  // get total of all cart items
			  double totalValue = getCartValue(cartItems);
			  map.addAttribute("cartTotal", totalValue);
			  map.addAttribute("cartItems", cartItems);
 		  }
		  
		  map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
	        return "cartDetails"; 
	    }
	  
	  	@SuppressWarnings("unchecked")
	  	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	    public String cartAddItem(@RequestParam String id, ModelMap map, HttpServletRequest request) 
	    {
	  			HttpSession session = request.getSession();
	  			int idValue = Integer.parseInt(id);
	  			System.out.println("I am in addToCart");
	  			
			  // if cart is already in session then retrieve it else create a new cart list and 
			  // save it to session
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cartItems") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cartItems");
			  
			  if (isItemInCart(cartItems, idValue)) {
				  map.addAttribute("error", "This item is already in your cart");
			  } else {
				  Product product = productService.findProduct(idValue);
				  CartItem item = new CartItem();
				  
				  item.setProductId(idValue);
				  item.setQty(1);
				  item.setRate(product.getListPrice());
				  double dprice = item.getRate() * item.getQty(); 
				  item.setPrice(dprice); 
				  item.setProductName(product.getProductName()) ;
				  cartItems.add(item);
				  
				  session.setAttribute("cartItems", cartItems);
				  session.setAttribute("cartTotal", getCartValue(cartItems));
			  }	  
	        return "redirect:home"; 
	    }	
	  	
	  @SuppressWarnings("unchecked")
	  @RequestMapping(value = "/cartDeleteItem", method = RequestMethod.GET)
	    public String cartDeleteItem(ModelMap map, javax.servlet.http.HttpServletRequest request, 
	    		@RequestParam(value="id", required=true) String id) 
	    {
		  // check if user is logged in
		  	HttpSession session = request.getSession();
		  	int idValue = Integer.parseInt(id);
			List<CartItem> cartItems = new ArrayList<CartItem>();
			
			if (session.getAttribute("cartItems") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cartItems");
			  	  
			  for(CartItem item: cartItems) {
				  if (item.getProductId() == idValue) {
					  cartItems.remove(item);
					  session.setAttribute("cartItems", cartItems);
					  session.setAttribute("cartTotal", getCartValue(cartItems));
					  break;
				  }
			   }
		  	
	        return "redirect:showCart"; 
	    }	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	    public String checkout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  request.setAttribute("message", "You must login to check out");
			  return "login";
		  } else {
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  double totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);
		  }
		  map.addAttribute("pageTitle", "SPORTY SHOES - CHECKOUT");
	        return "checkOutDetails"; 
	    }

//	  @RequestMapping(value = "/completepurchase", method = RequestMethod.GET)
//	    public String completePurchase(ModelMap map, javax.servlet.http.HttpServletRequest request) 
//	    {
//		  // check if user is logged in
//		  HttpSession session = request.getSession();
//		  if (session.getAttribute("user_id") == null) {
//			  map.addAttribute("error", "Error, You need to login before completing purchase");
//		  } else {
//			  // take items from cart and update the databae 
//			  List<CartItem> cartItems = new ArrayList<CartItem>();
//			  if (session.getAttribute("cart_items") != null)
//				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
//			  BigDecimal totalValue = getCartValue(cartItems);
//			  
//			  long userId = (Long) session.getAttribute("user_id") ;
//			  
//			  Purchase purchase = new Purchase();
//			  purchase.setUserId(userId);
//			  purchase.setDate(Calendar.getInstance().getTime());
//			  purchase.setTotal(totalValue);
//			  long purchaseId = purchaseService.updatePurchase(purchase);
//			  
//			  for(CartItem item: cartItems) {
//				  PurchaseItem pItem = new PurchaseItem();
//				  pItem.setPurchaseId(purchaseId);
//				  pItem.setProductId(item.getProductId());
//				  pItem.setUserId(userId);
//				  pItem.setRate(item.getRate());
//				  pItem.setQty(item.getQty());
//				  pItem.setPrice(item.getPrice());
//				  
//				  purchaseItemService.updateItem(pItem);
//			  }
//			  map.addAttribute("cartValue", totalValue);
//			  map.addAttribute("cartItems", cartItems);
//
//		  }
//		  
//	        return "redirect:confirm";  
//	    }

	  @SuppressWarnings("unchecked")
	@RequestMapping(value = "/gateway", method = RequestMethod.GET)
	    public String gateway(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before making payment");
		  } else {
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cartItems") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cartItems");
			  double totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }
		  
		  map.addAttribute("pageTitle", "SPORTY SHOES - PAYMENT GATEWAY");
	        return "gateway"; 
	    }
	  
	  @SuppressWarnings("unchecked")
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	    public String confirm(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before completing the purchase");
		  } else {
			  // clear items from cart as order has completed 
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cartItems");
			  double totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);

			  
			  cartItems.clear();
			  session.setAttribute("cart_items", null);
		  }
		  map.addAttribute("pageTitle", "SPORTY SHOES - PURCHASE CONFIRMATION");
	        return "confirm"; 
	    }	  
	  
	  /**
	   * Check if an item is already in the cart
	   * @param list
	   * @param item
	   * @return
	   */
	  private boolean isItemInCart(List<CartItem> list, long item) {
		  boolean retVal = false;
		  
		  for(CartItem thisItem: list) {
			  if (item == thisItem.getProductId()) {
				  retVal = true;
				  break;
			  }
		  }
		  return retVal;
	  } 

	  /**
	   * Get total value of items in cart
	   * @param list
	   * @return
	   */
	  private double getCartValue(List<CartItem> list) {
		  double total = 0.0;
		  
		  for(CartItem item: list) {
			  double dprice = item.getRate() * (item.getQty());
			  total += dprice;
		   }
		  return total;
	  }

}
