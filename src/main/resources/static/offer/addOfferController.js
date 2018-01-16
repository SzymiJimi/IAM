var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.controller('addOfferController', function($scope, $http, $filter,$timeout, $mdSidenav, $log,$mdDialog,$window,  homeService) {

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
    var data = {
        idOffer: $scope.offerId
    };
    $scope.submitForm = function(ev){

        config = {
            headers : {
                Accept: 'text/html'
            }
        };

        var url = "http://localhost:8090/offer/add";
        console.log("SubmitForm");

        // data.idOffer=idOffer;
        console.log(data);

        var offerData = {

            information:$scope.info,
            name: $scope.name,
            insurancetype:$scope.type,
            duration: $scope.duration ,
            paymentAmount: $scope.paymentAmount ,
            active: $scope.active
        };
        console.log(offerData);

        $http.post(url, offerData, config).then(function (response) {
            $scope.postResultMessage = response.data;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });

        url = "http://localhost:8090/offer/add";

        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = response.data;
            $scope.showAlert(ev);
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });

    };
});

