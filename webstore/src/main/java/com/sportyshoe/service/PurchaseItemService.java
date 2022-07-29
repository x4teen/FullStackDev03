package com.sportyshoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.repository.ProductRepository;

@Service
public class PurchaseItemService {
	@Autowired
	ProductRepository pr;

}
