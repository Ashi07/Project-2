app.factory('BlogPostService',function($http){
	var blogPostService={}
	
	blogPostService.addBlogPost=function(blog){
	
	var url="http://localhost:8084/Project2middleware/saveBlog"
		return $http.post(url,blog)
	}
	
	blogPostService.getApprovedBlogs=function(){
		var url="http://localhost:8084/Project2middleware/approvedblogs"
		return $http.get(url)
	}
	
	blogPostService.getBlogs=function(id){
		var url="http://localhost:8084/Project2middleware/getblogs/"+id
		return $http.get(url)
	}
	
	blogPostService.blogsToBeApproved=function(){
		var url="http://localhost:8084/Project2middleware/waitingforapproval"
		return $http.get(url)
	}
	
	blogPostService.approveBlogPost=function(blogPost){
		
		console.log(blogPost)
		var url="http://localhost:8084/Project2middleware/approveBlogPost/"+blogPost.id
			return $http.put(url,blogPost)
	}
	
	blogPostService.rejectBlogPost=function(blogPost,rejectionReason){
		console.log(blogPost)
		
		var url="http://localhost:8084/Project2middleware/rejectBlogPost/"+blogPost.id+"?rejectionReason="+rejectionReason
		
			return $http.put(url,blogPost)
	}
	
	blogPostService.getnotificationnotviewed=function()
	{
		var url="http://localhost:8084/Project2middleware/notifications"
			return $http.get(url)
		
	}
	
	blogPostService.addBlogComment=function(blogPost,commentText){
		var url="http://localhost:8084/Project2middleware/addcomment?commentText="+commentText
		return $http.post(url,blogPost)
	}
	
	blogPostService.getallblogcomment=function(blogPostId){
		
		var url="http://localhost:8084/Project2middleware/addcomment/getcomments/"+blogPostId
		return $http.get(url)
		
	}
	
	
	
	return blogPostService;
})