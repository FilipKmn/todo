(function() {
    'use strict';

    angular
        .module('adminPortal', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ui.router',
            'ui.bootstrap', 'ngMessages', 'pascalprecht.translate', 'app.config', 'uiGmapgoogle-maps'
        ])
        .config(routes)
        .run(run);

    routes.$inject = ['$stateProvider', '$urlRouterProvider'];

    function routes($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('usersPage', {
                url: '/user/list',
                templateUrl: 'src/app/components/pages/usersPage.html',
                controller: 'UsersPageController'
            })
            .state('signUpPage', {
                url: '/signUp',
                templateUrl: 'src/app/components/pages/signUpPage.html',
                controller: 'SignUpPageController'
            })
            .state('verifyEmailPage', {
                url: '/verifyEmail',
                templateUrl: 'src/app/components/pages/verifyEmailPage.html',
                controller: 'VerifyEmailPageController'
            })
            .state('forgotPasswordPage', {
                url: '/forgotPassword',
                templateUrl: 'src/app/components/pages/forgotPasswordPage.html',
                controller: 'ForgotPasswordPageController'
            })
            .state('signInPage', {
                url: '/signIn',
                templateUrl: 'src/app/components/pages/signInPage.html',
                controller: 'SignInPageController'
            });

        $urlRouterProvider.otherwise('/');
    }

    run.$inject = ['$rootScope', '$location', 'sessionService'];

    function run($rootScope, $location, sessionService) {
        var publicRoutes = ['/signUp', '/verifyEmail', '/forgotPassword', '/signIn'],
            isPublicRoute = function(route) {
                publicRoutes.forEach(function(publicRoute) {
                    if (route === publicRoute) {
                        return true;
                    }
                });
                return false;
            };

        $rootScope.$on('$stateChangeStart', function(ev, to) {
            if (to.name === 'signIn') {
                sessionService.clear();
            }
            $rootScope.pageTitle = to.title;

            if (!isPublicRoute($location.url()) && !sessionService.isLoggedIn()) {
                $location.path('/');
            }
        });
    }
})();