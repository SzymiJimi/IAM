var app = angular.module('app', ['ngMaterial', 'ngMessages']);
// nie zapomnijcie w kwadratowe nawiasy dodać 'ngMaterial', 'ngMessages'
app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
    $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
    $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
    $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
});
app.controller('showListController', function ($scope, $http, $window, $timeout, $filter, $q, $log, homeService) {

    var config = {
        headers: {
            Accept: undefined
        }
    };

    var self = this;

    self.states = loadAll();
    self.selectedItem = null;
    $scope.searchText = "";

    $scope.querySearch = function querySearch(query) {
        var results = query ? self.states.filter(createFilterFor(query)) : self.states;
        var deferred = $q.defer();
        $timeout(function () {
            deferred.resolve(results);
        }, Math.random() * 1000, false);
        return deferred.promise;
    };


    function loadAll() {
        var url = "http://localhost:8090/client/getList";
        var tmp;
        var allStates = [];
        $http.get(url, config).then(function (response) {
            allStates = response.data;
            self.states = allStates.map(function (state) {
                url = "http://localhost:8090/client/findOne/" + state.userId;
                var value = $http.get(url, config).then(function (response) {
                    var deffer = $q.defer();
                    tmp = response.data;
                    state.name = tmp.name;
                    state.surname = tmp.surname;
                    state.value = state.pesel.toLowerCase();
                    return state;
                }).$$state;
                return value;
            });
        }, function (error) {
        });
    }

    function createFilterFor(query) {
        var lowercaseQuery = angular.lowercase(query);

        return function filterFn(state) {
            return (state.value.value.indexOf(lowercaseQuery) === 0);
        };

    }

    $scope.role = {};
    $scope.commands = [];
    $scope.response = {};
    $scope.notifications = [];
    $scope.statuses = ['Wszystkie', 'Potwierdzone', 'Stworzone', 'Oczekujące wyceny', 'Odrzucone', 'Zakończone', 'Oceniane'];
    $scope.selectedStatus = 'Wszystkie';
    $scope.realStatus = '';
    $scope.loggedUser = {};
    $scope.pesel = "";

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
        if ($scope.selectedStatus !== undefined) {
            if ($scope.selectedStatus === 'Wszystkie') {
                $scope.realStatus = '';
            } else if($scope.selectedStatus === 'Potwierdzone'){

                $scope.realStatus = 'CONFIRMED';
            }else if($scope.selectedStatus === 'Stworzone'){

                $scope.realStatus = 'CREATED';
            }else if($scope.selectedStatus === 'Oczekujące wyceny'){

                $scope.realStatus = 'AVAITING_VALUATION';
            }else if($scope.selectedStatus === 'Odrzucone'){

                $scope.realStatus = 'REJECTED';
            }else if($scope.selectedStatus === 'Zakończone'){

                $scope.realStatus = 'ENDED';
            }else if($scope.selectedStatus === 'Oceniane'){

                $scope.realStatus = 'IVESTIGATING';
            }
            return $scope.selectedStatus;
        } else {
            return "Wybierz status do wyświetlenia...";
        }
    };

    Array.prototype.remove = function (from, to) {
        var rest = this.slice((to || from) + 1 || this.length);
        this.length = from < 0 ? this.length + from : from;
        return this.push.apply(this, rest);
    };

    $scope.deleteFromList = function (id) {

        var newList = $scope.notifications;
        var deleteID;

        for (var i = 0; i < newList.length; i++) {
            if (newList[i].idNotification === id) {
                deleteID = i;
            }
        }

        newList.remove(deleteID);
        $scope.notifications = newList;
    };

    $scope.loadUserNotification = function (pesel) {
        var url = "http://localhost:8090/notification/getUserNotification";
        $http.post(url, pesel, config).then(function (response) {
            $scope.notifications = response.data;
        }, function (error) {
        });
    };

    $scope.loadDetails = function (id) {
        var url = "http://localhost:8090/notification/show/" + id;
        $window.location.href = url;
    };

    $scope.loadNotifications = function () {

        var url = "http://localhost:8090/notification/getList";

        $http.get(url, config).then(function (response) {
            $scope.notifications = response.data;
        }, function (error) {
        });
    }

});

