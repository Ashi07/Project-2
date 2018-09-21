package com.niit.Dao;

import java.util.List;

import com.niit.model.Job;

public interface jobDao {
	void saveJob(Job job);
	List<Job>   getAllJobs();

}
