var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.controller('controllerReserve', function($scope, $http, $filter,$timeout, $mdSidenav, $log,$mdDialog,$window,  homeService) {

    var config = {
        headers: {
            Accept: undefined
        }
    };


    // Element który musi być w każdym kontrollerze..
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

         $scope.username="";
         $scope.password="";
       $scope.name="";
         $scope.surname="";
         $scope.email="";




    $scope.showAlert = function (ev) {

        var confirm = $mdDialog.confirm()
            .title('Dodano klienta')
            .textContent('Powróć na stronę główną')
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function () {
            $window.location.href = "/home"
        });

    };

    $scope.submitForm = function(ev){

        config = {
            headers : {
                Accept: 'text/html'
            }
        };

        var url = "/user/add";
        var userdata = {
            username: $scope.username,
            password: $scope.password,
            name: $scope.name,
            surname: $scope.surname,
            email: $scope.email,
            idRole: '12'
        };

        $http.post(url, userdata, config).then(function (response) {
            $scope.showAlert(ev);
            $scope.postResultMessage = response.data;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    };
});

