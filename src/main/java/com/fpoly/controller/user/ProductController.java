package com.fpoly.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.SanPham;
import com.fpoly.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/details/{id}")
	public String productDetails(@PathVariable("id") Integer id, Model model) {
	  	SanPham sp = productService.findById(id);
		model.addAttribute("sp",sp);
		System.out.println(sp.getAnhSanPham());
		List<SanPham> dssp = productService.findAllByNSX(sp.getNhaSanXuat().getTenNsx());
		model.addAttribute("dssp",dssp);
		return "User/productDetails";
	}
	
}
