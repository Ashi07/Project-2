package com.niit.Project2backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Dao.BlogDao;
import com.niit.model.BlogPost;

public class BlogtestCase {
	
	static BlogDao blogDao=null;
	
	@BeforeClass
	public static void setup() throws Exception 
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogDao=(BlogDao)context.getBean("blogDao");
		
		
	}

	@Test
	public void deletetest() {
		BlogPost blogPost=blogDao.getBlogPost(242);
		blogDao.deleteBlog(blogPost);
		System.out.println("deleted");
		
		
	}

}
