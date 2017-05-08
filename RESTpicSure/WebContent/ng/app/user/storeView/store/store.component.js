angular.module('storeViewModule').component('storeView', {
	templateUrl : 'ng/app/user/storeView/store/store.component.html',
	controller : function($routeParams, storeService, $scope) {
		var vm = this;
		vm.stores = [];
		vm.storeInventory = [];
		vm.showStore = true;
		vm.cart = [];
		
		
		
		vm.showCart = function() {
			vm.showStore = false;
			vm.getCart();
		}
		
		vm.getCart = function() {
			storeService.showCart().then(function(res){
			vm.cart = res.data;
			vm.cartItemCount = vm.cart.cartItems.length;
			})
		}
		
		vm.backToStore = function() {
			vm.showStore = true;
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
		
		vm.addToCart = function(dates, item) {
			storeService.addToCart(dates, item.id).then(function(res){
				vm.getCart();
			})
		}
		
		vm.removeItem = function(ci) {
			storeService.removeCartItem(ci.id).then(function(res){
				vm.getCart();
			})
		}
		
		vm.getCart();
		
	},
	controllerAs : 'vm',
	bindings : {
		store : '<',
		goBack : '&',
		onUpdate : '&'
	}
})
