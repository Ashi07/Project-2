package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.NotificationDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Notification;

@Controller
public class NotificationController 
{
	@Autowired
	private NotificationDao notificationDao;
	
	@RequestMapping(value="/notifications",method=RequestMethod.GET)
	public ResponseEntity<?> getnotificationnotviewed(HttpSession session)
	{
		System.out.println("Notificationcontroller");

		   String email=(String)session.getAttribute("loggedInUser");
		   if(email==null)
		   {
			   ErrorClazz errorClazz=new ErrorClazz(6,"Unauthorised access..please login");
			   return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		   }
		   List<Notification> notifications=notificationDao.getNotificationNotViewed(email);
		   return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}
	
	@RequestMapping(value="/notification/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getnotification(@PathVariable int id,HttpSession session)
	{
		System.out.println(id);
		String email=(String)session.getAttribute("loggedInUser");
		   if(email==null)
		   {
			   ErrorClazz errorClazz=new ErrorClazz(6,"Unauthorised access..please login");
			   return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		   }
		   Notification notification=notificationDao.getNotification(id);
		   return new ResponseEntity<Notification>(notification,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/updatenotifications/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> updatenotification(@PathVariable int id,HttpSession session)
	{
		System.out.println(id);
		String email=(String)session.getAttribute("loggedInUser");
		   if(email==null)
		   {
			   ErrorClazz errorClazz=new ErrorClazz(6,"Unauthorised access..please login");
			   return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		   }
		   notificationDao.updateNotification(id);
		   return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

}
