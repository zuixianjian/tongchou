<template>
  <div style="height: 100%">
    <a @click="showModal">模态对话框</a>|
    <a @click="display = true">打开抽屉</a>
    <Dialog
      v-model:display="visible"
      icon="call"
      title="模态对话框"
      :inside="true"
      width="500"
    ></Dialog>
    <drawer
      title="我是一个抽屉组件"
      v-model:display="display"
      :inside="true"
      :width="drawerWidth"
      :mask="true"
    ></drawer>
  </div>
</template>

<script>
import drawer from '@/views/desktop/drawer.vue';
import Dialog from '@/views/desktop/DialogModal.vue';
export default {
  components: {
    drawer,
    Dialog
  },
  data () {
    return {
      drawerWidth: '500px',
      display: false,
      loading: false,
      visible: false,
      formInline: {
        user: '',
        password: ''
      },
      text: `A dog is a type of domesticated animal.Known for its loyalty and faithfulness,it can be found as a welcome guest in many households across the world.`,
      activeKey: ['1']
    };
  },
  methods: {
    showModal () {
      this.visible = true;
    },
    handleOk () {
      this.loading = true;
      setTimeout(() => {
        this.visible = false;
        this.loading = false;
      }, 3000);
    },
    handleCancel () {
      this.visible = false;
    },
    onClose () {
      alert('close');
    }
  }
};
</script>

<style></style>

