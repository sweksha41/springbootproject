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
    
    
    
    //HTTP POST/PUT methods for add/edit employee
    $scope.registerUser = function() {

        var method = "";
        var url = "";
        if ($scope.form.id == -1) {
            //Id is absent so add employee - POST operation
            method = "POST";
            url = '/users/register';
        } else {
            //If Id is present, it's edit operation - PUT operation
            method = "PUT";
            url = 'employees/' + $scope.form.id;
        }
        
        if(validate())
        {
	        $http({
	            method : method,
	            url : url,
	            data : angular.toJson($scope.form),
	            headers : {
	                'Content-Type' : 'application/json'
	            }
	        }).then( _success, _error );
        }
    };
    
    
    $scope.loginUser = function(){
    	 method = "POST";
         url = '/users/authenticate';
         
         $http({
	            method : method,
	            url : url,
	            data : angular.toJson($scope.form),
	            headers : {
	                'Content-Type' : 'application/json'
	            }
	        }).then( _authenticationSuccess, _authenticationError );
    };
    
    
    function validate()
    {
    	var valid = true;
    	var message = "";
    	if($scope.form.password == "")
		{
    		valid = false;
			message = "Password cannot be blank";
		}
    	else if($scope.form.username == "")
		{
    		valid = false;
			message = "Username cannot be blank";
		}
    	else if($scope.form.password != $scope.form.cnfpassword)
		{
			valid = false;
			message = "Password doesn't match";
		}
    	if(!valid)
    		alert(message);
    	
    	return valid;
    }
    
    function _authenticationSuccess(response) {
    	$scope.successMsg = "User loggedin";
    	
    	$window.localStorage.setItem('currUsername',response.data.username);
    	$window.localStorage.setItem('currUserAuthority',response.data.authority);
    	$window.localStorage.setItem('token',response.data.token);
    	
    	// $window.localStorage.currentUser = { username: response.data.username, token: response.data.token };

        // add jwt token to auth header for all requests made by the $http service
    	$http.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;
    	$window.location.href = '/views/bookmanager.html';
    	
    	/*method = "GET";
         url = '/views/bookmanager.html';
         var authorization = 'Bearer '+response.data.token;
    	
    	$http({
            method : method,
            url : url,
            headers : {
                'Content-Type' : 'application/json',
                'Authorization': authorization
            }
        }).then( _success, _error );*/
    	
//    	 $timeout(function() {
//             $location.path('bookmanager.html');
//             }, 5000);
    };
    
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
    //HTTP GET- get all employees collection
    function _refreshPageData() {
//        $http({
//            method : 'GET',
//            url : 'employees'
//        }).then(function successCallback(response) {
//            $scope.employees = response.data.employees;
//        }, function errorCallback(response) {
//            console.log(response.statusText);
//        });
    }
    
  //Clear the form
    function _clearForm() {
        $scope.form.firstName = "";
        $scope.form.lastName = "";
        $scope.form.userName = "";
        $scope.form.password = "";
        $scope.form.cnfpassword = "";
        $scope.form.id = -1;
    };
  
    $scope.getAllUsers = function() {
    	//check if control is coming here
    	
    	var method = "GET";
        var url = "/users/getall";
        var authorization = 'Bearer '+$window.localStorage.getItem('token');
    	
        $http({
            method : method,
            url : url,
            headers : {
                'Content-Type' : 'application/json',
                'Authorization':authorization
            }
        }).then( onGetUsers, _error );
    	
    	//alert(test);
    };
    
    $scope.getUserDetails = function(userId)
    {
    	debugger;
    	var userId = userId;
    }
    
    
    function onGetUsers(response)
    {
    	// $location.path('users').search({jsonData: response.data });
    	$scope.libraryUsers = response.data;
    }
    
    
});
 
app.controller('rolesController', function($scope) {
    $scope.headingTitle = "Roles List";
});


