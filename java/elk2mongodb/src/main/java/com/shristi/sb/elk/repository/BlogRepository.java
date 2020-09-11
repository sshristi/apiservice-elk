package com.shristi.sb.elk.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shristi.sb.elk.model.Blog;

public interface BlogRepository extends ElasticsearchRepository<Blog, String>{

}
