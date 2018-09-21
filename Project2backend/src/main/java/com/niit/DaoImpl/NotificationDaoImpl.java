package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.NotificationDao;
import com.niit.model.Notification;

@Repository
@Transactional
public class NotificationDaoImpl implements NotificationDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void addNotification(Notification notification) {
	  
		Session session=sessionFactory.getCurrentSession();
		session.save(notification);
		
		
	}

}
