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

angular.module('SmartPhinder').controller('AppCtrl', ['$scope', 'ResponseFactory', function ($scope, ResponseFactory) {

	$scope.responseFactory = ResponseFactory;

	$scope.showForm = true;
	$scope.customer = $scope.responseFactory.getCustomer();

	$scope.$watchCollection('responseFactory.getInventory()', function(newVal) {
		if (newVal.length > 0) {
			$scope.showForm = false;
		} else {
			$scope.showForm = true;
		}
    });

	$scope.$watch('responseFactory.getCustomer()', function(newVal) {
		if (angular.isObject(newVal)) {
			$scope.customer = newVal;
		} else {
			$scope.customer = null;
		}
    });

    $scope.signOut = function() {
        $scope.responseFactory.signOut();
    }
}]);
