angular.module('appModule', [ 'ngRoute', 'static', 'navbar', 'authModule', 'listerModule', 'userModule', 'ui.bootstrap', 'modal']).config(
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
			}).otherwise({
				template : '<error></error>'
			})
		});
