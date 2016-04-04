(function() {
    'use strict';

    angular
        .module('adminPortal')
        .controller('SignInPageController', SignInPageController);

    SignInPageController.$inject = ['$scope', '$state'];

    function SignInPageController($scope, $state) {

        $scope.onClickForgotPassword = onClickForgotPassword;

        function onClickForgotPassword() {
            $state.go('forgotPasswordPage');
        }

    }
})();