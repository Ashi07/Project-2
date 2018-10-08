package com.niit.Dao;

import java.util.List;

import com.niit.model.BlogComment;

public interface BlogCommentDao {
	
	public void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComments(int blogPostId);

}
