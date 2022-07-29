<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes - Admin Page</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="navbarAdmin.html" %>
	<%@ include file="header.html" %>
	
	<c:out value="${message}"></c:out>
	
	<h2>Product List</h2>

<table border="1">
  <tr>
    <th>Name</th>
    <th>Brand</th>
    <th>Size</th>
    <th>Price</th>
    <th>Action</th>
    <th>Action</th>
  </tr>
  
  <c:forEach items= "${requestScope.pList}" var="product">			
  <tr>
	 <td>${product.productName}</td>
	 <td>${product.productManufacturer}</td>
	 <td>${product.productSize}</td>
	 <td>${product.listPrice}</td>
     <td><a href="getProductDetails?id=${product.id}">Edit</a></td>
     <td><a href="deleteProduct?id=${product.id}">Delete</a></td>
  </tr>
  </c:forEach>
  			
  
  </table>
	
	
</body>
</html>