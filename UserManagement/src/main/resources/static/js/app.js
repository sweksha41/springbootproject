var app = angular.module('app', ['ngRoute','ngResource']);

app.config(function($routeProvider){
    $routeProvider
        .when('/signup',{
            templateUrl: '/views/signup.html',
            controller: 'usersController'
        })
        .when('/signin',{
            templateUrl: '/views/signin.html',
            controller: 'usersController'
        })
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/userDetails',{
            templateUrl: '/views/userDetails.html',
            controller: 'usersController'
        })
         .when('/allBooks',{
            templateUrl: '/views/libraryBooks.html',
            controller: 'usersController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
    
 // use the HTML5 History API
   // $locationProvider.html5Mode(true);
});
