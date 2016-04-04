(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('changePasswordForm', function() {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/changePasswordForm.html',
                controller: 'ChangePasswordFormController'
            };
        });

    angular
        .module('adminPortal')
        .controller('ChangePasswordFormController', ChangePasswordFormController);

    ChangePasswordFormController.$inject = ['$scope', 'authenticationApi'];

    function ChangePasswordFormController($scope, authenticationApi) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submit = submit;

        function submit(form) {
            if (form.$submitted && form.$invalid) {
                return false;
            }
            authenticationApi.changePassword($scope.model).then(onSuccess, onError);

            function onSuccess(response) {

            }

            function onError(response) {
                if (response.data && response.data.code) {
                    $scope.errorCode = response.data.code;
                } else {
                    $scope.errorCode = 'UNKNOWN_ERROR';
                }
            }

        }

    }

})();