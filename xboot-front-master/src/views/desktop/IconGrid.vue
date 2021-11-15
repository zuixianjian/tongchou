<template>
	<div class="icon-section">
		<appIcon v-for="(item,index) in icons" :key="item.id" :item="item" :grid="grid" :cell="cell" :order="index"></appIcon>
	</div>
</template>

<script>
import appIcon from './Icon.vue';
export default {
	name: 'IconGrid',
	components: {
		appIcon
	},
	data() {
		return {
			grid: {//网格行列个数
				row: 0,
				col: 0
			},
			cell: {//每个单元格大小
				height: 130,
				width: 130
			},
		}
	},
	created(){
		let w = document.body.clientWidth
		let h = document.body.clientHeight * 0.90
		this.grid.row = Math.ceil(h / this.cell.height)
		this.grid.col = Math.ceil(w / this.cell.width)
	},
	computed: {
		icons() {
			let icons = []
			this.$store.state.core.applications.forEach(item => {
				if(item.link){
					icons.push(item)
				}
			})
			return icons;
		}
	},
	methods: {}
};
</script>

<style></style>
