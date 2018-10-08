package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.FriendDao;
import com.niit.Dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Friend;
import com.niit.model.User;

@Controller
public class FriendController {
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private UserDao userDao;
		
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
		public ResponseEntity<?> getAllSuggestedUsers(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<User> suggestedUsers=friendDao.getSuggestedUsers(email);
			return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
		}
	
	@RequestMapping(value="/friendRequest",method=RequestMethod.POST)
	public ResponseEntity<?>  friendRequest(@RequestBody User friendrequestToId,HttpSession session)
	{
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		User fromId=userDao.getUser(email);
		Friend friend=new Friend();
		friend.setFromId(fromId);
		friend.setToId(friendrequestToId);
		friend.setStatus('P');
		friendDao.addFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/pendingrequest",method=RequestMethod.GET)
	public ResponseEntity<?> pendingRequest(HttpSession session)
	{
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		List<Friend> pendingrequest=friendDao.getPendingRequests(email);
		return new ResponseEntity<List<Friend>>(pendingrequest,HttpStatus.OK);
		
	}

	@RequestMapping(value="/acceptrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> acceptRequest(@RequestBody Friend friend,HttpSession session){
		System.out.println("controller friend");
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		friend.setStatus('A');
		friendDao.acceptFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> rejectRequest(@RequestBody Friend friend,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		friendDao.deleteFriendRequest(friend);;
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/listoffriends",method=RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<User> friendsDetails=friendDao.listOfFriends(email);
		return new ResponseEntity<List<User>>(friendsDetails,HttpStatus.OK);
	}
	
	
	
}
