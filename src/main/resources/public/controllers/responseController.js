angular.module('SmartPhinder').controller('ResponseCtrl', ['$scope', '$http', 'ResponseFactory', function($scope, $http, ResponseFactory) {

    $scope.inventory = [];
    $scope.responseFactory = ResponseFactory;

    $scope.$watchCollection('responseFactory.getInventory()', function(newVal) {
        $scope.inventory = newVal;
    });

    $scope.resetResponse = function() {
        ResponseFactory.resetResponse();
    }

    $scope.buySmartphone = function(index) {
        if ($scope.inventory[index].stock < 1) {
            return;
        }

        ResponseFactory.buySmartphone(
                $scope.inventory[index].smartphone.id,
                $scope.inventory[index].location.city,
                $scope.inventory[index].location.state);

        $scope.inventory[index].stock--;
    }
}]);
