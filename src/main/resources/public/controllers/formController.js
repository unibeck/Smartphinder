angular.module('SmartPhinder').controller('FormCtrl', ['$scope', 'ResponseFactory', function($scope, ResponseFactory){

  var numOfConstraints = 9;
  $scope.currentQuestion = 0;

  $scope.nextQuestion = function() {
    $scope.numOfValidConstraints = checkUserConstraint();
    $scope.currentQuestion++;
  }

  $scope.previousQuestion = function() {
    $scope.numOfValidConstraints = checkUserConstraint();
    $scope.currentQuestion--;
  }

  $scope.userConstraint = {
    "brand": "",
    "os": "",
    "price": 0,
    "battery": 0,
    "camera": 0,
    "ram": 0,
    "storage": 0,
    "resolution": 0,
    "display-size": 0
  }

  $scope.numOfValidConstraints = 0;

  function checkUserConstraint() {
    var count = 0;

    if ($scope.userConstraint.brand) {
      count++;
    }
    if($scope.userConstraint.os) {
      count++;
    }
    if($scope.userConstraint.price) {
      count++;
    }
    if($scope.userConstraint.battery) {
      count++;
    }
    if($scope.userConstraint.camera) {
      count++;
    }
    if($scope.userConstraint.ram) {
      count++;
    }
    if($scope.userConstraint.storage) {
      count++;
    }
    if($scope.userConstraint.resolution) {
      count++;
    }
    if($scope.userConstraint['display-size']) {
      count++;
    }

    return (count * 11) + 1;
  };

  $scope.smartphones = undefined;
  $scope.constraintsUsed = undefined;

  $scope.submitConstraints = function() {
    ResponseFactory.submitConstraints($scope.userConstraint);

    $scope.smartphones = ResponseFactory.getSmartphones();
  }

}]);
