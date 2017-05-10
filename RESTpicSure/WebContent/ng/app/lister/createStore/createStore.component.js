angular.module('listerModule').component('createStore', {
	templateUrl : 'ng/app/lister/createStore/createStore.component.html',
	controller : function(listerService, geoService) {
		var vm = this;
		var newGeo={longitude:"nope",latitude:"nope"};
        var geo = {longitude:"",latitude:""};
		
		 vm.createStore = function() {
             geo = (vm.storeData.address.street+","+
                      vm.storeData.address.city +","+
                      vm.storeData.address.state);
             geoService.address(geo).then(function(data){
                 newGeo = data;
                 vm.storeData.address.latitude = newGeo.lat;
                 vm.storeData.address.longitude = newGeo.long;
                 vm.storeData.active = true;
                 vm.storeData.address.country = "United States of America";
                 
                 listerService.createStore(vm.storeData).then(
                         function(res) {                            
                 });
                 
             }); 

            }
	},
	controllerAs : 'vm'
})