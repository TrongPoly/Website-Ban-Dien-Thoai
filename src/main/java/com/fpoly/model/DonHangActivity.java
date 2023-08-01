package com.fpoly.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "don_hang_activity")
public class DonHangActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_don_hang")
    private DonHang maDonHang;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_cap_nhat")
    private Instant ngayCapNhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_thuc_hien")
    private TaiKhoan nguoiThucHien;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @Column(name = "checked")
    private int checked;
    
    
    public DonHangActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public DonHangActivity(DonHang maDonHang, Integer trangThai, Instant ngayCapNhat, int checked) {
		super();
		this.maDonHang = maDonHang;
		this.trangThai = trangThai;
		this.ngayCapNhat = ngayCapNhat;
		this.checked = checked;
	}



	public String getMoTa() {
		return moTa;
	}



	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}



	public int getChecked() {
		return checked;
	}



	public void setChecked(int checked) {
		this.checked = checked;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DonHang getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(DonHang maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Instant getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Instant ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public TaiKhoan getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(TaiKhoan nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }

}