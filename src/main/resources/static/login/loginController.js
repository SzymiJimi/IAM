var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.config(function($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller('loginController', function($scope, $http, $window) {


    $scope.dropDown= function(){
        document.getElementById("myDropdown").classList.toggle("show");
    };

    // do tąd

});

