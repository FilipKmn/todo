(function() {
    'use strict';

    angular
        .module('adminPortal')
        .service('userApi', userApi);

    userApi.$inject = ['$http', 'sessionService'];

    function userApi($http, sessionService) {

        var backendApiUrl = 'http://localhost:8080';

        this.init = function(backendUrl) {
            backendApiUrl = backendUrl;
        };

        this.users = function() {
            return $http({
                method: 'GET',
                url: backendApiUrl + '/api/users',
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

        this.userTodos = function(model) {
            return $http({
                method: 'GET',
                url: backendApiUrl + '/api/userTodos',
                params: {
                    userId: model.userId,
                    drop: model.drop,
                    take: model.take
                },
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

    }
})();