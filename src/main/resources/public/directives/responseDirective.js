angular.module('SmartPhinder').directive('responseDirective', function(){

	return {
		restrict: 'A',
		templateUrl: '../views/response.html',
		controller: 'ResponseCtrl'
	}
});
