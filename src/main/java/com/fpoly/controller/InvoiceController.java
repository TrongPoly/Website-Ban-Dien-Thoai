package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvoiceController {
	
	@RequestMapping("/admin/invoice")
	public String invoice(Model model) {
		return "Admin/invoiceManagement";
	}
}
