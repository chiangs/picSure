angular.module('listerModule')
	.component('listerInventory',{
		
		templateUrl : 'ng/app/lister/listerInventory/listerInventory.component.html',
		
		controller : function(listerService, authService, $location, $scope){
			
			var vm = this;
			
			vm.store={};
			vm.listerInventory = [];
			vm.showAddForm = false;
			vm.showEditField = false;

			vm.cancelEdit = function() {
				vm.displayStoreInventoryByUserId();
				vm.showEditField = false;
			}
			
			vm.saveEdit = function(item) {
				item.rentalRate = item.equipment.rate;
				console.log(item.rentalRate)
				console.log(item.equipment.rate)
				listerService.updateInventoryRate(item).then(function(res){
					vm.listerInventory = res.data;
					vm.displayStoreInventoryByUserId();
					vm.showEditField = false;
				})
			}
			
			vm.editItem = function() {
				vm.showEditField = true;
			}
			
			vm.displayAddForm = function(){
				vm.showAddForm = true;
				vm.listerInventory = false;
			}
			
			vm.addInventoryItem = function(e){
				listerService.addEquipmentItem(e)
				.then(function(res){
					listerService.addItemToInventoryList(res.data.id)
					.then(function(result){
						vm.showAddForm = false;
						vm.listerInventory = true;	
						vm.displayStoreInventoryByUserId();	
					})
					
					
					
				})
			}
			
			vm.showInventory = function(){
				vm.showAddForm = false;
				vm.displayStoreInventoryByUserId();	
			}

			vm.displayStoreInventoryByUserId = function(){
				listerService.storeInventoryByUserId()
						.then(function(res){
							vm.store = res.data
							listerService.inventoryIndex(res.data.id)
							.then(function(r){
								vm.inventory = r.data;
								vm.listerInventory = r.data.inventoryItems;
							})
						}).catch(function(r){
							console.log('fail')
						})
			}
			
			
			vm.updateItems = function(inventoryId, item) {
				listerService.updateInventoryItems(inventoryId, item)
				.then(function(){
				})
				vm.displayStoreInventoryByUserId()
			}
			
			vm.editInventoryItems = function(){
				listerService.editInventoryItems()
				.then(function(){
					
				})
				vm.displayStoreInventoryByUserId()
			}
			
			vm.showInventory();
		},
		controllerAs : 'vm'
	})
	
	