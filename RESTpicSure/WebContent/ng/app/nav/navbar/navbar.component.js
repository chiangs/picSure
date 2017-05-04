angular.module('navbar').component('navbar', {
	templateUrl: 'ng/app/nav/navbar/navbar.component.html',
	controller : function(authService) {
		var vm = this;
		
		vm.isLoggedIn = function() {
			if (authService.getToken().id) {
				return true;
			}
			return false;
		}
			
		},
		controllerAs : 'vm'
})