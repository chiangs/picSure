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
			
			service.addToCart = function(dates, id) {
				checkLogin();
				return $http({
					method : 'POST',
					url : BASE_URL + 'user/' + authService.getToken().id + '/inventoryItem/' + id + '/cartItem',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : dates
				}).then(function(res){
					return res;
				})
			}
			
			service.showCart = function() {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'user/' + authService.getToken().id  + '/cart'
				}).then(function(res){
					return res;
				})
			}
			
			service.removeCartItem = function(id) {
				checkLogin();
				return $http({
					method : 'DELETE',
					url : BASE_URL + '/cartItem/' + id
				})
			}
			
			service.bookReservation = function(storeId) {
				checkLogin();
				return $http({
					method : 'POST',
					url : BASE_URL + 'user/' + authService.getToken().id + '/store/' + storeId + '/reservation',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : cart
				}).then(function(res){
					console.log(res)
				})
			}
			
			service.emptyCart = function() {
				return $http({
					method : 'PUT',
					url : BASE_URL + 'user/' + authService.getToken().id + '/emptyCart',
					headers : {
						'Content-type' : 'application/json'
					},
					data : cart
				}).then(function(res){
					return res;
				})
			}


			return service;
	})