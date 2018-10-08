package com.niit.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.BlogCommentDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

@Repository
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void addBlogComment(BlogComment blogComment) {
		
		System.out.println("DAOIMPL message");
		
		Session session=sessionFactory.getCurrentSession();
		System.out.println("DAOIMPL sf message");
		try {
			
			
		session.save(blogComment);
	
		
		blogComment.setId(blogComment.getId());
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		
		
		
	}
	
	public List<BlogComment> getBlogComments(int blogPostId) {
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from BlogComment where blogPost.id=?");
		query.setInteger(0, blogPostId);
		List<BlogComment> blogComments=query.list();
		return blogComments;
	}

}
