angular.module('appModule', [ 'ngRoute', 'static', 'navbar', 'authModule', 'listerModule', 'userModule', 'ui.bootstrap', 'modal', 'geoModule']).config(
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
				template : '<store></store>'
			}).when('/user/userMain', {
				template : '<user-main></user-main>'
			}).when('/user/userProfile', {
				template : '<user-profile></user-profile>'
			}).when('/user/userReservations', {
				template : '<user-reservation></user-reservation>' 
			}).when('/lister/listerInventory', {
				template : '<lister-inventory></lister-inventory>' 
			}).otherwise({
				template : '<error></error>'
			})
		});
