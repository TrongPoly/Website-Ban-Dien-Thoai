package com.fpoly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "nha_san_xuat")
public class NhaSanXuat {
    @Id
    @Column(name = "ma_nsx", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "ten_nsx", length = 100)
    private String tenNsx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenNsx() {
        return tenNsx;
    }

    public void setTenNsx(String tenNsx) {
        this.tenNsx = tenNsx;
    }

}