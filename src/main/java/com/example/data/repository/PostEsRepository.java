package com.example.data.repository;

import com.example.data.entity.es.PostEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostEsRepository extends ElasticsearchRepository<PostEs, String> {
}
