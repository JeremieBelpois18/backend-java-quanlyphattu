package com.example.QlPhatTu.model.Entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "kieuthanhvien")
public class KieuThanhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

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
