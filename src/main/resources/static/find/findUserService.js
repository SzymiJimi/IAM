angular.module('app').service('findUserService', function ($http, $q) {
    var result = [];
    return {

        result: result,

        findUserInDB: function (url, name, config) {
            var deffer = $q.defer();
            $http.post(url, name, config).then(function (response) {
                result = response.data;
                deffer.resolve(result);
            }, function error(response) {
                result = "Error with status: " + response.statusText;
            });
            return deffer.promise;
    }
    }

});
