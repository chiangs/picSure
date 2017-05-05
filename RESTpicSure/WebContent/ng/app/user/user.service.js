angular.module('userModule')
.factory('userService', function($http, authService, $location) {
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
	
	
	return service;
})