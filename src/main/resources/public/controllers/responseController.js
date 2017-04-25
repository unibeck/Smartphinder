angular.module('SmartPhinder').controller('ResponseCtrl', ['$scope', '$http', 'ResponseFactory', function($scope, $http, ResponseFactory) {

    $scope.inventory = [];
    $scope.loadingInventory = true;
    $scope.responseFactory = ResponseFactory;

    $scope.$watch('responseFactory.getInventory()', function(newVal) {
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

    $scope.buySmartphone = function(index) {
        if ($scope.inventory[index].stock < 1) {
            return;
        }
        $scope.inventory[index].stock--;

        ResponseFactory.buySmartphone(
                $scope.inventory[index].smartphone.id,
                $scope.inventory[index].location.city,
                $scope.inventory[index].location.state);
    }
}]);
