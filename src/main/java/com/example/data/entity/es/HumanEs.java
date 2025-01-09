package com.example.data.entity.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//@Document(indexName = "humans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HumanEs {
    @Id
    private String humanId;

    private String nickname;
    private int age;
}
