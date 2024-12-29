package com.example.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrwlReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;

    @ManyToOne
    @JoinColumn(name = "pid")
    private CrwlPost crwlPost;

    private String userNickname;

    @Column(length = 1000)
    private String content;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

}
