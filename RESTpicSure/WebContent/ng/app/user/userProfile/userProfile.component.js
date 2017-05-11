angular.module('userModule')
	.component('userProfile', {
		templateUrl: 'ng/app/user/userProfile/userProfile.component.html',
		controller : function(userService, authService, $location, $scope){
		
			var vm = this;
			vm.userData = [];
			
			vm.destroyuserAccount = function() {
				userService.destroyUserAccount(authService.getToken().id).then(function(res) {
					$location.path('/');
				})
			}
			
			vm.reload = function() {
				userService.getUserData().then(function(res) {
					vm.userData = res.data;
				});
			}
			
			vm.deleteAccount = function() {
				userService.destroyUser(authService.getToken().id)
				.then(function(res) {
					$location.path('/');
					vm.reload();
				})
			}
			
			vm.updateUserProfile = function() {
				userService.updateUser(vm.userData).then(function(res){
					vm.userData = res.data;
					vm.reload();
				})
			}
			
			vm.reload();
			
		},
		controllerAs: 'vm'
	})