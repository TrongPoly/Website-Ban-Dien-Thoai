package com.fpoly.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class UserInvoiceController {
	@RequestMapping("/index")
	public String ivoice() {
		return "User/invoice";
	}
	@RequestMapping("/details")
	public String details() {
		return "User/invoiceDetails";
	}
}
