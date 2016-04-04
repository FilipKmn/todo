(function() {
    'use strict';

    angular
        .module('adminPortal')
        .service('authenticationApi', authenticationApi);

    authenticationApi.$inject = ['$http', 'sessionService'];

    function authenticationApi($http, sessionService) {

        var backendApiUrl = 'http://localhost:8080';

        this.init = function(backendUrl) {
            backendApiUrl = backendUrl;
        };

        this.authenticate = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/authenticate',
                data: {
                    accessToken: model.accessToken
                }
            });
        };

        this.signOut = function() {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/signOut',
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

        this.refreshToken = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/refreshToken',
                data: {
                    refreshToken: model.refreshToken
                }
            });
        };

        this.signUp = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/signUp',
                data: {
                    firstName: model.firstName,
                    lastName: model.lastName,
                    email: model.email,
                    password: model.password
                }
            });
        };

        this.signIn = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/signIn',
                data: {
                    email: model.email,
                    password: model.password
                }
            });
        };

        this.resetPassword = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/resetPassword',
                data: {
                    email: model.email
                }
            });
        };

        this.verifyEmail = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/verifyEmail',
                data: {
                    emailVerificationCode: model.emailVerificationCode
                }
            });
        };

        this.changePassword = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/changePassword',
                data: {
                    oldPassword: model.oldPassword,
                    newPassword: model.newPassword
                },
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

    }
})();