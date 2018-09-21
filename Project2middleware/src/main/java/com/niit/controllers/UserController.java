package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class UserController 
{
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registeration(@RequestBody User user)
	{
		try
		{
			if(!userDao.isEmailUnique(user.getEmail()))
			{
				ErrorClazz errorClazz=new ErrorClazz(2,"Email already exist");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			else
			{
		userDao.registeration(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
		catch(Exception e)
		{
			ErrorClazz errorClazz=new ErrorClazz(1,"something went wrong"+e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.PUT)
	public ResponseEntity<?> userLogin(@RequestBody User user,HttpSession session)
	{
		User validUser=userDao.login(user);
		if(validUser==null)
		{
			ErrorClazz errorClazz=new ErrorClazz(4,"Invalid login Credentials");
			
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
	    
		else
		{
			validUser.setOnline(true);
			userDao.updateUser(validUser);
			session.setAttribute("loggedInUser", validUser.getEmail());
			System.out.println("sessionId"+session.getId());
			System.out.println("session creation time:"+session.getCreationTime());
			System.out.println("Session Attribute"+session.getAttribute("loggedInUser"));
		
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session)
	{
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null)
		{
			ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorised access...please login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		user.setOnline(false);
		session.removeAttribute("loggedInUser");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updateuser",method=RequestMethod.PUT)
	public ResponseEntity<?> updateuser(@RequestBody User user,HttpSession session)
	{

		String email=(String)session.getAttribute("loggedInUser");
		if(email==null)
		{
			ErrorClazz errorClazz=new ErrorClazz(5,"Unable to update user");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		try
		{
		userDao.updateUser(user);
		}
		catch(Exception e)
		{

			ErrorClazz errorClazz=new ErrorClazz(5,"Unable to update user");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
}
