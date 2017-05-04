angular.module('authModule').component('login', {
	templateUrl : 'ng/app/authModule/login/login.component.html',
	controller : function(authService, $location) {
		var vm = this;
		vm.error = null;
		vm.showListerLogin = false;
		vm.showListerLoginButton = true;
		
		vm.loginUser = function(user) {
			authService.login(user).then(function(){
				console.log('in auth loginUser')
				$location.path('/contact');
			}).catch(function(){
				vm.error = "Something went wrong";
			})
		}
		
		vm.displayListerLogin = function() {
			vm.showListerLogin = true;
			vm.showListerLoginButton = false;
		}
		
	},
	controllerAs : 'vm'
})