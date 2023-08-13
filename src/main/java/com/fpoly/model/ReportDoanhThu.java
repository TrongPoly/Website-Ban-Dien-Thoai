package com.fpoly.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReportDoanhThu implements java.io.Serializable {
	@Id
	String tenSanPham;
	long soLuong;
	BigDecimal tongTien;


	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(long soLuong) {
		this.soLuong = soLuong;
	}

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}


	public ReportDoanhThu(String tenSanPham, long soLuong, BigDecimal tongTien) {
		super();
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}

	public ReportDoanhThu() {
		super();
		// TODO Auto-generated constructor stub
	}

}
