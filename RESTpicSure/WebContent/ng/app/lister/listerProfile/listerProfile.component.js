angular.module('listerModule').component('listerProfile',
                {templateUrl : 'ng/app/lister/listerProfile/listerProfile.component.html',
                    controller : function(geoService, listerService,
                    authService, $location, $scope) {
                        var vm = this;
                        var newGeo={longitude:"nope",latitude:"nope"};
                        var geo = {longitude:"",latitude:""};
                        vm.listerData = [];
                        vm.storeData = [];
                        
                        vm.reload = function() {
                            listerService.getListerData().then(function(res) {
                                vm.listerData = res.data;
                                listerService.getStoreData().then(function(r) {
                                    vm.storeData = r.data;
                                })
                            })
                        }

                        vm.destroyUserAccount = function() {
                            listerService.destroyListerAccount().then(function(){
                            	})
                            }
                        
                        vm.updateLister = function() {
                            listerService.updateLister(vm.listerData).then(
                                    function(res) {
                                        vm.listerData = res.data;
                                        vm.reload();
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
                        geoService.address(geo).then(function(data){
                            newGeo = data;
                            
                            vm.storeData.address.latitude = newGeo.lat;
                            vm.storeData.address.longitude = newGeo.long;
                            console.log(vm.storeData)
                            listerService.updateStoreData(vm.storeData).then(
                                    function(res) {
                                    	vm.storeData = res.data;
                                        
                            });
                            listerService.updateStoreAddress(vm.storeData).then(
                            		function(res){
                            			vm.storeData.address = res.data;
                            			vm.reload();
                            		});
                        }); 
       	
                       }
                       vm.reload();
                    },
                    controllerAs : 'vm'
                })

				