package com.fpoly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.math.BigDecimal;

@NamedQueries({
	@NamedQuery(name="findByTenSanPham", query = "select sp from SanPham sp "
			+ "where sp.tenSanPham like ?1")})

@Entity
@Table(name = "san_pham")
public class SanPham implements Serializable{
    @Id
    @Column(name = "ma_san_pham", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Nationalized
    @NotBlank(message = "Không được để trống tên")
    @Column(name = "ten_san_pham", length = 100)
    private String tenSanPham;

    @Column(name = "anh_san_pham", length = 50)
    private String anhSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nha_san_xuat")
    private NhaSanXuat nhaSanXuat;
    
    
    @Positive(message = "Giá không được nhỏ hơn 0")
    @NotNull(message = "Không được để trống giá")
    @Column(name = "don_gia", precision = 19, scale = 4)
    private BigDecimal donGia;
    
    @Positive(message = "Số lượng không được nhỏ hơn 0")
    @NotNull(message = "Không được để trống số lượng")
    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Positive(message = "Ram không được nhỏ hơn 0")
    @NotNull(message = "Vui lòng nhập Ram")
    @Column(name = "ram")
    private Integer ram;

    @Positive(message = "Rom không được nhỏ hơn 0")
    @NotNull(message = "Vui lòng nhập Rom")
    @Column(name = "rom")
    private Integer rom;

    @Positive(message = "Pin không được nhỏ hơn 0")
    @NotNull(message = "Vui lòng nhập Pin")
    @Column(name = "pin")
    private Integer pin;

    @Nationalized
    @NotBlank(message = "Vui lòng nhập chip")
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