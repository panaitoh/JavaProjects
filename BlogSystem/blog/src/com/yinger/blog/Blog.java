package com.yinger.blog;

public class Blog {

	private String id;
	private String title;
	private String content;
	private String categoryid;
	private String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategoryId() {
		return categoryid;
	}
	public void setCategoryId(String categoryId) {
		this.categoryid = categoryId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
