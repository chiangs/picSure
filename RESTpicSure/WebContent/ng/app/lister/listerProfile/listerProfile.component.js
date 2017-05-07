angular.module('listerModule')
	.component('listerProfile',{
		templateUrl : 'ng/app/lister/listerProfile/listerProfile.component.html',
			controller : function(listerService, authService,$location, $scope) {

			 var vm = this;

			 vm.listerData = [];

			 vm.storeData = [];

						vm.reload = function() {
							listerService.getListerData().then(function(res) {
								console.log(res.data);
								vm.listerData = res.data;
								listerService.getStoreData().then(function(r) {
									console.log(r.data);
									vm.storeData = r.data;
								})
							})
						}

						vm.destroyListerAccount = function() {
							listerService.destroyListerAccount(
									authService.getToken().id).then(
									function(res) {
										$location.path('/');
									})
						}
						vm.updateLister = function() {
							listerService.updateLister
						}

						vm.reload();
					},
					controllerAs : 'vm'
				})