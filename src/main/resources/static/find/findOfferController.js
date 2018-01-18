var app = angular.module('app',[]);
app.controller('findOfferController', [
    '$scope',
    '$http' ,
    'findOfferService',
    '$window',
    function($scope, $http, findOfferService, $window ) {


        $scope.submitForm = function(){
            var url = "http://localhost:8090/offer/find";

            var config = {
                headers : {
                    Accept: undefined
                }
            };

            var value = $scope.name;
            $scope.postResult=[];

            findOfferService.findOfferInDB(url, value, config).then(function (result) {
                angular.copy(result, $scope.postResult);
                if($scope.postResult.length===0)
                {
                    $scope.response="Nie znaleziono oferty!";
                }else{
                    $scope.response="Znaleziono oferte!";
                }
            });
        };

        $scope.selectOffer = function(id){
            url = "http://localhost:8090/offer/show/"+id;
            $window.location.href =url;
        }
    }]);