(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('userRoleDropDown', function() {
            return {
                restrict: 'E',
                scope: {
                    name: '@',
                    required: '@',
                    id: '@',
                    selectedId: '='
                },
                templateUrl: 'src/app/components/dropdowns/enums/userRoleDropDown.html',
                controller: 'UserRoleDropDownController'
            };
        });

    angular
        .module('adminPortal')
        .controller('UserRoleDropDownController', UserRoleDropDownController);

    UserRoleDropDownController.$inject = ['$scope', 'userRole'];

    function UserRoleDropDownController($scope, userRole) {

        $scope.model = userRole;

    }
})();