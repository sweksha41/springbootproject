//var app=angular.module('app',[]);

//var myapp = angular.module("app", []);


app.controller('userBooksController', function($scope, $http, $timeout, $window) {

	$scope.getUserDetails = function(userId) {

		var userBookDetails = $scope.userBookMap[userId];
		
		$scope.userBookList = userBookDetails.userBooks;
		$scope.userFname = userBookDetails.firstName;
		
	//	$rootScope.userBookList = userBookDetails.userBooks;
		//$rootScope.userFname = userBookDetails.firstName;
		
	};

});

