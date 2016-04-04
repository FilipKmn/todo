(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('verifyEmailForm', function() {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/verifyEmailForm.html',
                controller: 'VerifyEmailFormController'
            };
        });

    angular
        .module('adminPortal')
        .controller('VerifyEmailFormController', VerifyEmailFormController);

    VerifyEmailFormController.$inject = ['$scope', 'authenticationApi'];

    function VerifyEmailFormController($scope, authenticationApi) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submit = submit;

        function submit(form) {
            if (form.$submitted && form.$invalid) {
                return false;
            }
            authenticationApi.verifyEmail($scope.model).then(onSuccess, onError);

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