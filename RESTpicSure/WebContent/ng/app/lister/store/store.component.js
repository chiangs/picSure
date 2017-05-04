angular.module('listerModule')
	.component('store', {
		templateUrl: 'ng/app/lister/store/store.component.html',
		controller : function(){
		
			var vm = this;
			vm.inventory = [];
			
			
			
			
			
		},
		controllerAs: 'vm'
	})