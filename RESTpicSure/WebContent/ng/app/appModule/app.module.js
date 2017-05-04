angular.module('appModule', [ 'ngRoute', 'static', 'navUser', 'navLister', 'authModule' ]).config(
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
			}).otherwise({
				template : '<error></error>'
			})
		});
