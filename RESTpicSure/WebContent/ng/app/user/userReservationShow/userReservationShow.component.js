angular.module('userModule').component('userResShow', {
	templateUrl :  'ng/app/user/userReservationShow/userReservationShow.component.html',
	controller : function(userService, authService, $location, $scope, $routeParams	) {
		var vm = this;
		vm.editMode = true;
		vm.editRes = null;
		
		
		vm.backButton = function() {
			if (parseInt($routeParams.id)) {
				$location.path('/user/userReservations')
			} else {
				vm.goBack();
			}
		}
		
		vm.reload = function() {
			userService.getUserReservations().then(function(res) {
				vm.userReservations = res.data;
			});
		}
		
		vm.deleteItem = function(resItem) {
			console.log(resItem);
			userService.destroyResItem(resItem.id).then(function(){
				vm.editMode = false;
				vm.editRes = null;
				vm.reload();
			})
		}
		

		
	},
	controllerAs : 'vm',
	bindings : {
		res : '<',
		goBack : '&',
		store : '<'
	}
})