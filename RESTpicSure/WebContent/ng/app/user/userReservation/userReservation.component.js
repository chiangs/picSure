angular.module('userModule')
	.component('userReservation', {
		templateUrl: 'ng/app/user/userReservation/userReservation.component.html',
		controller : function(userService, authService, $location, $scope){
		var vm = this;	
		vm.userReservations = [];
		
		vm.reload = function() {
			userService.getUserReservations().then(function(res) {
				vm.userReservations = res.data;
				console.log(vm.userReservations);
			});
		}
		
		vm.reload();	
		},
		controllerAs: 'vm'
	})