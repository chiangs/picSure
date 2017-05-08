angular.module('listerModule').component('listerProfile',
				{templateUrl : 'ng/app/lister/listerProfile/listerProfile.component.html',
					controller : function(geoService, listerService,
					authService, $location, $scope, $q) {
						var vm = this;
						var newGeo={longitude:"nope",latitude:"nope"};
						var defer = $q.defer();
						var geo = {longitude:"",latitude:""};
						vm.listerData = [];
						vm.storeData = [];
						vm.reload = function() {
							listerService.getListerData().then(function(res) {
								console.log(res.data);
								vm.listerData = res.data;
								listerService.getStoreData().then(function(r) {
									console.log(r.data);
									vm.storeData = r.data;
								})
							})
						}
				       
						vm.destroyListerAccount = function() {
							listerService.destroyListerAccount(
									authService.getToken().id).then(
									function(res) {
										$location.path('/');
									})
						}
						
						vm.updateLister = function() {
							listerService.updateLister(vm.listerData).then(
									function(res) {
										vm.listerData = res.data;
										vm.reload();
									})
						}

						vm.updateStore = function() {
						geo = (vm.storeData.address.street+","+
								 vm.storeData.address.city +","+
								 vm.storeData.address.state);
						console.log(geo);
						geoService.address(geo).then(function(data){
							console.log("IN THEN BLOCK");
							console.log(data);
							newGeo = data;
							
							vm.storeData.address.latitude = newGeo.lat;
							vm.storeData.address.longitude = newGeo.long;
							
							console.log("IN THEN VM STORE");
							console.log(vm.storeData);
							
							listerService.updateStoreData(vm.storeData).then(
									function(res) {
										vm.storeData = res.data;
										vm.reload();
							});
						});
				
						}
						
						vm.reload();
					},
					controllerAs : 'vm'
				})