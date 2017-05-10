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
				method : 'DELETE',
				url : BASE_URL + 'user/' + authService.getToken().id,
			}).then(function(res){
				authService.logout();
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
		
		service.createStore = function(store) {
			checkLogin();
			return $http ({
				method : 'POST',
				url: BASE_URL + 'user/' + authService.getToken().id + '/store',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : store
			}).then(function(res){
				$location.path('/inventory');
			})
		}

		service.updateStoreData = function(store){
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
		
		//this updates active/inactive
		service.updateInventoryItems = function(id, item) {
			checkLogin();
			return $http({
				method : 'PUT',
				url : BASE_URL + 'inventoryItems/inventory/' + id,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : item
			}).then(function(res){
				return res;
			})
		}
		
		//this updates rate
		service.updateInventoryRate = function(item) {
			checkLogin();
			return $http({
				method : 'PUT',
				url : BASE_URL + 'inventoryItem/' + item.id,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : item
			}).then(function(res){
				$http({
					method : 'PUT',
					url : BASE_URL + 'equipment/' + item.equipment.id,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : item.equipment
				})
				return res;
			})
		}
		
		service.updateStoreAddress = function(store){
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
		
		service.getListerReservations = function(storeId) {
			checkLogin();
			return $http(
					{
						method : 'GET',
						url : BASE_URL + 'store/' + authService.getToken().id + '/reservation',
					}).then(function(res) {
				return res;
			})
		}
		
		service.getListerSingleReservation = function(id) {
			checkLogin();
			return $http({
				method : 'GET',
				url : BASE_URL + 'reservation/' + id
			}).then(function(res){
				return res;
			})
		}
		
		
		return service;
	})
