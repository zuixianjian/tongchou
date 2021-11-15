<style lang="less">
.colContent {
  display: flex;
  justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
}
</style>
<template>
  <div>
    <Card>
      <p slot="title">添加统筹单</p>

      <order-compnt
        :orderPro="order"
        ref="order"
        @onDataValid="onDataValid"
      ></order-compnt>

      <div class="colContent">
        <Button
          @click="handleSubmit"
          :loading="submitLoading"
          type="primary"
          style="margin-right: 5px"
          v-has="'orderZancun'"
          >暂存保存</Button
        >
        <!-- <Button
          @click="handleSubmitForAudit"
          :loading="submitLoading"
          type="primary"
          style="margin-right: 5px"
          v-has="'orderCommitForAudit'"
          >提交审核</Button
        > -->
      </div>
    </Card>
  </div>
</template>

<script>
import { orderCreate, orderCommitForAudit } from "@/api/index";
import orderCompnt from "./order-compnt.vue";
export default {
  components: { orderCompnt },
  name: "orderadd",
  data () {
    return {
      loading: true, // 表单加载状态
      submitLoading: false, // 表单提交状态

      backRoute: "",
      order: {},
    };
  },
  methods: {
    init () {
      this.backRoute = this.$route.query.backRoute;
      this.order = this.$route.query.order;
    },
    handleSubmit () {
      this.$refs.order.getOrderDatas()

    },
    onDataValid (d) {
      if (decodeURI == null) {
        return
      }
      let str = JSON.stringify(d);
      let data = JSON.parse(str);
      let checkedDetail = data.orderDetails.filter((item) => {
        return item.isChecked;
      });
      let orderFiles = []
      if (data.pics != null) {
        data.pics.forEach(item => {
          orderFiles.push({ fileUrl: item })
        })
      }
      data.orderFiles = orderFiles;
      data.orderDetails = checkedDetail;
      console.log(data);
      orderCreate(data).then((res) => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("统筹单创建成功");
          // this.closeCurrentPage();
        }
      });
    },
    handleSubmitForAudit () {
      let str = JSON.stringify(this.$refs.order.form);
      let data = JSON.parse(str);
      if (this.$refs.order.form.order.id == null) {
        this.$Modal.error({
          title: "提交审核错误",
          content: "请暂存后再提交审核",
        });
      }

      orderCommitForAudit({ orderId: data.order.id }).then((res) => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("提交审核成功");
          // this.closeCurrentPage();
        }
      });
    },
    // 关闭当前页面
    closeCurrentPage () {
      this.$store.commit("removeTag", "orderadd");
      localStorage.pageOpenedList = JSON.stringify(
        this.$store.state.app.pageOpenedList
      );
      this.$router.push({
        name: this.backRoute,
      });
    },
  },
  watch: {
    $route (to, from) {
      if (to.name == "orderadd") {
        this.order = null;
      }
    },
  },
  mounted () {
    this.init();
  },
};
</script>