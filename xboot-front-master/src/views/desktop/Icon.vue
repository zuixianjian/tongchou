<template>
	<div
		class="icon-item"
		:class="{selected:item.selected}"
		@contextmenu="onContextmenu($event)"
		:style="{
			height: this.cell.height + 'px',
			width: this.cell.width + 'px',
			top: this.position.y + 'px',
			left: this.position.x + 'px'
		}"
	>
		<div class="icon-body" @click="onClick($event)" @dblclick="onDblclick()" @dragstart="onDragstart()" @dragend="onDragend()" draggable="true">
			<div class="icon" :class="item.icon"></div>
			<div class="text">{{ item.name }}</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'Icon',
	props: {
		item: Object,
		grid: Object,
		cell: Object,
		order: Number
		//select: Function,
		//dragstart: Function
	},
	data() {
		return {
			position: {
				x: 0,
				y: 0
			},
			dragging: false
		};
	},
	created() {
		this.position.x = Math.floor(this.order / this.grid.row) * this.cell.width;
		this.position.y = Math.floor(this.order % this.grid.row) * this.cell.height;
		//console.log('====>' + (this.order % this.grid.row));
	},
	methods: {
		onMousedown(event) {
			console.log('onMousedown==========>' + event);
		},
		onContextmenu(event) {
			this.$store.commit('core/SET_CONTEXT_MENU', {"x":event.clientX,"y":event.clientY,"type":"icon",'data':this.item.id})
		},
		onClick() {
			this.$store.dispatch('core/selectIcon',this.item.id)
		},
		onDblclick() {
			this.$store.dispatch('core/openApp',this.item.id)
		},
		onDragstart() {},
		onDragend() {}
	}
};
</script>

<style lang="less" rel="stylesheet/less">
.icon-item {
	position: absolute;
	/*border:1px dashed #fff;*/
	&.selected {
		.icon-body {
			/*border:2px solid rgba(43, 115, 199, 0.36);*/
			/*background:rgba(43, 115, 199, 0.2);*/
			background: rgba(0, 134, 255, 0.27);
		}
	}
	&.dragging {
		opacity: 0.6;
	}
	.icon-body {
		/*border:2px solid transparent;*/
		border-radius: 4px;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translateX(-50%) translateY(-50%);
		width: 90px;
		.icon {
			height: 80px;
			width: 100%;
			margin: 0 auto;
			background-size: 64px;
		}
		.text {
			min-height: 20px;
			line-height: 20px;
			text-align: center;
			/*margin:0 -10px; */
			overflow: hidden;
			word-wrap: break-word;
			word-break: break-all;
			text-overflow: ellipsis;
			padding-bottom: 5px;
			color: #FFFFFF;
		}
	}
}
</style>
