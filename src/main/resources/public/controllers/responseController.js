angular.module('SmartPhinder').controller('ResponseCtrl', ['$scope', '$http', 'ResponseFactory', function($scope, $http, ResponseFactory) {

  $scope.smartphones = [];
  $scope.loadingSmartphones = true;

  $scope.responseFactory = ResponseFactory;
	$scope.$watch('responseFactory.getSmartphones()', function(newVal) {
    console.log(newVal);
    $scope.smartphones = newVal;

    if(angular.isObject($scope.smartphones)) {
      $scope.loadingSmartphones = false;
    } else {
      $scope.loadingSmartphones = true;
    }
  });

  $scope.resetResponse = function() {
    ResponseFactory.resetResponse();
  }
}]);
