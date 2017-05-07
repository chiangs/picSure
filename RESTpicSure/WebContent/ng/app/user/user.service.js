angular.module('userModule').factory(
		'userService',
		function($http, authService, $location) {
			var service = {};

			var BASE_URL = 'http://localhost:8080/RESTpicSure/rest/';

			var checkLogin = function() {
				if (!authService.getToken().id) {
					$location.path('user/');
				}
			}

			service.listStores = function() {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'address'
				}).then(function(res) {
					return res;
				})
			}

			service.getUserData = function() {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'user/' + authService.getToken().id
				}).then(function(res) {
					return res;
				})
			}

			service.destroyUser = function() {
				checkLogin();
				return $http({
					method : 'DELETE',
					url : BASE_URL + 'user/' + authService.getToken().id
				}).then(function() {
					authService.logout();
				})
			}

			service.updateUser = function(user) {
				checkLogin();
				return $http({
					method : 'PUT',
					url : BASE_URL + 'user/' + authService.getToken().id,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : user
				})
			}

			service.getUserReservations = function() {
				checkLogin();
				return $http(
						{
							method : 'GET',
							url : BASE_URL + 'user/'
									+ authService.getToken().id
									+ '/reservation'
						}).then(function(res) {
					return res;
				})
			}

			service.destroyReservation = function(id) {
				checkLogin();
				return $http({
					method : 'DELETE',
					url : BASE_URL + 'reservation/' + id
				}).then(function(res) {
					return res;
				})
			}
			
			service.destroyResItem = function(id) {
				checkLogin();
				return $http({
					method : 'DELETE',
					url : BASE_URL + 'reservationItem/' + id
				}).then(function(res){
					return res;
				})
			}

			service.getEquipmentList = function() {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'equipment'
				}).then(function(res) {
					return res;
				})
			}

			service.getStoreData = function(id) {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'store/' + id
				}).then(function(res) {
					return res;
				})
			}
			
			service.getStoresByEquipmentId = function(id) {
				checkLogin();
				return $http({
					method : 'GET',
					url : BASE_URL + 'store/equipment/' + id
				}).then(function(res) {
					return res;
				})
			}

			return service;
		})