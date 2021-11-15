import helper from '@/utils/helper.js'
export default {
	namespaced: true,
	state: {
		count: 0,
		current: {},
		list: []
	},
	mutations: {
		PUSH_MESSAGE(state, message) {
			message.id = helper.uuid()
			state.current = message
			state.list.push(message)
			state.count = state.list.length
		},
		CLEAN_MESSAGE(state) {
			state.list = []
			state.count = 0
		},
		DELETE_MESSAGE(state, id) {
			state.list.forEach(function(item, i) {
				if (item.id == id) {
					state.list.splice(i, 1)
				}
			})
		}
	},
	actions: {
		pushMessage(context, message) {
			context.commit('PUSH_MESSAGE', message)
		},
		cleanMessage(context, message) {
			context.commit('CLEAN_MESSAGE', message)
		},
		deleteMessage(context, id) {
			context.commit('DELETE_MESSAGE', id)
		}
	},
	getters: {}
}
