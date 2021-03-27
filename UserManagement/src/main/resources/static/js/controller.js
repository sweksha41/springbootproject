
app.controller('usersController', function($scope, $http, $timeout, $window) {

	$scope.form = {
		id : -1,
		firstName : "",
		lastName : "",
		userName : "",
		password : "",
		cnfpassword : ""
	};
	
	$scope.headingTitle = "Register";
	$scope.errorMsg = "";
	$scope.successMsg = "";
	
	

	// HTTP POST/PUT methods for add/edit employee
	$scope.registerUser = function() {

		var method = "";
		var url = "";
		if ($scope.form.id == -1) {
			// Id is absent so add employee - POST operation
			method = "POST";
			url = '/users/register';
		} else {
			// If Id is present, it's edit operation - PUT operation
			method = "PUT";
			url = 'employees/' + $scope.form.id;
		}

		if (validate()) {
			$http({
				method : method,
				url : url,
				data : angular.toJson($scope.form),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).then(_success, _error);
		}
	};

	$scope.loginUser = function() {
		method = "POST";
		url = '/users/authenticate';

		$http({
			method : method,
			url : url,
			data : angular.toJson($scope.form),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(_authenticationSuccess, _authenticationError);
	};

	function validate() {
		var valid = true;
		var message = "";
		if ($scope.form.password == "") {
			valid = false;
			message = "Password cannot be blank";
		} else if ($scope.form.username == "") {
			valid = false;
			message = "Username cannot be blank";
		} else if ($scope.form.password != $scope.form.cnfpassword) {
			valid = false;
			message = "Password doesn't match";
		}
		if (!valid)
			alert(message);

		return valid;
	}
	

	function _authenticationSuccess(response) {
		$scope.successMsg = "User loggedin";

		$window.localStorage.setItem('currUsername', response.data.username);
		$window.localStorage.setItem('currUserAuthority',
				response.data.authority);
		$window.localStorage.setItem('token', response.data.token);

		// $window.localStorage.currentUser = { username:
		// response.data.username, token: response.data.token };

		// add jwt token to auth header for all requests made by the $http
		// service
		$http.defaults.headers.common['Authorization'] = 'Bearer '
				+ response.data.token;
		$window.location.href = '/views/bookmanager.html';

	}

	function _authenticationError(response) {
		$scope.successMsg = "Authentication failed";
	}

	function _success(response) {
		$scope.successMsg = "Registered Successfully. Login to continue";
		_refreshPageData();
		_clearForm()
	}

	function _error(response) {
		$scope.errorMsg = response.statusText;
	}

	/* Private Methods */
	// HTTP GET- get all employees collection
	function _refreshPageData() {
		// $http({
		// method : 'GET',
		// url : 'employees'
		// }).then(function successCallback(response) {
		// $scope.employees = response.data.employees;
		// }, function errorCallback(response) {
		// console.log(response.statusText);
		// });
	}

	// Clear the form
	function _clearForm() {
		$scope.form.firstName = "";
		$scope.form.lastName = "";
		$scope.form.userName = "";
		$scope.form.password = "";
		$scope.form.cnfpassword = "";
		$scope.form.id = -1;
	}

	
	$scope.getAllUsers = function() {
		// check if control is coming here

		var method = "GET";
		var url = "/users/getall";
		var authorization = 'Bearer ' + $window.localStorage.getItem('token');

		$http({
			method : method,
			url : url,
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : authorization
			}
		}).then(onGetUsers, _error);
	};
	
	
	$scope.getAllBooks = function() {
		// check if control is coming here

		var method = "GET";
		var url = "/books/getall";
		var authorization = 'Bearer ' + $window.localStorage.getItem('token');

		$http({
			method : method,
			url : url,
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : authorization
			}
		}).then(onGetBooks, _error);
	};

	

	$scope.getUserDetails = function(userId) {

		var userBookDetails = $scope.userBookMap[userId];
		
		$scope.$parent.userBookList = userBookDetails.userBooks;
		$scope.$parent.userFname = userBookDetails.firstName;
		
	};
	
	$scope.getBookDetails = function(bookId)
	{
		
	};
	
	$scope.loadBookDetails = function(book)
	{
		$scope.$parent.bookId = book.bookId;
		$scope.$parent.bookName = book.bookName;
		$scope.$parent.author = book.author;
		$scope.$parent.form_availability = book.availableCount;
		$scope.$parent.form_price = book.bookPrice;
	};
	
	$scope.editBookDetails = function()
	{
		var book = new Object();
		var bookAvailable = angular.element(document.getElementById("bookAvail"));  
		var bookPrice = angular.element(document.getElementById("bookPrice"));  
		book.bookId = $scope.$parent.bookId;
		book.availableCount = bookAvailable.val();
		book.bookPrice = bookPrice.val();
		
		var method = "PUT";
		var url = "/books/edit";
		var authorization = 'Bearer ' + $window.localStorage.getItem('token');

		$http({
			method : method,
			url : url,
			data : angular.toJson(book),
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : authorization
			}
		}).then(onEditBooks, _error);
	}
	
	$scope.generateFine = function(userBook)
	{
		var method = "PUT";
		var url = "/userbooks/generatefine";
		var authorization = 'Bearer ' + $window.localStorage.getItem('token');
		
		$http({
			method : method,
			url : url,
			data : angular.toJson(userBook),
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : authorization
			}
		}).then(onGenerateFine, _error);
	};
	
	
	function onGetUsers(response) {
		// $location.path('users').search({jsonData: response.data });
		$scope.libraryUsers = response.data;

		$scope.userBookMap = {};
		angular.forEach($scope.libraryUsers, function(user) {
			var userObj = {};

			userObj.firstName = user.firstName;
			userObj.lastname = user.lastName;

			var userBooks = [];
			angular.forEach(user.userBooks, function(userBook) {
				var bookObj = {};

				bookObj.bookId = userBook.book.bookId;
				bookObj.bookName = userBook.book.bookName;
				bookObj.author = userBook.book.author;
				bookObj.issuedDate = userBook.issuedDate;
				bookObj.quantity = userBook.bookCount;
				bookObj.fine = userBook.bookFine;
				bookObj.user = user;

				if (userBook.returnedDate == null)
					userBook.returnedDate = '';

				bookObj.returnedDate = userBook.returnedDate;
				userBooks.push(bookObj);
			});
			userObj.userBooks = userBooks;

			$scope.userBookMap[user.userId] = userObj;
		});
	}
	
	function onGetBooks(response)
	{
		$scope.libraryBooks = response.data;
	}
	
	function onEditBooks(response)
	{
		
	}
	
	function onGenerateFine(response)
	{
		var responseData = response.data;
		
		var fine = responseData.bookFine;
		var bookId = responseData.book.bookId;
		
		var userBookList = $scope.$parent.userBookList;
		
		for(var i =0; i< userBookList.length; i++)
		{
			if(userBookList[i].bookId = bookId)
			{
				$scope.$parent.userBookList[i].fine = fine;
				break;
			}
		}
		if(fine == 0)
			alert("No overdue fine for this book.");
		else
			alert("Overdue fine: "+ fine);
		
	}

});

/*
app.controller('userBooksController', function($scope, $http, $timeout, $window) {

	$scope.getUserDetails = function(userId) {

		var userBookDetails = $scope.userBookMap[userId];
		
		$scope.userBookList = userBookDetails.userBooks;
		$scope.userFname = userBookDetails.firstName;
		
	//	$rootScope.userBookList = userBookDetails.userBooks;
		//$rootScope.userFname = userBookDetails.firstName;
		
	};

});*/


app.controller('rolesController', function($scope) {
	$scope.headingTitle = "Roles List";
});
