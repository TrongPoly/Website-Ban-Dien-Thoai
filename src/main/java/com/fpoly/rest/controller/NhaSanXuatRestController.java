package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.NhaSanXuat;
import com.fpoly.repository.NhaSanXuatRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/rest")
public class NhaSanXuatRestController {
	
	@Autowired
	NhaSanXuatRepository nsxdao;

	@GetMapping("/nhasanxuat")
	public List<NhaSanXuat> getAll (Model model){
		return nsxdao.findAll();
	}
}
