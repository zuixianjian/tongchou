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
        <a-divider :dashed="true" style="color: #0f0">统筹信息</a-divider>
        <Row type="flex" justify="center">
          <Col span="6">
            <FormItem label="车牌号码" prop="carNo">
              <Row>
                <!-- <Col span="4">
                  <Select
                    v-model="form.carNoHead"
                    placeholder="请选择"
                    :disabled="op_type == 'check'"
                  >
                    <Option
                      v-for="(item, index) in this.$store.state.dict
                        .regionGroupss"
                      :value="item.value"
                      :key="index"
                      >{{ item.value }}</Option
                    >
                  </Select>
                </Col> -->
                <Col span="24">
                  <Input
                    v-model="form.carNo"
                    style="margin-right: 10px"
                    placeholder="请输入车牌号"
                    :readonly="op_type == 'check'"
                  />
                </Col>
              </Row>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="车架号" prop="carframeNo">
              <div style="display: flex">
                <Input
                  v-model="form.carframeNo"
                  style="margin-right: 10px"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="统筹单号" prop="orderId">
              <div style="display: flex">
                <Input
                  v-model="form.orderId"
                  style="margin-right: 10px"
                  :readonly="op_type == 'check'"
                />
                <Button
                  icon="md-list"
                  @click="queryOrders"
                  :disabled="op_type == 'check'"
                  >查询</Button
                >
              </div>
            </FormItem>
          </Col>
        </Row>

        <Row type="flex" justify="center">
          <Col span="6">
            <FormItem label="被统筹人" prop="clientName" ref="clientName">
              <div style="display: flex">
                <Input
                  v-model="form.clientName"
                  readonly
                  style="margin-right: 10px"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="车型名称" prop="carTypeName" ref="carTypeName">
              <div style="display: flex">
                <Input
                  v-model="form.carTypeName"
                  readonly
                  style="margin-right: 10px"
                />
                >
              </div>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="统筹期限" prop="startDate">
              <Row>
                <Col span="11">
                  <Input
                    v-model="form.startDate"
                    readonly
                    style="margin-right: 10px"
                  />
                </Col>
                <Col span="2">
                  <span style="margin-left: 5px"> 至</span>
                </Col>
                <Col span="11">
                  <Input
                    v-model="form.endDate"
                    readonly
                    style="margin-right: 10px"
                  />
                </Col>
              </Row>
            </FormItem>
          </Col>
        </Row>
        <a-divider :dashed="true" style="color: #0f0">报案信息</a-divider>

        <Row type="flex" justify="center">
          <Col span="4">
            <FormItem label="报案人" prop="reportName">
              <div style="display: flex">
                <Input
                  v-model="form.reportName"
                  style="margin-right: 10px"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="报案电话" prop="reportTel">
              <div style="display: flex">
                <Input
                  v-model="form.reportTel"
                  style="margin-right: 10px"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="事故类型" prop="accidentType">
              <div style="display: flex">
                <Input
                  v-model="form.accidentType"
                  style="margin-right: 10px"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="事故时间" prop="accidentTime">
              <div style="display: flex">
                <Date-picker
                  type="datetime"
                  placeholder="选择日期和时间"
                  ref="accidentTime"
                  :value="form.accidentTime"
                  :editable="false"
                  @on-change="accidentTimeChange('accidentTime')"
                  :readonly="op_type == 'check'"
                ></Date-picker>
              </div>
            </FormItem>
          </Col>
        </Row>

        <Row type="flex" justify="center">
          <Col span="6">
            <FormItem label="三者车牌" prop="thirdCarNo">
              <div style="display: flex">
                <Input
                  v-model="form.thirdCarNo"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="三者车型" prop="thirdCarType">
              <div style="display: flex">
                <Input
                  v-model="form.thirdCarType"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem label="三者电话" prop="thirdPhone">
              <div style="display: flex">
                <Input
                  v-model="form.thirdPhone"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span="18">
            <FormItem label="事故地点" prop="accidentPlace">
              <div style="display: flex">
                <Input
                  v-model="form.accidentPlace"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span="18">
            <FormItem label="事故概况" prop="accidentDesc">
              <div style="display: flex">
                <Input
                  v-model="form.accidentDesc"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span="18">
            <FormItem label="事故备注说明" prop="remark">
              <div style="display: flex">
                <Input
                  v-model="form.remark"
                  type="textarea"
                  :rows="6"
                  :readonly="op_type == 'check'"
                />
              </div>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Card>
  </div>
</template>

<script>
import { getValidOrderListData } from "@/api/index";
export default {
  name: "base-info",
  props: {
    baseInfo: {
      type: Object,
    },
    op_type: {
      type: String,
    }
  },
  watch: {
    baseInfo: function () {
      if (this.baseInfo == null) {
        this.form = {}
      } else {
        this.form = this.baseInfo
      }

    }
  },
  data () {
    return {
      form: {
        carNoHead: ''
      },
      searchForm: {
        // 搜索框对应data对象
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc" // 默认排序方式
      },
    };
  },
  methods: {
    accidentTimeChange (refName) {
      this.$refs[refName].onSelectionModeChange("time");
      this.form.accidentTime = this.$refs[refName].visualValue;
      this.$forceUpdate();
    },
    queryOrders () {
      var _this = this;
      if ((this.form.orderId == null || this.form.orderId.length == 0) && (this.form.carNo == null || this.form.carNo.length == 0) &&
        (this.form.carframeNo == null || this.form.carframeNo.length == 0)) {
        this.$Message.warning('请输入查询条件');
        return
      }
      this.searchForm.id = this.form.orderId;
      this.searchForm.carNo = this.form.carNo;
      this.searchForm.carframeNo = this.form.carframeNo;
      getValidOrderListData(this.searchForm).then((res) => {
        if (res.success) {
          if (res.result != null && res.result.length > 0) {
            //查询到数据
            let order = res.result[0];
            _this.form.carNo = order.carNo;
            _this.form.carframeNo = order.carframeNo;
            _this.form.orderId = order.id;
            _this.form.clientName = order.clientName;
            _this.form.carTypeName = order.carTypeName;
            _this.form.startDate = order.startDate;
            _this.form.endDate = order.endDate;
            _this.$forceUpdate();
          }

        }
      })
    },
    init () { },
  },
  mounted () {
    this.init();
  },
};
</script>