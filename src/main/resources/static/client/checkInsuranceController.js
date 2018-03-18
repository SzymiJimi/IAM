var app = angular.module('app', ['ngMaterial', 'ngMessages']);
// nie zapomnijcie w kwadratowe nawiasy dodać 'ngMaterial', 'ngMessages'
app.controller('checkInsurance', function ($scope, $http, $window, homeService) {

    var config = {
        headers: {
            Accept: undefined
        }
    };

    $scope.role = {};
    $scope.commands = [];
    $scope.response = {};
    $scope.contracts={};
    $scope.pesel="";
    $scope.empty=false;
    $scope.isInsuranced=false;

    $scope.loggedUser = {};
    if (homeService.commands.length === 0) {
        homeService.initiate();
    }

    $scope.commands = homeService.commands;
    $scope.loggedUser = homeService.user;

    $scope.selectCommand = function (idCommand) {
        console.log("Jestem tutaj");
        homeService.runSelectedCommand(idCommand);
    };

    $scope.dropDown = function () {
        document.getElementById("myDropdown").classList.toggle("show");
    };


    $scope.checkClientInsurace = function (pesel) {
        
        var url = "/contract/check";
        $http.post(url,pesel, config).then(function (response) {
            console.log(response.data);
            if(response.data.idContract===undefined && response.data[0].idContract===undefined)
            {
                $scope.isInsuranced=false;
            }
            else {
                $scope.isInsuranced=true;
                $scope.empty=true;
                $scope.contracts=response.data;
            }
        }, function (response) {
            $scope.isInsuranced=false;
            $scope.empty=true;
        });
    }

});

