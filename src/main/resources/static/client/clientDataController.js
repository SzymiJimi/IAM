var app = angular.module('app', []);
app.config(function($locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    });


app.controller('clientDataController', ['$scope', '$location', '$http' , function($scope, $location, $http) {

        var splitData = $location.path().split('/');
        var value = splitData[3];
        var url = "http://localhost:8090/client/show";

        var config = {
            headers : {
                Accept: undefined
            }
        };

        $scope.postResult=[];

                $http.post(url, value, config).then(function (response) {
                    $scope.postResult = response.data;
                });
    }]);