angular.module('listerModule')
.filter('fuzzySearchInv', function() {
  return function(list, text) {
    if (!text) return list;
    var results = [];
    list.forEach(function(item) {
    	for (var i in item.equipment) {

    		if(String (item.equipment[i]).toLowerCase().includes(text.toLowerCase())) {
    			return results.push(item);
    		}
    	}
    })
    return results;
  }
})
