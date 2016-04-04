(function() {
    'use strict';

    angular
        .module('adminPortal')
        .constant('userRole', [
            'ADMIN',
            'CUSTOMER'
        ]);
})();