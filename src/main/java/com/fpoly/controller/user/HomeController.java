package com.fpoly.controller.user;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpoly.model.SanPham;
import com.fpoly.model.TaiKhoan;
import com.fpoly.service.ProductService;
import com.fpoly.service.SessionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	SessionService session;

	@RequestMapping("/index")
	public String index(Model model) throws Exception {
		List<SanPham> dssp = productService.findAll();
		model.addAttribute("dssp", dssp);
		return "User/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "User/login";
	}

	@RequestMapping("/register")
	public String register() {
		return "User/register";
	}

	
}
