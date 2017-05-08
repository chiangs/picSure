angular.module('geoModule')
	.factory('geoService', function($http, $location){
		var service = {};

		var BASE_URL = 'http://maps.google.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU'

		service.address = function(address) {
            var geocoder = new google.maps.Geocoder();
            geocoder.geocode({ 'address': address }, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var latitude = results[0].geometry.location.lat();
                    var longitude = results[0].geometry.location.lng();
                    var geo = {longitude:longitude,latitude:latitude};
                    console.log("longitude "+ geo.longitude+" latitude "+ latitude);
                    return geo;

                } else {
                    console.log("Request failed.")
                }
                return geo;
            });

        };

		return service;
	})

	
	
	
