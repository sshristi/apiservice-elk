package com.shristi.sb.elk.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.shristi.sb.elk.model.Blog;

@Service
public class BlogMongoDBServiceImpl implements BlogMongoDBService {

//	@Autowired
//	BlogRepository blogRepository;
	@Autowired
	BlogElasticSearchService blogElasticSearchService;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public boolean addBlogsFromElk() throws IOException {
		Iterable<Blog> blogs = blogElasticSearchService.getAllBlogs();
		if(blogs == null)
			return false;
		for(Blog blog: blogs)
			mongoTemplate.save(blog);
		return true;
	}

	@Override
	public List<Blog> getAllBlogs() {
		return mongoTemplate.findAll(Blog.class);
	}

	@Override
	public boolean deleteBlogs(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		DeleteResult deleted = mongoTemplate.remove(query, Blog.class);
		if(deleted == null)
			return false;
		return true;
	}
	
	
}
