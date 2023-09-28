package com.te.spring.controller;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring.dto.UserDTO;
import com.te.spring.entity.User;
import com.te.spring.exception.InvalidInputException;
import com.te.spring.repository.AppRepository;
import com.te.spring.service.AppService;

@Controller
public class AppController {
	@Autowired
	private AppRepository repository;

	@Autowired
	private AppService service;

	@ModelAttribute
	public void createUser(Model model, Principal principal) {
		if (principal != null) {
			User user = repository.findByuserName(principal.getName());
			model.addAttribute("user", user);
		}
	}

	@GetMapping("/")
	public String login() {
		return "home";
	}

	@GetMapping("/signin")
	public String signIn() {
		return "signin";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/save")
	public String saveData(@ModelAttribute UserDTO dto) throws InvalidInputException {
		UserDTO user = service.saveData(dto);
		return "success";
	}

	@GetMapping("/login")
	public String getProfile() {
		return "profile";
	}

}
