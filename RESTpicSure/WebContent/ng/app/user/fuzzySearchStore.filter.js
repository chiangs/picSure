angular.module('userModule')
.filter('fuzzySearchStore', function() {
  return function(list, text) {
	  console.log(list)
    if (!text) return list;
    var results = [];
    list.forEach(function(item) {
    	for (var i in item.equipment) {

    		if(String (item.equipment[i]).toLowerCase().includes(text.toLowerCase())) {
    			return results.push(item);
    		}
    	}
    })
    console.log(results)
    return results;
  }
})
