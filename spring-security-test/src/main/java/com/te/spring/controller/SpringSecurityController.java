package com.te.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SpringSecurityController {
	@RequestMapping("/")
	public String getDisplay() {
		return "display.jsp";
	}
}
