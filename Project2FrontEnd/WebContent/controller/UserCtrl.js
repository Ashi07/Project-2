/**
 * 
 */
app.controller('UserCtrl',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	$scope.registeration=function(user){
		UserService.registeration(user).then(function(response){
			alert("registered successfully..pleease login")
			$location.path('/login')
		},
		function(response){
			$scope.error=response.data;
		})
	}
	
	$scope.login=function(user){
		UserService.login(user).then(function(response){
			$cookieStore.put('userDetails',response.data)
			$rootScope.user=response.data
			$location.path('/home')
		},function(response){
			$scope.error=response.data;
		})
	}
	
	$scope.updateuser=function(user){
		UserService.updateuser(user).then(function(response){
			$rootScope.user=response.data
			$cookieStore.put('userDetails',response.data)
			alert("profile is updated successfully")
		},function(response){
			if(response.status==401)
				$location.path("/login")
			$scope.error=response.data;
		})
	}
	
	
})