var app = angular.module('app', ['ngMaterial', 'ngMessages']);
app.controller('orderInsuranceController', function($scope, $http, $window, $mdDialog) {

    var config = {
        headers : {
            Accept: undefined
        }
    };

    $scope.name="";
    $scope.surname="";
    $scope.email="";
    $scope.phone="";
    $scope.postalCode="";
    $scope.postResultMessage="";
    $scope.orderLoading=false;
    $scope.loadEmpty=true;


    $scope.showAlert= function (ev) {

        var confirm = $mdDialog.confirm()
            .title('Zamówienie zostało pomyślnie stworzone')
            .textContent($scope.postResultMessage)
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function() {
            $window.location.href ="/"
        });

    };

    $scope.submitOrder= function(ev){
        $scope.orderLoading=true;
        $scope.loadEmpty=false;
        var url = "/order/create";

        var order= {
            name: $scope.name,
            surname: $scope.surname,
            email: $scope.email,
            phone: $scope.phone,
            postalCode: $scope.postalCode
        };

        $http.post(url, order, config).then(function (response) {
            $scope.orderLoading=false;
            $scope.postResultMessage = response.data;
            $scope.showAlert(ev);
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    }

});

