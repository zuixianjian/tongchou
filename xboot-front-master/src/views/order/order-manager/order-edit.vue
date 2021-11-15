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
      <p slot="title">编辑统筹单</p>

      <order-compnt
        :orderPro="order"
        ref="order"
        @onDataValid="onDataValid"
      ></order-compnt>
      <div class="colContent">
        <div
          v-show="
            orderStatus == 'ORDER_STATUS_AUDIT_FAILURE' ||
            orderStatus == 'ORDER_STATUS_ZANCUN'
          "
        >
          <Button
            @click="handleSubmit"
            :loading="submitLoading"
            type="primary"
            style="margin-right: 5px"
            v-has="'orderCommitAndSave'"
            >修改并保存</Button
          >
        </div>

        <div
          v-show="
            orderStatus == 'ORDER_STATUS_AUDIT_FAILURE' ||
            orderStatus == 'ORDER_STATUS_ZANCUN'
          "
        >
          <Button
            @click="handleSubmitForAudit"
            :loading="submitLoading"
            type="primary"
            style="margin-right: 5px"
            v-has="'orderCommitForAudit'"
            >提交审核</Button
          >
        </div>
        <div v-show="orderStatus == 'ORDER_STATUS_WAIT_AUDIT'">
          <Button
            @click="handleSubmitAuditEdit"
            :loading="submitLoading"
            type="primary"
            style="margin-right: 5px"
            v-has="'orderAuditEdit'"
            >审核修改</Button
          >
        </div>
      </div>
    </Card>
  </div>
</template>

<script>
import orderCompnt from "./order-compnt.vue";
import { orderEdit, orderCommitForAudit, orderAuditEdit } from "@/api/index";
export default {
  name: "orderedit",
  components: { orderCompnt },
  data () {
    return {
      loading: true, // 表单加载状态
      submitLoading: false, // 表单提交状态
      backRoute: "",
      order: {},
      orderStatus: 'ORDER_STATUS_ZANCUN',
      actionFlag: 1
    };
  },
  methods: {
    init () {
      this.order = this.$route.query.order;
      if (this.order.order.feeFloatRate != null) {
        this.order.order.feeFloatRate = parseFloat(
          this.order.order.feeFloatRate
        );
      }
      if (this.order.order.feeFloatRate != null) {
        this.order.order.feeFloatRate = parseFloat(this.order.order.feeFloatRate);
      }
      if (this.order.order.isCheckCar == null) {
        this.order.order.isCheckCar = false;
      }
      if (this.order.order.isCheckCar == null) {
        this.order.order.isCheckCar = false;
      }
      if (this.order.orderFiles != null) {
        let pics = [];
        this.order.orderFiles.forEach((item) => {
          pics.push(item.fileUrl);
        });
        this.order.pics = pics;
      }
      this.orderStatus = this.order.order.orderStatus
      this.backRoute = this.$route.query.backRoute;
    },

    handleSubmit () {
      this.actionFlag = 1

      this.$refs.order.getOrderDatas()

    },

    handleSubmitAuditEdit () {
      this.actionFlag = 2
      this.$refs.order.getOrderDatas()

    },
    onDataValid (d) {
      if (d == null) {
        return
      }

      let str = JSON.stringify(d);
      let data = JSON.parse(str);
      let checkedDetail = data.orderDetails.filter((item) => {
        return item.isChecked;
      });
      data.orderDetails = checkedDetail;
      let orderFiles = [];
      if (data.pics != null) {
        data.pics.forEach((item) => {
          orderFiles.push({ fileUrl: item });
        });
      }
      data.orderFiles = orderFiles;

      console.log(data);
      if (this.actionFlag == 1) {
        orderEdit(data).then((res) => {
          this.submitLoading = false;
          if (res.success) {
            this.$Message.success("编辑统筹单成功");
            this.closeCurrentPage();
          }
        });
      } else {
        //审核员修改
        orderAuditEdit(data).then((res) => {
          this.submitLoading = false;
          if (res.success) {
            this.$Message.success("编辑统筹单成功");
            this.closeCurrentPage();
          }
        });
      }

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
      this.$store.commit("removeTag", "orderedit");
      localStorage.pageOpenedList = JSON.stringify(
        this.$store.state.app.pageOpenedList
      );
      this.$router.push({
        name: this.backRoute,
      });
    },
  },
  watch: {
    // 监听路由变化通过id获取数据
    $route (to, from) {
      if (to.name == "orderedit") {
        this.init();
      }
    },
  },
  mounted () {
    this.init();
  },
};
</script>