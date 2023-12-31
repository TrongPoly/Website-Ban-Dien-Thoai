package com.fpoly.rest.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DiaChi;
import com.fpoly.model.DonHang;
import com.fpoly.model.DonHangActivity;
import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.DonHangChiTietId;
import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.KhachHang;
import com.fpoly.model.SanPham;
import com.fpoly.service.AddressService;
import com.fpoly.service.CartDetailsService;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.DonHangActivityService;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;
import com.fpoly.service.ProductService;

@RestController
@RequestMapping("/rest")
public class OrderRestController {
	@Autowired
	CustomerService customer;
	@Autowired
	OrderService order;
	@Autowired
	OrderDetailsService orderDetails;
	@Autowired
	AddressService address;
	@Autowired
	CartDetailsService cartDetails;
	@Autowired
	CartService cart;
	@Autowired
	ProductService product;
	@Autowired
	DonHangActivityService donHangActivityService;

	@PostMapping("/order")
	public ResponseEntity<Void> order(@RequestParam("diaChiId") Integer dchi) {
		KhachHang khachHang = customer.findByUser();
		//Kiểm tra chọn địa chỉ
		DiaChi diaChi = address.findById(dchi);
		if (diaChi == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Instant ngayDatHang = Instant.now();
		
		//Tạo đơn hàng mới
		DonHang donHang = new DonHang(ngayDatHang, 1, khachHang, diaChi);
		order.luu(donHang);
		
		GioHang gh = cart.findByNguoiMua();
		List<GioHangChiTiet> listGHCT = cartDetails.findByGioHang(gh.getId()).stream()
				.filter(ghct -> ghct.getChonMua() == true).toList();

		for (int i = 0; i < listGHCT.size(); i++) {
			SanPham sp = product.findById(listGHCT.get(i).getMaSanPham().getId());
			if (listGHCT.stream().anyMatch(ghct -> ghct.getSoLuong() > ghct.getMaSanPham().getSoLuongTon())) {
				order.xoa(donHang);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			DonHangChiTietId dhctId = new DonHangChiTietId();
			dhctId.setMaDonHang(donHang.getId());
			dhctId.setMaSanPham(sp.getId());

			DonHangChiTiet dhct = new DonHangChiTiet(dhctId, donHang, sp, listGHCT.get(i).getSoLuong(),
					listGHCT.get(i).getMaSanPham().getDonGia());
			orderDetails.luu(dhct);
			
			//Trừ số lượng sản phẩm sau khi mua
			sp.setSoLuongTon(sp.getSoLuongTon() - listGHCT.get(i).getSoLuong());
			cartDetails.xoaSanPham(listGHCT.get(i));
		}
		//Ghi trạng thái đơn hàng
		DonHangActivity activity = new DonHangActivity(donHang, 1, ngayDatHang,1);
		donHangActivityService.luu(activity);
		return ResponseEntity.ok().build();
	}
}
