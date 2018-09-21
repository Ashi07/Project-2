app.controller('BlogPostCtrl',function($scope,$location,BlogPostService){
$scope.addBlogPost=function(blogPost){
	BlogPostService.addBlogPost(blogPost).then(function(response){
		alert("Blog has been inserted successfully,Please wait for the approval")
		$location.path('/home');
	},function(response){
		if(response.status==401)
			$location.path('/login')
		$scope.error=response.data;
	})
}

function getApprovedBlogs(){
	BlogPostService.getApprovedBlogs().then(function(response){
		$scope.blogsApproved=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
		
	})
}

function getBlogsToBeApproved(){
	BlogPostService.blogsToBeApproved().then(function(response){
		$scope.blogsToBeApproved=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
		
	})
}

getApprovedBlogs()
getBlogsToBeApproved()
	
})
