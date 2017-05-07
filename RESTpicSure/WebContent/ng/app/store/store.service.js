angular.module('storeModule').factory(
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
			
			


			return service;
	})