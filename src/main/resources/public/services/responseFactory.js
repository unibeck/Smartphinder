angular.module('SmartPhinder').factory('ResponseFactory', ['$http', function($http) {

	var response = {
        inventory: [],
        customer: null,
        userLocation: {
            "city": "Phoenix",
            "state": "Arizona",
            "longitude": "33.45",
            "latitude": "-122.07"
        }
	};

    var key = 'AIzaSyCsdZDC-n4T_ti327fVJg8cdSWs91AJ_Ig';
    var cx = '011401687234119170726:ze8oydtvimo';

    var searchUrl = 'https://www.googleapis.com/customsearch/v1?key='+
        key+'&cx='+cx+'&searchType=image&num=1&imgType=photo&q=';

	var updateInventory = function(item) {
		return $http.get(searchUrl + item.smartphone['device-name'])
			.then(function (result) {
                item.imgUrl = result.data.items[0].link;
                item['transit-duration'] = getDuration(item.location);

                return item;
			});
	};

	var createInventory = function(inventory) {
		if (!angular.isObject(inventory)) {
			console.log("There is no inventory");
			response.inventory = [];
			return;
		}

		var count = (inventory.length > 3) ? 3 : inventory.length;

		response.inventory = [];
        for (var i = 0; i < count; i++) {
            updateInventory(inventory[i]).then(function (result) {
                response.inventory.push(result);
            });
        }
	};

	var getDuration = function(itemLocation) {
		if (!angular.isObject(itemLocation)) {
			console.log("The item's inventory location is not valid");
			response.inventory = null;
			return;
		}

        var distance = Math.hypot(
                    itemLocation.longitude - response.userLocation.longitude,
                    itemLocation.latitude - response.userLocation.latitude);

		if (distance <= 16) {
		    return "1";
		} else if (distance <= 32) {
		    return "2";
		} else if (distance <= 48) {
		    return "3";
		} else {
		    return "4+";
		}
	};

	return {
		signIn: function(customer) {
			if (!angular.isObject(customer)) {
				console.log("The customer provided is not valid");
				return;
			};

			$http({
			    url: "/login",
			    method: "POST",
			    headers: {
			        'username': customer.username,
			        'password': customer.password
			    }
            }).then(function (result) {
                response.customer = result.data;
                response.customer['status-code'] = result.status;
            });
		},

		signOut: function() {
			if (!angular.isObject(response.customer)) {
				console.log("The current customer empty and thus cannot be signed out");
				return;
			};

			response.customer = null;
		},

        submitConstraints: function(constraints) {
			if (!angular.isObject(constraints)) {
				console.log("The constraints provided are not valid");
				return;
			};

			$http({
			    url: "/smartphone/constraint",
			    method: "POST",
			    params: {"city": response.userLocation.city, "state": response.userLocation.state},
			    data: constraints,
			    header: {'Content-Type': 'application/json'}
			    })
				.then(function (result) {
                    createInventory(result.data);
            });
		},

		buySmartphone: function(smartphoneId, city, state) {
		    if (smartphoneId == null) {
				console.log("The smartphoneId provided are not valid");
				return;
			};

			$http({
			    url: "/smartphone/" + smartphoneId + "/buy",
			    method: "POST",
			    params: {"city": city, "state": state},
			    header: {'Content-Type': 'application/json'}
            });
        },

		resetResponse: function() {
		    response.inventory = [];
		},

		getInventory: function() {
			return response.inventory;
		},

        getCustomer: function() {
			return response.customer;
		},

		getResponse: function() {
			return response;
		}
	};
}]);
