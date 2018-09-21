app.controller("BlogInDetailCtrl",function($scope,$location,BlogPostService,$routeParams,$sce){
	var id=$routeParams.Id

	$scope.isRejected=false

	BlogPostService.getBlogs(id).then(function(response){
		$scope.blogPost=response.data
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		if(response.status==401)
			{
			$location.path('/login')
			}
		
	})
	
	$scope.approveBlogPost=function(blogPost){
		BlogPostService.approveBlogPost(blogPost).then(function(response){
			console.log(blogPost)
			$location.path('/blogswaitingforapproval')
			alert("Blog has been  Approved ")
		}
		,function(response){
			if(response.status==401)
			{
			$location.path('/login')
			}
		})
	}
	
	$scope.rejectBlogPost=function(blogPost){
		 
		BlogPostService.rejectBlogPost(blogPost).then(function(response){
			$location.path('/blogswaitingforapproval')
			alert("blog has been rejected")
		},function(response){
			if(response.status==401)
			{
			$location.path('/login')
			}
		})
	}
	
})