angular.module('app').service('homeService', function ($http, $window, $q) {
    var result = [];
    var commands=[];
    var userRoles=[];
    var user={};
    return {

        result: result,
        commands: commands,
        userRoles: userRoles,
        user:user,

        initiate: function () {

            var deffer = $q.defer();

            var url = "/user/get";

            var config = {
                headers : {
                    Accept: undefined
                }
            };

            $http.get(url, config).then(function (response) {
                angular.copy(response.data,user);
                return user.roles;
            }, function error(response) {
                return null;
            }).then(function (roles) {
                url = "/role/get";

                $http.post(url, roles ,config).then(function (response) {
                    angular.copy(response.data,commands);
                    deffer.resolve(user);
                }, function error(response) {
                });
            });


            return deffer.promise;


        },

        runSelectedCommand: function (idCommand){

            var config = {
                headers : {
                    Accept: undefined
                }
            };
        var url = "/role/run/"+idCommand;
        $http.get(url, config).then(function (response) {
            $window.location.href =response.data;
        });
    }
    }
});
