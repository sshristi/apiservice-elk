package com.shristi.sb.elk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shristi.sb.elk.constants.DataConstants;
import com.shristi.sb.elk.model.Blog;
import com.shristi.sb.elk.service.BlogMongoDBService;

@RestController
public class BlogMongoDBController {

	@Autowired
	BlogMongoDBService blogMongoDBService;
	
	@GetMapping("/migrate")
	public ResponseEntity<?> addBlogsFromElk(){
		try {
			DataConstants result = blogMongoDBService.addBlogsFromElk()?DataConstants.SUCCESS : DataConstants.EMPTY_INDEX;
			return new ResponseEntity<>(result.value(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/showmongo")
	public ResponseEntity<?> getAllBLogs(){
		try {
			List<Blog> blogs = blogMongoDBService.getAllBlogs();
			if(blogs == null) 
				return new ResponseEntity<>(DataConstants.EMPTY_COLLECTION.value(), HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(blogs, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deletemongo/{name}")
	public ResponseEntity<?> deleteBlogs(@PathVariable("name") String name){
		try {
			DataConstants result = blogMongoDBService.deleteBlogs(name)? DataConstants.DELETED : DataConstants.FAILURE;
			return new ResponseEntity<>(result.value(),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

		}
	}
}
