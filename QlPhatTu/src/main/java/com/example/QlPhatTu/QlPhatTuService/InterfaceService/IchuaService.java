package com.example.QlPhatTu.QlPhatTuService.InterfaceService;

import com.example.QlPhatTu.model.Entity.Chua;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface IchuaService {
    //lấy tát cả thông tin chùa
    Page<Chua> getAllChua(int numberPgae, int sizePage);

    //tạo thông tin chùa
    ResponseEntity<String> createChua(Chua chua);

    //xoá thông tin chùa
    ResponseEntity<?> deleteChua(int id);

    //sửa thông tin chùa
    ResponseEntity<?> editChua(int id, String tenChua, LocalDate ngayThanhLap, String diaChi);
}
