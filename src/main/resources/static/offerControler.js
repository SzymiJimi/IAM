var app = angular.module('app', []);
app.controller('postcontroller', function($scope, $http, $location) {

    // noinspection JSAnnotator
    $scope.submitForm1() = function(){
        var url = "http://localhost:8090/offer/add";
        var config = {
            headers : {
                Accept: 'text/html'
            }
        }

        var data = {
            idclient: $scope.idclient,
            information: $scope.information,
            insurancetype: $scope.insurancetype,
            name: $scope.name

        };

        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = response.data;
        }, function error(response) {
        $scope.postResultMessage = "Error with status: " +  response.statusText;
        });

        $scope.idclient = "";
        $scope.information = "";
    }
});

