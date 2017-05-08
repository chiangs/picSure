angular.module('listerModule')
	.component('listerInventory',{
		
		templateUrl : 'ng/app/lister/listerInventory/listerInventory.component.html',
		
		controller : function(listerService, authService, $location, $scope){
			
			var vm = this;
			
			vm.store={};
			vm.listerInventory = [];
			
			
			vm.displayStoreInventoryByUserId = function(){
				listerService.storeInventoryByUserId()
						.then(function(res){
							vm.store = res.data
							listerService.inventoryIndex(res.data.id)
							.then(function(r){
								vm.listerInventory = r.data.inventoryItems;
								console.log(vm.listerInventory)

							})
						}).catch(function(r){
							console.log('fail')
						})
			}
			vm.destroyInventoryItem = function(){
				listerService.destroyInventoryItem(id)
			}
			
			vm.displayStoreInventoryByUserId();	
			},
	
		controllerAs : 'vm'
	})
	
	