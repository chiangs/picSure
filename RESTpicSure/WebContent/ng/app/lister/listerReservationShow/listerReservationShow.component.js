angular.module('listerModule').component('listerResShow', {
	templateUrl :  'ng/app/lister/listerReservationShow/listerReservationShow.component.html',
	controller : function(listerService, authService, $location, $scope, $routeParams	) {
		var vm = this;
		vm.editMode = true;
		vm.editRes = null;
		
		
		vm.backButton = function() {
			if (parseInt($routeParams.id)) {
				$location.path('/lister/listerReservations')
			} else {
				vm.goBack();
			}
		}
		
		vm.reload = function() {
			listerService.getListerSingleReservation(vm.res.id).then(function(res) {
				vm.res.reservationItems = res.data.reservationItems;
			});
		}
		
		vm.totalDaysRented = function(item) {
			var timestamp1 = new Date(item.timeOut).getTime();
			var timestamp2 = new Date(item.timeIn).getTime();
			var diff = 1;

			if (diff >= 2) {
				diff = timestamp1 - timestamp2
			}
			return diff;
		}
		
		vm.totalItemRevenue = function(item) {
			return vm.totalDaysRented(item) * item.inventoryitems.rentalRate;
		}
			
	},
	controllerAs : 'vm',
	bindings : {
		res : '<',
		goBack : '&',
		store : '<'
	}
})