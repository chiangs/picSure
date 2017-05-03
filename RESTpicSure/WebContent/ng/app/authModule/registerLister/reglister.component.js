angular.module('authModule').component('registerLister', {
	templateUrl : 'ng/app/authModule/registerLister/reglister.component.html',
	controller : function(authService){
		var vm = this;
		
		vm.initialCountry = 'United States of America';

		vm.register = function(user) {
			return authService.register(user);
		}

	},
	controllerAs : 'vm'
		
})