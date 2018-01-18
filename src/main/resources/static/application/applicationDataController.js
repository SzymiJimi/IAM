var app = angular.module('app',  ['ngMaterial', 'ngMessages']);
app.config(function($locationProvider, $mdThemingProvider) {
    $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
    $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
    $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
    $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});


app.controller('applicationDataController', ['$scope', '$location', '$http', 'homeService' , function($scope, $location, $http, homeService) {

    var splitData = $location.path().split('/');
    var value = splitData[3];
    var url = "http://localhost:8090/application/show";

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

    $scope.postResult=[];

    $http.post(url, value, config).then(function (response) {
        $scope.postResult = response.data;
    });
}]);