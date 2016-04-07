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
        $scope.selectedPage = 1;
        $scope.pages = [];
        var pageSize = 25;
        $scope.errorCode = null;
        $scope.onUserSelected = eventBus.onEvent('UserSelected', onUserSelected);

        $scope.onPageSelect = onPageSelect;

        function load(userId, drop, take) {
            var request = {
                userId: userId,
                drop: drop,
                take: take
            }
            userApi.userTodos(request).then(onSuccess, onError);

            function onSuccess(response) {
                $scope.model = response.data.results;
                calculatePages(response.data);
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

        function calculatePages(data) {
            var numberOfPages = ~~(data.totalCount / pageSize)
            if (data.totalCount % pageSize > 0) numberOfPages++;

            if (numberOfPages == $scope.pages.length) return;

            $scope.pages = [];
            for (var i = 1; i <= numberOfPages; i++) {
                $scope.pages.push(i);
            }
        }

        function onPageSelect(page) {
            var drop = (page - 1) * pageSize;
            $scope.selectedPage = page;
            load(drop, pageSize);
        }

    }
})();