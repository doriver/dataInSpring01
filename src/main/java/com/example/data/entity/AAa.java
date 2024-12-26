package com.example.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AAa { // 테이블 aaa로 생김
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    // 데이터타입 bigint로 생김

    private String sTr; // 컬럼 s_tr로 생김
    // 데이터타입 varchar(255)로 생김
    
    private int iNt; // 컬럼 i_nt로 생김
    // 데이터타입 int로 생김
}
