
var app = angular.module('app',['ngMaterial', 'ngMessages']);

app.controller('findOfferController', [
    '$scope',
    '$http' ,
    'findOfferService',
    '$window',
    'homeService',
    function($scope, $http, findOfferService, $window , homeService) {


        var config = {
            headers : {
                Accept: undefined
            }
        };
        $scope.findData="";
        $scope.role={};
        $scope.commands=[];
        $scope.response={};

        $scope.loggedUser={};
        if(homeService.commands.length===0)
        {
            homeService.initiate();
        }

        $scope.commands=homeService.commands;
        $scope.loggedUser=homeService.user;

        $scope.selectCommand=function (idCommand) {
            console.log("Jestem tutaj");
            homeService.runSelectedCommand(idCommand);
        };

        $scope.dropDown= function(){
            document.getElementById("myDropdown").classList.toggle("show");
        };
        $scope.submitForm = function(value){
            var url = "http://localhost:8090/offer/find";
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
