<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes - Purchase Report</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="navbarAdmin.html" %>
	<%@ include file="header.html" %>
	
	<h2>Purchase Report for  <c:out value = "${item.date}"/></h2>

<table border="1">
  <tr>
    <th>id</th>
    <th>Email</th>
    <th>Total</th>
  </tr>
  <c:set var= "dailyTotal" scope= "request" value = "$0.00"/>
  <c:forEach items= "${requestScope.purchaseList}" var="item">			
  <tr>
	 <td>${item.id}</td>
	 <td>${item.emailId}</td>
	 <td>${item.total}</td>
	  
  </tr>
  </c:forEach>
  			
  </table>
  
  
  
	
</body>
</html>