var app = angular.module('app', []);
app.controller('homeController', function($scope, $http, $window) {

    $scope.user={};
    $scope.role={};
    $scope.commands=[];
    $scope.response={};


    var url = "http://localhost:8090/user/get";

    var config = {
        headers : {
            Accept: undefined
        }
    };

    $http.get(url, config).then(function (response) {
                $scope.response= response;
                $scope.user = response.data;
                return $scope.user.roles;
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " +  response.statusText;
                return null;
            }).then(function (roles) {

        url = "http://localhost:8090/role/get";

        $http.post(url, roles ,config).then(function (response) {
            angular.copy(response.data, $scope.commands);
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    });

    $scope.selectCommand= function (idCommand){
        url = "http://localhost:8090/role/run/"+idCommand;
        $http.get(url, config).then(function (response) {
            $window.location.href =response.data;
        });
    }

});

