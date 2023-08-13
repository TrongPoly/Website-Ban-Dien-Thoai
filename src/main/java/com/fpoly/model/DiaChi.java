package com.fpoly.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "dia_chi")
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dia_chi", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang maKhachHang;

    @Nationalized
    @Column(name = "tinh", length = 100)
    private String tinh;

    @Nationalized
    @Column(name = "quan", length = 100)
    private String quan;

    @Nationalized
    @Column(name = "phuong", length = 100)
    private String phuong;

    @Nationalized
    @Lob
    @Column(name = "dc_chi_tiet")
    private String dcChiTiet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KhachHang getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(KhachHang maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getDcChiTiet() {
        return dcChiTiet;
    }

    public void setDcChiTiet(String dcChiTiet) {
        this.dcChiTiet = dcChiTiet;
    }

	public DiaChi(String tinh, String quan, String phuong, String dcChiTiet) {
		super();
		this.tinh = tinh;
		this.quan = quan;
		this.phuong = phuong;
		this.dcChiTiet = dcChiTiet;
	}

	public DiaChi() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}