(function () {
    'use strict';
    var app= angular.module('app');
    app.service('findUserService', function ($http, $q) {
        var result = [];
        return {

            result: result,

            findUserInDB: function (url, name, config) {
                var deffer = $q.defer();
                $http.post(url, name, config).then(function (response) {
                    result = response.data;
                    deffer.resolve(result);
                });
                return deffer.promise;
            }
        }

    })
})();