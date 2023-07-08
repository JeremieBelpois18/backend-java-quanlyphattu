package com.example.QlPhatTu.QlPhatTuService.InterfaceService;

import com.example.QlPhatTu.model.Entity.DaoTrang;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface IdaoTrangService {
    //Thêm event đạo trang
    ResponseEntity<?> createDaoTrang(String noiToChuc, LocalDate thoiGianToChuc, String noidung, int daKetThuc, int idtruTri, int idDaoTrang);

    //cập nhập buổi đạo trang
    ResponseEntity<?> updateDaoTrang(int daoTrangId);

    //sua thông tin buổi đạo trang
    ResponseEntity<?> editDaoTrang(DaoTrang daoTrang);

    //tìm đạo trang
    Page<DaoTrang> timDaoTrang(String daoTrangId, String noiToChuc, String daKetThuc, String thoiGian, int numberPage, int sizePage);

    //xoa dạo trang
    ResponseEntity<?> xoaDaoTrang(int id);
}
