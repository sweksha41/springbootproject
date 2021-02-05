app.controller('usersController', function($scope, $http) {
	$http.get('').then(function(response){
		$scope.response = response.data;
	});
    $scope.headingTitle = "User List";
});
 
app.controller('rolesController', function($scope) {
    $scope.headingTitle = "Roles List";
});
