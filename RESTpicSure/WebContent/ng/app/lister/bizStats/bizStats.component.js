angular.module('listerModule').component('bizStats', {
	templateUrl : 'ng/app/lister/bizStats/bizStats.component.html',
	controller : function(listerService, authService, $location, $scope) {
		var vm = this;
		vm.reservations = [];
		vm.numReservations = null;
		vm.numItemsReserved = null;
		
		
		vm.reservations = function() {
			listerService.listerResIndex().then(function(res){
				console.log(res.data);
				vm.reservations = res.data;
			})
		}
		
		vm.numItemsReserved = function() {
			var counter = 0;
			for (var i = 0; i < vm.reservations.length; i++) {
				for (var j = 0; j < vm.reservations[i].reservationItems.length; j++) {
					counter++;
				}
			}
			return counter;
		}
		
		vm.reservations();
	},
	controllerAs : 'vm'
})
