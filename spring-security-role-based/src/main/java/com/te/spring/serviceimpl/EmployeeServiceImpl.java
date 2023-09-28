package com.te.spring.serviceimpl;
import com.te.spring.entity.Employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.te.spring.dto.EmployeeDTO;
import com.te.spring.repository.EmployeeRepository;
import com.te.spring.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Override
	public void saveEmployee(EmployeeDTO dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);
		employee.setPassword(encoder.encode(dto.getPassword()));
		employee.setEmployeeRole("ROLE_"+dto.getEmployeeRole().toUpperCase());
		repository.save(employee);
		
	}

}
