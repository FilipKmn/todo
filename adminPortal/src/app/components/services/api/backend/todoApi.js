(function() {
    'use strict';

    angular
        .module('adminPortal')
        .service('todoApi', todoApi);

    todoApi.$inject = ['$http', 'sessionService'];

    function todoApi($http, sessionService) {

        var backendApiUrl = 'http://localhost:8080';

        this.init = function(backendUrl) {
            backendApiUrl = backendUrl;
        };

        this.findTodo = function(model) {
            return $http({
                method: 'GET',
                url: backendApiUrl + '/api/todos',
                params: {
                    drop: model.drop,
                    take: model.take
                },
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

        this.readTodo = function(model) {
            return $http({
                method: 'GET',
                url: backendApiUrl + '/api/todo/' + model.id + '',
                params: {
                    id: model.id
                },
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

        this.createTodo = function(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/todo',
                data: {
                    userId: model.userId,
                    task: model.task,
                    date: model.date
                },
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

        this.updateTodo = function(model) {
            return $http({
                method: 'PUT',
                url: backendApiUrl + '/api/todo/' + model.id + '',
                data: {
                    id: model.id,
                    userId: model.userId,
                    task: model.task,
                    date: model.date
                },
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

        this.deleteTodo = function(model) {
            return $http({
                method: 'DELETE',
                url: backendApiUrl + '/api/todo/' + model.id + '',
                data: {
                    id: model.id
                },
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

        this.allTodos = function() {
            return $http({
                method: 'GET',
                url: backendApiUrl + '/api/allTodos',
                headers: {
                    'X-TODO-AUTH': sessionService.getSessionData().accessToken
                }
            });
        };

    }
})();