var app = angular.module('app', ['ngMaterial', 'ngMessages']);
// nie zapomnijcie w kwadratowe nawiasy dodać 'ngMaterial', 'ngMessages'
app.config(function($mdThemingProvider) {
    $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
    $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
    $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
    $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
});
app.controller('showListController', function($scope, $http, $window, homeService) {

    var config = {
        headers : {
            Accept: undefined
        }
    };

    $scope.role={};
    $scope.commands=[];
    $scope.response={};
    $scope.notifications=[];
    $scope.statuses=['CONFIRMED' ,'CREATED', 'AVAITING_VALUATION', 'REJECTED', 'ENDED','INVESTIGATED', 'ALL'];
    $scope.selectedStatus='ALL';
    $scope.realStatus='';
    $scope.loggedUser={};
    $scope.pesel="";

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

    $scope.getSelectedText = function() {
        if ($scope.selectedStatus !== undefined) {
            if($scope.selectedStatus==='ALL')
            {
                $scope.realStatus='';
            }else{
                $scope.realStatus=$scope.selectedStatus;
            }
            return  $scope.selectedStatus;
        } else {
            return "Wybierz status do wyświetlenia...";
        }
    };

    Array.prototype.remove = function(from, to) {
        var rest = this.slice((to || from) + 1 || this.length);
        this.length = from < 0 ? this.length + from : from;
        return this.push.apply(this, rest);
    };

    $scope.deleteFromList = function (id) {

        var newList= $scope.notifications;
        var deleteID;

        for (var i=0;i<newList.length;i++)
        {
            if(newList[i].idNotification===id)
            {
                deleteID=i;
            }
        }

        newList.remove(deleteID);
        $scope.notifications=newList;
    };

    $scope.loadUserNotification=function (pesel) {
        var url = "http://localhost:8090/notification/getUserNotification";

        $http.post(url,pesel, config).then(function (response) {
            $scope.notifications=response.data;
        },function(error){
        });
    };

    $scope.loadDetails= function (id) {
        var url = "http://localhost:8090/notification/show/"+id;
        $window.location.href =url;
    };

    $scope.loadNotifications = function () {

        var url = "http://localhost:8090/notification/getList";

        $http.get(url, config).then(function (response) {
            $scope.notifications=response.data;
        },function(error){
        });
    }

});

