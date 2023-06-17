package com.example.QlPhatTu.Repository;

import com.example.QlPhatTu.entity.Chua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuaRepo extends JpaRepository<Chua,Integer> {

}
