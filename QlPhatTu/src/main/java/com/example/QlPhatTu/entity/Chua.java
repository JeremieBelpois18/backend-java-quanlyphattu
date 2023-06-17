package com.example.QlPhatTu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chua")
public class Chua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Max(0L)
    @Column(name = "tenchua")
    private String TenChua;

    @Column(name = "ngaythanhlap")
    private LocalDate NgayThanhLap;

    @Max(0L)
    @Column(name = "diachi")
    private String DiaChi;

    @Column(name = "capnhap")
    private LocalDate CapNhap;


    @OneToMany(mappedBy = "chua")
    List<PhatTu> phatTuList;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenChua() {
        return TenChua;
    }

    public void setTenChua(String tenChua) {
        TenChua = tenChua;
    }

    public LocalDate getNgayThanhLap() {
        return NgayThanhLap;
    }

    public void setNgayThanhLap(LocalDate ngayThanhLap) {
        NgayThanhLap = ngayThanhLap;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public LocalDate getCapNhap() {
        return CapNhap;
    }

    public void setCapNhap(LocalDate capNhap) {
        CapNhap = capNhap;
    }
}
