angular.module(
		'appModule',
		[ 'ngRoute', 'static', 'navbar', 'authModule', 'listerModule',
				'userModule', 'ui.bootstrap', 'modal', 'storeViewModule',
				'geoModule', 'angular.filter' ]).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).when('/contact', {
				template : '<contact></contact>'
			}).when('/registerUser', {
				template : '<register-user></register-user>'
			}).when('/registerLister', {
				template : '<register-lister></register-lister>'
			}).when('/createStore', {
				template : '<create-store></create-store>'
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
			}).when('/lister/listerReservations', {
				template : '<lister-reservation></lister-reservation>'
			}).when('/store/store/cart/:id', {
				template : '<store-view></store-view>'
			}).when('/lister/listerInventory', {
				template : '<lister-inventory></lister-inventory>'
			}).when('/store/store/:id', {
				template : '<store-view></store-view>'
			}).when('/bizStats', {
				template : '<biz-stats></biz-stats>'
			}).otherwise({
				template : '<error></error>'
			})
		});
