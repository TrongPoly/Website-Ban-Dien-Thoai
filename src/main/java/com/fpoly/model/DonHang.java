package com.fpoly.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_hang", nullable = false)
    private Integer id;

    @Column(name = "ngay_lap")
    private Instant ngayLap;

    @Column(name = "ngay_thanh_toan")
    private Instant ngayThanhToan;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_mua")
    private KhachHang nguoiMua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dia_chi")
    private DiaChi diaChi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Instant ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Instant getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Instant ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public KhachHang getNguoiMua() {
        return nguoiMua;
    }

    public void setNguoiMua(KhachHang nguoiMua) {
        this.nguoiMua = nguoiMua;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

}