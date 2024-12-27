package com.example.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sample02 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sid;

    @Column
    private String writer; // varchar(255)

    @Column(length = 500) // varchar(500)
    private String title;

    private int views;
    private int likes;
    private int comments;

    @Column(length = 10000) //  varchar(10000) , 숫자 너무 크면 오류남, length = 30000로 했을때 MySQL 데이터 타입 text로 잡힘, 5만도 text로 잡힘
    private String content;


}
