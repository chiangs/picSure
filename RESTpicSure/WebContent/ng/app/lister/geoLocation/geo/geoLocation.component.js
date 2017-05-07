angular.module('geoModule').component('address', {
	templateUrl : 'ng/app/lister/geoLocation/address/geoLocation.component.html',
	controller : function(geoService, $location) {
		
		var vm = this;
		vm.address = geoService.address("Riverside, Ca");
	}
})