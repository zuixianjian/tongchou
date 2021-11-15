<template>
	<div class="display-container">
		<div class="display-preview"><img :src="preview" /></div>
		<div class="display-list">
			<div class="display-list-item" :class="{ 'item-selected': item.selected }" v-for="(item, index) in images" :key="index" @click="onSelect(item.url)"><img :src="item.url" /></div>
		</div>
	</div>
</template>

<script>
import helper from '@/utils/helper'
export default {
	name: 'Display',
	data() {
		return {
			images: [
				{ url: './wall/wall-0.jpg', selected: false },
				{ url: './wall/wall-1.jpg', selected: false },
				{ url: './wall/wall-2.jpg', selected: true },
				{ url: './wall/wall-3.jpg', selected: false },
				{ url: './wall/wall-4.jpg', selected: false },
				{ url: './wall/wall-5.jpg', selected: false }
			],
			preview: helper.getLocalstorage('wallpaper',"")
		};
	},
	computed: {
		wallpaper() {
			return this.$store.state.core.wallpaper;
		}
	},
	created() {
		this.selectwallpaper()
	},
	methods: {
		selectwallpaper(){
			this.images.forEach(item => {
				if(item.url === this.wallpaper){
					item.selected = true
				} else {
					item.selected = false
				}
			})
		},
		onSelect(url){
			this.preview = url
			this.$store.commit('core/SET_WALLPAPER',url);
			this.selectwallpaper()
			this.$store.commit('message/PUSH_MESSAGE', {'type':'notification','content':'切换成功1111111111111111111111111111111111111111111111111111111111111111','title':'更换壁纸'})
		}
	}
};
</script>
<style scoped>
.display-container {
	background-color: #f9f9f9;
	width: 100%;
	height: 100%;
	margin: 0;
}
.display-preview {
	width: 60%;
	margin: 0px auto;
	padding: 15px;
}
.display-preview img {
	width: 100%;
}
.display-list {
	width: 100%;
	white-space: nowrap;
	overflow-x: auto;
	-webkit-overflow-scrolling: touch;
	margin: 0px auto;
	padding: 10px 20px;
}
.display-list-item {
	margin: 5px;
	display: inline-block;
}
.display-list-item:hover {
	border: 1px solid #a0d911;
}
.item-selected {
	border: 1px solid red;
}
.display-list-item:active {
	border: 1px solid #a8071a;
}

.display-list-item img {
	height: 130px;
}
</style>
