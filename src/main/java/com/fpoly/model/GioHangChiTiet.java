package com.fpoly.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gio_hang_chi_tiet")
public class GioHangChiTiet {
    @EmbeddedId
    private GioHangChiTietId id;

    @MapsId("maGioHang")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_gio_hang", nullable = false)
    private GioHang maGioHang;

    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_san_pham", nullable = false)
    private SanPham maSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "chon_mua")
    private Boolean chonMua;

    public GioHangChiTietId getId() {
        return id;
    }

    public void setId(GioHangChiTietId id) {
        this.id = id;
    }

    public GioHang getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(GioHang maGioHang) {
        this.maGioHang = maGioHang;
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

    public Boolean getChonMua() {
        return chonMua;
    }

    public void setChonMua(Boolean chonMua) {
        this.chonMua = chonMua;
    }

	public GioHangChiTiet(GioHangChiTietId id, GioHang maGioHang, SanPham maSanPham, Integer soLuong, Boolean chonMua) {
		super();
		this.id = id;
		this.maGioHang = maGioHang;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.chonMua = chonMua;
	}

	public GioHangChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}