(function() {
    'use strict';

    angular
        .module('adminPortal')
        .service('sessionService', sessionService);

    function sessionService() {
        this.save = function(sessionData) {
            localStorage.setItem('accessToken', sessionData.accessToken);
            localStorage.setItem('refreshToken', sessionData.refreshToken);
            localStorage.setItem('userId', sessionData.userId);
            localStorage.setItem('userFirstName', sessionData.userFirstName);
            localStorage.setItem('userLastName', sessionData.userLastName);
            localStorage.setItem('userRole', sessionData.userRole);
            localStorage.setItem('userEmail', sessionData.userEmail);
        };

        this.clear = function() {
            localStorage.removeItem('accessToken');
            localStorage.removeItem('refreshToken');
            localStorage.removeItem('userId');
            localStorage.removeItem('userFirstName');
            localStorage.removeItem('userLastName');
            localStorage.removeItem('userRole');
            localStorage.removeItem('userEmail');
        };

        this.getSessionData = function() {
            return {
                accessToken: localStorage.getItem('accessToken'),
                refreshToken: localStorage.getItem('refreshToken'),
                userId: localStorage.getItem('userId'),
                userFirstName: localStorage.getItem('userFirstName'),
                userLastName: localStorage.getItem('userLastName'),
                userRole: localStorage.getItem('userRole'),
                userEmail: localStorage.getItem('userEmail')
            };
        };

        this.isLoggedIn = function() {
            return localStorage.getItem("accessToken") !== null;
        };
    }
})();