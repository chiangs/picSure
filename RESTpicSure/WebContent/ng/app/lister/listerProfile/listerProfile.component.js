angular.module('listerModule')
	.component('listerProfile', {
		templateUrl: 'ng/app/lister/listerProfile/listerProfile.component.html',
		controller : function(listerService, authService, $location, $scope){
		
			var vm = this;
			vm.destroyListerAccount = function() {
				listerService.destroyListerAccount(authService.getToken().id)
				.then(function(res) {
					$location.path('/');
				})
			}
			vm.updateLister = function(){
				listerService.updateLister
			}
		},
		controllerAs: 'vm'
	})