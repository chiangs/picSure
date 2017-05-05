angular.module('userModule').component('userMain', {
	 templateUrl : 'ng/app/user/usermain/usermain.component.html',
	 controller : function(userService, $scope) {
		var vm = this;
		$scope.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU";
		vm.locations = [];
		vm.showLocations = false;
		vm.showTable = true;
		vm.showEquipment = false;
		
		vm.showLocList = function() {
			vm.showTable = false;
			vm.showLocations = true;
			vm.showEquipment = false;
		}
		
		vm.showMapButton = function() {
			vm.showTable = true;
			vm.showLocations = false;
			vm.showEquipment = false;
		}

		vm.showEquipList = function() {
			vm.showTable = false;
			vm.showLocations = false;
			vm.showEquipment = true;
		}
	
//		vm.reload = function() {
//			userService.locationIndex().then(function(res) {
//				console.log(res.data);
//				vm.locations = res.data;
//			});
//		}
//		 	
//	 reload();
	 },
	 controllerAs: 'vm'
})