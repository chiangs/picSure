angular.module('navbar').component('navbar', {
	templateUrl : 'ng/app/nav/navbar/navbar.component.html',
	controller : function(authService, $location) {
		var vm = this;

		vm.isLoggedIn = function() {
			if (authService.getToken().id) {
				vm.name = authService.getToken().fName;
				return true;
			}
			return false;
		}

		vm.isAdmin = function() {
			return (authService.getToken().admin === "true");
		}
		
		vm.profileRoute = function() {
			if (authService.getToken().admin === "true") {
				$location.path('/lister/listerProfile');
			} 
			else {
				$location.path('/user/userProfile');				
			}
		}

	},
	controllerAs : 'vm'
})