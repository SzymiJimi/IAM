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

    $scope.showAlert= function (ev) {

        var confirm = $mdDialog.confirm()
            .title('Zamówienie zostało pomyślnie stworzone')
            .textContent('Powróć na stronę główną')
            .ariaLabel('Alert Dialog Demo')
            .targetEvent(ev)
            .ok('Ok!');

        $mdDialog.show(confirm).then(function() {
            $window.location.href ="http://localhost:8090"
        });

    };

    $scope.submitOrder= function(ev){
        var url = "http://localhost:8090/order/create";
        console.log("jestem w dodawaniu");
        var order= {
            name: $scope.name,
            surname: $scope.surname,
            email: $scope.email,
            phone: $scope.phone,
            postalCode: $scope.postalCode
        };

        $http.post(url, order, config).then(function (response) {
            $scope.postResultMessage = response.data;
            console.log(response.data);
            $scope.showAlert(ev);
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    }

});

