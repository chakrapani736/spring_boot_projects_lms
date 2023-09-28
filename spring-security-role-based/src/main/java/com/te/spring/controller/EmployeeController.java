package com.te.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.te.spring.dto.EmployeeDTO;
import com.te.spring.entity.Employee;
import com.te.spring.repository.EmployeeRepository;
import com.te.spring.service.EmployeeService;
import com.te.spring.util.JwtUtil;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmployeeController {
	@Autowired
	private JwtUtil util;
	private String token;
	@Autowired
	private EmployeeService service;
	@Autowired
	private EmployeeRepository repository;

	@ModelAttribute
	public void getAuthentication(Model model, Principal principal) {
		if (principal != null) {
			Employee employee = repository.findByuserName(principal.getName());
			model.addAttribute("employee", employee);
			if (token == null)
				token = util.generateToken(principal.getName());
		}
	}

	@GetMapping("/")
	public String getHome() {
		return "home";
	}

	@GetMapping("/register")
	public String getRegistration() {
		return "register";
	}

	@PostMapping("/createEmp")
	public String saveEmployee(HttpServletRequest request, @ModelAttribute EmployeeDTO dto) {
		if (request.getParameter("employeeRole").toUpperCase().equals("USER")) {
			service.saveEmployee(dto);
			return "success";
		} else {
			return "failure";
		}
	}

	@PostMapping("/adminRegister")
	public String saveAdmin(HttpServletRequest request, @ModelAttribute EmployeeDTO dto) {
			service.saveEmployee(dto);
			return "success";

	}

	@GetMapping("/signin")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/user/profile")
	public String getUserProfile() {
		try {
			if (util.isTokenExpired(token)) {
				return "employeeProfile";
			}
		} catch (Exception e) {
			token = null;
			return "redirect:/logout";
		}
		return "home";
	}

	@GetMapping("/admin/profile")
	public String getAdminProfile() {
		try {
			if (util.isTokenExpired(token)) {
				return "adminProfile";
			}
		} catch (Exception e) {
			token = null;
			return "redirect:/logout";
		}
		return "home";
	}
}
