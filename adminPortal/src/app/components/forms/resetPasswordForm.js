(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('resetPasswordForm', function() {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/resetPasswordForm.html',
                controller: 'ResetPasswordFormController'
            };
        });

    angular
        .module('adminPortal')
        .controller('ResetPasswordFormController', ResetPasswordFormController);

    ResetPasswordFormController.$inject = ['$scope', 'authenticationApi'];

    function ResetPasswordFormController($scope, authenticationApi) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submit = submit;

        function submit(form) {
            if (form.$submitted && form.$invalid) {
                return false;
            }
            authenticationApi.resetPassword($scope.model).then(onSuccess, onError);

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