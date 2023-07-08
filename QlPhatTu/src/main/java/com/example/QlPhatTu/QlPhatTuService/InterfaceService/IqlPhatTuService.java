package com.example.QlPhatTu.QlPhatTuService.InterfaceService;

import com.example.QlPhatTu.model.Entity.Chua;
import com.example.QlPhatTu.model.Entity.DaoTrang;
import com.example.QlPhatTu.model.Entity.KieuThanhVien;
import com.example.QlPhatTu.model.Entity.PhatTu;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface IqlPhatTuService {


    //in tất cả thông tin của các phật tử
    Page<PhatTu> getPhatTu(int page, int zisepage);

    // thêm thông tin của các phật tử
    ResponseEntity<String> addPhatTu(PhatTu phatTu);

    // xoá thông tin của các phật tử
    ResponseEntity<String> DeletePhatTu(int phatTuId);

    // sửa thông tin của các phật tử
    ResponseEntity<String> editPhatTu(PhatTu phatTu);

    // tìm thông tin phật tử theo tên , pháp danh , gioi tính và đã hoàn tực hay chưa
    Page<PhatTu> findPhatTu(String ten, String phapDanh, String gioiTinh, String hoantuc, int numberPage, int sizePage);
}
