package com.example.QlPhatTu.QlPhatTuService.InterfaceService;

import org.springframework.http.ResponseEntity;

public interface IdonDangKyServiec {
    //Tạo đơn đăng Ký
    ResponseEntity<String> createDonDangKy(String ten, String phapDanh, String sdt, String email, int daoTrangId);

    //Quản Lý dơn đang ký
    ResponseEntity<?> QuanLyDon(int id, int trangThai,String nguoiXuLy,String lyDoKhongThamGia);


}
