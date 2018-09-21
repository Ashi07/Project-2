var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	
	
	$routeProvider
	.when('/',{templateUrl:'views/home.html'})
	.when('/registeration',{controller:'UserCtrl',templateUrl:'views/registerationform.html'})
	.when('/getallusers',{controller:'UserCtrl',templateUrl:'views/getusers.html'})
	.when('/updateuser',{controller:'UserCtrl',templateUrl:'views/updateuser.html'})
	.when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
	.when('/addjob',{controller:'JobCtrl',templateUrl:'views/jobForm.html'})
	.when('/getalljobs',{controller:'JobCtrl',templateUrl:'views/listofjobs.html'})
	.when('/addBlog',{controller:'BlogPostCtrl',templateUrl:'views/BlogpostForm.html'})
	.when('/getBlogs',{controller:'BlogPostCtrl',templateUrl:'views/ApprovedBlogs.html'})
	.when('/getBlog/:Id',{controller:'BlogInDetailCtrl',templateUrl:'views/BlogInDetail.html'})
	.when('/blogswaitingforapproval',{controller:'BlogPostCtrl',templateUrl:'views/WaitingForApproval.html'})
	.when('/getBlogsWaitingForApproval/:Id',{controller:'BlogInDetailCtrl',templateUrl:'views/ApprovalForm.html'})
	

	.otherwise({templateUrl:'views/home.html'})
})

app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.user==undefined)
		$rootScope.user=$cookieStore.get('userDetails')
		
        $rootScope.logout=function(){
		alert("successfully logged out")
		UserService.logout().then(function(){
			delete $rootScope.user
			$cookieStore.remove("userDetails")
			$location.path("/login")
		},
				function(){
			if($rootScope.user!=undefined)
				delete $rootScope.user
				$cookieStore.remove("userDetails")
				$location.path("/login")
				
		})
	}
})