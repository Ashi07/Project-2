app.factory('JobService',function($http){
	var jobService={}
	
	jobService.addJob=function(job){
		var url="http://localhost:8084/Project2middleware/addjob" ;
		return $http.post(url,job);
	}
	
	jobService.getAllJobs=function(){
		var url="http://localhost:8084/Project2middleware/getalljobs" ;
		return $http.get(url);
	}
	
	return jobService;
})