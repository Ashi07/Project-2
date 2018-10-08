app.factory('FriendService',function($http){
	var friendService={}

	friendService.getsuggestedusers=function(){
		var url="http://localhost:8084/Project2middleware/suggestedusers"
			return $http.get(url)
	}
	
	friendService.sendFriendRequest=function(friendRequestToId){
		var url="http://localhost:8084/Project2middleware/friendRequest"
			return $http.post(url,friendRequestToId)
	}
	
	friendService.getpendindrequest=function(){
		var url="http://localhost:8084/Project2middleware/pendingrequest"
			return $http.get(url)
		
	}
	
	friendService.acceptrequest=function(request){
		alert("servicejs")
		var url="http://localhost:8084/Project2middleware/acceptrequest"
			return $http.put(url,request)
		
	}
	
	friendService.rejectrequest=function(request){
		var url="http://localhost:8084/Project2middleware/rejectrequest"
			return $http.put(url,request)
		
	}
	
	friendService.listOfFriends=function(pendingrequest){
		var url="http://localhost:8084/Project2middleware//listoffriends"
			return $http.get(url)
		
	}
	
	
	
	return friendService
})

