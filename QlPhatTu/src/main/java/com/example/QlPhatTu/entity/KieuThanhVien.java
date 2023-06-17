package com.example.QlPhatTu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

import java.util.List;

@Entity
@Table(name = "kieuthanhvien")
public class KieuThanhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Max(0L)
    @Column(name = "code")
    private String Code;

    @Column(name = "tenkieu")
    private String TenKieu;

    @OneToMany(mappedBy = "kieuThanhVien")
    List<PhatTu> phatTuList;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getTenKieu() {
        return TenKieu;
    }

    public void setTenKieu(String tenKieu) {
        TenKieu = tenKieu;
    }
}
