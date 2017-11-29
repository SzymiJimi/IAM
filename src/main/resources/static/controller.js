var app = angular.module('app', []);
app.controller('postcontroller', function($scope, $http, $location) {

    $scope.submitForm = function(){
        var url = "http://localhost:8090/user/add";

        var config = {
            headers : {
                Accept: 'text/html'
            }
        }

        var data = {
            username: $scope.username,
            password: $scope.password,
            name: $scope.name,
            surname: $scope.surname,
            email: $scope.email,
            role: 'ROLE_USER'
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

