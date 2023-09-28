package com.te.spring.dto;


import lombok.Data;
@Data
public class UserDTO {
	private int userId;
	private String userFullName;
	private String userEmail;
	private String userName;
	private String password;
	private String roles;
}
