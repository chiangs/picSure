angular.module('listerModule')
	.component('listerInventory',{
		
		templateUrl : 'ng/app/lister/listerInventory/listerInventory.component.html',
		
		controller : function(listerService, authService, $location, $scope){
			
			var vm = this;
			
			vm.listerInventory = [];
			
			vm.displayListerInventory = function(){
				listerService.inventoryIndex(
						authService.getToken().id)
						.then(function(res){
							vm.listerInventory = res.data;
						})
			}
		},
	
		controllerAs : 'vm'
	})
	
	