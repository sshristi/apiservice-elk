package com.shristi.sb.elk.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@org.springframework.data.elasticsearch.annotations.Document(indexName = "blogs")
@org.springframework.data.mongodb.core.mapping.Document(collection = "BLOG")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
	@Id
	private String id;
	private String name;
	private String type;
	private String by;
	private float rating; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", name=" + name + ", type=" + type + ", by=" + by + ", rating=" + rating + "]";
	}
}
