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
		

		
	},
	controllerAs : 'vm',
	bindings : {
		res : '<',
		goBack : '&',
		store : '<'
	}
})