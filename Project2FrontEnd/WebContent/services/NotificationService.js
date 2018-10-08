/**
 * 
 */

app.factory('NotificationService',function($http){
	var notificationService={}
	
	notificationService.getnotification=function(id){
		var url="http://localhost:8084/Project2middleware/notification/"+id
		return $http.get(url)
	}
	
	notificationService.getnotificationnotviewed=function()
	{
		var url="http://localhost:8084/Project2middleware/notifications"
			return $http.get(url)
		
	}
	
	notificationService.updateNotification=function(id)
	{
		var url="http://localhost:8084/Project2middleware/updatenotifications/"+id
		return $http.get(url)
	}
	
	return notificationService;
})