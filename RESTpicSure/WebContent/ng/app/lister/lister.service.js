angular.module('listerModule')
	.factory('listerService', function($http, $filter, authService, $location, $rootScope){
		var service = {};
		
		var BASE_URL = 'http://localhost:8080/RESTpicSure/rest/'
			
		var checkLogin = function() {
			if (!authService.getToken().id) {
				$location.path('/');
			}
		}
		
		service.listerResIndex = function() {
			checkLogin();
			return $http({
				method : 'GET',
				url : BASE_URL + 'store/' + authService.getToken().id + '/reservation'
			}).then(function(res) {
				return res;
			})
		}
		
		service.showRes = function(id) {
			checkLogin();
			return $http({
				method : 'GET',
				url : BASE_URL + 'reservation/' + id
			})
		}
		
		service.inventoryIndex = function(id) {
			checkLogin();
			return $http({
				method : 'GET',
				url : BASE_URL + 'inventoryItem/' + id
			}).then(function(res) {
				return res;
			})
		}
		
		service.destroyListerAccount = function() {
			checkLogin();
			return $http({
				method : 'DELETE',
				url : BASE_URL + '/user/' + authService.getToken().id
			}).then(function(res){
				authService.logout();
			})
		}
		
		
			
		return service;
	})