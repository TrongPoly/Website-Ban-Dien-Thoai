package com.fpoly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.GioHang;
import com.fpoly.model.TaiKhoan;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	@Autowired
	SessionService session;
	@Autowired
	CartService cart;
	@Autowired 
	CustomerService customer;
	
	@GetMapping("/login")
	public String formLogin(Model model) {
		TaiKhoan tk = new TaiKhoan("", "", null, null);
		model.addAttribute("taiKhoan",tk);
		return "User/login";
	}
	@PostMapping("/login")
	public String doLogin(Model model,@ModelAttribute("taiKhoan") TaiKhoan tk) {
		session.set("user", tk);
		GioHang ghExist = cart.findByUser();
		if (ghExist == null) {
			GioHang gh = new GioHang();
			gh.setNguoiSoHuu(customer.findByUser());
			cart.luu(gh);
		}
		return "redirect:/home/index";
	}
	@RequestMapping("/logout")
	public String doLogout() {
		session.remove("user");
		return "redirect:/home/index";
	}
	
}
