angular.module('userModule')
	.component('userProfile', {
		templateUrl: 'ng/app/user/userProfile/userProfile.component.html',
		controller : function(userService, authService, $location, $scope){
		
			var vm = this;
			vm.destroyuserAccount = function() {
				userService.destroyUserAccount(authService.getToken().id).then(function(res) {
					$location.path('/');
				})
			}
			
		},
		controllerAs: 'vm'
	})