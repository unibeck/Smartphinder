angular.module('SmartPhinder').factory('ResponseFactory', ['$http', function($http) {

	var response = {
        inventory: null,
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

	var setInventoryIndex = function(item, i) {
		$http.get(searchUrl + item.smartphone['device-name'])
			.then(function (result) {
                item.imgUrl = result.data.items[0].link;
                item['transit-duration'] = getDuration(item.location);

                response.inventory[i] = item;
			});
	};

	var createInventory = function(inventory) {
		if (!angular.isObject(inventory)) {
			console.log("There is no inventory");
			response.inventory = null;
			return;
		}

		var count = (inventory.length > 3) ? 3 : inventory.length;

		response.inventory = {};
        for (var i = 0; i < count; i++) {
            setInventoryIndex(inventory[i], i);
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
		    response.inventory = null;
		},

		getInventory: function() {
			return response.inventory;
		},

		getResponse: function() {
			return response;
		}
	};
}]);
