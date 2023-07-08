package com.example.QlPhatTu.QlPhatTuService;
import com.example.QlPhatTu.QlPhatTuService.InterfaceService.IqlPhatTuService;
import com.example.QlPhatTu.Repository.*;
import com.example.QlPhatTu.model.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class QlPhatTuService implements IqlPhatTuService {
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private DonDangKyRepo donDangKyRepo;

    @Autowired
    private PhatTuDaoTrangRepo phatTuDaoTrangRepo;

    @Autowired
    private ChuaRepo chuaRepo;

    @Autowired
    private DaoTrangRepo daoTrangRepo;
    @Autowired
    private KieuThanhVienRepo kieuThanhVienRepo;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;


    //in tất cả thông tin của các phật tử
    @Override
    public Page<PhatTu> getPhatTu(int page, int zisepage) {
        PageRequest pageRequest = PageRequest.of(page,zisepage);
        return new PageImpl<>(phatTuRepo.findAll(),pageRequest,phatTuRepo.findAll().size());
    }

    // thêm thông tin của các phật tử
    @Override
    public ResponseEntity<String> addPhatTu(PhatTu phatTu) {
        if (phatTuRepo.existsByEmail(phatTu.getEmail()) == 1 && phatTuRepo.existsBySDT(phatTu.getSoDienThoai()) == 1) {
            String xoaKhoangTrang = phatTu.getPhapDanh().trim();
            String xoaKhoangtTen = phatTu.getTen().trim();
            String xoaKhoangTSDT = phatTu.getSoDienThoai().trim();
            String xoaKhoangEmail = phatTu.getEmail().trim();
            phatTu.setNgayCapNhap(LocalDate.now());
            phatTu.setPhapDanh(xoaKhoangTrang);
            phatTu.setSoDienThoai(xoaKhoangTSDT);
            phatTu.setTen(xoaKhoangtTen);
            phatTu.setEmail(xoaKhoangEmail);
            if (isPasswordValid(phatTu.getMatKhau())) {
               // String encodedPassword = passwordEncoder.encode(phatTu.getMatKhau());
                phatTu.setMatKhau(phatTu.getMatKhau());
                phatTuRepo.save(phatTu);
                return ResponseEntity.ok("Thêm thành công");
            }
            return ResponseEntity.badRequest().body("Mật Khẩu hoặc thông tin chưa đúng");
        }
        return ResponseEntity.badRequest().body("Email và SDT đã tồn tại ");
    }



    // xoá thông tin của các phật tử
    @Override
    public ResponseEntity<String> DeletePhatTu(int phatTuId) {
        Optional<PhatTu> phatTu = phatTuRepo.findById(phatTuId);
        if (phatTu.isEmpty()) {
            return ResponseEntity.badRequest().body("Phật tử không tồn tại");
        }
        phatTu.get().setDaHoanTuc(1);
        phatTuRepo.save(phatTu.get());
        return ResponseEntity.ok("Xoá Thành Công");
    }

    // sửa thông tin của các phật tử
    @Override
    public ResponseEntity<String> editPhatTu(PhatTu phatTu) {
        if (phatTuRepo.existsById(phatTu.getId())) {
            phatTu.setNgayCapNhap(LocalDate.now());
            phatTuRepo.save(phatTu);
            return ResponseEntity.ok("Sửa thành công");
        }
        return ResponseEntity.badRequest().body("Sửa Thất Bại");
    }

    // tìm thông tin phật tử theo tên , pháp danh , gioi tính và đã hoàn tực hay chưa
    @Override
    public Page<PhatTu> findPhatTu(String ten, String phapDanh, String gioiTinh, String hoantuc, int numberPage, int sizePage) {
        PageRequest pageRequest = PageRequest.of(numberPage,sizePage);
        if (ten == null && phapDanh == null && gioiTinh == null && hoantuc ==null) {
            return new PageImpl<>(phatTuRepo.findAll(),pageRequest,phatTuRepo.findAll().size());
        }
        List<PhatTu> phatTuList = phatTuRepo.findPhatTu(ten, phapDanh,gioiTinh, hoantuc);
        return new PageImpl<>(phatTuList,pageRequest,phatTuList.size());
    }

    private boolean isPasswordValid(String passwords) {
        // Kiểm tra mật khẩu có chứa ít nhất một chữ số và một kí tự đặc biệt không
        String pattern = ("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]+$");
        return passwords.matches(pattern);
    }



    //đăng nhập tài khoản
//    @Override
//    public ResponseEntity<String> dangNhap(String email, String password) {
//        List<PhatTu> phatTu = phatTuRepo.findPhatTuEmailPass(email, password);
//        if (phatTu.get(0).getEmail().isEmpty() && phatTu.get(0).getMatKhau().isEmpty()) {
//            return ResponseEntity.badRequest().body("Tài Khoản hoặc Mật Khẩu Không Chính xác");
//        }
//
//        return ResponseEntity.ok("Đăng nhập thành công");
//    }
//
//    //dăng xuất
//    public ResponseEntity<?> dangXuat(String email){
//        if(phatTuRepo.existsByEmail(email) == 1){
//            return ResponseEntity.ok("Lỗi");
//        }
//        return  null;
//    }
//
//    //Thay đổi mật khẩu
//    @Override
//    public ResponseEntity<String> doiMatKhau(String email, String newpassword, String oldpass) {
//        PhatTu phatTu = phatTuRepo.findPassword(email);
//        int check = 1;
//        if (phatTu.getEmail().isEmpty()) {//kiểm tra email có tồn tại hay không
//            return ResponseEntity.badRequest().body("Email chưa dăng ký hoặc chưa đúng");
//        }
//        if (phatTu.getMatKhau().equals(oldpass)) {
//            check = 0;
//        } else {
//            return ResponseEntity.badRequest().body("Mật Khẩu cũ không đúng");
//        }
//        if (!isPasswordValid(newpassword)) {
//            return ResponseEntity.badRequest().body("Mật Khẩu phải có 8 k tự và chữ số và một kí tự đặc biệt ");
//        }
//        phatTu.setMatKhau(newpassword);
//        phatTuRepo.save(phatTu);
//        return ResponseEntity.ok("Thay đôi thành công");
//    }

}


