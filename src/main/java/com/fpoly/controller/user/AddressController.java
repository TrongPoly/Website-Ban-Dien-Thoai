package com.fpoly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.DiaChi;
import com.fpoly.service.AddressService;
import com.fpoly.service.CustomerService;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressService addressService;
	@Autowired
	CustomerService customerService;

	@RequestMapping()
	public String createAddress(Model model, @ModelAttribute("address") DiaChi diachi) {
		diachi.setMaKhachHang(customerService.findByUser());
		addressService.luu(diachi);
		return "redirect:/cart/index";
	}
}
