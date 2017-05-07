angular.module('userModule')
	.component('userReservation', {
		templateUrl: 'ng/app/user/userReservation/userReservation.component.html',
		controller : function(userService, authService, $location, $scope){
		var vm = this;	
		vm.showResSummary = true;
		vm.userReservations = [];
		vm.selected = null;

		
		vm.reload = function() {
			userService.getUserReservations().then(function(res) {
				vm.userReservations = res.data;
			});
		}
		
		vm.numItems = function(reservation) {
			return reservation.reservationItems.length;
		}
		
		vm.displayRes = function(res) {
			console.log(res);
			vm.showResSummary = false;
			vm.selected = res;
		}
		
		vm.goBack = function() {
			vm.showResSummary = true;
		}
		
		vm.deleteRes = function(reservation) {
			console.log('in delete');
			userService.destroyReservation(reservation.id).then(function(res) {
				vm.reload();
			})
		}
		
		vm.reload();	
		},
		controllerAs: 'vm'
	})