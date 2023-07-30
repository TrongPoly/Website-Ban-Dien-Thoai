package com.fpoly.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "don_hang_chi_tiet")
public class DonHangChiTiet {
    @EmbeddedId
    @JsonIgnore
    private DonHangChiTietId id;

    @MapsId("maDonHang")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_don_hang", nullable = false)
    @JsonIgnore
    private DonHang maDonHang;

    @MapsId("maSanPham")
    @JsonIgnoreProperties({"anhSanPham","nhaSanXuat","soLuongTon","ram","rom","pin","chip"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_san_pham", nullable = false)
    private SanPham maSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia", precision = 19, scale = 4)
    private BigDecimal donGia;

    public DonHangChiTietId getId() {
        return id;
    }

    public void setId(DonHangChiTietId id) {
        this.id = id;
    }

    public DonHang getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(DonHang maDonHang) {
        this.maDonHang = maDonHang;
    }

    public SanPham getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(SanPham maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

	public DonHangChiTiet(DonHangChiTietId id, DonHang maDonHang, SanPham maSanPham, Integer soLuong,
			BigDecimal donGia) {
		super();
		this.id = id;
		this.maDonHang = maDonHang;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}


	public DonHangChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}

}