package com.example.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrwlPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    private String category;
    private String userNickname;
    private String title;

    @Column(length = 2000)
    private String content;

    private String imageSrc;

    private int viewCount;
    private int likeCount;

    @OneToMany(mappedBy = "crwlPost", cascade = CascadeType.ALL)
    private List<CrwlReply> replys = new ArrayList<>();

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime updatedAt; // insertable = false 안해주면 db에 null로 들어감, 아마 jpa가 필드 값을 명시적으로 설정하지 않으면 null로 처리해서 그런듯

    // 연관관계 메소드 추가
    public void addreply(CrwlReply crwlReply) {
        if (this.replys == null) {
            this.replys = new ArrayList<>();
        }
        this.replys.add(crwlReply);
        crwlReply.setCrwlPost(this);
    }
}
