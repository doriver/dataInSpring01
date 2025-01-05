package com.example.data.repository;

import com.example.data.entity.es.HumanEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HumanEsRepository extends ElasticsearchRepository<HumanEs, String> {
    HumanEs findByNickname(String nickname);
}
