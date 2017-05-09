angular.module('storeViewModule').component('storeView', {
	templateUrl : 'ng/app/user/storeView/store/store.component.html',
	controller : function($routeParams, storeService, $scope, $location, $filter) {
		var vm = this;
		var storeId = $routeParams.id;
		vm.stores = [];
		vm.storeInventory = [];
		vm.showStore = true;
		vm.cart = [];
		vm.showConfirmationButton = false;

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

		var navCartRoute = function() {
			if ($location.path().split('/')[3] == 'cart') {
				vm.showCart()
			}
		}
		
		navCartRoute();
		
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
		
		vm.createReservation = function() {
			storeService.bookReservation(storeId).then(function(res){
				storeService.emptyCart().then(function(){
					vm.getCart();
				});
				vm.showConfirmationButton = true;
			})
		}
		
		vm.continueButton = function() {
			$location.path('/user/userReservations');
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
