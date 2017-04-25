angular.module('SmartPhinder').controller('ResponseCtrl', ['$scope', '$http', 'ResponseFactory', function($scope, $http, ResponseFactory) {

    $scope.inventory = [];
    $scope.loadingInventory = true;
    $scope.responseFactory = ResponseFactory;

    $scope.$watch('responseFactory.getInventory()', function(newVal) {
        console.log(newVal);
        $scope.inventory = newVal;

        if (angular.isObject($scope.inventory)) {
            $scope.loadingInventory = false;
        } else {
            $scope.loadingInventory = true;
        }
    });

    $scope.resetResponse = function() {
        ResponseFactory.resetResponse();
    }
}]);
