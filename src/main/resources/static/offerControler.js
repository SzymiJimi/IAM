(function () {
    'use strict';
    var app= angular.module('app');
    app.controller('offerController', function ($scope, $http) {

        $scope.acceptForm = function () {

            $scope.postMessage = "";
            var url = "/offer/add";
            var config = {
                headers: {
                    Accept: 'text/html'
                }
            };

            var data = {
                idclient: $scope.idclient,
                information: $scope.information,
                insurancetype: $scope.insurancetype,
                name: $scope.name

            };

            $http.post(url, data, config).then(function (response) {
                $scope.postMessage = response.data;
            }, function error(response) {
                $scope.postMessage = "Error with status: " + response.statusText;
            });

            $scope.idclient = "";
            $scope.information = "";
        }
    })
})();

