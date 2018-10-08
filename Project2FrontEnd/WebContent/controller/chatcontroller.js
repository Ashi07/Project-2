app.controller('ChatController', function($scope,$rootScope,chatService)
{
	$scope.messages=[];
	$scope.message="";
	$scope.max=250;
	
	$scope.addMessage=function()
	{
		chatService.send($rootScope.user.email+":" +$scope.message);
		$scope.message="";
	};

	chatService.receive().then(null,null,function(message)
	{
		$scope.messages.push(message);
	});	

});
