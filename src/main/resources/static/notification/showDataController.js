var app = angular.module('app', ['ngMaterial', 'ngMessages']);
// nie zapomnijcie w kwadratowe nawiasy dodać 'ngMaterial', 'ngMessages'
app.config(function($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});
app.controller('showDataController', function($scope, $http, $window,$location,$mdDialog, homeService) {

    var config = {
        headers : {
            Accept: undefined
        }
    };

    $scope.role={};
    $scope.commands=[];
    $scope.response={};
    $scope.contract={};
    $scope.userData={};
    $scope.clientData={};
    $scope.postResultMessage="";
    $scope.result

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
    $scope.notificationData=[];

    $scope.initialize= function () {
        var splitData = $location.path().split('/');
        var value = splitData[3];
        var url = "http://localhost:8090/notification/show";
        $http.post(url, value, config).then(function (response) {
            $scope.notificationData = response.data;
        }).then(function (id) {
            url = "http://localhost:8090/contract/get/"+$scope.notificationData.idContract;
            $http.get(url, config).then(function (response) {
                $scope.contract = response.data;
            }).then(function (id) {
                url = "http://localhost:8090/user/findOne/"+$scope.contract.idUser;
                $http.get(url, config).then(function (response) {
                    $scope.userData = response.data;
                }).then(function (id) {
                    url = "http://localhost:8090/clientData/find/"+$scope.contract.idUser;
                    $http.get(url, config).then(function (response) {
                        $scope.clientData = response.data;
                    })
                })
            })
        });
    };

    $scope.showAlert = function (ev) {

        var confirm = $mdDialog.confirm()
            .title($scope.result)
            .textContent($scope.postResultMessage)
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Powrót na stronę główną');

        $mdDialog.show(confirm).then(function () {
            $window.location.href = "http://localhost:8090/home"
        });
    };

    $scope.delete = function (ev, id) {
        var url = "http://localhost:8090/notification/delete";
        $http.post(url, id, config).then(function (response) {
            $scope.result="Sukces!";
            $scope.postResultMessage="Usunięcie zakończone pozytywnie!";
            $scope.showAlert(ev);
        }, function (response) {
        });
    };


    $scope.redirectNotification = function (id, ev) {
        var url = "http://localhost:8090/notification/redirect";
        $http.post(url, id, config).then(function (response) {
            $scope.result="Sukces!";
            $scope.postResultMessage=response.data;
            $scope.showAlert(ev);
        }, function (response) {
            $scope.result="Wystąpił błąd!";
            $scope.postResultMessage=response.data;
            $scope.showAlert(ev);
        });
    };


});

