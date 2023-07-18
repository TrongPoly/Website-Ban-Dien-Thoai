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

}