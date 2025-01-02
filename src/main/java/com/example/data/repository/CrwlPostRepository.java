package com.example.data.repository;

import com.example.data.entity.CrwlPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrwlPostRepository extends JpaRepository<CrwlPost, Long> {

//    List<CrwlPost> findTop30(); // 가져오는 개수 제한 , ByOrderByIdDesc

}
