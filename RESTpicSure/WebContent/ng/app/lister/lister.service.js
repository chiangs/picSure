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
				url : BASE_URL + 'reservation/' + id,
			})
		}

		service.inventoryIndex = function(id) {
			checkLogin();
			return $http({
				method : 'GET',
				url : BASE_URL + 'store/' +id + '/inventory/',
			}).then(function(res) {
				return res;
			})
		}

		service.storeInventoryByUserId = function(){
			checkLogin();
			return $http({
				method :'GET',
				url : BASE_URL +'user/' + authService.getToken().id  +'/store/',
			})
		}

		service.destroyStoreAccount = function() {
			checkLogin();
			return $http({
				method : 'DELETE',
				url : BASE_URL + 'store/' + authService.getToken().id,
			}).then(function(res){
				authService.logout();
			})
		}

		service.destroyListerAccount = function(){
			checkLogin();
			return $http({
				method : 'Delete',
				url : BASE_URL + 'user/' + authService.getToken().id,
			}).then(function(res){
				authSercive.logout();
			})
		}

		service.updateLister = function (user) {
			checkLogin()
			return $http({
				method : 'PUT',
				url : BASE_URL + 'user/' + authService.getToken().id,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : user
			})
		}

		service.getListerData = function() {
			checkLogin();
			return $http({
				method : 'GET',
				url: BASE_URL +'user/' + authService.getToken().id,
			}). then(function(res){
				return res;
			})
		}

		service.getStoreData = function() {
			checkLogin();
			return $http ({
				method : 'GET',
				url : BASE_URL + 'store/' + authService.getToken().id,
			}). then(function(res){
				return res;
			})
		}

		service.updateStoreData = function(store){
			console.log(store)
			console.log("IN SERVICE")
			console.log(store.address)
			checkLogin();
			return $http ({
				method : 'PUT',
				url: BASE_URL + 'store/' + authService.getToken().id,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : store
			})
		}

		service.destroyInventoryItem = function(id) {
			checkLogin();
			return $http({
				method : 'DELETE',
				url : BASE_URL + 'inventoryItem/' + id,
			}).then(function(res){
				return res;
			})
		}
		
		service.updateInventoryItems = function(inventoryId, item) {
			checkLogin();
			return $http({
				method : 'PUT',
				url : BASE_URL + 'inventoryItems/inventory/' + inventoryId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : item
			}).then(function(res){
				return res;
			})
		}
		
		service.updateStoreAddress = function(store){
			console.log(store);
		checkLogin();
		return $http ({
			method : 'PUT',
			url: BASE_URL + 'store/' + store.address.id + '/address/',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : store.address
		})
	}
		
		service.editInventoryItems = function(){
			checkLogin();
			return $http ({
				method : 'PUT',
				url: BASE_URL + 'inventoryItem' + id, 
			})
		}
		
		service.addEquipmentItem = function(inventoryItem){
			checkLogin();
			return $http({
				method : 'POST',
				url: BASE_URL + 'equipment',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : inventoryItem
			}).then(function(res){
				return res;
			})
		}

		service.addItemToInventoryList = function(id){
			checkLogin();
			return $http({
				method : 'POST',
			    url: BASE_URL + 'user/' + authService.getToken().id + '/equipment/'+ id + '/inventoryItem/',
				headers : {
					'Content-Type' : 'application/json'
				}
			})
		}
		
		
		return service;
	})
