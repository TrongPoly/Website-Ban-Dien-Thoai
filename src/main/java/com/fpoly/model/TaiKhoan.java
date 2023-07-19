package com.fpoly.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {
    @Id
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "mat_khau", length = 10)
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phan_quyen")
    private PhanQuyen phanQuyen;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public PhanQuyen getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(PhanQuyen phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

	public TaiKhoan(String email, String matKhau, PhanQuyen phanQuyen, Boolean trangThai) {
		super();
		this.email = email;
		this.matKhau = matKhau;
		this.phanQuyen = phanQuyen;
		this.trangThai = trangThai;
	}

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

}