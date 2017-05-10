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
		
		vm.totalStoreRevenue = function() {
			var grandTotal = 0;
			for (var i = 0; i < vm.reservations.length; i++) {
				for (var k = 0; k < vm.reservations[i].reservationItems.length; k++) {
					
				var diff = vm.totalDaysRented(vm.reservations[i].reservationItems[k]);
				var total = vm.totalItemRevenue(vm.reservations[i].reservationItems[k]);
				var sum = diff + total;
				grandTotal = grandTotal + sum;
				}
			}
			return grandTotal;
		}
		
		
		vm.reservations();
	},
	controllerAs : 'vm'
})
