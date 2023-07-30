package com.fpoly.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @Column(name = "ma_khach_hang", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "ten_khach_hang", length = 50)
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    @JsonIgnoreProperties({ "matKhau","phanQuyen" })
    private TaiKhoan email;

    @Column(name = "diem_tich_luy")
    private Integer diemTichLuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hang_khach_hang")
    private HangKhachHang hangKhachHang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public TaiKhoan getEmail() {
        return email;
    }

    public void setEmail(TaiKhoan email) {
        this.email = email;
    }

    public Integer getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(Integer diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public HangKhachHang getHangKhachHang() {
        return hangKhachHang;
    }

    public void setHangKhachHang(HangKhachHang hangKhachHang) {
        this.hangKhachHang = hangKhachHang;
    }

}