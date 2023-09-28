package com.te.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.spring.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Employee findByuserName(String username);
	
}
