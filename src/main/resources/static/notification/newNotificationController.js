var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.controller('newNotificationController', function ($scope, $http, $window, $mdDialog, homeService) {

    var config = {
        headers: {
            Accept: undefined
        }
    };
    $scope.result="";
        $scope.role = {};
    $scope.commands = [];
    $scope.response = {};
    $scope.email = "jerzy@wp.pl";
    $scope.offerLoaded = true;
    $scope.userLoaded = true;
    $scope.userResult = {};
    $scope.userResponse = "";
    $scope.isInsuranced = false;
    $scope.clientData = {};
    $scope.contracts = [];
    $scope.carCrash = "Wypadek samochodowy";
    $scope.sickness = "Choroba";
    $scope.otherCrash = "Inny wypadek";
    $scope.travelIssue = "Problem podróży";
    $scope.healthProblem = "Inny problem zdrowotny";

    $scope.items = [1, 2, 3, 4, 5, 6, 7];
    $scope.selectedItem = undefined ;
    $scope.loggedUser = {};
    $scope.choosenId = undefined;


    if (homeService.commands.length === 0) {
        homeService.initiate();
    }

    $scope.commands = homeService.commands;
    $scope.loggedUser = homeService.user;

    $scope.selectCommand = function (idCommand) {
        homeService.runSelectedCommand(idCommand);
    };

    $scope.dropDown = function () {
        document.getElementById("myDropdown").classList.toggle("show");
    };

    $scope.getSelectedText = function () {
        if ($scope.selectedItem !== undefined) {
            return $scope.selectedItem;
        } else {
            return "Wybierz typ zgłoszenia";
        }
    };

    $scope.showAlert = function (ev) {

        var confirm = $mdDialog.confirm()
            .title($scope.result)
            .textContent($scope.postResultMessage)
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Powrót na stronę główną');

        $mdDialog.show(confirm).then(function () {
            $window.location.href = "/home"
        });
    };

    var fillClientData= function (data) {
        var clientData={
            pesel: data,
            place: data,
            phone: data,
            houseNr: data,
            flatNr: data
        };
        return clientData;
    };
    $scope.loadUser = function (email) {
        var url = "/client/find";
        $http.post(url, email, config).then(function (response) {
            $scope.userResult = response.data[0];
            if (response.data.length !== 0) {
                $scope.userResponse = "Znaleziono użytkownika";
            } else {
                $scope.userResponse = "Nie znaleziono użytkownika";
            }
            $scope.userLoaded = false;
        }).then(function (data) {
            var url = "/clientData/find/" + $scope.userResult.idUser;
            $http.get(url, config).then(function (response) {
                if(response.data===""){
                    console.log("Jestem nullem");
                    $scope.clientData= fillClientData("Brak danych...", response.data);
                    console.log($scope.clientData.pesel);
                }else{
                    $scope.clientData = response.data;
                }
            });
        })
            .then(function (data) {
                var url = "/contract/find/" + $scope.userResult.idUser;
                $http.get(url, config).then(function (response) {
                    $scope.isInsuranced = response.data;
                });
            })
            .then(function (data) {
                var url = "/contract/getList/" + $scope.userResult.idUser;
                $http.get(url, config).then(function (response) {
                    $scope.contracts = response.data;
                });
            });
    };


    $scope.chooseInsurance = function (id) {
        $scope.choosenId = id;
    };



    $scope.submitForm = function (ev) {

        config = {
            headers: {
                Accept: undefined
            }
        };
        console.log($scope.description);
        var url = "/notification/add";

        var notificationData = {
            type: $scope.selectedItem,
            status: "",
            incident_time: "",
            incident_description: $scope.description,
            valuation: "",
            idContract: $scope.choosenId
        };
        console.log(notificationData);

        $http.post(url, notificationData, config).then(function (response) {
            $scope.result="Sukces!";
            $scope.postResultMessage = "Zgłoszenie zostało dodane poprawnie!";
            $scope.showAlert(ev);
        }, function error(response) {
            $scope.result="Wystapił błąd!";
            $scope.postResultMessage = "Niestety nie udało się dodać zgłoszenia... ";
            console.log($scope.postResultMessage);
        });

    };
});

