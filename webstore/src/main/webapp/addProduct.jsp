<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>Add New Product</title>
</head>
<body>

	<%@ include file="navbarAdmin.html" %>
	<%@ include file="header.html" %>

		<form class="frmStandard" action="storeProduct" method="post">
			<label>Product Id</label>
			<input type="text" name="id">
			<label>Product Name</label>
			<input type="text" name="productName">
			<label>Product Brand</label>
			<input type="text" name="productBrand">
			<label>Product Category</label>
			<input type="text" name="productCategory">
			<label>Product Description</label><br>
			<textarea rows = "10" cols = 100 name="productDescription">
			</textarea><br><br>
			<span class="radio">
			Gender : 
			<input type="radio" id="male" name="gender" value="male">
			<label>Male</label>
			
			<input type="radio" id="female" name="gender" value="female">
			<label>Female</label>
			</span><br><br>
			<label>Shoe Size</label>
			<input type="text" name="productSize">
			<label>List Price</label>
			<input type="text" name="listPrice">
			
			<input type="submit" value="Submit" id="frmButton">
			<input type="reset" value="Reset" id="frmButton">
		</form>

</body>
</html>