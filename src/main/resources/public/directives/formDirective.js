angular.module('SmartPhinder').directive('formDirective', function(){

	return {
		restrict: 'A',
		templateUrl: '../views/form.html',
		controller: 'FormCtrl'
	}
});
