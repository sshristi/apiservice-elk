package com.shristi.sb.elk.service;

import java.io.IOException;
import java.util.List;

import com.shristi.sb.elk.model.Blog;

public interface BlogMongoDBService {

	public boolean addBlogsFromElk() throws IOException;
	public List<Blog> getAllBlogs();
	public boolean deleteBlogs(String name);
}
