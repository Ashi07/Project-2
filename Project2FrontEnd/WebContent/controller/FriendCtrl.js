app.controller('FriendCtrl',function($scope,$location,FriendService){

	
	function getSuggestedUsers(){
		FriendService.getsuggestedusers().then(function(response){
			$scope.suggestedusers=response.data
			
		},function(response){
			if(response.status==401)
				{
				$location.path('/login')
				}
			
		})
	}
		
		$scope.sendFriendRequest=function(friendRequestToId){
			FriendService.sendFriendRequest(friendRequestToId).then(function(response){
				alert("Friend Request Sent")
				getSuggestedUsers()
			},function(response){
				if(response.status==401)
				{
				$location.path('/login')
				}
				
			})
		}
		
		function pendingrequest(){
			FriendService.getpendindrequest().then(function(response){
				$scope.pendingrequest=response.data
				
			},function(response){
				if(response.status==401)
				{
				$location.path('/login')
				}
				
			})
		}
		
		$scope.acceptrequest=function(request){
		FriendService.acceptrequest(request).then(function(response){
			alert("controller js")
			pendingrequest() 
			
		},function(response){
			if(response.status==401)
			{
			$location.path('/login')
			}
		})
		}
		
		$scope.rejectrequest=function(request){
			FriendService.rejectrequest(request).then(function(response){
				pendingrequest() 
				
			},function(response){
				if(response.status==401)
				{
				$location.path('/login')
				}
			})
			}
		
		FriendService.listOfFriends().then(function(response){
			$scope.friendsDetails=response.data 
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
		
		getSuggestedUsers()
		pendingrequest() 
	
	
})