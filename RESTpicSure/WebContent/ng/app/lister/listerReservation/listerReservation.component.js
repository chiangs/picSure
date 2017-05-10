angular.module('listerModule')
	.component('listerReservation', {
		templateUrl: 'ng/app/lister/listerReservation/listerReservation.component.html',
		controller : function(listerService, authService, $location, $scope){
		var vm = this;	
		vm.showResSummary = true;
		vm.listerReservations = [];
		vm.selected = null;
		vm.storeDetails = [];

		
		vm.reload = function() {
			listerService.getListerReservations().then(function(res) {
				vm.listerReservations = res.data;
			});
		}
		
		vm.numItems = function(reservation) {
			return reservation.reservationItems.length;
		}
		
		vm.displayRes = function(r) {
			vm.showResSummary = false;
			listerService.getStoreData(r.id).then(function(res){
				vm.storeDetails = res.data;
			})
			vm.selected = r;
			
		}
		
		vm.goBack = function() {
			vm.showResSummary = true;
		}
		
		
		
		vm.displayTable = function() {
			vm.showResSummary = true;
			vm.selected = null;
		}
		
		vm.reload();	
		},
		controllerAs: 'vm'
	})