package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.UserDao;
import com.niit.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
   @Autowired
  private SessionFactory sessionFactory;
	public void registeration(User user) {
		try {
	Session session=sessionFactory.getCurrentSession();
	session.save(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public boolean isEmailUnique(String email) 
	{
		try
		{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=:email");
		query.setParameter("email", email);
		User user=(User)query.uniqueResult();
		if(user!=null)
		return false;
		else
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
			
		
		
	}
	public User login(User user) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from User where email=:email and password=:password");
			query.setParameter("email",user.getEmail());
			query.setParameter("password",user.getPassword());
			return (User)query.uniqueResult();
	
			
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void updateUser(User user)
	{
      try
      {
    	Session session=sessionFactory.getCurrentSession();
    	session.update(user);
      }
		catch(Exception e)
      {
			e.printStackTrace();
      }
	}
	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		return user;
	}

}
