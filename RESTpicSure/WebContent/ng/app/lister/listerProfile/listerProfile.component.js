angular
		.module('listerModule')
		.component(
				'listerProfile',
				{
					templateUrl : 'ng/app/lister/listerProfile/listerProfile.component.html',
					controller : function(geoService, listerService,
							authService, $location, $rootScope) {

						var vm = this;

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

						vm.updateStore = function(store) {
							var string = (vm.storeData.address.street+","+
									 vm.storeData.address.city +","+
									 vm.storeData.address.state);
							var geo = geoService.address(string);
							console.log(geo+"****************************************");
							listerService.updateStoreData(vm.storeData).then(
									function(res) {
										vm.storeData = res.data;
										vm.reload();
									})
						}
//						console.log(geoService.address(vm.storeData.address.street+","+
//								 vm.storeData.address.city +","+
//								 vm.storeData.address.state));


						vm.reload();
					},
					controllerAs : 'vm'
				})