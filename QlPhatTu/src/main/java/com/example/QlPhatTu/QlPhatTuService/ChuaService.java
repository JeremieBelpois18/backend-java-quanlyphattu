package com.example.QlPhatTu.QlPhatTuService;

import com.example.QlPhatTu.QlPhatTuService.InterfaceService.IchuaService;
import com.example.QlPhatTu.Repository.ChuaRepo;
import com.example.QlPhatTu.Repository.PhatTuRepo;
import com.example.QlPhatTu.model.Entity.Chua;
import com.example.QlPhatTu.model.Entity.PhatTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChuaService implements IchuaService {

    @Autowired
    private ChuaRepo chuaRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;


    //lấy tát cả thông tin chùa
    @Override
    public Page<Chua> getAllChua(int numberPgae, int sizePage){
        PageRequest pageRequest = PageRequest.of(numberPgae, sizePage);
        return new PageImpl<>(chuaRepo.findAll(),pageRequest,chuaRepo.findAll().size());
    }

    //tạo thông tin chùa
    @Override
    public ResponseEntity<String> createChua(Chua chua) {
        String tenChua = chua.getTenChua().trim();
        if (phatTuRepo.checkTenChua(tenChua) == 1) {
            return ResponseEntity.badRequest().body(" Thông tin chùa đã tồn tại");
        }
        chua.setCapNhap(LocalDate.now());
        chua.setTenChua(tenChua);
        chuaRepo.save(chua);
        return ResponseEntity.ok("Tạo thành công");
    }

    //xoá thông tin chùa
    @Override
    public ResponseEntity<?> deleteChua(int id) {
        List<PhatTu> getPhatTu = phatTuRepo.findAll();
        for (PhatTu phatTu : getPhatTu) {
            if (phatTu.getChua().getId() == id) {//kiểm tra thông tin chùa có tồn tại hay không
                phatTu.getChua().setId(0);// không tồn tại thì sửa thành 0
            }
        }
        if(chuaRepo.existsById(id)){
            chuaRepo.deleteById(id);
            return ResponseEntity.ok("xoá thành công");
        }
        return ResponseEntity.badRequest().body("Thông tin chùa không tồn tại");
    }

    //sửa thông tin chùa
    @Override
    public ResponseEntity<?> editChua(int id, String tenChua, LocalDate ngayThanhLap, String diaChi){
        Optional<Chua> chua = chuaRepo.findById(id);
        if(chua.isPresent()){
            if(tenChua != null && ngayThanhLap == null || diaChi == null) {chua.get().setTenChua(tenChua);}
            if(ngayThanhLap != null && tenChua == null || diaChi == null){chua.get().setNgayThanhLap(ngayThanhLap);}
            if(diaChi != null && tenChua == null || ngayThanhLap == null){chua.get().setDiaChi(diaChi);}
            if(tenChua != null && ngayThanhLap != null && diaChi != null){
                chua.get().setNgayThanhLap(ngayThanhLap);
                chua.get().setDiaChi(diaChi);
                chua.get().setTenChua(tenChua);
            }
            chua.get().setCapNhap(LocalDate.now());
            chuaRepo.save(chua.get());
            return ResponseEntity.ok("Thay đổi thành công");
        }
        return ResponseEntity.badRequest().body("Thông tin không tồn tại");
    }
}
