package com.example.data.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class CrwlDTO {

    private String writer;
    private String title;
    private LocalDateTime createdAt;
    private String content;
    private String imgSrc;
    private int views;
    private int likes;
    private List<Map<String, Object>> postReplyLists;

}
