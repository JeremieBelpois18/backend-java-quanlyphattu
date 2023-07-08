package com.example.QlPhatTu.Repository;

import com.example.QlPhatTu.model.Entity.DaoTrang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoTrangRepo extends JpaRepository<DaoTrang,Integer> {

    @Query(value = "select * from daotrang where id like ?1% and noitochuc like %?2% and thoigiantochuc like %?3% and  daketthuc like ?4%",nativeQuery = true)
    List<DaoTrang> findDaoTrang(String daoTrangId, String noiToChuc, String thoiGian, String daKetThuc);


}
