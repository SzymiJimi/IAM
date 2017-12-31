(function () {
    'use strict';
    var app= angular.module('app');
    app.controller('applicationController', function ($scope, $http) {

        $scope.acceptForm = function () {

            $scope.postMessage = "";
            var url = "http://localhost:8090/application/add";
            var config = {
                headers: {
                    Accept: 'text/html'
                }
            };

            var data = {
                reason: $scope.reason,
                type: $scope.type,
                user_UserId: $scope.user_UserId,
                accepted: $scope.accepted


            };

            $http.post(url, data, config).then(function (response) {
                $scope.postMessage = response.data;
            }, function error(response) {
                $scope.postMessage = "Error with status: " + response.statusText;
            });


        }
    })
})();














