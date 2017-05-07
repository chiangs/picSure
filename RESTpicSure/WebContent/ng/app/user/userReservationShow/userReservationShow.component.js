angular.module('userModule').component('userResShow', {
	templateUrl :  'ng/app/user/userReservationShow/userReservationShow.component.js',
	controller : function(userService, authService, $location, $scope) {
		var vm=this;
		vm.editMode = false;
		vm.editRes = null;
		
		
		
	},
	controllerAs : 'vm',
	bindings : {
		res : '<',
		goBack : '&',
		onUpdate : '&'
	}
})