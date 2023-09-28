package com.te.spring.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
	private int userId;
	private String userName;
	private String userEmail;
	private String password;
	private String userRole;
}
