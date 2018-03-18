angular.module('app').service('notificationService', function ($http, $window, $q) {

    var userResult={};
    var userLoaded= true;
    var isInsuranced= false;
    var clientData={};
    var contracts={};

    var config = {
        headers: {
            Accept: undefined
        }
    };


        this.getUser=function (email) {
            console.log("getUser");
            console.log(email);
            var deffer = $q.defer();
            var url = "/client/find";
            $http.post(url, email, config).then(function (response) {
                this.userResult = response.data[0];
                console.log( userResult);
                this.userLoaded = false;
                console.log("Przed resolve w getUser");
                console.log( userResult.idUser);
                deffer.resolve(userResult.idUser);
                return deffer.promise;
            });

        };
        this.loadContract=function (idUser) {
            console.log("loadContract");
            console.log(idUser);
            var deffer = $q.defer();
            var url = "/contract/find/" + idUser;
            $http.get(url, config).then(function (response) {
                this.isInsuranced = response.data;
                console.log("Przed resolve w loadContract");
                console.log(idUser);
                deffer.resolve(idUser);
            },function(error){
                deffer.reject();
            });
            return deffer.promise;
        };
        this.loadClientData=function (idUser) {
            console.log("loadClientData");
            console.log(idUser);
            var deffer = $q.defer();
            var url = "/clientData/find/" + idUser;
            $http.get(url, config).then(function (response) {
                this.clientData = response.data;
                console.log("Przed resolve w loadClientData");
                console.log(idUser);
                deffer.resolve(idUser);
            },function(error){
                deffer.reject();
            });
            return deffer.promise;
        };
        this.loadContractList= function (idUser) {
            console.log("loadContractList");
            console.log(idUser);
            var deffer = $q.defer();
            var url = "/contract/getList/" + idUser;
            $http.get(url, config).then(function (response) {
                this.contracts = response.data;
                console.log("Przed resolve w LoadContractList");
                console.log(idUser);
                deffer.resolve(idUser);
            },function(error){
                deffer.reject();
            });
            return deffer.promise;
        }


});