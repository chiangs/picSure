angular.module('geoModule')
	.factory('geoService', function($http, $location){
		var service = {};
		var geo = {'longitude':'nope','latitude':'nope'};

		var BASE_URL = 'https://maps.googleapis.com/maps/api/geocode/json?'
			
		service.address = function(address) {
			var p = new Promise(function(resolve, reject){
	            var geocoder = new google.maps.Geocoder();
	            geocoder.geocode({ 'address': address}, function (results, status) {
	                if (status == google.maps.GeocoderStatus.OK) {
	                    var lat = results[0].geometry.location.lat();
	                    var long = results[0].geometry.location.lng();
	                    geo = {'long':long,'lat':lat};
	                    resolve(geo);
	                } 
	                else {
	                    console.log("Request failed.")
	                    reject(results);
	                }
	            });
			});
			
			return p;
        };

		return service;
	})
