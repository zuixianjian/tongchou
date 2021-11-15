<template>
	<div class="gutter-example"><a-table :dataSource="dataSource" :columns="columns" bordered /></div>
</template>
<script>
export default {
	setup() {
		return {
			dataSource: [
				{
					key: '1',
					name: '胡彦斌',
					age: 32,
					address: '西湖区湖底公园1号'
				},
				{
					key: '2',
					name: '胡彦祖',
					age: 42,
					address: '西湖区湖底公园1号'
				}
			],

			columns: [
				{
					title: '姓名',
					dataIndex: 'name',
					key: 'name'
				},
				{
					title: '年龄',
					dataIndex: 'age',
					key: 'age'
				},
				{
					title: '住址',
					dataIndex: 'address',
					key: 'address'
				}
			]
		};
	}
};
</script>

<style>
.gutter-example {
	padding: 10px 10px;
}
.gutter-example :deep(.ant-row > div) {
	background: transparent;
	border: 0;
}
.gutter-box {
	background: #00a0e9;
	padding: 5px 0;
}
</style>
