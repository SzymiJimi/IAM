var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.controller('newApplicationController', function($scope, $http, $filter,$timeout, $mdSidenav, $log,$mdDialog,$window,  homeService) {

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

    $scope.info = "";
    $scope.name = "";
    $scope.type = "";
    $scope.duration = "";
    $scope.paymentAmount = "";
    $scope.active = "";

    $scope.showAlert = function (ev) {

        var confirm = $mdDialog.confirm()
            .title('Dodano oferte')
            .textContent('Powróć na stronę główną')
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function () {
            $window.location.href = "http://localhost:8090/home"
        });

    };
    $scope.submitForm = function(ev){

        config = {
            headers : {
                Accept: 'text/html'
            }
        };

        var url = "http://localhost:8090/application/add";
        console.log("SubmitForm");

        // data.idOffer=idOffer;

        var applicationData = {
            type: $scope.type,
            reason: $scope.reason,
            user_idUser: $scope.user_idUser,
            description: $scope.description
        };
        console.log(applicationData);


        $http.post(url, applicationData, config).then(function (response) {
            $scope.postResultMessage = response.data;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });




    };
});