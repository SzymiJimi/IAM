var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
    $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
    $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
    $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
});
app.controller('showAllController', function($scope, $http, $window, homeService, $mdDialog) {

    var url = "/offer/showList";

    var config = {
        headers : {
            Accept: undefined
        }
    };

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
        homeService.runSelectedCommand(idCommand);
    };

    $scope.dropDown= function(){
        document.getElementById("myDropdown").classList.toggle("show");
    };

    Array.prototype.remove = function (from, to) {
        var rest = this.slice((to || from) + 1 || this.length);
        this.length = from < 0 ? this.length + from : from;
        return this.push.apply(this, rest);
    };


    $scope.deleteFromList = function (id) {

        var newList = $scope.offers;
        var deleteID;

        for (var i = 0; i < newList.length; i++) {
            if (newList[i].idOffer === id) {
                deleteID = i;
            }
        }
        newList.remove(deleteID);
        $scope.offers = newList;
    };

    $scope.showAlert= function (ev) {

        var confirm = $mdDialog.confirm()
            .title('Zapisaliśmy to!')
            .textContent('Twoje zainteresowanie tą ofertą zostało zarejestrowanie.\n' +
                'Nasz konsultant skomunikuje się z Tobą aby omówić szczegóły...\n\n' +
                'Powrót na stronę główną')
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function() {
            $window.location.href ="/"
        });

    };

    $scope.interested= function(id, ev){

        $scope.showAlert(ev);
    };

    $scope.offers=[];

    $http.get(url, config).then(function (response) {
        $scope.offers = response.data;
    });
});

