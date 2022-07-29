<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes - Receipt Details</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<%@ include file="header.html" %>
	
	<c:out value="${message}"></c:out>
	<h2>Customer Email: <c:out value="${sessionScope.email}"></c:out></h2>
	<h2>Date & Time: <c:out value="${sessionScope.date}"></c:out></h2>
	
	<h2>Receipt # <c:out value="${sessionScope.purchaseId}"></c:out></h2>

<table border="1">
  <tr>
    <th>Name</th>
    <th>Quantity</th>
    <th>Price</th>
    <th>Total</th>
  </tr>
  
  <c:forEach items= "${sessionScope.purchaseDetails}" var="item">			
  <tr>
	 <td>${item.productName}</td>
	 <td>${item.qty}</td>
	 <td>${item.rate}</td>
	 <td>${item.price}</td>
  </tr>
  </c:forEach>
  			
  </table>
  
  <br><h3>
  Total: 
  <c:out value="${sessionScope.purchaseTotal}"></c:out>
  </h3>
  
  <h3>Thank you for shopping with us.</h3>
 
  
	
	
</body>
</html>