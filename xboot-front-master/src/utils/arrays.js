export default {

	findIndexById: function(array, id) {
		for (var index = 0; index < array.length; index++) {
			var item = array[index]
			if (item.id == id) {
				return index
			}
		}
		return -1
	},
	
	deleteByIndex: function(array, index) {
		if(index < array.length){
			array.splice(index, 1)
			return 1
		} else {
			return -1
		}
	}
}
