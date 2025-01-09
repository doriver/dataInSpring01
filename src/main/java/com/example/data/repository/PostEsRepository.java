package com.example.data.repository;

import com.example.data.entity.es.PostEs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostEsRepository extends ElasticsearchRepository<PostEs, String> {
    List<PostEs> findByContent(String content, Pageable pageable);
}
