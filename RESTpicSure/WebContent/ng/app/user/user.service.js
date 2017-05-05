angular.module('userModule')
.factory('userService', function($http, authService, $location) {
	var service = {};
	
	var BASE_URL = 'http://localhost:8080/RESTpicSure/rest/user';
	
	var checkLogin = function() {
		if (!authService.getToken().id) {
			$location.path('/');
		}
	}
	
//	needs store index
	
//	needs equip index
	
	return service;
})