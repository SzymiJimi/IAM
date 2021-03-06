
var app = angular.module('app',['ngMaterial', 'ngMessages']);
app.controller('findClientController', [
    '$scope',
    '$http' ,
    'findClientService',
    '$window',
    'homeService',
    function($scope, $http, findUserService, $window , homeService) {

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
            var url = "/client/find";

            // var value = $scope.name;
            console.log(value);
            $scope.postResult=[];

            findUserService.findClientInDB(url, value, config).then(function (result) {
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
            url = "/client/show/"+id;
            $window.location.href =url;
        }
    }]);