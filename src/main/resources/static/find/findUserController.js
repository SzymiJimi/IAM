var app = angular.module('app',[]);
app.controller('findUserController', ['$scope','$http','findUserService', function($scope, $http, findUserService) {


    $scope.submitForm = function(){
        var url = "http://localhost:8090/user/find";

        var config = {
            headers : {
                Accept: undefined
            }
        };

        var value = $scope.value;
        $scope.postResult=[];

        findUserService.findUserInDB(url, value, config).then(function (result) {
            angular.copy(result, $scope.postResult);
            $scope.response="Znaleziono użytkownika!";

        });



    }
}]);

