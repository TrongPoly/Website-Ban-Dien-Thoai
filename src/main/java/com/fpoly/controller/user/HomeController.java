package com.fpoly.controller.user;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/home")
public class HomeController {
	@RequestMapping("/index")
	public String index(Model model) throws Exception {
		String path = "C:\\Users\\Admin\\eclipse-workspace_Java6\\Website-Ban-Dien-Thoai\\src\\main\\resources\\products.json";
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> sp = mapper.readValue(new File(path), List.class);
		model.addAttribute("sp",sp);
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
	@RequestMapping("/product")
	public String product() {
		return "User/productDetails";
	}
}
