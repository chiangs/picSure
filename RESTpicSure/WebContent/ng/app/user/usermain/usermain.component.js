angular.module('userModule')
.component('userMain', {
	 templateUrl : 'ng/app/user/usermain/usermain.component.html',
	 controller : function(userService, $scope, $uibModal) {
		var vm = this;
		vm.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU&libraries=places";
		vm.equipmentList = [];
		vm.modalItems = [];
		vm.locations = [];
		vm.markers = [];
		vm.showTable = true;
		vm.showLocations = false;
		vm.showEquip = false;
		vm.showEquipmentList = false;
		vm.selectedEquipment = null;
		
		vm.showLocList = function() {
			vm.showTable = false;
			vm.showLocations = true;
			vm.showEquip = false;
			vm.showEquipmentList = false;
			vm.selectedEquipment = null;
		}
		
		vm.showMapButton = function() {
			vm.showTable = true;
			vm.showLocations = false;		
			vm.showEquip = false;
			vm.showEquipmentList = false;
			vm.selectedEquipment = null;
		}

		vm.showEquipList = function() {
			vm.showTable = false;
			vm.showLocations = false;
			vm.showEquip = false;
			vm.showEquipmentList = true;
			vm.selectedEquipment = null;
		}
		
		vm.showEquipment = function(e) {
			vm.showTable = false;
			vm.showLocations = false;
			vm.showEquipmentList = false;
			vm.showEquip = true;
			vm.selectedEquipment = e;
		}
		
//		Get array of stores and address info
		userService.listStores().then(function(res){
		console.log(res.data);
		vm.locations = res.data;
		for (var i = 0; i < vm.locations.length; i++) {
			vm.markers[i] = '[' + vm.locations[i].latitude + ',' + vm.locations[i].longitude + ']' ;
		}
		return vm.markers;
		})
		
		vm.showStore = function(mk) {
			console.log('clicked');
			console.log(mk);	
		}

//		Get array of equipment list
		userService.getEquipmentList().then(function(res){
			vm.equipmentList = res.data;
		})
      
	 },
	 controllerAs: 'vm'
})