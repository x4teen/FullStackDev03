<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sproty Shoes - Checkout</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<%@ include file="header.html" %>
	
	<c:out value="${message}"></c:out>
	
	<h2>Checkout Details</h2>

<table border="1">
  <tr>
    <th>Name</th>
    <th>Quantity</th>
    <th>Price</th>
    <th>Total</th>
    <th>Action</th>
  </tr>
  
  <c:forEach items= "${sessionScope.cartItems}" var="item">			
  <tr>
	 <td>${item.productName}</td>
	 <td>${item.qty}</td>
	 <td>${item.rate}</td>
	 <td>${item.price}</td>
     <td><a href="cartDeleteItem?id=${item.productId}">Delete</a></td>
  </tr>
  </c:forEach>
  			
  </table>
  
  <br><h3>
  Total: 
  <c:out value="${sessionScope.cartTotal}"></c:out>
  </h3>
  
  <span class="button">
  <a href="/completePurchase">Complete Payment</a>
  <a href="/">Continue Shopping</a>
  </span>
  
	
	
</body>
</html>