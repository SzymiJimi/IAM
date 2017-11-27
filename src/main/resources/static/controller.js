var app = angular.module('app', []);
app.controller('postcontroller', function($scope, $http, $location) {
    $scope.submitForm = function(){
        var url = "http://localhost:8090/user/add";
        var url1 =  $location.absUrl() + "postcustomer";

        console.log(url1);

        var config = {
            headers : {
                Accept: 'text/html'
            }
        }

        var data = {
            username: $scope.username,
            name: $scope.name,
            surname: $scope.surname,
            email: $scope.email,
            role: 'Admin'
        };

        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = response.data;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });

        $scope.firstname = "";
        $scope.lastname = "";
    }
});

