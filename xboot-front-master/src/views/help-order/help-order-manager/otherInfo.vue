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
      <Form ref="form" :model="form" :label-width="90">
        <Row type="flex" justify="center">
          <Col span="4">
            <FormItem label="总损失金额" prop="totalLoss">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.totalLoss"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="责任占比(%)" prop="dutyPercent">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.dutyPercent"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="交强险补偿" prop="compulsoryCompensateMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.compulsoryCompensateMoney"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="代调查费" prop="investigateMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.investigateMoney"
              ></InputNumber>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span="4">
            <FormItem label="诉讼费" prop="lawsuitMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.lawsuitMoney"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="仲裁费" prop="arbitrateMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.arbitrateMoney"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="公估费" prop="publicMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.publicMoney"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="鉴定费" prop="appraisalMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.appraisalMoney"
              ></InputNumber>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span="4">
            <FormItem label="其他费用" prop="oteherMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.oteherMoney"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="垫付金额" prop="dianfuMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.dianfuMoney"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="统筹补偿金额" prop="compensateMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.compensateMoney"
              ></InputNumber>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="待补偿金额" prop="waitForCompensateMoney">
              <InputNumber
                :max="10000000"
                :min="0"
                v-model="form.waitForCompensateMoney"
              ></InputNumber>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span="6">
            <FormItem label="补偿对象" prop="compensateTarget">
              <div style="display: flex">
                <Input
                  v-model="form.compensateTarget"
                  style="margin-right: 10px"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="开户行" prop="openBankName">
              <div style="display: flex">
                <Input v-model="form.openBankName" style="margin-right: 10px" />
              </div>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="银行账号" prop="bankNo">
              <div style="display: flex">
                <Input v-model="form.bankNo" style="margin-right: 10px" />
              </div>
            </FormItem>
          </Col>
        </Row>
        <div
          class="colContent"
          v-if="
            order.status == 'HELPORDER_STATUS_DIAOCHA' ||
            order.status == 'HELPORDER_STATUS_CHECK' ||
            order.status == 'HELPORDER_STATUS_CHECK_FAIL'
          "
        >
          <Button
            @click="submit"
            :loading="submitLoading"
            type="primary"
            icon="ios-create-outline"
            style="margin-right: 5px"
            v-has="'helpOrderOtherMsgCommit'"
            >修改并保存</Button
          >
        </div>
      </Form>
    </Card>
  </div>
</template>

<script>
import { getByHelpOrderId, editHelperOrderCompensate } from "@/api/index";
export default {
  name: "other-info",
  components: {},
  watch: {
    horder: {
      handler (newValue, oldValue) {
        if (this.horder != null) {
          this.order = this.horder;
          this.getData();
        }
      },
      deep: true,
    },
  },
  props: ["horder"],
  data () {
    return {
      order: {},
      form: {},
      submitLoading: false,
    };
  },
  methods: {
    init () {
      this.getData();
    },
    getData () {
      if (this.order.id == null) {
        return;
      }
      getByHelpOrderId({ helpOrderId: this.order.id }).then((resp) => {
        if (resp.success) {
          if (resp.result == null) {
            this.form = {};
          } else {
            this.form = resp.result;
          }
        }
      });
    },
    submit () {
      this.submitLoading = true;
      this.form.helperOrderId = this.order.id;
      editHelperOrderCompensate(this.form).then((resp) => {
        if (resp.success) {
          this.$Message.success("保存成功");
          this.submitLoading = false;
        }
      });
    },
  },
  mounted () {
    this.init();
  },
};
</script>

<style lang="less">
@import "../../../styles/table-common.less";
</style>