package com.fpoly.rest.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.GioHangChiTietId;
import com.fpoly.model.KhachHang;
import com.fpoly.model.SanPham;
import com.fpoly.model.TaiKhoan;
import com.fpoly.service.CartDetailsService;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.ProductService;
import com.fpoly.service.SessionService;

@RestController
public class CartRestController {
	@Autowired
	CartDetailsService cartDetails;
	@Autowired
	ProductService product;
	@Autowired
	SessionService session;
	@Autowired
	CartService cart;
	@Autowired
	CustomerService customer;

	@PostMapping("/rest/cart")
	public ResponseEntity<GioHangChiTiet> addToCart(@RequestParam("productId") Integer id) {
		TaiKhoan user = session.get("user");
		if (user == null)
			return ResponseEntity.badRequest().build();
		
		GioHang gh = cart.findByUser();
		SanPham sp = product.findById(id);
		
		GioHangChiTiet productExist = cartDetails.findByMaGioHangVaMaSanPham(gh.getId(), sp.getId());
		// SP Chưa có trong giỏ hàng
		if (productExist == null) {
			themVaoGH(gh.getId(), sp.getId(), gh, sp);
		} else {
			int sl = productExist.getSoLuong() + 1;
			productExist.setSoLuong(sl);
			cartDetails.luu(productExist);
		}
		
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/rest/cart")
	public ResponseEntity<Void> deleteProduct(@RequestParam("productId") Integer productId,
			@RequestParam("cartId") Integer cartId) {
		GioHangChiTiet ghct = cartDetails.findByMaGioHangVaMaSanPham(cartId, productId);
		cartDetails.xoaSanPham(ghct);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/rest/cart")
	public ResponseEntity<Void> updateCart(@RequestParam("soLuongSP") Integer soLuong,
			@RequestParam("productId") Integer productId, @RequestParam("cartId") Integer cartId) {
		GioHangChiTiet ghct = cartDetails.findByMaGioHangVaMaSanPham(cartId, productId);
		if (ghct.getMaSanPham().getSoLuongTon() < soLuong) {
			soLuong = ghct.getMaSanPham().getSoLuongTon();
			ghct.setSoLuong(soLuong);
			cartDetails.luu(ghct);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else if (soLuong <= 0) {
			soLuong = 1;
		}
		ghct.setSoLuong(soLuong);
		cartDetails.luu(ghct);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/rest/cart/checked")
	public ResponseEntity<Void> CheckProduct(@RequestParam("productCheck") Boolean check,
			@RequestParam("productId") Integer productId, @RequestParam("cartId") Integer cartId) {
		GioHangChiTiet ghct = cartDetails.findByMaGioHangVaMaSanPham(cartId, productId);
		ghct.setChonMua(check);
		cartDetails.luu(ghct);
		return ResponseEntity.ok().build();
	}

	private GioHangChiTiet themVaoGH(int maGH, int maSP, GioHang gh, SanPham sp) {
		GioHangChiTietId ghctId = new GioHangChiTietId();
		ghctId.setMaGioHang(maGH);
		ghctId.setMaSanPham(maSP);
		GioHangChiTiet ghct = new GioHangChiTiet(ghctId, gh, sp, 1, true);
		cartDetails.luu(ghct);
		return ghct;
	}
}
