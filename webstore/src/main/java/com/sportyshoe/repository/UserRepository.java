package com.sportyshoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoe.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	

}
