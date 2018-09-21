package com.niit.Dao;

import com.niit.model.User;

public interface UserDao
{
	void registeration(User user);
	public boolean isEmailUnique(String email);
	
	User login(User user);
	void updateUser(User user);
	User getUser(String email);
	 

}
