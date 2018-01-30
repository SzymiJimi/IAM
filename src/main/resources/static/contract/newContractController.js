var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.controller('newContractController', function($scope, $http, $filter,$timeout, $mdSidenav, $log,$mdDialog,$window,  homeService) {

    var that = this;
    var config = {
        headers : {
            Accept: undefined
        }
    };

    // Element który musi być w każdym kontrollerze..
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


    $scope.offerId="";
    $scope.userId="";
    $scope.email="";
    $scope.name="";
    $scope.surname="";
    $scope.pesel="";
    $scope.identificationNr="";
    $scope.phone="";
    $scope.place="";
    $scope.street="";
    $scope.houseNr="";
    $scope.flatNr="";
    $scope.postalCode="";
    $scope.offerLoaded = true;
    $scope.userLoaded = true;
    $scope.clientDataLoaded = false;
    $scope.offerResult={};
    $scope.userResult={};
    $scope.userResponse="";
    $scope.clientDataResponse={};


    var idOffer;

    $scope.showAlert= function (ev) {

        var confirm = $mdDialog.confirm()
            .title('Umowa została pomyślnie stworzona')
            .textContent('Powróć na stronę główną')
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function() {
            $window.location.href ="http://localhost:8090/home"
        });

    };
    $scope.loadOffer = function (id){

        var url = "http://localhost:8090/offer/findOne";
        idOffer = id;
        $http.post(url,id, config).then(function (response) {
            $scope.offerResult = response.data;
            $scope.offerLoaded= false;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    };

    that.loadClientData= function (userId) {
        var url = "http://localhost:8090/clientData/find/"+userId;
        $http.get(url, config).then(function (response) {
            $scope.clientDataResponse = response.data;
            if(response.data.length!==0) {
                $scope.pesel=$scope.clientDataResponse.pesel;
                $scope.identificationNr=$scope.clientDataResponse.idNumber;
                $scope.phone=$scope.clientDataResponse.phone;
                $scope.place=$scope.clientDataResponse.place;
                $scope.street=$scope.clientDataResponse.street;
                $scope.houseNr=$scope.clientDataResponse.houseNr;
                $scope.flatNr=$scope.clientDataResponse.flatNr;
                $scope.postalCode= $scope.clientDataResponse.postalCode;
                $scope.clientDataLoaded = true;
            }
        });
    };


    $scope.loadUser = function (email){

        console.log(email);
        var url = "http://localhost:8090/client/find";
        $http.post(url,email, config).then(function (response) {
            $scope.userResult = response.data;
            console.log(response.data[0]);
            if(response.data.length!==0)
            {
                $scope.userResponse="Znaleziono użytkownika";
                $scope.name= $scope.userResult[0].name;
                $scope.surname= $scope.userResult[0].surname;
                $scope.userId= $scope.userResult[0].idUser;
                that.loadClientData($scope.userId);
            }else{
                $scope.userResponse="Nie znaleziono użytkownika";
            }
            $scope.userLoaded=false;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    };



    $scope.changeOffer = function () {
        $scope.offerLoaded=true;
    };

    var data = {
        idUser: $scope.userId,
        idOffer: $scope.offerId
    };


    $scope.submitForm = function(ev){

        config = {
            headers : {
                Accept: 'text/html'
            }
        };

        var url = "http://localhost:8090/clientData/add";
        console.log("SubmitForm");
        data.idUser=$scope.userId;
        data.idOffer=idOffer;
        console.log(data);

        var clientData = {
            userId: $scope.userId,
            idNumber: $scope.identificationNr,
            pesel: $scope.pesel,
            phone: $scope.phone,
            place: $scope.place,
            houseNr: $scope.houseNr,
            flatNr: $scope.flatNr,
            street: $scope.street,
            postalCode: $scope.postalCode
        };
        console.log(clientData);

        $http.post(url, clientData, config).then(function (response) {
            $scope.postResultMessage = response.data;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });

        url = "http://localhost:8090/contract/add";

        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = response.data;
            $scope.showAlert(ev);
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });

    };
    });



