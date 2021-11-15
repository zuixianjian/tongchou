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
      <p slot="title">编辑批单</p>

      <order-edit-compnt
        :orderPro="order"
        :originOrder="originOrder"
        ref="order"
        @onDataValid="onDataValid"
      ></order-edit-compnt>
      <div class="colContent">
        <div v-if="order.order.id === originOrder.order.id">
          <Button
            @click="handleSubmit"
            :loading="submitLoading"
            type="primary"
            style="margin-right: 5px"
            v-has="'order-edit-manager-commitForAudit'"
            >提交审核</Button
          >
        </div>
        <div
          v-if="
            order.order.orderStatus == 'ORDER_STATUS_WAIT_AUDIT' ||
            order.order.orderStatus == 'ORDER_STATUS_AUDIT_FAILURE'
          "
        >
          <Button
            @click="handleEdit"
            :loading="submitLoading"
            type="primary"
            style="margin-right: 5px"
            v-has="'order-edit-manager-edit-commit'"
            >修改并保存</Button
          >
        </div>
      </div>
    </Card>
  </div>
</template>

<script>
import orderEditCompnt from "./order-edit-compnt.vue";
import { editOrderEdit, editOrderCommitForAudit } from "@/api/index";
export default {
  name: "ordereditedit",
  components: { orderEditCompnt },
  data () {
    return {
      loading: true, // 表单加载状态
      submitLoading: false, // 表单提交状态
      backRoute: "",
      order: { order: { id: 0, orderStatus: "ORDER_STATUS_WAIT_AUDIT" } },
      orderStatus: 'ORDER_STATUS_ZANCUN',
      originOrder: { order: { id: 0 } },
      editFlag: 0
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
      if (this.order.order.originData != null && this.order.order.originData.length != 0) {
        //编辑
        this.originOrder = JSON.parse(this.order.order.originData);
      } else {
        //新增
        this.order.order.orderAgree = "";
        var s = JSON.stringify(this.order)
        this.originOrder = JSON.parse(s)
      }
    },

    handleSubmit () {
      this.editFlag = 1;
      this.$refs.order.getOrderDatas()
    },
    handleEdit () {
      this.editFlag = 2;
      this.$refs.order.getOrderDatas()
    },
    onDataValid (d) {
      if (this.editFlag == 1) {
        //提交审核
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
        editOrderCommitForAudit(data).then((res) => {
          this.submitLoading = false;
          if (res.success) {
            this.$Message.success("批单提交成功");
            this.closeCurrentPage();
          }
        });
      } else if (this.editFlag == 2) {
        //编辑提交
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
        editOrderEdit(data).then((res) => {
          this.submitLoading = false;
          if (res.success) {
            this.$Message.success("批单编辑提交成功");
            this.closeCurrentPage();
          }
        });
      }

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
      if (to.name == "ordereditedit") {
        this.init();

      }
    },
  },
  mounted () {
    this.init();
  },
};
</script>