angular.module('storeViewModule').factory(
		'storeService',
		function($http, authService, $location) {
			var service = {};

			var BASE_URL = 'http://localhost:8080/RESTpicSure/rest/';

			var checkLogin = function() {
				if (!authService.getToken().id) {
					$location.path('user/');
				}
			}

			service.show = function(id) {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'store/' + id
				}).then(function(res) {
					return res;
				})
			}
			
			service.index = function() {
				checkLogin();
				return $http({
					method : 'GET',
					url :  BASE_URL + 'store'
				}).then(function(res) {
					return res;
				})
			}
			
			service.showStoreInventory = function(id) {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'store/' + id + '/inventory'
				}).then(function(res) {
					return res;
				})
			}
			
			service.addToCart = function(item) {
				checkLogin();
				return $http({
					method : 'POST',
					url : BASE_URL + 'user/' + authService.getToken().id + '/inventoryItem/' + item.id + '/cartItem',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : item
				}).then(function(res){
					return res;
				})
			}


			return service;
	})