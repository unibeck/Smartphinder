angular.module('SmartPhinder').directive('loginDirective', function(){

	return {
		restrict: 'A',
		templateUrl: '../views/login.html',
		controller: 'LoginCtrl'
	}
});
