angular.module('userModule')
.factory('userService', function($http, authService, $location) {
	var service = {};
	
	var BASE_URL = 'http://localhost:8080/RESTpicSure/rest/';
	
	var checkLogin = function() {
		if (!authService.getToken().id) {
			$location.path('user/');
		}
	}
	
//	needs store index
	var listStores = function() {
		checkLogin();
		
	}
	
//	needs equip index
	
	return service;
})