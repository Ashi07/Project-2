/**
 * 
 */
app.factory('UserService',function($http){
	
	var userService={}
	userService.registeration=function(user){
		var url="http://localhost:8084/Project2middleware/register";
	return	$http.post(url,user);
	}
	
	userService.login=function(user){
		var url="http://localhost:8084/Project2middleware/login";
		return $http.put(url,user);
	}
	
	userService.logout=function(){
		var url="http://localhost:8084/Project2middleware/logout";
		return $http.put(url);
	}
	
	userService.updateuser=function(user){
		var url="http://localhost:8084/Project2middleware/updateuser";
		return $http.put(url,user);
	}
	return userService;
})