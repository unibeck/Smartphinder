angular.module('SmartPhinder').factory('ResponseFactory', ['$http', function($http) {

	var response = {
    smartphones: null,
		constraintsUsed: null
	};

	var key = 'AIzaSyCsdZDC-n4T_ti327fVJg8cdSWs91AJ_Ig';
  var cx = '011401687234119170726:ze8oydtvimo';

  var searchUrl = 'https://www.googleapis.com/customsearch/v1?key='+
        key+'&cx='+cx+'&searchType=image&num=1&imgType=photo&q=';

	var setSmartphoneObj = function(name, i) {
		$http.get(searchUrl+name)
			.then(
				function (result) {
					// response.smartphones[i] = {};
					//
					// response.smartphones[i].name = name;
					// response.smartphones[i].imgUrl = result.data.items[0].link;

					var newSmartphone =
						{
							"name": name,
							"imgUrl": result.data.items[0].link
						};

					response.smartphones[i] = newSmartphone;
				}
			);
	};

	var createSmartphones = function(smartphones) {
		if(!angular.isObject(smartphones)) {
			console.log("There are no smartphones");
			response.smartphones = null;
			return;
		}

		var count = (smartphones.length > 3) ? 3 : smartphones.length;

		response.smartphones = {};
    for(var i = 0; i < count; i++) {
      setSmartphoneObj(smartphones[i]['device-name']);
    }
	};

	return {
		submitConstraints: function(constraints) {
			if(!angular.isObject(constraints)) {
				console.log("The constraints provided are not valid");
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
            createSmartphones(result.data.remainder);
						response.constraintsUsed = result.data.constraintsUsed;
					}
        );
		},

		resetResponse: function() {
      response.smartphones = null;
      response.constraintsUsed = null;
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
