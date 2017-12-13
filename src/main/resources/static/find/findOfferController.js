(function () {
    'use strict';
    var app= angular.module('app', []);
    app.controller('findOfferController', ['$scope', '$http', 'findOfferService', function ($scope, $http, findOfferService) {


        $scope.submitForm = function () {
            var url = "http://localhost:8090/offer/find";

            var config = {
                headers: {
                    Accept: undefined
                }
            };

            var value = $scope.name;
            $scope.postResult = [];

            findOfferService.findOfferInDB(url, value, config).then(function (result) {
                angular.copy(result, $scope.postResult);
                $scope.response = "Znaleziono umowe!";

            });


        }
    }])
})();
