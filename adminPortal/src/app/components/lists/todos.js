(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('todos', function() {
            return {
                restrict: 'E',
                scope: {

                },
                templateUrl: 'src/app/components/lists/todos.html',
                controller: 'TodosController'
            };
        });

    angular
        .module('adminPortal')
        .controller('TodosController', TodosController);

    TodosController.$inject = ['$scope', 'eventBus', 'userApi'];

    function TodosController($scope, eventBus, userApi) {

        $scope.model = [];
        $scope.errorCode = null;
        $scope.onUserSelected = eventBus.onEvent('UserSelected', onUserSelected);

        function load(userId, drop, take) {
            var request = {
                userId: userId,
                drop: drop,
                take: take
            }
            userApi.userTodos(request).then(onSuccess, onError);

            function onSuccess(response) {
                $scope.model = response.data;
            }

            function onError(response) {
                if (response.data && response.data.code) {
                    $scope.errorCode = response.data.code;
                } else {
                    $scope.errorCode = 'UNKNOWN_ERROR';
                }
            }

        }

        function onUserSelected(event, payload) {
            load(payload.id)
        }

    }
})();