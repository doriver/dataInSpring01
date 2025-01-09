package com.example.data.dto;

import com.example.data.entity.es.Category;
import com.example.data.entity.es.ReplyEs;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostEsDTO {

    private String id;

    // 글쓴이 정보
    private String nickname;

    // 게시글 정보
    private String title;

    private String content;

    private Category category;

    private int viewCount;

    private int likeCount;

    private List<ReplyEs> replies;

    private int replyCount;

    private LocalDateTime createdAt;
}
