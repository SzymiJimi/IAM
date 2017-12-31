(function () {
    'use strict';
    var app= angular.module('app',[]);
    app.controller('findApplicationController', ['$scope','$http','findApplicationService', function($scope, $http, findApplicationService) {


        $scope.submitForm = function(){
            var url = "http://localhost:8090/application/find";

            var config = {
                headers : {
                    Accept: undefined
                }
            };

            var value = $scope.type;
            $scope.postResult=[];

            findApplicationService.findApplicationInDB(url, value, config).then(function (result) {
                angular.copy(result, $scope.postResult);
                if($scope.postResult.length===0)
                {
                    $scope.response="Nie znaleziono wniosków o podanym typie!";
                }else{
                    $scope.response="Znaleziono wnioski!";
                }
            });



        }
    }])
})();

