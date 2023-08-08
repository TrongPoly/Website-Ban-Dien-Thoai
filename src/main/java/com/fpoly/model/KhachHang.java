package com.fpoly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NamedQueries({
	@NamedQuery(name="findByTenKhachHang", query = "select kh from KhachHang kh "
			+ "where kh.tenKhachHang like ?1")})


@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @Column(name = "ma_khach_hang", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nationalized
    @Column(name = "ten_khach_hang", length = 50)
    @NotBlank(message = "không được để trống")
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", length = 15)
    @NotBlank(message = "không được để trống")
    private String soDienThoai;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    @JsonIgnoreProperties({ "matKhau","phanQuyen" })
    @NotNull(message = "không được để trống")
    private  TaiKhoan email;

    @JsonIgnore
    @Column(name = "diem_tich_luy")
    @NotNull(message = "không được để trống")
    @PositiveOrZero(message = "điểm không thể là số âm")
    private Integer diemTichLuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hang_khach_hang")
    @JsonIgnore
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