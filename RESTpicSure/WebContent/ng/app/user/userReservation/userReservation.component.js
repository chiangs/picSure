angular.module('userModule')
	.component('userReservation', {
		templateUrl: 'ng/app/user/userReservation/userReservation.component.html',
		controller : function(userService, authService, $location, $scope){
		var vm = this;	
		vm.showResSummary = true;
		vm.userReservations = [];
		vm.selected = null;
		vm.storeDetails = [];

		
		vm.reload = function() {
			userService.getUserReservations().then(function(res) {
				vm.userReservations = res.data;
			});
		}
		
		vm.numItems = function(reservation) {
			return reservation.reservationItems.length;
		}
		
		vm.displayRes = function(r) {
			console.log(r);
			vm.showResSummary = false;
			userService.getStoreData(r.id).then(function(res){
				vm.storeDetails = res.data;
			})
			vm.selected = r;
			
		}
		
		vm.goBack = function() {
			vm.showResSummary = true;
		}
		
		vm.deleteRes = function(reservation) {
			userService.destroyReservation(reservation.id).then(function(res) {
				vm.reload();
			})
		}
		
		vm.displayTable = function() {
			vm.showResSummary = true;
			vm.selected = null;
		}
		
		vm.reload();	
		},
		controllerAs: 'vm'
	})