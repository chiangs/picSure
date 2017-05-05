angular.module('listerModule')
	.component('store', {
		templateUrl: 'ng/app/lister/store/store.component.html',
		controller : function(listerService, $filter, $scope){
		
			var vm = this;
			vm.inventory = [];			
			
			vm.reload = function() {
				listerService.inventoryIndex().then(function(res) {
					console.log(res.data);
					vm.inventory = res.data;
				});
			}
			
			vm.reload();
		},
		controllerAs: 'vm'
	})