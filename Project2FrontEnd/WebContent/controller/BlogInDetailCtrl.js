app.controller("BlogInDetailCtrl",function($scope,$location,BlogPostService,$routeParams,$sce){
	var id=$routeParams.Id



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
		blogPost.id=id;
		
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
		console.log(blogPost)
		BlogPostService.rejectBlogPost(blogPost,$scope.rejectionReason).then(function(response){
			console.log(blogPost)
			$location.path('/blogswaitingforapproval')
			alert("blog has been rejected")
		},function(response){
			if(response.status==401)
			{
			$location.path('/login')
			}
		})
	}
	
	$scope.showTextArea=function(){
		$scope.isRejected=!$scope.isRejected
	}
	
	$scope.addBlogComment=function(blogPost,commentText){
		if(commentText==undefined || commentText=="")
			alert("Please enter some comment.")
			else
				
		BlogPostService.addBlogComment(blogPost,commentText).then(function(response){
			alert("comment posted successfully")
			$scope.commentText=""
				$scope.error=""
					$scope.blogComment=response.data
		
		}
				,function(response){
			if(response.status==401)
			{
			$location.path('/login')
			}
			
		})
		
		$scope.getallblogcomment=function(blogPostId){
				BlogPostService.getallblogcomment(blogPostId).then(function(response){
					$scope.blogComments=response.data
					
				},function(response){
					if(response.status==401)
					{
					$location.path('/login')
					}
					
				})
			}
		
		
	}
	
})