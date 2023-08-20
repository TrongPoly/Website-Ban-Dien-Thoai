package com.fpoly.rest.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DonHang;
import com.fpoly.model.DonHangActivity;
import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.SanPham;
import com.fpoly.service.CustomerService;
import com.fpoly.service.DonHangActivityService;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;
import com.fpoly.service.ProductService;

@RestController
@RequestMapping("/admin/rest/order")
public class OderMgmtController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailsService orderDetailsService;
	@Autowired
	CustomerService customerService;
	@Autowired
	DonHangActivityService donHangActivityService;
	@Autowired
	ProductService productService;

	@GetMapping("/index")
	public List<DonHang> getAll() {
		return orderService.findAll();
	}

	@GetMapping("/details/{id}")
	public List<DonHangChiTiet> detailsOrder(@PathVariable("id") Integer id) {
		return orderDetailsService.findByMaDonHang(id);
	}
	@GetMapping("/activity/{orderId}")
	public List<DonHangActivity> getActivity(@PathVariable("orderId") Integer id){
		return donHangActivityService.findByOrderId(id);
	}


	@PutMapping("/cancelOrder/{id}")
	public void cancelOrder(@PathVariable("id") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		donHang.setTrangThai(4);
		List<DonHangChiTiet> dhct = orderDetailsService.findByMaDonHang(id);
		for(int i = 0 ; i<dhct.size(); i++) {
			SanPham sp = productService.findById(dhct.get(i).getMaSanPham().getId());
			sp.setSoLuongTon(sp.getSoLuongTon()+dhct.get(i).getSoLuong());
			productService.luu(sp);
		}
		Instant ngayCapNhat = Instant.now();
		DonHangActivity activity = new DonHangActivity(donHang, 4, ngayCapNhat, 2);
		donHangActivityService.luu(activity);
		orderService.luu(donHang);
	}

	@PutMapping("/notCancelOrder/{id}")
	public void notCancelOrder(@PathVariable("id") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		Instant ngayCapNhat = Instant.now();
		DonHangActivity activity = new DonHangActivity(donHang, 6, ngayCapNhat, 2);
		donHangActivityService.luu(activity);
		donHang.setTrangThai(1);
		orderService.luu(donHang);
	}

	@PutMapping("/delivery/{id}")
	public void delivery(@PathVariable("id") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		donHang.setTrangThai(2);
		Instant ngayCapNhat = Instant.now();
		DonHangActivity activity = new DonHangActivity(donHang, 2, ngayCapNhat, 2);
		donHangActivityService.luu(activity);
		orderService.luu(donHang);
	}

	@PutMapping("/successfulDelivery/{id}")
	public void success(@PathVariable("id") Integer id) {
		Instant ngayCapNhat = Instant.now();
		DonHang donHang = orderService.findByMaDonHang(id);
		donHang.setTrangThai(3);
		donHang.setNgayThanhToan(ngayCapNhat);

		DonHangActivity activity = new DonHangActivity(donHang, 3, ngayCapNhat, 2);
		donHangActivityService.luu(activity);
		orderService.luu(donHang);
	}

	@PutMapping("/seeMessage/{id}")
	public void seeMessage(@PathVariable("id") Integer id) {
		DonHangActivity activity = donHangActivityService.findById(id);
		activity.setChecked(3);
		donHangActivityService.luu(activity);
	}

	@GetMapping("/loadMessage")
	public List<DonHangActivity> loadMessage() {
		return donHangActivityService.findByChecked();
	}

}
