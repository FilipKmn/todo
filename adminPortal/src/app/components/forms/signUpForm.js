(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('signUpForm', function() {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/signUpForm.html',
                controller: 'SignUpFormController'
            };
        });

    angular
        .module('adminPortal')
        .controller('SignUpFormController', SignUpFormController);

    SignUpFormController.$inject = ['$scope', 'authenticationApi'];

    function SignUpFormController($scope, authenticationApi) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submit = submit;

        function submit(form) {
            if (form.$submitted && form.$invalid) {
                return false;
            }
            authenticationApi.signUp($scope.model).then(onSuccess, onError);

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