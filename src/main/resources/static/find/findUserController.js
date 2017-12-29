(function () {
    'use strict';
    var app= angular.module('app',[]);
    app.controller('findUserController', ['$scope','$http','findUserService', function($scope, $http, findUserService) {


    $scope.submitForm = function(){
        var url = "http://localhost:8090/user/find";

        var config = {
            headers : {
                Accept: undefined
            }
        };

        var value = $scope.name;
        $scope.postResult=[];

        findUserService.findUserInDB(url, value, config).then(function (result) {
            angular.copy(result, $scope.postResult);
            if($scope.postResult.length===0)
            {
                $scope.response="Nie znaleziono użytkownika w bazie!";
            }else{
            $scope.response="Znaleziono użytkownika!";
            }
        });
    };

    $scope.selectUser = function(id){
         url = "http://localhost:8090/client/show/"+id;
         $window.location.href =url;
    }
}])
})();

