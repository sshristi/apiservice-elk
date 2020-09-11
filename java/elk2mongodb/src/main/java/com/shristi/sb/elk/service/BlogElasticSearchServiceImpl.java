package com.shristi.sb.elk.service;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shristi.sb.elk.model.Blog;
import com.shristi.sb.elk.repository.BlogRepository;

@Service
public class BlogElasticSearchServiceImpl implements BlogElasticSearchService{

	@Autowired
	BlogRepository blogRepository;
	
	RestHighLevelClient client = new RestHighLevelClient(
	        RestClient.builder(new org.apache.http.HttpHost("localhost", 9200, "http")));
	
	@Override
	public boolean checkConnection() throws IOException{
		return client.ping(RequestOptions.DEFAULT);	
	}
	
	@Override
	public int addBlog(List<Blog> blog) {
		long countBeforeAdding = countBlogs();
		Iterable<Blog> blogs = blogRepository.saveAll(blog);
		long countAfterAdding = countBlogs();
		if(countBeforeAdding == countAfterAdding)
			return 0;
		else if(countBeforeAdding + blog.size() > countAfterAdding)
			return (int) (countBeforeAdding-countAfterAdding);
		else
			return blog.size();
		
	}

	@Override	
	public long countBlogs(){
		return blogRepository.count();
	}
	
	@Override
	public Iterable<Blog> getAllBlogs() throws IOException{
		Iterable<Blog> blogs = blogRepository.findAll();
//		GetResponse response;
//		GetRequest getRequest;
//
//		for(Blog blog : blogs) {
//			getRequest= new GetRequest("blogs", blog.getId());
//			response = client.get(getRequest, RequestOptions.DEFAULT);
//			System.out.println(response.getFields().get("name"));
//		}
//		System.out.println();
		return blogs;
	}
	
	@Override
	public boolean deleteBlogs() {
		blogRepository.deleteAll();
		return true;
	}
}
