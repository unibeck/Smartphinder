angular.module('SmartPhinder').factory('ResponseFactory', ['$http', function($http) {

	var response = {
    smartphones: [],
		constraintsUsed: []
	};

	return {
		submitConstraints: function(constraints) {
			if(!angular.isObject(constraints)) {
				return;
			};

      var config = {
        headers : {
          'Content-Type': 'application/json'
        }
      };

			$http.post("http://localhost:8080/smartphone/constraint", constraints, config)
				.then(
					function (result) {
            response.smartphones = result.data.remainder;
						response.constraintsUsed = result.data.constraintsUsed;
					}
        );
		},

		resetResponse: function() {
      response.smartphones = [];
      response.constraintsUsed = [];
		},

		getSmartphones: function() {
			return response.smartphones;
		},

		getConstraintsUsed: function() {
			return response.constraintsUsed;
		},

		getResponse: function() {
			return response;
		}
	};
}]);
