package com.example.QlPhatTu.Repository;

import com.example.QlPhatTu.model.Entity.PhatTuDaoTrang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhatTuDaoTrangRepo extends JpaRepository<PhatTuDaoTrang,Integer> {
}
