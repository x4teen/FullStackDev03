<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>

<core:forEach items= "${requestScope.pList}" var="product">
<div class="productItem">
	<h3>${product.productName}</h3>
	<p>${product.productDescription}</p>
	<p>Brand: ${product.productManufacturer}</p>
	<p>Size: ${product.productSize}</p>
	<p>Price: ${product.listPrice}</p><br>
	<span class="button"><a href="addToCart?id=${product.id}">Add to Cart</a></span>
</div>
</core:forEach>

