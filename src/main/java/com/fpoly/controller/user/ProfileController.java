package com.fpoly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.KhachHang;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.repository.TaiKhoanRepository;
import com.fpoly.service.AddressService;
import com.fpoly.service.CustomerService;

@Controller
public class ProfileController {
	
	@Autowired
	KhachHangRepository daokh;
	
	@Autowired
	AddressService addressService;
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TaiKhoanRepository tkdao;
	
	@RequestMapping("/Profile")
	public String Profile(Model model) {
		
		KhachHang kh = new KhachHang();
		model.addAttribute("kh",kh);
		
		return "/User/Profile";
	}

}
