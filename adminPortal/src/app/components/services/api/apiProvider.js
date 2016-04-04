(function() {
    'use strict';

    angular
        .module('adminPortal')
        .provider('api', api)
        .config(apiProvider);

    function api() { // you will use just 'api' as a name when injecting the provider into other modules (controllers etc.)
        this.isMocked = false;

        this.$get = ['apiService', 'apiMockService', 'clientConfigurationValues', function(apiService, apiMockService, clientConfigurationValues) {
            if (this.isMocked) {
                return apiMockService;
            } else {
                if (clientConfigurationValues.remoteBackendUrl) {
                    apiService.init(clientConfigurationValues.remoteBackendUrl);
                }
                return apiService;
            }
        }];
    }

    function apiProvider(clientConfigurationValues, apiProvider) { //be careful to inject provider into config function by stamping provider name with 'Provider' (api + Provider)
        apiProvider.isMocked = clientConfigurationValues.useServerMock;
    }

})();