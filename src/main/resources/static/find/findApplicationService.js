(function () {
    'use strict';
    var app= angular.module('app');
    app.service('findApplicationService', function ($http, $q) {
        var result = [];
        return {

            result: result,

            findApplicationInDB: function (url, type, config) {
                var deffer = $q.defer();
                $http.post(url, type, config).then(function (response) {
                    result = response.data;
                    deffer.resolve(result);
                });
                return deffer.promise;
            }
        }

    })
})();
