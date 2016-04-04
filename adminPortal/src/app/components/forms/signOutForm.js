(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('signOutForm', function() {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/signOutForm.html',
                controller: 'SignOutFormController'
            };
        });

    angular
        .module('adminPortal')
        .controller('SignOutFormController', SignOutFormController);

    SignOutFormController.$inject = ['$scope', 'authenticationApi'];

    function SignOutFormController($scope, authenticationApi) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submit = submit;

        function submit(form) {
            if (form.$submitted && form.$invalid) {
                return false;
            }
            authenticationApi.signOut($scope.model).then(onSuccess, onError);

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