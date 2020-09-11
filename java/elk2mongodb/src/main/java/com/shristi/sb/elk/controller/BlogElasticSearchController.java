package com.shristi.sb.elk.controller;

import java.io.IOException;
import java.util.List;

import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shristi.sb.elk.constants.DataConstants;
import com.shristi.sb.elk.model.Blog;
import com.shristi.sb.elk.service.BlogElasticSearchService;

@RestController
public class BlogElasticSearchController {
	
	@Autowired
	BlogElasticSearchService blogElasticSearchService;
	
	@GetMapping(value = {"","/","/check"})
	public ResponseEntity<?> checkConnection() throws IOException{
		try {
			blogElasticSearchService.checkConnection();
			return new ResponseEntity<>(DataConstants.SUCCESS.value(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(DataConstants.FAILURE.value(),HttpStatus.BAD_REQUEST);
		}
//		finally {
//			System.out.println("client closed");
//		}
	}
	
	@PostMapping("/addblog")
	public ResponseEntity<?> addBlog(@RequestBody List<Blog> blogs){
		try {
			int result = blogElasticSearchService.addBlog(blogs);
			if(result == 0)
				return new ResponseEntity<>(DataConstants.DUPLICATE_DATA_SET.value(), HttpStatus.NOT_ACCEPTABLE);
			else if(result<0)
				return new ResponseEntity<>(DataConstants.PARTIALLY_DUPLICATE_DATA_SET.value(), HttpStatus.OK);
			else
				return new ResponseEntity<>(DataConstants.DATA_SET.value(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/count")
	public ResponseEntity<?> countBlogs(){
		try {
			long blogCount = blogElasticSearchService.countBlogs();
			return new ResponseEntity<>(blogCount,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("/show")
	public ResponseEntity<?> getAllBlogs(){
		try {
			Iterable<Blog> blogs = blogElasticSearchService.getAllBlogs();
			return new ResponseEntity<>(blogs,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBlogs(){
		try {
			DataConstants result = blogElasticSearchService.deleteBlogs()? DataConstants.SUCCESS : DataConstants.FAILURE;
			return new ResponseEntity<>(result.value(),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

		}
	}
}
