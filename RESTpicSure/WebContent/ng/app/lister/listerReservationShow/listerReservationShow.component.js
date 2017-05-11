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
			var timestamp1 = new Date(item.timeOut).getDate();
			var timestamp2 = new Date(item.timeIn).getDate();
			var diff = timestamp2 = timestamp1;
			if (diff >= 2) {
				return diff;
			} else {
				diff = 1;
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