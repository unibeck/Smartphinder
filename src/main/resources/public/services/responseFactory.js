angular.module('SmartPhinder').factory('ResponseFactory', ['$http', function($http) {

	var response = {
    smartphones: null
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
					console.log(response.smartphones);
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
      setSmartphoneObj(smartphones[i]['device-name'], i);
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
            createSmartphones(result.data);
					}
        );
		},

		resetResponse: function() {
      response.smartphones = null;
		},

		getSmartphones: function() {
			return response.smartphones;
		},

		getResponse: function() {
			return response;
		}
	};
}]);
