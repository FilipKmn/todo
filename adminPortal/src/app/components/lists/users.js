(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('users', function() {
            return {
                restrict: 'E',
                scope: {

                },
                templateUrl: 'src/app/components/lists/users.html',
                controller: 'UsersController'
            };
        });

    angular
        .module('adminPortal')
        .controller('UsersController', UsersController);

    UsersController.$inject = ['$scope', 'eventBus', 'userApi'];

    function UsersController($scope, eventBus, userApi) {

        $scope.model = [];
        $scope.errorCode = null;

        $scope.onSelect = onSelect;

        load();

        function load() {

            userApi.users().then(onSuccess, onError);

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

        function onSelect(selected) {
            eventBus.emitEvent('UserSelected', {
                id: selected.id
            });
        }
    }
})();