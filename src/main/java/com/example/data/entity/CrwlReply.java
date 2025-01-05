package com.example.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude = "crwlPost")
public class CrwlReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;

    @ManyToOne
    @JoinColumn(name = "pid")
    private CrwlPost crwlPost;

    private String userNickname;

    @Column(length = 2000)
    private String content;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime updatedAt; // insertable = false 안해주면 db에 null로 들어감, 아마 jpa가 필드 값을 명시적으로 설정하지 않으면 null로 처리해서 그런듯

}
