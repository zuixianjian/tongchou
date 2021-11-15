<style lang="less">
@import "../../../styles/single-common.less";
.colContent {
  display: flex;
  justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
  margin-top: 20px;
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
          <div class="head-name">事故调查</div>
          <span></span>
          <a @click="close" class="window-close">
            <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
          </a>
        </div>
      </div>
      <Row>
        <Col :span="24">
          事故基本信息
          <a class="drop-down" @click="dropDown">
            {{ dropDownContent }}
            <Icon :type="dropDownIcon"></Icon>
          </a>
          <span v-if="drop">
            <base-info ref="baseinfo" :baseInfo="form" /> </span
        ></Col>
      </Row>
      <Row>
        <Col :span="24">
          损失信息登记

          <a class="drop-down" @click="dropDownDamage">
            {{ dropDownContentDamage }}
            <Icon :type="dropDownIconDamege"></Icon>
          </a>
          <span v-if="dropDamage">
            <damage-list ref="damageList" :helpOrder="form" />
          </span>
        </Col>
      </Row>
      <Row>
        <Col :span="24">
          其他信息
          <a class="drop-down" @click="dropDownOther">
            {{ dropDownContentOther }}
            <Icon :type="dropDownIconOther"></Icon>
          </a>
          <span v-if="dropOther">
            <other-info ref="other-info" :horder="form" /> </span
        ></Col>
      </Row>
      <Row>
        <Col :span="24">
          附件信息
          <a class="drop-down" @click="dropDownPics">
            {{ dropDownContentPics }}
            <Icon :type="dropDownIconPics"></Icon>
          </a>
          <span v-if="dropPics">
            <help-order-pics ref="other-info" :horder="form" /> </span
        ></Col>
      </Row>
      <div class="colContent">
        <!-- <Button @click="handleSubmit" :loading="submitLoading" type="primary"
          >提交并保存</Button
        > -->
        <!-- <Button @click="handleReset">重置</But ton> -->
        <!-- <Button type="dashed" @click="diaocha">调查派工</Button> -->
        <div
          v-if="
            form.status == 'HELPORDER_STATUS_DIAOCHA' ||
            form.status == 'HELPORDER_STATUS_CHECK_FAIL'
          "
        >
          <Button
            type="dashed"
            @click="commitForCheck"
            v-has="'helpOrderCommitForCheck'"
            >提交审核</Button
          >
        </div>

        <Button type="dashed" @click="close">关闭</Button>
      </div>
      <Spin size="large" fix v-if="loading"></Spin>
    </Card>
    <Modal
      title="选择调查员"
      v-model="diaochaVisible"
      :mask-closable="false"
      @on-ok="submitDiaocha"
    >
      <Table
        highlight-row
        :columns="diaochaCollom"
        :data="diaochaList"
        @on-current-change="diaochaSelect"
      ></Table>
    </Modal>
  </div>
</template>

<script>
import baseInfo from "./baseInfo.vue";
import {
  editHelperOrderData,
  getDiaochaUserListData,
  startDiaocha,
  damageList,
  helperOrderCommitForCheck
} from "@/api/index";
import DamageList from "./damageList.vue";
import otherInfo from "./otherInfo.vue";
import helpOrderPics from "./helpOrderPics.vue"
export default {
  name: "help-order-diaocha",
  components: {
    baseInfo,
    DamageList,
    otherInfo,
    helpOrderPics
  },
  props: {
    helpOrder: Object,
  },
  data () {
    return {
      loading: false, // 表单加载状态
      submitLoading: false, // 表单提交状态
      form: {},
      diaochaVisible: false,
      diaochaCollom: [
        {
          type: "index",
          width: 60,
          align: "center",
        },
        {
          title: "账号",
          key: "nickname",
        },
        {
          title: "昵称",
          key: "username",
        },
        {
          title: "电话",
          key: "mobile",
        },
      ],
      diaochaList: [],
      selectDiaochaId: "",
      dropDownContent: "收起",
      dropDownIcon: "ios-arrow-up",
      drop: true,
      dropDamage: true,
      dropDownContentDamage: "收起",
      dropDownIconDamege: "ios-arrow-up",
      dropOther: true,
      dropDownContentOther: "收起",
      dropDownIconOther: "ios-arrow-up",
      dropPics: true,
      dropDownContentPics: "收起",
      dropDownIconPics: "ios-arrow-up",
    };
  },
  methods: {
    dropDown () {
      if (this.drop) {
        this.dropDownContent = "展开";
        this.dropDownIcon = "ios-arrow-down";
      } else {
        this.dropDownContent = "收起";
        this.dropDownIcon = "ios-arrow-up";
      }
      this.drop = !this.drop;
    },
    dropDownDamage () {
      if (this.dropDamage) {
        this.dropDownContentDamage = "展开";
        this.dropDownIconDamege = "ios-arrow-down";
      } else {
        this.dropDownContentDamage = "收起";
        this.dropDownIconDamege = "ios-arrow-up";
      }
      this.dropDamage = !this.dropDamage;
    },
    dropDownOther () {
      if (this.dropOther) {
        this.dropDownContentOther = "展开";
        this.dropDownIconOther = "ios-arrow-down";
      } else {
        this.dropDownContentOther = "收起";
        this.dropDownIconOther = "ios-arrow-up";
      }
      this.dropOther = !this.dropOther;
    },
    dropDownPics () {
      if (this.dropPics) {
        this.dropDownContentPics = "展开";
        this.dropDownIconPics = "ios-arrow-down";
      } else {
        this.dropDownContentPics = "收起";
        this.dropDownIconPics = "ios-arrow-up";
      }
      this.dropPics = !this.dropPics;
    },
    init () {
      this.form = this.helpOrder;
    },
    handleReset () {
      // this.$refs.form.resetFields();
    },
    handleSubmit () {
      let baseInfo = this.$refs.baseinfo.form;
      //   if (baseInfo.carNoHead == null || baseInfo.carNoHead.length == 0) {
      //     this.$Message.warning("请选择车辆省份");
      //     return;
      //   }
      if (baseInfo.carNo == null || baseInfo.carNo.length == 0) {
        this.$Message.warning("请输入车辆号码");
        return;
      }
      if (baseInfo.carframeNo == null || baseInfo.carframeNo.length == 0) {
        this.$Message.warning("请输入车架号");
        return;
      }
      if (baseInfo.orderId == null || baseInfo.orderId.length == 0) {
        this.$Message.warning("请输入统筹单号");
        return;
      }
      if (baseInfo.clientName == null || baseInfo.clientName.length == 0) {
        this.$Message.warning("请输入被统筹人");
        return;
      }
      if (baseInfo.carTypeName == null || baseInfo.carTypeName.length == 0) {
        this.$Message.warning("请输入车型名称");
        return;
      }
      if (baseInfo.startDate == null || baseInfo.startDate.length == 0) {
        this.$Message.warning("请输入统筹期限");
        return;
      }
      if (baseInfo.endDate == null || baseInfo.endDate.length == 0) {
        this.$Message.warning("请输入统筹期限");
        return;
      }
      if (baseInfo.reportName == null || baseInfo.reportName.length == 0) {
        this.$Message.warning("请输入报案人");
        return;
      }
      if (baseInfo.reportTel == null || baseInfo.reportTel.length == 0) {
        this.$Message.warning("请输入报案电话");
        return;
      }
      if (baseInfo.accidentType == null || baseInfo.accidentType.length == 0) {
        this.$Message.warning("请输入事故类型");
        return;
      }
      if (baseInfo.accidentTime == null || baseInfo.accidentTime.length == 0) {
        this.$Message.warning("请选择事故时间");
        return;
      }
      if (
        baseInfo.accidentPlace == null ||
        baseInfo.accidentPlace.length == 0
      ) {
        this.$Message.warning("请输入事故地点");
        return;
      }
      if (baseInfo.accidentDesc == null || baseInfo.accidentDesc.length == 0) {
        this.$Message.warning("请输入事故概况");
        return;
      }
      editHelperOrderData(baseInfo).then((res) => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("事故信息修改成功");
          this.submited();
        }
      });
    },
    diaocha () {
      this.selectDiaochaId = "";
      getDiaochaUserListData().then((res) => {
        if (res.success) {
          this.diaochaList = res.result;
          this.diaochaVisible = true;
        }
      });
    },
    diaochaSelect (curentRow, oldCurrentRow) {
      this.selectDiaochaId = curentRow.id;
    },
    submitDiaocha () {
      if (this.selectDiaochaId.length == 0) {
        this.$Message.warning("请选择调查员");
        return;
      }
      //提交调查
      startDiaocha({
        id: this.form.id,
        diaochaUserId: this.selectDiaochaId,
      }).then((res) => {
        if (res.success) {
          this.$Message.success("派工成功");
          this.diaochaVisible = false;
        }
      });
    },
    close () {
      this.$emit("close", true);
    },
    submited () {
      this.$emit("submited", true);
    },
    commitForCheck () {
      //提交审核
      helperOrderCommitForCheck({ helpOrderId: this.form.id }).then(resp => {
        if (resp.success) {
          this.$Message.success("提交审核成功");
        }
      })
    }
  },
  mounted () {
    this.init();
  },
};
</script>