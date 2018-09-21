package com.niit.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.jobDao;
import com.niit.model.Job;

@Repository
@Transactional
public class jobDaoImpl implements jobDao
{
	@Autowired
 private SessionFactory sessionFactory;
	
	public void saveJob(Job job) 
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
		
		
	}

	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Job");//HQL
        List<Job> jobs=query.list();
        return jobs;
	}

}
