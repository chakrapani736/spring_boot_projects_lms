package com.te.spring.service;

import java.util.Optional;

import com.te.spring.dto.UserDTO;
import com.te.spring.entity.User;
import com.te.spring.exception.InvalidInputException;

public interface AppService {

	UserDTO saveData(UserDTO dto) throws InvalidInputException;
}
