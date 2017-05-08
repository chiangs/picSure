angular.module('storeViewModule').component('storeView', {
	templateUrl : 'ng/app/user/storeView/store/store.component.html',
	controller : function($routeParams, storeService, $scope) {
		var vm = this;
		vm.stores = [];
		vm.storeInventory = [];
		vm.showStore = true;
		vm.cart = [];
		
		vm.showCart = function(){
			vm.showStore = false;
		}
		
		if (!vm.store && parseInt($routeParams.id)) {
			storeService.show(parseInt($routeParams.id)).then(function(res) {
				console.log(res.data);
				vm.store = res.data;
			})
		}
		
		storeService.index().then(function(res) {
			vm.stores = res.data;
		})
		
		storeService.showStoreInventory($routeParams.id).then(function(res) {
			console.log(res.data);
			vm.storeInventory = res.data;
		})
		
		
		
		
	},
	controllerAs : 'vm',
	bindings : {
		store : '<',
		goBack : '&',
		onUpdate : '&'
	}
})
