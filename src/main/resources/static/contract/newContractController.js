var app = angular.module('app', []);
app.controller('newContractController', function($scope, $http, $location) {

    var config = {
        headers : {
            Accept: undefined
        }
    };


    $scope.offerId="";
    $scope.offerLoaded = true;
    $scope.offerResult={};


    $scope.myStyle = {
        "word-break": "break-all"
    };
    $scope.loadOffer = function (id){

        var url = "http://localhost:8090/offer/findOne";
        console.log(id);
        $http.post(url,id, config).then(function (response) {
            $scope.offerResult = response.data;
            $scope.offerLoaded= false;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    };

    $scope.changeOffer = function () {
        $scope.offerLoaded=true;
    };


    $scope.submitForm = function(){
        var url = "http://localhost:8090/user/add";
        console.log("SubmitForm");
        $http.post(url, $scope.offerId, config).then(function (response) {
            $scope.postResultMessage = response.data;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });

        $scope.firstname = "";
        $scope.lastname = "";
    }
});

