angular.module('app').service('findClientService', function ($http, $q) {
    var result = [];
    return {

        result: result,

        findClientInDB: function (url, name, config) {
            console.log(name);
            var deffer = $q.defer();
            $http.post(url, name, config).then(function (response) {
                result = response.data;
                deffer.resolve(result);
            });
            return deffer.promise;
        }
    }

});
