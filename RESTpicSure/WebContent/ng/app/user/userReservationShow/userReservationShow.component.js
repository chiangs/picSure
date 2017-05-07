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
		
		vm.deleteItem = function(resItem) {
			
		}
		
		vm.reload = function() {
			userService.getUserReservations().then(function(res) {
				vm.userReservations = res.data;
			});
			vm.showResSummary = false;
		}
		
	},
	controllerAs : 'vm',
	bindings : {
		res : '<',
		goBack : '&',
		store : '<'
	}
})