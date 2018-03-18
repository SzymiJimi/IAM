var app = angular.module('app', ['ngMaterial', 'ngMessages']);
// nie zapomnijcie w kwadratowe nawiasy dodać 'ngMaterial', 'ngMessages'
app.controller('changePassController', function($scope, $http, $window, homeService,$mdDialog) {

    var config = {
        headers : {
            Accept: undefined
        }
    };

    $scope.role={};
    $scope.commands=[];
    $scope.response={};
    $scope.oldPassword="";
    $scope.checkResult="";
    $scope.checkStatus=false;
    $scope.checkCounter=0;
    $scope.changeResult="";
    $scope.changeDescription="";
    $scope.passChanging=false;
    $scope.changingStarted=true;


    $scope.loggedUser={};
    if(homeService.commands.length===0)
    {
        homeService.initiate();
    }

    $scope.commands=homeService.commands;
    $scope.loggedUser=homeService.user;

    $scope.selectCommand=function (idCommand) {
        homeService.runSelectedCommand(idCommand);
    };

    $scope.dropDown= function(){
        document.getElementById("myDropdown").classList.toggle("show");
    };

    // do tąd

    $scope.showAlert= function (ev) {

        var confirm = $mdDialog.confirm()
            .title($scope.changeResult)
            .textContent($scope.postResultMessage)
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function() {
            $window.location.href ="/"
        });

    };

    $scope.checkOld= function(){
        var url = "/user/checkOldPass";
        $scope.checkCounter=$scope.checkCounter+1;

        $http.post(url,$scope.oldPassword, config).then(function (response) {
            $scope.checkResult = response.data;
            console.log("Jest git");
            $scope.checkStatus =true;
        }, function (error) {
            $scope.oldPassword="";
            console.log("Jest słabo");
            $scope.checkStatus =false;
            $scope.checkResult= error.data;
        });
    };

    $scope.submitChange=function(pass, ev){
        $scope.passChanging=true;
        $scope.changingStarted=false;

        console.log("Zmiana hasła...")
        var url = "/user/changePass";
        $http.post(url,pass, config).then(function (response) {
            $scope.passChanging=false;
            $scope.changeResult=response.data;
            $scope.changeDescription="Hasło zostało zaaktualizowane pomyślnie!\nPowrót na stronę główną...";
            $scope.showAlert(ev);
            console.log(response.data);
        }, function (reason) {
            $scope.passChanging=false;
            $scope.changeResult=reason.data;
            $scope.changeDescription="Podczas aktualizacji wystąpił nieoczekiwany błąd...\nPowrót na stronę główną...";
            $scope.showAlert(ev);
            console.log(reason.data);
        });
    }
});

