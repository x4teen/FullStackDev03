package com.sportyshoe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.bean.Product;
import com.sportyshoe.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository pr;
	
	public List<Product> findAllProducts(){
		return pr.findAll();
	}
	
	public Product findProduct(int id) {
		Optional<Product> product = pr.findById(id);
		if (product.isPresent() ) {
			return product.get();
		} else {
			return null;}
	}
	
	public String storeProduct(Product item) {
		boolean existing = (pr.findById(item.getId()) != null);
		
		if (existing) {
			return "Duplicate product id";
		} else if (pr.save(item) != null) {
			return "Record Stored";
		} else {
			return "Record could not be save";
		}
	}
	
	public String updateProduct(Product item) {
		boolean existing = (pr.findById(item.getId()) != null);
		
		if (existing) {
			if (pr.saveAndFlush(item) != null) {return "Record Updated";}
			else {return "Record could not be updateds";}
		}else {return "Item does not exist";}
	}
	
	public String deleteProduct(int id) {
		Optional<Product> product = pr.findById(id);
		if (product.isEmpty()) {
			return "No product was found";
		} else {
			pr.deleteById(id);
			return "Product was deleted";
		}
	}
		

}
