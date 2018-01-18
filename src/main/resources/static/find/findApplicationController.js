var app = angular.module('app',['ngMaterial', 'ngMessages']);
app.controller('findApplicationController', [
    '$scope',
    '$http' ,
    'findApplicationService',
    '$window',
    'homeService',
    function($scope, $http, findApplicationService, $window , homeService) {


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





        $scope.submitForm = function(){
            var url = "http://localhost:8090/application/find";


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

        $scope.selectApplication = function(id){
            url = "http://localhost:8090/application/show/"+id;
            $window.location.href =url;
        }
    }]);
