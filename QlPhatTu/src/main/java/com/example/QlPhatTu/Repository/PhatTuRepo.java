package com.example.QlPhatTu.Repository;

import com.example.QlPhatTu.entity.DaoTrang;
import com.example.QlPhatTu.entity.PhatTu;

import jakarta.persistence.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface PhatTuRepo extends JpaRepository<PhatTu,Integer> {

    @Query(value = "select * from phattu where  ten like %?1% and phapdanh like %?2% and gioitinh like ?3% and dahoantuc like ?4%", nativeQuery = true)
    List<PhatTu> findPhatTu(String ten, String phapDanh, String gioitinh, String dahoantuc);


    @Query(value = "SELECT CASE WHEN EXISTS (SELECT * FROM phattu WHERE email = ?1) THEN 0 ELSE 1 END", nativeQuery = true)
    long existsByEmail(String email);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT * FROM phattu WHERE sodienthoai = ?1) THEN 0 ELSE 1 END", nativeQuery = true)
    long existsBySDT(String sdt);

    @Query(value = "SELECT * FROM phattu WHERE ten = ?1 and phapdanh = ?2 and sodienthoai = ?3 and email = ?4 LIMIT 1", nativeQuery = true)
    Optional<PhatTu> findByTPSE(String ten, String phapDanh, String sdt, String email);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT * FROM dondangky WHERE phattuid = ?1) THEN 0 ELSE 1 END limit 1", nativeQuery = true)
    long existsByPhatTuId(int phattuId);
    @Query(value = "SELECT CASE WHEN EXISTS (SELECT * FROM phattu WHERE matkhau = ?1) THEN 0 ELSE 1 END", nativeQuery = true)
    long existsByPassword(String password);

    @Query(value = "select * from phattu where email = ?1 limit 1 ", nativeQuery = true)
    PhatTu findPassword(String email);


    @Query(value = "select if(tenchua = ?1 ,1,0) from chua where tenchua = ?1 limit 1",nativeQuery = true)
    int checkTenChua(String tenChua);

    @Query(value = "select trangthaidon from dondangky  where phattuid  = ?1",nativeQuery = true)
    int findTrangThaiDon(int phatTuId);

    @Query(value = "select count(phattuid) from dondangky  where daotrangid = ?1 and trangthaidon = 1 ",nativeQuery = true)
    int countnumberThamGia(int daoTrangId);

    @Query(value = "select distinct phattu.* from phattudaotrang,phattu where phattudaotrang.phattuid = phattu.id and phattudaotrang.dathamgia in (1)  ", nativeQuery = true)
    List<PhatTu> findPhatTuDaoTrang();

}