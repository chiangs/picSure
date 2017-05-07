angular.module('userModule').component('userResShow', {
	templateUrl :  'ng/app/user/userReservationShow/userReservationShow.component.html',
	controller : function(userService, authService, $location, $scope, $routeParams	) {
		var vm = this;
		vm.editMode = true;
		vm.editRes = null;
		vm.storeData = [];
		
		vm.backButton = function() {
			if (parseInt($routeParams.id)) {
				$location.path('/user/userReservations')
			} else {
				vm.goBack();
			}
		}
		
		
	},
	controllerAs : 'vm',
	bindings : {
		res : '<',
		goBack : '&',
		onUpdate : '&'
	}
})