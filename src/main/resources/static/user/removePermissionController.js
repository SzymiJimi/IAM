var app = angular.module('app', ['ngMaterial', 'ngMessages']);
// nie zapomnijcie w kwadratowe nawiasy dodać 'ngMaterial', 'ngMessages'
app.controller('removePerksController', function($scope, $http, $window, homeService,$mdDialog) {

    var config = {
        headers : {
            Accept: undefined
        }
    };

    $scope.role={};
    $scope.commands=[];
    $scope.response={};
    $scope.starChanging=false;
    $scope.finished=true;
    $scope.changeResult="";
    $scope.description="";

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

    $scope.showAlert= function (ev) {

        var confirm = $mdDialog.confirm()
            .title($scope.changeResult)
            .textContent($scope.description)
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function() {
            $window.location.href ="/"
        });

    };

    $scope.removePerks=function (ev) {
        $scope.starChanging=true;
        $scope.finished=false;

        var url = "/user/removePerks";

        $http.get(url, config).then(function (response) {
            $scope.starChanging=false;
            $scope.description="Uprawnienia zostały cofnięte pomyślnie.\nPowrót na stronę główną...";
            $scope.changeResult = response.data;
            console.log(response.data);
            $scope.showAlert(ev);
        }, function (error) {
            $scope.starChanging=false;
            $scope.description="Niestety coś poszło nie tak...\nPowrót na stronę główną...";
            console.log(error.data);
            $scope.showAlert(ev);
        });

    }

});

