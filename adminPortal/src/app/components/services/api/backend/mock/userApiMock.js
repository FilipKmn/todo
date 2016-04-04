(function() {
    'use strict';

    angular
        .module('adminPortal')
        .service('userApi', userApi);

    userApi.$inject = ['$timeout'];

    function userApi($timeout) {

        this.users = function() {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.userTodos = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

    }
})();