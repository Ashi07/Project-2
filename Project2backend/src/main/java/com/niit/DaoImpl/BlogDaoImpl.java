package com.niit.DaoImpl;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.BlogDao;
import com.niit.model.BlogPost;

@Repository
@Transactional
public class BlogDaoImpl implements BlogDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void saveBlog(BlogPost blogPost)
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);
		
	}

	public List<BlogPost> getApprovedBlogs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approvalStatus=true");
		List<BlogPost> blogpost=query.list();
		return blogpost;
	}
	
	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		return blogPost;
	}
	public List<BlogPost> getBlogsWaitingForApproval() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approvalStatus=false");
		List<BlogPost> blogPosts=query.list();
		return blogPosts;
	}

	public void blogUpdate(BlogPost blogPost) {
		Session session =sessionFactory.getCurrentSession();
		session.update(blogPost);
				
		
	}

	public void deleteBlog(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(blogPost);
		
	}


}
