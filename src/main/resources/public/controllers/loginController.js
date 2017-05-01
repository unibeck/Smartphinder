angular.module('SmartPhinder').controller('LoginCtrl', ['$scope', 'ResponseFactory', function($scope, ResponseFactory){

    $scope.auth = null;

    $scope.signIn = function() {
        ResponseFactory.signIn($scope.auth);
    }
}]);
