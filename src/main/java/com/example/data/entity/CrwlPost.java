package com.example.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrwlPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String category;
    private String userNickname;
    private String title;

    @Column(length = 8000)
    private String content;
    private int viewCount;
    private int likeCount;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime updatedAt; // insertable = false 안해주면 db에 null로 들어감, 아마 jpa가 필드 값을 명시적으로 설정하지 않으면 null로 처리해서 그런듯

}
