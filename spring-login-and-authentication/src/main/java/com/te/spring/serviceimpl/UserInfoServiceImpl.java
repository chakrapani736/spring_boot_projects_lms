package com.te.spring.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.spring.dto.UserInfoDTO;
import com.te.spring.model.UserInfo;
import com.te.spring.repository.UserInfoRepository;
import com.te.spring.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserInfoRepository repo;
	@Override
	public void saveData(UserInfoDTO dto) {
		UserInfo user = new UserInfo();
		BeanUtils.copyProperties(dto, user);
		user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		repo.save(user);
	}

}
