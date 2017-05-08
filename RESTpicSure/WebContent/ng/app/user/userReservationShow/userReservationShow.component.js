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
			userService.getUserSingleReservation(vm.res.id).then(function(res) {
				vm.res.reservationItems = res.data.reservationItems;
			});
		}
		
		vm.deleteItem = function(resItem) {
			userService.destroyResItem(resItem.id).then(function(){
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