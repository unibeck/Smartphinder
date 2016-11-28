angular.module('SmartPhinder', ['ngMaterial', 'ngAnimate']);

angular.module('SmartPhinder').config(function ($mdThemingProvider) {
	$mdThemingProvider.theme('default')
		.primaryPalette('blue', {
			'default': '600'
		})
		.accentPalette('orange', {
			'default': '500'
		})
		.warnPalette('red');
});

angular.module('SmartPhinder').controller('AppCtrl', function ($scope, $http) {
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
	}

	$scope.response = undefined;

	var config = {
    headers : {
      'Content-Type': 'application/json'
    }
  }

	$scope.submitConstraints = function() {
		$http.post("http://localhost:8080/smartphone/constraint", $scope.userConstraint, config)
			.then(
				function (result) {
					console.log(result);
					$scope.response = result.data;
				});
	}
});
