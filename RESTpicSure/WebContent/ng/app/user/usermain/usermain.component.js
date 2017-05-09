angular.module('userModule')
.component('userMain', {
	 templateUrl : 'ng/app/user/usermain/usermain.component.html',
	 controller : function(userService, $scope, $uibModal, $location) {
		var vm = this;
		vm.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU&libraries=places";
		vm.equipmentList = [];
		vm.equipmentListByType = [];
		vm.storesByEquipment = [];
		vm.modalItems = [];
		vm.locations = [];
		vm.locationsByEquipment = [];
		vm.markers = [];
		vm.showTable = true;
		vm.showLocations = false;
		vm.showEquip = false;
		vm.showStoreDiv = false;
		vm.showEquipmentList = false;
		vm.showStoresByEquipment = false;
		vm.selectedStore = null;
		vm.selectedEquipment = null;
		
		
		
		vm.showLocList = function() {
			vm.showTable = false;
			vm.showLocations = true;
			vm.showEquip = false;
			vm.showEquipmentList = false;
			vm.selectedEquipment = null;
			vm.selectedStore = null;
		}
		
		
		
		vm.showMapButton = function() {
			vm.showTable = true;
			vm.showLocations = false;		
			vm.showEquip = false;
			vm.showEquipmentList = false;
			vm.selectedEquipment = null;
			vm.selectedStore = null;
		}

		
		
		vm.showEquipList = function() {
			vm.showTable = false;
			vm.showLocations = false;
			vm.showEquip = false;
			vm.showEquipmentList = true;
			vm.selectedEquipment = null;
			vm.selectedStore = null;
		}
		
		
		
		vm.showEquipment = function(e) {
			vm.showTable = false;
			vm.showLocations = false;
			vm.showEquipmentList = false;
			vm.showEquip = true;
			vm.selectedEquipment = e;
			vm.selectedStore = null;
//		Get array of stores by equipment id
			userService.getStoresByEquipmentId(vm.selectedEquipment.id).then(function(res){
				vm.locationsByEquipment = res.data;
			})
			console.log(vm.locationsByEquipment);
		}
		
		
		
//		Get array of stores and address info
		userService.listStores().then(function(res){
		vm.locations = res.data;
		for (var i = 0; i < vm.locations.length; i++) {
			vm.markers[i] = '[' + vm.locations[i].address.latitude + ',' + 
				vm.locations[i].address.longitude + ','+ JSON.stringify(vm.locations[i]) + ']';
		}
		return vm.markers;
		})
		
		
		
		// first argument is metadata from the map 
		// second argument it marker data provided by 'userService.listStores()' above
		vm.showStore = function(mk, data) {
			var modifiedStrArray = data.split(",").slice(2).join(",").split("");
			modifiedStrArray.pop();
			modifiedStrArray = modifiedStrArray.join("")
			vm.selectedStore = JSON.parse(modifiedStrArray);
			console.log(vm.selectedStore);
			vm.showStoreDiv = true;
			vm.showStoresByEquipment = false;
		}

		
		
//		Get array of equipment list
		userService.getEquipmentList().then(function(res){
			vm.equipmentList = res.data;
		})
		
		
		
		vm.goToStore = function(s) {
			console.log(s);
			$location.path('store/store/'+ s.id);
		}
		
		
		
		vm.searchStoresByEquipment = function(equipmentId) {
			
			console.log("***************************************************************************************")
			userService.getStoresByEquipmentId(equipmentId).then(function(res){
				console.log(res);
				vm.storesByEquipment = res.data;
				vm.locations = res.data;
			})

			vm.showTable = true;
			vm.showLocations = false;
			vm.showEquip = false;
			vm.showStoreDiv = false;
			vm.showEquipmentList = false;
			vm.showStoresByEquipment = true;
		}
		
		
		vm.searchEquipmentByType = function(id) {
			userService.getStoresByEquipmentId(id).then(function(res){
				vm.equipmentByType = res.data;
			})
		}
      
	 },
	 controllerAs: 'vm'
})