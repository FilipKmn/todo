(function() {
    'use strict';

    angular
        .module('adminPortal')
        .service('todoApi', todoApi);

    todoApi.$inject = ['$timeout'];

    function todoApi($timeout) {

        this.findTodo = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.readTodo = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.createTodo = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.updateTodo = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.deleteTodo = function(model) {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

        this.todos = function() {
            $timeout(function() {
                successCallback({
                    //TODO fill up mocked data values
                }, 500);
            });
        };

    }
})();