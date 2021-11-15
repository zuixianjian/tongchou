<style lang="less">
@import "../../../styles/single-common.less";
.colContent {
  display: flex;
  justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
}
</style>
<template>
  <div>
    <Card>
      <div slot="title">
        <div class="edit-head">
          <a @click="close" class="back-title">
            <Icon type="ios-arrow-back" />返回
          </a>
          <div class="head-name">事故登记</div>
          <span></span>
          <a @click="close" class="window-close">
            <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
          </a>
        </div>
      </div>
      <base-info ref="baseinfo" />
      <div class="colContent">
        <Button @click="handleSubmit" :loading="submitLoading" type="primary"
          >提交并保存</Button
        >
        <Button
          @click="handleReset"
          style="margin-right: 10px; margin-left: 10px"
          >重置</Button
        >
        <Button type="dashed" @click="close">关闭</Button>
      </div>
    </Card>
  </div>
</template>

<script>
import baseInfo from "./baseInfo.vue"
import { createHelperOrderData } from "@/api/index"
export default {
  name: "help-order-add",
  components: {
    baseInfo
  },
  data () {
    return {
      submitLoading: false, // 表单提交状态
    };
  },
  methods: {
    accidentTimeChange (refName) {
      this.$refs[refName].onSelectionModeChange("time");
      this.form.accidentTime = this.$refs[refName].visualValue;
      this.$forceUpdate();
    },
    init () { },
    handleReset () {
      this.$refs.form.resetFields();
    },
    handleSubmit () {
      let baseInfo = this.$refs.baseinfo.form
      //   if(baseInfo.carNoHead==null||baseInfo.carNoHead.length==0){
      //      this.$Message.warning('请选择车辆省份');
      //      return;
      //   }
      if (baseInfo.carNo == null || baseInfo.carNo.length == 0) {
        this.$Message.warning('请输入车辆号码');
        return;
      }
      if (baseInfo.carframeNo == null || baseInfo.carframeNo.length == 0) {
        this.$Message.warning('请输入车架号');
        return;
      }
      if (baseInfo.orderId == null || baseInfo.orderId.length == 0) {
        this.$Message.warning('请输入统筹单号');
        return;
      }
      if (baseInfo.clientName == null || baseInfo.clientName.length == 0) {
        this.$Message.warning('请输入被统筹人');
        return;
      }
      if (baseInfo.carTypeName == null || baseInfo.carTypeName.length == 0) {
        this.$Message.warning('请输入车型名称');
        return;
      }
      if (baseInfo.startDate == null || baseInfo.startDate.length == 0) {
        this.$Message.warning('请输入统筹期限');
        return;
      }
      if (baseInfo.endDate == null || baseInfo.endDate.length == 0) {
        this.$Message.warning('请输入统筹期限');
        return;
      }
      if (baseInfo.reportName == null || baseInfo.reportName.length == 0) {
        this.$Message.warning('请输入报案人');
        return;
      }
      if (baseInfo.reportTel == null || baseInfo.reportTel.length == 0) {
        this.$Message.warning('请输入报案电话');
        return;
      }
      if (baseInfo.accidentType == null || baseInfo.accidentType.length == 0) {
        this.$Message.warning('请输入事故类型');
        return;
      }
      if (baseInfo.accidentTime == null || baseInfo.accidentTime.length == 0) {
        this.$Message.warning('请选择事故时间');
        return;
      }
      if (baseInfo.accidentPlace == null || baseInfo.accidentPlace.length == 0) {
        this.$Message.warning('请输入事故地点');
        return;
      }
      if (baseInfo.accidentDesc == null || baseInfo.accidentDesc.length == 0) {
        this.$Message.warning('请输入事故概况');
        return;
      }
      createHelperOrderData(baseInfo).then(res => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("事故登记成功");
          this.submited();
        }
      });


    },
    close () {
      this.$emit("close", true);
    },
    submited () {
      this.$emit("submited", true);
    },
  },
  mounted () {
    this.init();
  },
};
</script>