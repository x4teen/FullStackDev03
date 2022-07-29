<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes - User Login Page</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	
	<%@include file="navbar.jsp" %>
	<%@include file="header.html" %>
	
	<c:out value="${message}"></c:out>
	
	<h2>Login Details</h2>
	
	<form class="frmStandard" action="/signUp" method="post">
			<label>Email</label>
			<input type="text" name="email">
			<label>Password</label>
			<input type="password" name="password">
			<label>Name</label>
			<input type="text" name="name">
			<label>Address</label>
			<input type="text" name="address">
			<input type="submit" value="Signup" id="frmButton">
			<input type="reset" value="Reset" id="frmButton">
	</form>
	<br>
	<a href="\signupForm">Sign up for account</a>
	
	
	
	
</body>
</html>