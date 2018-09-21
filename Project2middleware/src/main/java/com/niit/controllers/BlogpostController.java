package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.BlogDao;
import com.niit.Dao.UserDao;
import com.niit.model.BlogPost;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class BlogpostController 
{
  @Autowired
  private BlogDao blogDao;
  
  @Autowired
  private UserDao userDao;
  
  @RequestMapping(value="/saveBlog",method=RequestMethod.POST)
  public ResponseEntity<?> saveblog(@RequestBody BlogPost blogPost,HttpSession session)
  {
	   String email=(String)session.getAttribute("loggedInUser");
	   if(email==null)
	   {
		   ErrorClazz errorClazz=new ErrorClazz(6,"Unauthorised access..please login");
		   return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	   }
	   
	  blogPost.setPostedOn(new Date());
	  blogPost.setPostedBy(userDao.getUser(email));
	  try
	  {
		  blogDao.saveBlog(blogPost);
	  }
	  catch(Exception e)
	  {
		  ErrorClazz errorClazz=new ErrorClazz(6,"Unable to post blog..try again");
		   return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		  
	  }
	  
	  return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	   
	  
  }
  
  @RequestMapping(value="/approvedblogs",method=RequestMethod.GET)
	public ResponseEntity<?> getApprovedBlogs(HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> approvedBlogs=blogDao.getApprovedBlogs();
		return new ResponseEntity<List<BlogPost>>(approvedBlogs,HttpStatus.OK);
	}
  
  
  @RequestMapping(value="/getblogs/{id}",method=RequestMethod.GET)
  public ResponseEntity<?> getBlog(@PathVariable int id,HttpSession session)
  {
	  String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
  
		BlogPost blogPost=blogDao.getBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
}
  
  @RequestMapping(value="/waitingforapproval",method=RequestMethod.GET)
 	public ResponseEntity<?> Blogswaitingforapproval(HttpSession session){
 		String email=(String)session.getAttribute("loggedInUser");
 		if(email==null){
 			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
 			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
 		}
 		User user=userDao.getUser(email);
 		if(!user.getRole().equals("admin"))
 		{
 			ErrorClazz errorClazz=new ErrorClazz(5,"Access Denied");
 			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
 		}
 		List<BlogPost> blogsWaitingForApproval=blogDao.getBlogsWaitingForApproval();
 		return new ResponseEntity<List<BlogPost>>(blogsWaitingForApproval,HttpStatus.OK);
 	}
  
 
  @RequestMapping(value="/approveBlogPost",method=RequestMethod.PUT)
   public ResponseEntity<?> approvedBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
  {
	System.out.println("hello");
	  String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		if(!user.getRole().equals("admin"))
 		{
 			ErrorClazz errorClazz=new ErrorClazz(5,"Access Denied");
 			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
 		}
		System.out.println(blogPost);
		blogPost.setApprovalStatus(true);
		System.out.println(blogPost);
		
		blogDao.blogUpdate(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
  }
  
  @RequestMapping(value="/rejectBlogPost",method=RequestMethod.PUT)
  public ResponseEntity<?> rejectBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
 {
	 
	  String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		if(!user.getRole().equals("admin"))
		{
			ErrorClazz errorClazz=new ErrorClazz(5,"Access Denied");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		blogDao.deleteBlog(blogPost);
		return new ResponseEntity<Void>(HttpStatus.OK);
 }
  
  }
