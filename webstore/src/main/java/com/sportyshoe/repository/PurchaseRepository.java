package com.sportyshoe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoe.bean.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
	
	public List<Purchase> findByDate(Date date);
	
	public List<Purchase> findByEmailId(String emailId);
}