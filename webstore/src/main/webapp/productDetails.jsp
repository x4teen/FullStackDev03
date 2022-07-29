<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.sportyshoe.service.ProductService"%>
<%@page import="com.sportyshoe.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>Update Product</title>
</head>
<body>

	<%@ include file="navbarAdmin.html" %>
	<%@ include file="header.html" %>
	
	 <c:set var = "item" scope = "page" value = "${requsestScope.item}"/>

		<form class="frmStandard" action="updateProduct" method="post">
			<label>Product Id</label>
			<input type="text" name="id" value="${item.id}" readonly>
			<label>Product Name</label>
			<input type="text" name="productName" value="${item.productName}">
			<label>Product Brand</label>
			<input type="text" name="productBrand" value="${item.productManufacturer}">
			<label>Product Category</label>
			<input type="text" name="productCategory" value="${item.productCategory}" }>
			<label>Product Description</label><br>
			<textarea rows = "10" cols = 100 name="productDescription"> ${item.productDescription}
			</textarea><br><br>
			<span class="radio">
			Gender : 
			<input type="radio" id="male" name="gender" value="male">
			<label>Male</label>
			
			<input type="radio" id="female" name="gender" value="female">
			<label>Female</label>
			</span><br><br>
			<label>Shoe Size</label>
			<input type="text" name="productSize" value="${item.productSize}">
			<label>List Price</label>
			<input type="text" name="listPrice" value="${item.listPrice}">
			
			<input type="submit" value="Update" id="frmButton">
			<input type="reset" value="Reset" id="frmButton">
		</form>

</body>
</html>