angular.module('userModule')
.filter('fuzzySearchRes', function() {
  return function(list, text) {
    if (!text) return list;
    var results = [];
    list.forEach(function(item) {
    	for (var i in item) {

    		if(String (item[i]).toLowerCase().includes(text.toLowerCase())) {
    			return results.push(item);
    		}
    	}
    })
    return results;
  }
})
