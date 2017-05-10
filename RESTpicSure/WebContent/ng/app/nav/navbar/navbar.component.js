angular.module('navbar').component('navbar', {
	templateUrl : 'ng/app/nav/navbar/navbar.component.html',
	controller : function(authService, $location, userService) {
		var vm = this;

		vm.isLoggedIn = function() {
			if (authService.getToken().id) {
				vm.name = authService.getToken().fName;
				vm.id = authService.getToken().id;
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
			} else {
				$location.path('/user/userProfile');
			}
		}

		vm.homeRoute = function() {
			if (authService.getToken().admin === "true") {
				$location.path('/contact');
			} else {
				$location.path('/user/userMain');
			}
		}
		
		vm.reservationRoute = function() {
			if (authService.getToken().admin === "true") {
				$location.path('/lister/listerReservations');
			} else {
				$location.path('/user/userReservations');
			}
		}
		
		vm.cartRoute = function() {
			if (vm.isLoggedIn) {
				$location.path('/store/store/cart/' + vm.id);
			}
			else {
				$location.path('/');
			}
		}

	},
	controllerAs : 'vm'
})