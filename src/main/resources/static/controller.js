var app = angular.module('app', ['ngMaterial', 'ngMessages']);
// nie zapomnijcie w kwadratowe nawiasy dodać 'ngMaterial', 'ngMessages'
app.controller('homeController', function($scope, $http, $window, homeService) {

    var config = {
            headers : {
                Accept: undefined
            }
        };

    $scope.role={};
    $scope.commands=[];
    $scope.response={};


    $scope.loggedUser={};
    if(homeService.commands.length===0)
    {
        homeService.initiate();
    }

    $scope.commands=homeService.commands;
    $scope.loggedUser=homeService.user;

    $scope.selectCommand=function (idCommand) {
        console.log("Jestem tutaj");
        homeService.runSelectedCommand(idCommand);
    };

    $scope.dropDown= function(){
        document.getElementById("myDropdown").classList.toggle("show");
    };

    // do tąd

});

