<template>
	<div class="wallpaper-section">
		<div class="wallpaper-section" @mousedown="onMousedown($event)" :style="{ background: 'url(' + wallpaper + ')' }"></div>
		<div class="mouse-action-section"><div class="selection-section" v-show="selection.startSelection"></div></div>
	</div>
</template>

<script>
export default {
	name: 'wallpaper',
	data() {
		return {
			selection: {
				startSelection: false,
				startPosition: {},
				currentPosition: {},
				topLeft: null,
				bottomRight: null
			}
		};
	},
	computed: {
		wallpaper() {
			return this.$store.state.core.wallpaper;
		}
	},
	methods: {
		onMousedown(event) {
			this.$store.commit('core/SELECT_ICON', ''); //清空 图标选择
			if (event.button == 2) {
				this.$store.commit('core/SET_CONTEXT_MENU', { x: event.clientX, y: event.clientY, type: 'wall' });
			} else {
				this.$store.dispatch('core/sidebarSwitch', false);
				this.$store.commit('core/CLEAN_CONTEXT_MENU');
			}
		}
	},
	ready: function() {}
};
</script>

<style lang="less">
.wallpaper-section {
	height: 100%;
	background-size: cover;
	background: #42b983;
}
</style>
