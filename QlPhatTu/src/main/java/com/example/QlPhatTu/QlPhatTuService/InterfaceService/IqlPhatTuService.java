package com.example.QlPhatTu.QlPhatTuService.InterfaceService;

import com.example.QlPhatTu.entity.Chua;
import com.example.QlPhatTu.entity.DaoTrang;
import com.example.QlPhatTu.entity.KieuThanhVien;
import com.example.QlPhatTu.entity.PhatTu;
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

    //Tạo đơn đăng Ký
    ResponseEntity<String> createDonDangKy(String ten, String phapDanh, String sdt, String email, int daoTrangId, int nguoiSuLy);

    //Quản Lý dơn đang ký
    ResponseEntity<?> QuanLyDon(int id, int trangThai, int nguoiSuLy);

    //đăng nhập tài khoản
    ResponseEntity<String> dangNhap(String email, String password);

    //Thay đổi mật khẩu
    ResponseEntity<String> doiMatKhau(String email, String newpassword, String oldpass);

    //tạo phat tu dao trang
    ResponseEntity<?> createPhatTuDaoTrang(int phatTuId, int daoTrangId, String lyDoKhongThamGia);

    //xoa phattudaotrang
    ResponseEntity<?> deletePhatTuDaoTrang(int phatTuDaoTrangId);

    // in những phật tử đã tham gia đạo trang
    Page<PhatTu> inPhatTuDaoTrang(int numbberPage, int sizePage);

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

    //lấy tát cả thông tin chùa
    Page<Chua> getAllChua(int numberPgae, int sizePage);

    //tạo thông tin chùa
    ResponseEntity<String> createChua(Chua chua);

    //xoá thông tin chùa
    ResponseEntity<?> deleteChua(int id);

    //sửa thông tin chùa
    ResponseEntity<?> editChua(int id, String tenChua, LocalDate ngayThanhLap, String diaChi);

    //tạo chức danh thành viên
    ResponseEntity<?> createKieuThanhVien(KieuThanhVien kieuThanhVien);
}
