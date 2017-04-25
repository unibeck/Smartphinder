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

	$scope.showForm = true;
	$scope.responseFactory = ResponseFactory;

	$scope.$watch('responseFactory.getInventory()', function(newVal) {
		if (newVal) {
			$scope.showForm = false;
		} else {
			$scope.showForm = true;
		}
  });
}]);
