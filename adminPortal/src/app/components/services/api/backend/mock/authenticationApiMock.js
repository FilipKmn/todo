(function() {
    'use strict';

    angular
        .module('adminPortal')
        .service('authenticationApi', authenticationApi);

    authenticationApi.$inject = ['$timeout'];

    function authenticationApi($timeout) {

        this.authenticate = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.signOut = function() {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.refreshToken = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.signUp = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.signIn = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.resetPassword = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.verifyEmail = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.changePassword = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

    }
})();