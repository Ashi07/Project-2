package com.niit.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.FriendDao;
import com.niit.model.Friend;
import com.niit.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao
{
	
 @Autowired
 private SessionFactory sessionFactory;
	
	
	public List<User> getSuggestedUsers(String email) {
		Session session=sessionFactory.getCurrentSession();
		
		
		     
		     NativeQuery query=session.createSQLQuery("select * from user_niit where email in"
				+ "( select email from user_niit where email!=:email "
				+ " minus "
				+ " (select FromId_email from Friend where ToId_email=:email "
				+ "   union "
				+ "  select ToId_email from Friend where FromId_email=:email))");
		     
		     query.setParameter("email", email);
		     query.setParameter("email", email);
		     query.setParameter("email", email);
		     query.addEntity(User.class);
		     return query.list();
	}

	public void addFriendRequest(Friend friend) {
		
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		
		
	}

	public List<Friend> getPendingRequests(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(" from Friend f where f.ToId.email=:email and f.status='P'");
		query.setParameter("email",email);
		List<Friend> pendingRequest=query.list();
		return pendingRequest;
	}

	public void acceptFriendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.update(friend);;
		
	}

	public void deleteFriendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(friend);
		
	}

	public List<User> listOfFriends(String email) {
		Session session=sessionFactory.getCurrentSession();
		
		Query query1=session.createQuery("select f.ToId from Friend f where f.FromId.email=:email and f.status='A'");
		query1.setParameter("email", email);
	
		List<User> list1=query1.list();
		
		Query query2=session.createQuery("select f.FromId from Friend f where f.ToId.email=:email and f.status='A'");
		query2.setParameter("email", email);
		
		List<User> list2=query2.list();
		
		list1.addAll(list2);
		
		return list1;
	}

}
