(function() {
    'use strict';

    angular.module('adminPortal').directive('imageSelector', function() {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                id: '@',
                name: '@',
                required: '@',
                path: '=?',
                base64: '=?',
                fileName: '=?'
            },
            templateUrl: "app/components/imageSelectors/imageSelector.html",
            controller: 'ImageSelectorController',
        }
    });

    angular.module('adminPortal').controller('ImageSelectorController', ImageSelectorController);
    ImageSelectorController.$inject = ['$scope', 'fileApi', '$element'];

    function ImageSelectorController($scope, fileApi, element) {

        $scope.flagValid = null;

        // watch for changes on input
        var input = element.find('input')[0];
        input.addEventListener('change', onChange);

        $scope.$watch('path', function(path) {
            if (path) {
                $scope.flagValid = "true";

                var splitted = $scope.path.split("/");
                var model = {
                    key: splitted[0],
                    fileName: splitted[1]
                }

                fileApi.findImage(model).then(onSuccess, onError);
            }
        });

        function onSuccess(response) {
            $scope.base64 = response.data.content.replace(/\s/g, "")
            $scope.fileName = $scope.path.substring($scope.path.lastIndexOf("/") + 1);
        }

        function onError(responseData) {}

        function onChange() {
            var file = input.files[0];
            var reader = new FileReader();
            reader.onload = function(event) {
                if (this.result.indexOf("data:image/") === 0) {
                    var base64Regex = "base64,";
                    $scope.base64 = event.target.result.substring(event.target.result.indexOf(base64Regex) + base64Regex.length);
                    $scope.flagValid = "true";
                    $scope.$apply();
                } else {
                    $scope.path = null;
                    $scope.flagValid = null;
                    console.log("Not an image!");
                }
            }

            if (file) {
                $scope.fileName = file.name;
                reader.readAsDataURL(file);
            }
        }

    };

})();