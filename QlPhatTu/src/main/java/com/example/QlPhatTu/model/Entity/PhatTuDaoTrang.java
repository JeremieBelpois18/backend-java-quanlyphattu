package com.example.QlPhatTu.model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phattudaotrang")
public class PhatTuDaoTrang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "dathamgia")
    private int DaThamGia;

    @Column(name = "lydokhongthamgia")
    private String LyDoKhongThamGia;

    @ManyToOne
    @JoinColumn(name = "daotrangid")
    private DaoTrang daoTrang;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    private PhatTu phatTu;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getDaThamGia() {
        return DaThamGia;
    }

    public void setDaThamGia(int daThamGia) {
        DaThamGia = daThamGia;
    }

    public String getLyDoKhongThamGia() {
        return LyDoKhongThamGia;
    }

    public void setLyDoKhongThamGia(String lyDoKhongThamGia) {
        LyDoKhongThamGia = lyDoKhongThamGia;
    }

    public DaoTrang getDaoTrang() {
        return daoTrang;
    }

    public void setDaoTrang(DaoTrang daoTrang) {
        this.daoTrang = daoTrang;
    }

    public PhatTu getPhatTu() {
        return phatTu;
    }

    public void setPhatTu(PhatTu phatTu) {
        this.phatTu = phatTu;
    }
}
