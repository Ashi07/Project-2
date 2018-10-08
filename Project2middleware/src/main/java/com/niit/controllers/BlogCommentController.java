package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.Dao.BlogCommentDao;
import com.niit.Dao.UserDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class BlogCommentController {
	
	@Autowired
	BlogCommentDao blogCommentDao;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/addcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogPost blogPost,@RequestParam String commentText,HttpSession session)
	{
		
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		BlogComment blogComment=new BlogComment();
		blogComment.setBlogPost(blogPost);
		blogComment.setCommentText(commentText);
		User user=userDao.getUser(email);
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		blogCommentDao.addBlogComment(blogComment);
		
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/addcomment/getcomments/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogComments(@PathVariable int blogPostId,HttpSession session){
		System.out.println("comments");
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogComment> comments=blogCommentDao.getBlogComments(blogPostId);
		return new ResponseEntity<List<BlogComment>>(comments,HttpStatus.OK);
	}
	

}
