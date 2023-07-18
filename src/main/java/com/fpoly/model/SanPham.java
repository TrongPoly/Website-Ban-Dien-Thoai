package com.fpoly.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @Column(name = "ma_san_pham", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "ten_san_pham", length = 100)
    private String tenSanPham;

    @Column(name = "anh_san_pham", length = 50)
    private String anhSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nha_san_xuat")
    private NhaSanXuat nhaSanXuat;

    @Column(name = "don_gia", precision = 19, scale = 4)
    private BigDecimal donGia;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ram")
    private Integer ram;

    @Column(name = "rom")
    private Integer rom;

    @Column(name = "pin")
    private Integer pin;

    @Nationalized
    @Column(name = "chip", length = 150)
    private String chip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getRom() {
        return rom;
    }

    public void setRom(Integer rom) {
        this.rom = rom;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

}