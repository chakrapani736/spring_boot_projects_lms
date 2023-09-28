package com.te.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	@RequestMapping("/")
	public String getConnection() {
		return "user.jsp";
	}
}
