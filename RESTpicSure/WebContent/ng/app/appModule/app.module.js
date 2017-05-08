angular.module('appModule', [ 'ngRoute', 'static', 'navbar', 'authModule', 'listerModule', 'userModule', 'ui.bootstrap', 'modal', 'storeViewModule', 'geoModule']).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).when('/contact', {
				template : '<contact></contact>'
			}).when('/registerUser', {
				template : '<register-user></register-user>'
			}).when('/registerLister', {
				template : '<register-lister></register-lister>'
			}).when('/login', {
				template : '<login></login>'
			}).when('/lister/listerProfile', {
				template : '<lister-profile></lister-profile>'
			}).when('/inventory', {
				template : '<lister-inventory></lister-inventory>'
			}).when('/user/userMain', {
				template : '<user-main></user-main>'
			}).when('/user/userProfile', {
				template : '<user-profile></user-profile>'
			}).when('/user/userReservations', {
				template : '<user-reservation></user-reservation>'
			}).when('/store/store/cart/:id', {
				template : '<store-view></store-view>'
			}).when('/lister/listerInventory', {
				template : '<lister-inventory></lister-inventory>'
			}).when('/store/store/:id', {
				template : '<store-view></store-view>'
			}).otherwise({
				template : '<error></error>'
			})
		});
