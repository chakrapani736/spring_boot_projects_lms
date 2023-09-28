package com.te.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring.dto.UserInfoDTO;
import com.te.spring.model.UserInfo;
import com.te.spring.repository.UserInfoRepository;
import com.te.spring.service.UserInfoService;

@RestController
public class UserController {
	@Autowired
	private UserInfoService service;
	@Autowired
	private UserInfoRepository repo;

	@GetMapping("/")
	public String home() {
		return "homepage";
	}

	@GetMapping("/register")
	public String signup() {
		return "registartionform";
	}

	@GetMapping("/signin")
	public String singin() {
		return "login";
	}

	@GetMapping("/userlogin")
	public String getProfile() {
		return "profile.jsp";
	}

	@ModelAttribute
	public void getAuthenticatedUser(Model model, Principal principal) {
		if (principal != null) {
			UserInfo user = repo.findByuserName(principal.getName());
			model.addAttribute("user", user);
		}
	}

	@PostMapping("/createUser")
	public String saveData(@ModelAttribute UserInfoDTO dto) {
		service.saveData(dto);
		return "success";
	}

}
