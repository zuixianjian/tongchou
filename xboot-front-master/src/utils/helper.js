export default {
	ObjectIsNull: function(object) {
		if (!object && typeof(object) != 'undefined' && object != 0) {
			return false
		}
		if (typeof(object) == "undefined") {
			return false //alert("undefined");
		}
		return Object.keys(object).length === 0
	},
	StringIsNull: function(str) {
		return '' === str || str === null
	},

	fieldIsUndefined: function(object) {
		return typeof(object) === "undefined"
	},
	//生成从minNum到maxNum的随机数
	randomNum: function(minNum, maxNum) {
		switch (arguments.length) {
			case 1:
				return parseInt(Math.random() * minNum + 1, 10)
			case 2:
				return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10)
				//或者 Math.floor(Math.random()*( maxNum - minNum + 1 ) + minNum )
			default:
				return 0
		}
	},
	uuid: function() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 36; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
		s[8] = s[13] = s[18] = s[23] = "-";

		var uuid = s.join("");
		return uuid;
	},

	/**
	 * Localstorage 存储数据
	 * @param {String} key
	 * @param {Object} value
	 * @returns
	 */
	setLocalstorage: function(key, value) {
		var storage = window.localStorage
		if (!storage) {
			window.alert('浏览器不支持localstorage')
			return false
		}
		storage.setItem(key, value)
	},

	/**
	 * Localstorage 提取存储
	 * @param   {String} key
	 * @returns {Boolean} Boolean or String
	 */
	getLocalstorage: function(key, defalut) {
		var storage = window.localStorage
		if (!storage) {
			window.alert('浏览器不支持localstorage')
			return defalut
		}
		let value = storage.getItem(key)
		return value ? value : defalut
	},

	/**
	 * Localstorage 删除存储
	 * @param {String} key
	 * @returns
	 */
	removeLocalstorage: function(key) {
		var storage = window.localStorage
		if (!storage) {
			window.alert('浏览器不支持localstorage')
			return false
		}
		storage.removeItem(key)
	}

}
