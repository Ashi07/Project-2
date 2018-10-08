/**
 * 
 */
app.controller("NotificationCtrl",function($scope,NotificationService,$routeParams,$location,$rootScope){
	var id=$routeParams.id;
	NotificationService.getnotification(id).then(function(response){
		$scope.notification=response.data
		
	},function(response){
		if(response.status==401)
			$location.path="/login"
		
	})
	
	NotificationService.updateNotification(id).then(function(response){
		getNotificationNotViewed()
	},function(response){
		if(response.status==401)
			$location.path="/login"
		
	})
	
	function getNotificationNotViewed(){
		NotificationService.getnotificationnotviewed(id).then(function(response){
			$rootScope.notifications=response.data
			$rootScope.notificationCount=$rootScope.notifications.length 
		},function(response){
			if(response.status==401)
				$location.path="/login"
		})
	}
})