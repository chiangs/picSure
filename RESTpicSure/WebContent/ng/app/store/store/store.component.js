angular.module('storeModule').component('store', {
	templateUrl : 'ng/app/store/store.component.html',
	controller : function($routeParams, storeService) {
		var vm = this;
		vm.store = [];
		
		if (!vm.todo && parseInt($routeParams.id)) {
			storeService.show(parseInt($routeParams.id)).then(function(res) {
				console.log(res.data);
				vm.store = res.data;
			})
		}

	},
	controllerAs : 'vm',
	bindings : {
		todo : '<',
		goBack : '&',
		onUpdate : '&'
	}
})
