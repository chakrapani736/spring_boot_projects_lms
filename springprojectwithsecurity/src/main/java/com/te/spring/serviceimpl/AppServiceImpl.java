package com.te.spring.serviceimpl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.spring.dto.UserDTO;
import com.te.spring.entity.User;
import com.te.spring.exception.InvalidInputException;
import com.te.spring.repository.AppRepository;
import com.te.spring.service.AppService;

@Service
public class AppServiceImpl implements AppService {
	@Autowired
	private AppRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDTO saveData(UserDTO dto) throws InvalidInputException {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		if (user != null) {
			// encoding password
			user.setPassword(encoder.encode(dto.getPassword()));
			 repository.save(user);
			 return dto;
		} else {
			throw new InvalidInputException("invalid data");
		}
	}

	
	

}
