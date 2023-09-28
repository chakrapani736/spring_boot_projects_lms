package com.te.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.spring.entity.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppRepository extends JpaRepository<User, Integer> {

	User findByuserName(String username);
	
}
