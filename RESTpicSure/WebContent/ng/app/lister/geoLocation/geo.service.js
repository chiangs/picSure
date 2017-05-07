angular.module('geoModule')
	.factory('geoService', function($http, $location){
		var service = {};
		
		var BASE_URL = 'http://maps.googleapis.com/maps/api/js?'
			
		service.address = function GetLocation(address) {
            var geocoder = new google.maps.Geocoder();
            var address = address;
            geocoder.geocode({ 'address': address }, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var latitude = results[0].geometry.location.lat();
                    var longitude = results[0].geometry.location.lng();
                    console.log("in function "+latitude+", "+longitude);
                } else {
                    console.log("Request failed.")
                }
            });
        };
		
		return service;
	})