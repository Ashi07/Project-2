package com.niit.Dao;

import java.util.List;

import com.niit.model.BlogPost;

public interface BlogDao 
{
	
void  saveBlog(BlogPost blogPost);
List<BlogPost> getApprovedBlogs();
List<BlogPost> getBlogsWaitingForApproval();
BlogPost getBlogPost(int id);
void blogUpdate(BlogPost blogPost);
void deleteBlog(BlogPost blogPost);

}
