<template>
	<div class="desktop">
		<wallpaper></wallpaper>
		<icon-grid></icon-grid>
		<Taskbar></Taskbar>
		<ContextMenu></ContextMenu>
		<Sidebar :sidebarShow="sidebarShow"></Sidebar>
	</div>
</template>
<script>
import Wallpaper from './Wallpaper.vue'
import Taskbar from './Taskbar.vue'
import ContextMenu from './ContextMenu.vue'
import IconGrid from './IconGrid.vue'
import Sidebar from './Sidebar.vue'
export default {
	name: 'Desktop',
	created() {
		//console.log('========Desktop=========>')
		//this.$store.dispatch('session/getToken')
	},
	beforeCreate() {
		//oncontextmenu 事件在元素中用户右击鼠标时触发并打开上下文菜单
		document.oncontextmenu = new Function('event.returnValue=false');
		//onselectstart几乎可以用于所有对象，其触发时间为目标对象被开始选中时（即选中动作刚开始，尚未实质性被选中）
		document.onselectstart = new Function("event.returnValue=false");
		this.$store.commit('core/APP_INIT');
	},
	components: {
		Taskbar,
		Wallpaper,
		IconGrid,
		ContextMenu,
		Sidebar
	},
	methods: {
		clean() {
			//this.$store.commit('manager/selectIcon', '');
		}
	},
	computed: {
		sidebarShow() {
			return this.$store.state.core.sidebar;
		}
	}
};
</script>

<style></style>
