app.controller('usersController', function($scope, $http) {
	
    
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
            url = '/v1/users/register';
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
    
    function validate()
    {
    	var valid = true;
    	var message = "";
    	if($scope.form.password == "")
		{
    		valid = false;
			message = "Password cannot be blank";
		}
    	else if($scope.form.userName == "")
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
    
});
 
app.controller('rolesController', function($scope) {
    $scope.headingTitle = "Roles List";
});


