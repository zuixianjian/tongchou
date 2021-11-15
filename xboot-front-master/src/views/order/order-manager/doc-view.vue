// table-expand.vue
<style scoped>
</style>
<template>
  <div>
    <div ref="file"></div>
  </div>
</template>
<script>
import { getFile } from "@/api/index";
let docx = require("docx-preview");
export default {
  name: "doc-view",
  props: {
    docUrl: String,
  },

  data() {
    return {};
  },
  watch: {
    docUrl: function () {
      docx.renderAsync("", this.$refs.file)
      if (this.docUrl != "") {
        getFile(this.docUrl).then((res) => {
          docx.renderAsync(res, this.$refs.file); // 渲染到页面预览
        });
      }
    },
  },
  created() {},
  methods: {
    
  },
  mounted() {
    if (this.docUrl != "") {
      getFile(this.docUrl).then((res) => {
        docx.renderAsync(res, this.$refs.file); // 渲染到页面预览
      });
    }
  },
};
</script>