package com.shristi.sb.elk.service;

import java.io.IOException;
import java.util.List;

import com.shristi.sb.elk.model.Blog;

public interface BlogElasticSearchService {

	boolean checkConnection() throws IOException;

	int addBlog(List<Blog> blog);

	long countBlogs();

	Iterable<Blog> getAllBlogs() throws IOException;

	boolean deleteBlogs();

}
