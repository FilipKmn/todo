(function() {
    'use strict';

    angular.module('adminPortal').directive('dryGoogleMaps', function() {
        return {
            restrict: 'E',
            scope: {
                latitude: '=',
                longitude: '=',
                editable: '='
            },
            templateUrl: 'src/app/components/maps/dryGoogleMaps.html',
            controller: 'DryGoogleMapsController'
        }
    });

    angular
        .module('adminPortal')
        .controller('DryGoogleMapsController', dryGoogleMapsController);

    dryGoogleMapsController.$inject = ['$scope'];

    function dryGoogleMapsController($scope) {

        $scope.map = {};
        initMap();

        // watch for changes for coordinates from outside
        $scope.$watch('latitude', function(newValue, oldValue) {
            if (newValue != oldValue && $scope.longitude) {
                setMarker($scope.latitude, $scope.longitude);
            }
        });

        $scope.$watch('longitude', function(newValue, oldValue) {
            if (newValue != oldValue && $scope.latitude) {
                setMarker($scope.latitude, $scope.longitude);
            }
        });

        function initMap() {
            if ($scope.latitude && $scope.longitude) {
                $scope.map.center = {
                    latitude: $scope.latitude,
                    longitude: $scope.longitude
                }
                $scope.map.zoom = 14;
                // set marker if it exits
                setMarker($scope.latitude, $scope.longitude);
            } else {
                // no marker on map
                // center on Europe
                $scope.map.center = {
                        latitude: 42,
                        longitude: 15
                    }
                    // zoom out to whole Europe
                $scope.map.zoom = 4;
            }

            if ($scope.editable) {
                $scope.map.events = {
                    click: function(mapModel, eventName, originalEventArgs) {
                        var e = originalEventArgs[0];
                        $scope.latitude = e.latLng.lat();
                        $scope.longitude = e.latLng.lng();
                        setMarker($scope.latitude, $scope.longitude);
                        $scope.$evalAsync();
                    }
                };
            }
        };

        function setMarker(lat, lng) {
            $scope.marker = {
                id: 1,
                coords: {
                    latitude: lat,
                    longitude: lng
                },
                options: {
                    draggable: $scope.editable
                },
                events: {
                    dragend: function(marker, eventName, args) {
                        $scope.latitude = marker.getPosition().lat();
                        $scope.longitude = marker.getPosition().lng();
                    }
                }
            };
            $scope.marker.id++;
        }

    }
})();