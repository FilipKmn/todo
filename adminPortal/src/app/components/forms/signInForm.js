(function() {
    'use strict';

    angular
        .module('adminPortal')
        .directive('signInForm', function() {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/signInForm.html',
                controller: 'SignInFormController'
            };
        });

    angular
        .module('adminPortal')
        .controller('SignInFormController', SignInFormController);

    SignInFormController.$inject = ['$scope', 'sessionService', 'eventBus', 'authenticationApi'];

    function SignInFormController($scope, sessionService, eventBus, authenticationApi) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submit = submit;

        function submit(form) {
            if (form.$submitted && form.$invalid) {
                return false;
            }
            authenticationApi.signIn($scope.model).then(onSuccess, onError);

            function onSuccess(response) {
                sessionService.save(response.data);
                eventBus.emitEvent('UserSignedIn', {
                    accessToken: response.data.accessToken,
                    refreshToken: response.data.refreshToken,
                    userId: response.data.userId,
                    userFirstName: response.data.userFirstName,
                    userLastName: response.data.userLastName,
                    userRole: response.data.userRole,
                    userEmail: response.data.userEmail
                });
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