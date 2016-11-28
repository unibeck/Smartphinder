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
