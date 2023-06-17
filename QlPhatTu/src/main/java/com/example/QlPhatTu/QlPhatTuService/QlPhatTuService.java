package com.example.QlPhatTu.QlPhatTuService;
import com.example.QlPhatTu.QlPhatTuService.InterfaceService.IqlPhatTuService;
import com.example.QlPhatTu.Repository.*;
import com.example.QlPhatTu.entity.*;
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


    //Tạo đơn đăng Ký
    @Override
    public ResponseEntity<String> createDonDangKy(String ten, String phapDanh, String sdt, String email, int daoTrangId, int nguoiSuLy) {
        DonDangKy donDangKy = new DonDangKy();
        Optional<PhatTu> phatTu = phatTuRepo.findByTPSE(ten, phapDanh, sdt, email);
        Optional<DaoTrang> daoTrang = daoTrangRepo.findById(daoTrangId);
        // kiểm tra thông tin phật tử và đơn đã đăng ký hay chưa
        if (daoTrang.isPresent() && phatTu.isPresent() && phatTuRepo.existsByPhatTuId(phatTu.get().getId()) == 1 && phatTu.get().getDaHoanTuc() == 0) {
            donDangKy.setPhatTuId(phatTu.get());
            donDangKy.setTrangThaiDon(0);
            donDangKy.setNguoiSuLyId(nguoiSuLy);
            donDangKy.setDaoTrangId(daoTrang.get());
            donDangKy.setNgayGuiDon(LocalDate.now());
            donDangKyRepo.save(donDangKy);
            return ResponseEntity.ok("Chờ Duyệt");
        }
        return ResponseEntity.badRequest().body(" Thông tin không đúng hoặc phật tử đã hoàn tục Hoặc dơn đa tồn tại");
    }

    private boolean isPasswordValid(String password) {
        // Kiểm tra mật khẩu có chứa ít nhất một chữ số và một kí tự đặc biệt không
        String pattern = ("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]+$");
        return password.matches(pattern);
    }

    //Quản Lý dơn đang ký
    @Override
    public ResponseEntity<?> QuanLyDon(int id, int trangThai, int nguoiSuLy) {
        Optional<DonDangKy> getDonId = donDangKyRepo.findById(id);
        if (getDonId.isEmpty()) {
            return ResponseEntity.badRequest().body("Đơn không tồn tại");
        }
        getDonId.get().setTrangThaiDon(trangThai);
        getDonId.get().setNgayXuLy(LocalDate.now());
        getDonId.get().setNguoiSuLyId(nguoiSuLy);
        donDangKyRepo.save(getDonId.get());
        return ResponseEntity.of(getDonId);
    }


    //đăng nhập tài khoản
    @Override
    public ResponseEntity<String> dangNhap(String email, String password) {
        if (phatTuRepo.existsByEmail(email) == 0 && phatTuRepo.existsByPassword(password) == 0) {
            return ResponseEntity.ok("Đăng nhập thành công");
        }
        return ResponseEntity.badRequest().body("Tài Khoản hoặc Mật Khẩu Không Chính xác");
    }

    //Thay đổi mật khẩu
    @Override
    public ResponseEntity<String> doiMatKhau(String email, String newpassword, String oldpass) {
        PhatTu phatTu = phatTuRepo.findPassword(email);
        int check = 1;
        if (phatTu.getEmail().isEmpty()) {//kiểm tra email có tồn tại hay không
            return ResponseEntity.badRequest().body("Email chưa dăng ký hoặc chưa đúng");
        }
        if (phatTu.getMatKhau().equals(oldpass)) {
            check = 0;
        } else {
            return ResponseEntity.badRequest().body("Mật Khẩu cũ không đúng");
        }
        if (isPasswordValid(newpassword)) {
            phatTu.setMatKhau(newpassword);
            phatTuRepo.save(phatTu);
            return ResponseEntity.ok("Thay đôi thành công");
        }
        return ResponseEntity.badRequest().body("Mật Khẩu phải có 8 k tự và chữ số và một kí tự đặc biệt ");
    }
    //tạo phat tu dao trang
    @Override
    public ResponseEntity<?> createPhatTuDaoTrang(int phatTuId, int daoTrangId, String lyDoKhongThamGia){
            Optional<DaoTrang> opDaoTrang = daoTrangRepo.findById(daoTrangId);
            Optional<PhatTu> opPhatTu = phatTuRepo.findById(phatTuId);
            PhatTuDaoTrang phatTuDaoTrang = new PhatTuDaoTrang();
            if(opPhatTu.isPresent() && opDaoTrang.isPresent() ){
                if(phatTuRepo.findTrangThaiDon(opPhatTu.get().getId()) == 1 && lyDoKhongThamGia == null) {
                    phatTuDaoTrang.setPhatTu(opPhatTu.get());
                    phatTuDaoTrang.setDaoTrang(opDaoTrang.get());
                    phatTuDaoTrang.setLyDoKhongThamGia("0");
                    phatTuDaoTrang.setDaThamGia(1);
                    phatTuDaoTrangRepo.save(phatTuDaoTrang);
                    return ResponseEntity.ok("thêm thành công");
                }
                phatTuDaoTrang.setPhatTu(opPhatTu.get());
                phatTuDaoTrang.setDaoTrang(opDaoTrang.get());
                phatTuDaoTrang.setLyDoKhongThamGia(lyDoKhongThamGia);
                phatTuDaoTrang.setDaThamGia(0);
                phatTuDaoTrangRepo.save(phatTuDaoTrang);
                return ResponseEntity.ok("thêm thành công");

            }
        return ResponseEntity.ok("Phật tu hoặc thông tin đạo trang không tồn tại");
    }

    //xoa phattudaotrang
    @Override
    public ResponseEntity<?> deletePhatTuDaoTrang(int phatTuDaoTrangId){
        if(phatTuDaoTrangRepo.existsById(phatTuDaoTrangId)){
            phatTuDaoTrangRepo.deleteById(phatTuDaoTrangId);
        }
        return ResponseEntity.ok("Phat tu dao trang không tồn tại");
    }




    // in những phật tử đã tham gia đạo trang
    @Override
    public Page<PhatTu> inPhatTuDaoTrang(int numbberPage, int sizePage) {
        List<PhatTu> phatTuList = phatTuRepo.findPhatTuDaoTrang();
        PageRequest pageable = PageRequest.of(numbberPage,sizePage);
        return new PageImpl<>(phatTuList,pageable,phatTuList.size());
    }

    //Thêm event đạo trang
    @Override
    public ResponseEntity<?> createDaoTrang(String noiToChuc, LocalDate thoiGianToChuc, String noidung, int daKetThuc, int idtruTri, int idDaoTrang){
       if(noiToChuc != null || thoiGianToChuc != null || noidung != null && daKetThuc > 0 && idtruTri > 0 && idDaoTrang > 0){
           Optional<PhatTu> opPhatTu = phatTuRepo.findById(idtruTri);
           if(opPhatTu.isEmpty()){
               return ResponseEntity.ok("Tru Tri không tồn tại");
           }
           DaoTrang daoTrang = new DaoTrang();
           daoTrang.setNoiToChuc(noiToChuc);
           daoTrang.setThoiGianToChuc(thoiGianToChuc);
           daoTrang.setNoiDung(noidung);
           daoTrang.setPhatTu(opPhatTu.get());
           daoTrang.setDaKetThuc(daKetThuc);
           daoTrangRepo.save(daoTrang);
           return ResponseEntity.ok("Tạo buổi đạo trang thanh công");
       }
       return ResponseEntity.ok("Tru tri , dao trang và đã kết thúc chưa đúng");
    }
    //cập nhập buổi đạo trang
    @Override
    public ResponseEntity<?> updateDaoTrang(int daoTrangId){
        Optional<DaoTrang> daoTrang = daoTrangRepo.findById(daoTrangId);
        if(daoTrang.isEmpty()){
            return ResponseEntity.ok("Buổi đạo trang đã kết thúc hoặc không tồn tại");
        }
        daoTrang.get().setSoThanhVienThamGia(phatTuRepo.countnumberThamGia(daoTrangId));
        daoTrangRepo.save(daoTrang.get());
        return ResponseEntity.of(daoTrang);
    }

    //sua thông tin buổi đạo trang
    @Override
    public ResponseEntity<?> editDaoTrang(DaoTrang daoTrang){
        if(daoTrangRepo.existsById(daoTrang.getId())){
            daoTrangRepo.save(daoTrang);
            return ResponseEntity.ok("Thay đổi thành công");
        }
        return ResponseEntity.badRequest().body("Thông tin đạo trang không tồn tại");
    }


    //tìm đạo trang
    @Override
    public Page<DaoTrang> timDaoTrang(String daoTrangId, String noiToChuc, String daKetThuc, String thoiGian, int numberPage, int sizePage){
        PageRequest pageRequest = PageRequest.of(numberPage,sizePage);
        if(daoTrangId ==null && noiToChuc == null && daKetThuc == null && thoiGian == null){
            return new PageImpl<>(daoTrangRepo.findAll(),pageRequest,daoTrangRepo.findAll().size());
        }
        List<DaoTrang> daoTrangList =  daoTrangRepo.findDaoTrang(daoTrangId,noiToChuc, thoiGian,daKetThuc);
        return new PageImpl<>(daoTrangList,pageRequest,daoTrangList.size());
    }
    //xoa dạo trang
    @Override
    public ResponseEntity<?> xoaDaoTrang(int id) {
        Optional<DaoTrang> daoTrangOptional = daoTrangRepo.findById(id);

        if (daoTrangOptional.isPresent()) {
            DaoTrang daoTrang = daoTrangOptional.get();
            daoTrang.setPhatTu(null);
            daoTrangRepo.save(daoTrang);
            daoTrangRepo.deleteById(id);
            return ResponseEntity.ok("Xoá thành công");
        } else {
            return ResponseEntity.badRequest().body("Thông tin không tồn tại");
        }
    }

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

    //tạo chức danh thành viên
    @Override
    public ResponseEntity<?> createKieuThanhVien(KieuThanhVien kieuThanhVien){
        kieuThanhVienRepo.save(kieuThanhVien);
        return ResponseEntity.ok("tạo thành công");
    }

}


