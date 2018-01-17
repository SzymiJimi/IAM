var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
    $http.get("/appUser")
        .success(function (data) {
            console.log(data);
            $scope.appUserList = data;
        });

    $scope.postUser = function () {
        $http({
            method: 'POST',
            url: "/appUser",
            data: {userName: $scope.userName, password: $scope.password}
        }).success(function (data) {
            $scope.newAppUser = data;
        });
    };

    $scope.updateAppUser = function () {
        $http({
            method: 'PUT',
            url: "/appUser/"+$scope.id,
            data: {userName: $scope.userName, password: $scope.password}
        }).success(function (data) {
            $scope.updatedUser = data;
        });
    };

    $scope.getUserById = function () {
        $http.get("/appUser/" + $scope.id)
            .success(function (data) {
                $scope.oneUser = data;
            });
    };

    $scope.deleteAppUserById = function () {
        $http.delete("/appUser/" + $scope.id)
            .success(function (data) {
                $scope.deleteStatus = data;
            });
    };


});