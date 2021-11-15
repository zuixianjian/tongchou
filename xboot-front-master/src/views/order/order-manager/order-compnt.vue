<style lang="less">
.feeTypeTitle {
  text-align: center;
}
.colContent {
  display: flex;
  justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
}
</style>
<template>
  <div>
    <Form ref="form" :model="form.order" :rules="formValidate">
      <a-divider :dashed="true" style="color: #0f0">一般信息</a-divider>
      <Row type="flex" justify="center">
        <Col span="3">
          <Form-item label="期限类型" :label-width="80" prop="timeLimitType">
            <Select
              v-model="form.order.timeLimitType"
              placeholder="请选择"
              clearable
              @on-change="timeLimitTypeChange"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.timeLimitTypes"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Form-item>
        </Col>
        <Col span="3">
          <Form-item label="自然月数" :label-width="80" prop="month">
            <Select
              v-model="form.order.month"
              placeholder="请选择"
              ref="month"
              clearable
              @on-change="monthChange"
              :disabled="monthDisable"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.months"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Form-item>
        </Col>
        <Col span="3">
          <Form-item label="短期折扣" prop="shortDiscount">
            <InputNumber
              :max="1"
              :min="0"
              v-model="form.order.shortDiscount"
              readonly
            ></InputNumber>
          </Form-item>
        </Col>
        <Col span="7">
          <FormItem label="统筹期限" prop="startDate">
            <Row>
              <Col span="11">
                <Date-picker
                  type="datetime"
                  placeholder="选择日期和时间"
                  ref="startDate"
                  :value="form.order.startDate"
                  :editable="false"
                  clearable
                  readonly
                  @on-change="startTimeChange('startDate')"
                ></Date-picker>
              </Col>
              <Col span="2">
                <span style="margin-left: 5px"> 至</span>
              </Col>
              <Col span="11">
                <Date-picker
                  type="datetime"
                  placeholder="选择日期和时间"
                  ref="endDate"
                  :value="form.order.endDate"
                  :editable="false"
                  clearable
                  readonly
                  @on-change="endDateChange('endDate')"
                ></Date-picker>
              </Col>
            </Row>
          </FormItem>
        </Col>
      </Row>
      <a-divider :dashed="true" style="color: #0f0">客户信息</a-divider>
      <Row type="flex" justify="center">
        <Col span="4">
          <FormItem label="被统筹人" prop="clientName" ref="clientName">
            <div style="display: flex">
              <Input
                v-model="form.order.clientName"
                readonly
                style="margin-right: 10px"
              />
              <Button icon="md-list" @click="openSelectClientName">选择</Button>
            </div>
          </FormItem>
        </Col>
        <Col span="6">
          <FormItem label="办理人" prop="handleName">
            <div style="display: flex">
              <Input
                v-model="form.order.handleName"
                readonly
                style="margin-right: 10px; width: 100px"
              />
              <Button
                icon="md-list"
                style="margin-right: 10px"
                @click="openSelectHandleName"
                >选择</Button
              >
              <Checkbox @on-change="handleNameSameClientName"
                >同被统筹人</Checkbox
              >
            </div>
          </FormItem>
        </Col>
        <Col span="8">
          <FormItem label="驾驶证车主" prop="runCardName">
            <div style="display: flex">
              <Input
                v-model="form.order.runCardName"
                readonly
                style="margin-right: 10px; width: 100px"
              />
              <Button
                icon="md-list"
                @click="openSelectRunCardName"
                style="margin-right: 10px"
                >选择</Button
              >
              <Checkbox
                style="margin-right: 10px"
                @on-change="runCardNameSameClientName"
                >同被统筹人</Checkbox
              >
              <Checkbox
                style="margin-right: 10px"
                @on-change="runCardNameSameHandleName"
                >同办理人</Checkbox
              >
            </div>
          </FormItem>
        </Col>
      </Row>
      <a-divider :dashed="true" style="color: #0f0">车型信息</a-divider>
      <Row type="flex" justify="center">
        <Col span="12">
          <FormItem label="车型" prop="carTypeName" ref="carTypeName">
            <div style="display: flex">
              <Input
                v-model="form.order.carTypeName"
                readonly
                style="margin-right: 10px"
              />
              <Button icon="md-list" @click="openSelectCarTypeName"
                >选择</Button
              >
            </div>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center">
        <Col span="4">
          <FormItem label="车型种类" prop="carTypeA">
            <Select
              v-model="form.order.carTypeA"
              placeholder="请选择"
              @on-change="getCartypeB(form.order.carTypeA)"
              style="width: 120px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.carTypeAs"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="细分类目" prop="carTypeB">
            <Select
              v-model="form.order.carTypeB"
              placeholder="请选择"
              ref="carTypeB"
              key="carTypeB"
              style="width: 120px"
            >
              <Option
                v-for="(item, index) in carTypeBs"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="行驶证类型" prop="runCardCarType">
            <Select
              v-model="form.order.runCardCarType"
              placeholder="请选择"
              style="width: 120px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.runCardCarTypes"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center">
        <Col span="4">
          <FormItem label="车系分类" prop="carSieralType">
            <Select
              v-model="form.order.carSieralType"
              placeholder="请选择"
              style="width: 120px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.carSieralTypess"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="所属性质" prop="belongType">
            <Select
              v-model="form.order.belongType"
              placeholder="请选择"
              style="width: 120px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.belongTypess"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="使用性质" prop="useType">
            <Select
              v-model="form.order.useType"
              placeholder="请选择"
              style="width: 120px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.useTypess"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </FormItem>
        </Col>
      </Row>

      <Row type="flex" justify="center">
        <Col span="4">
          <FormItem label="核定载重" prop="loadCapacity">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="核定载重"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.loadCapacity"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="整备质量" prop="weight">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="整备质量"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.weight"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="功率" prop="kws">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="功率"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.kws"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center">
        <Col span="3">
          <FormItem label="排量(升)" prop="displacement">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="排量"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.displacement"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="3">
          <FormItem label="座位数" prop="siteCount">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="座位数"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.siteCount"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="3">
          <FormItem label="新车购置价" prop="buyPrice">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="新车购置价(元)"
            >
              <InputNumber
                @on-change="calculateMonthOldPrice"
                :max="100000000"
                :min="0"
                v-model="form.order.buyPrice"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="3">
          <FormItem label="月折旧率" prop="monthOldRate">
            <Tooltip
              trigger="hover"
              placement="right-start"
              max-width="300"
              content="月折旧率"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.monthOldRate"
                @on-change="calculateMonthOldPrice"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
      </Row>
      <a-divider :dashed="true" style="color: #0f0">车辆信息</a-divider>
      <Row type="flex" justify="center">
        <Col span="6">
          <FormItem label="车牌号码" prop="carNo">
            <Row>
              <!-- <Col span="4">
                <Select v-model="form.order.carNoHead" placeholder="请选择">
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
                  v-model="form.order.carNo"
                  style="margin-right: 10px"
                  placeholder="请输入车牌号"
                />
              </Col>
            </Row>
          </FormItem>
        </Col>
        <Col span="6">
          <FormItem label="初登日期" prop="firstLogDate">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="初登日期"
            >
              <Date-picker
                type="datetime"
                placeholder="选择初登日期"
                style="width: 300px"
                ref="firstLogDate"
                format="yyyy-MM-dd"
                :value="form.order.firstLogDate"
                :editable="true"
                @on-change="firstLogDateChange"
                clearable
              ></Date-picker>
            </Tooltip>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center">
        <Col span="6">
          <FormItem label="折旧后车价" prop="monthOldRatePrice">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="折旧后车价"
            >
              <InputNumber
                style="width: 300px"
                :max="100000"
                :min="0"
                v-model="form.order.monthOldRatePrice"
                disabled
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="6">
          <FormItem label="协商车价" prop="negotiatePrice">
            <Tooltip
              trigger="hover"
              placement="right-start"
              max-width="300"
              content="协商车价"
            >
              <InputNumber
                style="width: 300px"
                :max="1000000000000"
                :min="0"
                v-model="form.order.negotiatePrice"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center">
        <Col span="6">
          <FormItem label="发动机号" prop="motorNo">
            <div style="display: flex">
              <Input v-model="form.order.motorNo" style="margin-right: 10px" />
            </div>
          </FormItem>
        </Col>
        <Col span="6">
          <FormItem label="车架号" prop="carframeNo">
            <div style="display: flex">
              <Input
                v-model="form.order.carframeNo"
                style="margin-right: 10px"
              />
            </div>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center">
        <div style="display: flex">
          <Checkbox v-model="form.order.isCheckCar">需要验车</Checkbox>
        </div>
      </Row>
    </Form>
    <Form>
      <a-divider :dashed="true" style="color: #0f0">统筹项目与统筹费</a-divider>
      <Row type="flex" justify="center" :gutter="8">
        <Col span="3">
          <div class="feeTypeTitle">统筹类型</div>
        </Col>
        <Col span="2">
          <div class="feeTypeTitle">每人统筹</div>
        </Col>
        <Col span="1">
          <div class="feeTypeTitle">座位数</div>
        </Col>
        <Col span="2">
          <div class="feeTypeTitle">统筹限额</div>
        </Col>
        <Col span="2">
          <div class="feeTypeTitle">标准统筹费</div>
        </Col>
        <Col span="2">
          <div class="feeTypeTitle">调整系数</div>
        </Col>
        <Col span="2">
          <div class="feeTypeTitle">应收统筹费</div>
        </Col>
        <Col span="2">
          <div class="feeTypeTitle">不记免责统筹费</div>
        </Col>
      </Row>
      <Row
        v-for="(item, index) in form.orderDetails"
        :key="item.feeType + index"
        Row
        type="flex"
        justify="center"
        :gutter="8"
      >
        <Col span="3">
          <Form-item :label-width="0" :prop="item.index + 'isChecked'">
            <!-- <Checkbox v-model="item.isChecked" ></Checkbox> -->

            <input
              type="checkbox"
              ref="check"
              v-on:click="CheckItem(item)"
              v-model="item.isChecked"
            />
            {{ getDictData(item.feeType) }}
          </Form-item>
        </Col>
        <Col span="2">
          <Form-item
            :label-width="0"
            :prop="item.index + 'everyFee'"
            v-if="item.feeType == 'passenger'"
          >
            <Select
              v-model="item.everyFee"
              placeholder="请选择"
              :label-in-value="true"
              @on-change="onEveryFeeSelctChange($event, item)"
            >
              <Option
                v-for="(op, opindx) in dict.everyFees"
                :value="op.value"
                :key="opindx"
                :label="op.title"
                ><div slot>{{ op.title }}</div></Option
              >
            </Select>
          </Form-item>
        </Col>
        <Col span="1">
          <Form-item
            :label-width="0"
            :prop="item.index + 'siteCount'"
            v-if="item.feeType == 'passenger'"
          >
            <Select
              v-model="item.siteCount"
              placeholder="请选择"
              @on-change="onEveryFeeSelctChange($event, item)"
            >
              <Option
                v-for="(op, opindx) in dict.siteCounts"
                :value="op.value"
                :key="opindx"
                >{{ op.title }}</Option
              >
            </Select>
          </Form-item>
        </Col>
        <Col span="2">
          <Form-item
            v-if="!(item.feeType == 'carloss' || item.feeType == 'passenger')"
            :label-width="0"
            :prop="item.index + 'insuranceMoney'"
          >
            <Select
              v-model="item.insuranceMoney"
              placeholder="请选择"
              @on-change="onInsuranceMoneyInputChange($event, item)"
              ref="insuranceMoney"
            >
              <Option
                v-for="(op, opindx) in dict.insuranceMoneys"
                :value="op.value"
                :key="opindx"
                >{{ op.title }}</Option
              >
            </Select>
          </Form-item>
          <FormItem
            v-if="item.feeType == 'carloss' || item.feeType == 'passenger'"
            :label-width="0"
            label=""
            :prop="item.index + 'insuranceMoney'"
            class="colContent"
          >
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="统筹限额(元)"
            >
              <Input
                @on-change="onInsuranceMoneyInputChange($event, item)"
                :max="100000000"
                :min="0"
                v-model="item.insuranceMoney"
              ></Input>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="2">
          <FormItem
            :label-width="0"
            label=""
            :prop="item.index + 'baseFee'"
            class="colContent"
          >
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="标准统筹费(元)"
            >
              <InputNumber
                @on-change="baseFeeInpuChanged($event, item)"
                :max="10000000000"
                :min="0"
                v-model="item.baseFee"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="2">
          <FormItem
            :label-width="0"
            label=""
            :prop="item.index + 'feeRate'"
            class="colContent"
          >
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="调整系数"
            >
              <InputNumber
                @on-change="feeRateInpuChanged($event, item)"
                :max="10000000000"
                :min="0"
                v-model="item.feeRate"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="2">
          <FormItem
            :label-width="0"
            label=""
            :prop="item.index + 'actualFee'"
            class="colContent"
          >
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="应收保费(元)"
            >
              <InputNumber
                @on-change="calculateFees1"
                :max="100000000000000"
                :min="0"
                v-model="item.actualFee"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="2">
          <FormItem
            class="colContent"
            :label-width="0"
            label=""
            :prop="item.index + 'deductibleFee'"
          >
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="不计免责(元)"
            >
              <InputNumber
                @on-change="calculateFees1"
                :max="100000"
                :min="0"
                v-model="item.deductibleFee"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
      </Row>
      <a-divider :dashed="true" style="color: #0f0">统筹费汇总</a-divider>
      <Row type="flex" justify="center" :gutter="8">
        <Col span="3">
          <FormItem label="统筹总金额(元)" prop="totalFee">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="应收总统筹费(元)"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.totalFee"
                disabled
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="6">
          <FormItem label="价格浮动因素(优惠)" prop="feeFloatReason">
            <div style="display: flex">
              <Input
                v-model="form.order.feeFloatReason"
                readonly
                style="margin-right: 10px"
              />
              <Button icon="md-list" @click="openSelectYouhui">选择</Button>
            </div>
          </FormItem>
        </Col>
        <Col span="3">
          <FormItem label="价格浮动" prop="feeFloatRate">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="价格浮动(系数)"
            >
              <InputNumber
                :max="100000"
                :min="0"
                v-model="form.order.feeFloatRate"
                @on-change="calculateFees1"
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="浮动后统筹总金额" prop="feeFloat">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="浮动后统筹总额(元)"
            >
              <InputNumber
                @on-change="calculateFees1"
                :max="100000"
                :min="0"
                v-model="form.order.feeFloat"
                disabled
              ></InputNumber>
            </Tooltip>
          </FormItem>
        </Col>
      </Row>

      <a-divider :dashed="true" style="color: #0f0">其他信息</a-divider>
      <Row type="flex" justify="center" :gutter="8">
        <Col span="12">
          <FormItem label="备注信息" prop="remark">
            <Tooltip
              trigger="hover"
              placement="top"
              max-width="300"
              content="备注信息"
            >
              <Input v-model="form.order.remark" style="width: 700px" />
            </Tooltip>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center" :gutter="8">
        <Col span="12">
          <FormItem label="特别约定" prop="orderAgree">
            <div style="display: flex">
              <Input
                v-model="form.order.orderAgree"
                style="margin-right: 10px"
                type="textarea"
                :rows="6"
              />
              <Poptip placement="right" width="1000" ref="agreeGroup">
                <Button icon="md-list"></Button>
                <div slot="content">
                  <table>
                    <thead align="center">
                      <tr>
                        特别约定
                      </tr>
                    </thead>
                    <tbody>
                      <Checkbox-group v-model="agreeGroup">
                        <tr
                          v-for="(item, index) in this.$store.state.dict
                            .orderAgrees"
                          :key="index"
                        >
                          <Checkbox
                            :label="'(' + (index + 1) + ')' + item.value"
                          />
                        </tr>
                      </Checkbox-group>
                    </tbody>
                  </table>
                  <Button
                    icon="md-list"
                    type="primary"
                    @click="orderAgreeClicked()"
                    >选定</Button
                  >
                </div>
              </Poptip>
            </div>
          </FormItem>
        </Col>
      </Row>
      <Row type="flex" justify="center" :gutter="8">
        <Col span="12">
          <FormItem label="图片备注" prop="pics">
            <mine-upload-pic-thumb
              v-model="form.pics"
              :uploadFileUrl="uploadFileUrl"
            />
          </FormItem>
        </Col>
      </Row>
    </Form>
    <Modal
      title="选择被统筹人"
      v-model="selectclientName"
      :styles="{ top: selectclientNameTop }"
      :width="1500"
    >
      <client-info-manager
        :singgleSelect="true"
        @outSelect="clientNameSelected"
      />
      <div slot="footer"></div>
    </Modal>
    <Modal
      title="选择办理人"
      v-model="selectHandleName"
      :styles="{ top: selectHandleNameTop }"
      :width="1500"
    >
      <client-info-manager
        :singgleSelect="true"
        @outSelect="handleNameSelected"
      />
      <div slot="footer"></div>
    </Modal>
    <Modal
      title="选择驾驶证车主"
      v-model="selectrunCardName"
      :styles="{ top: selectrunCardNameTop }"
      :width="1500"
    >
      <client-info-manager
        :singgleSelect="true"
        @outSelect="runCardNameSelected"
      />
      <div slot="footer"></div>
    </Modal>
    <Modal
      title="选择车型"
      v-model="selectCarTypeName"
      :styles="{ top: selectCarTypeNameTop }"
      :width="1500"
    >
      <car-type-info-manager
        :singgleSelect="true"
        @outSelect="carTypeNameSelected"
      />
      <div slot="footer"></div>
    </Modal>
    <Modal
      title="选择优惠"
      v-model="selectYouhui"
      :styles="{ top: selectYouhuiTop }"
      :width="1500"
    >
      <fee-youhui-rate
        :singgleSelect="true"
        @outSelect="youhuiSelected"
        :region="form.order.carNoHead"
        :onlymine="true"
      />
      <div slot="footer"></div>
    </Modal>
  </div>
</template>

<script>
import mineUploadPicThumb from "./mine-upload-pic-thumb.vue";
import { mapState } from "vuex";
import dictUtil from "@/libs/dictUtil";
import clientInfoManager from "@/views/client-info/client-info-manager/clientInfoManager.vue";
import carTypeInfoManager from "@/views/car-type-info/car-type-info-manager/carTypeInfoManager.vue";
import feeYouhuiRate from "@/views/fee-rate/fee-youhui-rate/feeYouhuiRate.vue";
import {
  getMineFeeList,
  getCarDepreciationListData,
  uploadMineFile,
  getCarTypeBByCartypeA,
} from "@/api/index";
export default {
  name: "order-compnt",
  components: {
    clientInfoManager,
    carTypeInfoManager,
    feeYouhuiRate,
    mineUploadPicThumb,
  },
  props: {
    orderPro: {
      type: Object,
      default: () => ({
        order: {},
        orderDetails: [],
        orderFiles: [],
      }),
    },
  },
  data () {
    return {
      agreeGroup: [],
      carTypeBs: this.$store.state.dict.carTypeBs,
      uploadFileUrl: uploadMineFile,
      monthDisable: false,
      selectYouhui: false,
      selectYouhuiTop: "20px",
      selectCarTypeName: false,
      selectCarTypeNameTop: "20px",
      selectrunCardName: false,
      selectrunCardNameTop: "20px",
      selectclientName: false,
      selectclientNameTop: "20px",
      selectHandleName: false,
      selectHandleNameTop: "20px",
      form: {
        order: {},
        orderDetails: [],
        orderFiles: [],
      },
      // 表单验证规则
      formValidate: {
        timeLimitType: [
          {
            required: true,
            message: "期限类型不能为空",
            trigger: "blur,change",
          },
        ],
        month: [
          {
            required: true,
            message: "自然月数不能为空",
            trigger: "blur,change",
          },
        ],
        startDate: [
          {
            required: true,
            message: "统筹开始时间不能为空",
            trigger: "change",
            pattern: /.+/,
          },
        ],
        endDate: [
          {
            required: true,
            message: "统筹结束时间不能为空",
            trigger: "change",
            pattern: /.+/,
          },
        ],
        clientName: [
          {
            required: true,
            message: "被统筹人姓名不能为空",
            trigger: "blur,change",
          },
        ],
        handleName: [
          {
            required: true,
            message: "办理人姓名不能为空",
            trigger: "blur,change",
          },
        ],
        runCardName: [
          {
            required: true,
            message: "行驶证车主姓名不能为空",
            trigger: "blur,change",
          },
        ],
        carType: [
          {
            required: true,
            message: "车辆种类不能为空",
            trigger: "blur,change",
          },
        ],
        carTypeName: [
          {
            required: true,
            message: "车型名称不能为空",
            trigger: "blur,change",
          },
        ],
        carNewPrice: [
          {
            required: true,
            message: "新车购置价不能为空",
            trigger: "blur,change",
            type: "number",
          },
        ],
        carNo: [
          {
            required: true,
            message: "车牌号码不能为空",
            trigger: "blur,change",
          },
        ],
        useType: [
          {
            required: true,
            message: "使用性质不能为空",
            trigger: "blur,change",
          },
        ],
        belongType: [
          {
            required: true,
            message: "所属性质不能为空",
            trigger: "blur,change",
          },
        ],
        runCardCarType: [
          {
            required: true,
            message: "行驶证车辆类型不能为空",
            trigger: "blur,change",
          },
        ],
        carSieralType: [
          {
            required: true,
            message: "车系分类不能为空",
            trigger: "blur,change",
          },
        ],
        firstLogDate: [
          {
            required: false,
            message: "初登日期不能为空",
            trigger: "blur,change",
            pattern: /.+/,
          },
        ],
        beforeMonthOldRatePrice: [
          {
            required: true,
            message: "折旧前车价不能为空",
            trigger: "blur,change",
            type: "number",
          },
        ],
        monthOldRate: [
          {
            required: true,
            message: "月折旧率不能为空",
            trigger: "blur,change",
            type: "number",
          },
        ],
        monthOldRatePrice: [
          {
            required: true,
            message: "折旧后车价不能为空",
            trigger: "blur,change",
            type: "number",
          },
        ],
        negotiatePrice: [
          {
            required: true,
            message: "协商车价不能为空",
            trigger: "blur,change",
            type: "number",
          },
        ],
        motorNo: [
          {
            required: true,
            message: "发动机号不能为空",
            trigger: "blur,change",
          },
        ],
        carframeNo: [
          { required: true, message: "车架号不能为空", trigger: "blur,change" },
        ],
      },
      loadingEdit: true,
      backRoute: "",
      feeFloatReasons: [],
    };
  },
  computed: {
    ...mapState(["dict"]),
  },
  watch: {
    "$store.state.dict.feeTypes": function () {
      //你需要执行的代码
      //处理一下数据,将未选择的统筹添加上
      let str = JSON.stringify(this.$store.state.dict.feeTypes);
      let data = JSON.parse(str);
      this.form.orderDetails.forEach((item) => {
        //遍历查询是否已勾选所有统筹类型
        let ofIndex = -1;
        data.forEach((i, index) => {
          if (i.value == item.feeType) {
            ofIndex = index;
          }
        });
        if (ofIndex != -1) {
          if (item.insuranceMoney != null) {
            item.insuranceMoney = item.insuranceMoney + "";
          }
          if (item.siteCount != null) {
            item.siteCount = item.siteCount + "";
          }
          if (item.everyFee != null) {
            item.everyFee = item.everyFee + "";
          }
          item.isChecked = true;
          //   if (this.$refs.check != null) {
          //     this.$refs.check.forEach((c) => {
          //       c.updateModel();
          //     });
          //   }
          data.splice(indexof, 1);
        }
      });
      data.forEach((item) => {
        this.form.orderDetails.push({
          feeType: item.value,
          isChecked: false,
          insuranceMoney: 100000,
          feeRate: 1,
        });
      });
    },
    orderPro: function () {
      console.log(this.orderPro);
      if (this.orderPro != null) {
        this.form = this.orderPro;
      } else {
        this.form = {
          order: {},
          orderDetails: [],
          orderFiles: [],
        };
      }
      if (this.form.order.firstLogDate == null) {
        this.form.order.firstLogDate = this.dateFormat("YYYY-mm-dd", new Date())
      }
      if (this.form.order.startDate == null) {
        this.calculateTimeLimit()
      }
      if (this.form.orderDetails == null) {
        this.form.orderDetails = [];
      }
      //你需要执行的代码
      //处理一下数据,将未选择的统筹添加上
      if (this.$store.state.dict.feeTypes != null) {
        let str = JSON.stringify(this.$store.state.dict.feeTypes);
        let data = JSON.parse(str);
        this.form.orderDetails.forEach((item) => {
          //遍历查询是否已勾选所有统筹类型
          let ofIndex = -1;
          data.forEach((i, index) => {
            if (i.value == item.feeType) {
              ofIndex = index;
              item.sortOrder = i.sortOrder
            }
          });
          if (ofIndex != -1) {
            if (item.insuranceMoney != null) {
              item.insuranceMoney = item.insuranceMoney + "";
            }
            if (item.siteCount != null) {
              item.siteCount = item.siteCount + "";
            }
            if (item.everyFee != null) {
              item.everyFee = item.everyFee + "";
            }
            item.isChecked = true;
            //   if (this.$refs.check != null) {
            //     this.$refs.check.forEach((c) => {
            //       c.updateModel();
            //     });
            //   }
            data.splice(ofIndex, 1);
          }
        });
        data.forEach((item) => {
          this.form.orderDetails.push({
            feeType: item.value,
            isChecked: false,

            sortOrder: item.sortOrder
          });
        });
        this.form.orderDetails = this.sortByKey(this.form.orderDetails, 'sortOrder')
      }
    },
  },
  methods: {
    sortByKey (array, key) {
      return array.sort(function (a, b) {
        var x = a[key];
        var y = b[key];
        return ((x < y) ? -1 : ((x < y) ? 1 : 0));
      });
    },
    handleNameSameClientName () {
      if (this.form.order.clientName == null) {
        return;
      }
      this.form.order.handleName = this.form.order.clientName
      this.form.order.handleCardNo = this.form.order.clientCardNo
      this.form.order.handlePhone = this.form.order.clientPhone
      this.$forceUpdate();
    },
    runCardNameSameClientName () {
      if (this.form.order.clientName == null) {
        return;
      }
      this.form.order.runCardName = this.form.order.clientName
      this.form.order.runCardCardNo = this.form.order.clientCardNo
      this.form.order.runCardPhone = this.form.order.clientPhone
      this.$forceUpdate();
    },
    runCardNameSameHandleName () {
      if (this.form.order.clientName == null) {
        return;
      }
      this.form.order.runCardName = this.form.order.handleName
      this.form.order.runCardCardNo = this.form.order.handleCardNo
      this.form.order.runCardPhone = this.form.order.handlePhone
      this.$forceUpdate();
    },
    dateFormat (fmt, date) {
      let ret;
      const opt = {
        "Y+": date.getFullYear().toString(), // 年
        "m+": (date.getMonth() + 1).toString(), // 月
        "d+": date.getDate().toString(), // 日
        "H+": date.getHours().toString(), // 时
        "M+": date.getMinutes().toString(), // 分
        "S+": date.getSeconds().toString(), // 秒
      };
      for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
          fmt = fmt.replace(
            ret[1],
            ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, "0")
          );
        }
      }
      return fmt;
    },

    calculateTimeLimit () {
      var day1 = new Date();
      day1.setDate(day1.getDate() + 1)
      var s1 = this.dateFormat("YYYY-mm-dd", day1)
      var startDate = s1 + " 00:00:00"
      this.form.order.startDate = startDate;
      if (this.form.order.month != null) {
        var month = parseInt(this.form.order.month)
        day1.setMonth(day1.getMonth() + month);
        day1.setDate(day1.getDate() - 1)
        var s1 = this.dateFormat("YYYY-mm-dd", day1)
        var endDate = s1 + " 23:59:59"
        this.form.order.endDate = endDate;
      }
    },
    getDict (carTypeB) {
      let dic = dictUtil.reflectDictData(
        carTypeB,
        this.$store.state.dict.carTypeBs
      );
      return dic.title;
    },
    getCartypeB (carTypeA) {
      var _this = this;
      this.carTypeBs = "";
      if (carTypeA == null || carTypeA.length == 0) {
        this.carTypeBs = [];
        return;
      }
      getCarTypeBByCartypeA({ carTypeA: carTypeA }).then((res) => {
        if (res.success) {
          this.refreshCarTypeB(res);
        }

      });
    },
    refreshCarTypeB (res) {
      if (res.result != null) {
        res.result.forEach((item) => {
          item.title = this.getDict(item.carTypeB);
          item.value = item.carTypeB;
        });
        this.carTypeBs = res.result;
      } else {
        this.carTypeBs = [];
      }

    },
    //供父组件调用获取order数据
    getOrderDatas () {
      var that = this;
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (that.form.orderDetails == null) {
            that.$Message.error("请选择统筹项目后再操作");
            return;
          }

          let details = that.form.orderDetails.filter((item) => {
            return item.isChecked;
          });
          if (details == null || details.length == 0) {
            that.$Message.error("请选择统筹项目后再操作");
            return;
          }
          //向父组件传值
          that.$emit("onDataValid", that.form);
        }
      });
    },
    orderAgreeClicked () {
      if (this.agreeGroup == null) {
        return
      }
      this.form.order.orderAgree = ""
      this.agreeGroup.forEach(item => {
        this.form.order.orderAgree += item + ";"
      })
      this.$forceUpdate();
      this.$refs.agreeGroup.handleClose();
    },
    //计算相关代码
    CheckItem (item) {
      console.log(item);
      item.isChecked = !item.isChecked;
      if (item.isChecked) {
        //该选项选中
        //判断必要信息是否已填写------------------------------------
        if (
          this.form.order.carTypeA == null ||
          this.form.order.carTypeB == null ||
          this.form.order.useType == null

        ) {
          this.$Message.error("请补全以上信息再编辑统筹费用");
          //将checkbox置为false
          item.isChecked = false;
          //TODO  刷新页面
          // if (this.$refs.check != null) {
          //     this.$refs.check.forEach((c) => {
          //         c.updateModel();
          //     });
          // }
          return;
        }
        if (
          item.feeType == "carloss" &&
          this.form.order.firstLogDate == null &&
          this.form.order.negotiatePrice == null
        ) {
          this.$Message.error("请填写初登日期和协商车价");
          //将checkbox置为false
          item.isChecked = false;
          //todo  刷新页面
          //   if (this.$refs.check != null) {
          //     this.$refs.check.forEach((c) => {
          //       c.updateModel();
          //     });
          //   }

          return;
        }
        if (item.feeType == "thirdDuty") {
          //第三者责任险
          if (item.insuranceMoney == null) {
            item.insuranceMoney = "100000";
          }

          //   if (this.$refs.insuranceMoney != null) {
          //     this.$refs.insuranceMoney.forEach((c) => {
          //       c.updateModel();
          //     });
          //   }
        }
        if (item.feeType == "passenger") {
          //第三者责任险
          if (item.insuranceMoney == null) {
            item.insuranceMoney = "100000";
          }

          //   if (this.$refs.insuranceMoney != null) {
          //     this.$refs.insuranceMoney.forEach((c) => {
          //       c.updateModel();
          //     });
          //   }
        }
        //获取费率信息--------------------------------------------------
        this.getFeeRate(item);
      } else {
        //该选项未选中
        this.calculateFees1();
      }
    },
    //每个座位的统筹额变化
    onEveryFeeSelctChange (e, item) {
      console.log("--------------------------");
      console.log(e);
      console.log(item);
      if (item.feeType == "passenger") {
        if (item.everyFee != null && item.siteCount != null) {
          if (item.feeRate == null) {
            item.feeRate = 1;
          }
          item.insuranceMoney =
            parseInt(item.everyFee) * parseInt(item.siteCount);
          item.baseFee = parseInt(item.insuranceMoney);
          item.actualFee = ((item.baseFee * parseFloat(item.feeRate)) / 100).toFixed(2);
        }
      }
      this.calculateFees1();
    },
    //当保额变化

    onInsuranceMoneyInputChange (e, item) {
      if (item.feeType == "thirdDuty") {
        // 重新获取费率
        let query = {};
        query.carTypeA = this.form.order.carTypeA;
        query.carTypeB = this.form.order.carTypeB;
        query.useType = this.form.order.useType;
        query.regionGroup = this.form.order.carNoHead;
        query.feeType = item.feeType;
        query.insuranceMoney = parseInt(item.insuranceMoney);

        getMineFeeList(query).then((res) => {
          if (res.success) {
            if (res.result == null || res.result.length == 0) {
              this.$Message.warning(
                "未查到相关费率配置信息，请手动补全费率配置"
              );
              item.baseFee = 0;
              item.feeRate = 0;
              item.actualFee = 0;
            } else {
              //补全配置信息
              var rateConfig = res.result[0];
              this.form.orderDetails.forEach((item) => {
                if (item.feeType == rateConfig.feeType) {
                  item.baseFee = rateConfig.baseFee;
                  item.feeRate = rateConfig.feeRate;
                  item.baseFee = rateConfig.baseFee;
                }
              });
            }
            this.calculateFees(item);
          }
        });
      } else {
        this.calculateFees(item);
      }
    },
    baseFeeInpuChanged (e, item) {
      this.calculateFees(item);
    },
    feeRateInpuChanged (e, item) {
      this.calculateFees(item);
    },
    calculateMonthOldPrice () {
      if (this.form.order.firstLogDate == null) {
        return;
      } else {
        var monthDiff = this.differenceMonth(this.form.order.firstLogDate);
        if (monthDiff <= 0) monthDiff = 0;

        if (this.form.order.monthOldRate == null) {
          this.form.order.monthOldRate = 0;
        }
        this.form.order.monthOldRatePrice =
          (this.form.order.buyPrice *
            (1 - (this.form.order.monthOldRate / 100) * monthDiff));
      }
      this.form.order.negotiatePrice = this.form.order.monthOldRatePrice;
      this.$forceUpdate();
    },
    getFeeRate (item) {
      let query = {};
      query.carTypeA = this.form.order.carTypeA;
      query.carTypeB = this.form.order.carTypeB;
      query.useType = this.form.order.useType;
      query.regionGroup = this.form.order.carNoHead;
      query.feeType = item.feeType;
      if (item.feeType == "carloss") {
        //车损 要计算车辆年限
        query.yearType = this.differenceYear(this.form.order.firstLogDate);
      }
      if (item.feeType == "thirdDuty") {
        //第三者责任险要加上保额
        query.insuranceMoney = item.insuranceMoney;
      }

      getMineFeeList(query).then((res) => {
        if (res.success) {
          if (res.result == null || res.result.length == 0) {
            item.baseFee = 0;
            item.feeRate = 0;
            item.actualFee = 0;
            item.deductibleFee = 0;

            this.$Message.warning("未查到相关费率配置信息，请手动补全费率配置");
          } else {
            //补全配置信息
            var rateConfig = res.result[0];
            item.baseFee = rateConfig.baseFee;
            item.feeRate = rateConfig.feeRate;
          }
          this.calculateFees(item);
        }
      });
    },
    differenceYear (dateBegin) {
      var dateDiff = new Date().getTime() - new Date(dateBegin).getTime(); //时间差的毫秒数
      var dayDiff = Math.ceil(dateDiff / (24 * 3600 * 1000)); //计算出相差天数
      return Math.floor(dayDiff / 360);
    },
    differenceMonth (dateBegin) {
      var dateDiff = new Date().getTime() - new Date(dateBegin).getTime(); //时间差的毫秒数
      var dayDiff = Math.ceil(dateDiff / (24 * 3600 * 1000)); //计算出相差天数
      return Math.floor(dayDiff / 30);
    },
    monthChange () {
      if (this.form.order.month != 12) {
        this.form.order.timeLimitType = "month";
      } else {
        this.form.order.timeLimitType = "year";
        this.monthDisable = true;
      }
      //计算短期折扣
      let dict = dictUtil.reflectDictDataTitle(
        this.form.order.month,
        this.$store.state.dict.shortDiscounts
      );
      this.form.order.shortDiscount = parseFloat(dict.value);
      this.calculateTimeLimit();
      this.$forceUpdate();
    },
    //计算各种费
    calculateFees (item) {
      var dtls = this.form.orderDetails;
      if (dtls == null) {
        return;
      }
      if (item.feeType == "passenger") {
        if (item.everyFee != null && item.siteCount != null) {
          if (item.feeRate == null) {
            item.feeRate = 1;
          }
          item.insuranceMoney =
            parseInt(item.everyFee) * parseInt(item.siteCount);
          item.baseFee = 0;
          item.actualFee = ((item.baseFee * parseFloat(item.feeRate)) / 100).toFixed(2);
        }
      } else if (item.feeType == "carloss") {
        //车损
        item.insuranceMoney = this.form.order.negotiatePrice;
        if (item.baseFee == null) item.baseFee = 0;
        if (item.feeRate == null) item.feeRate = 1;
        item.actualFee =
          (item.baseFee + (item.feeRate * item.insuranceMoney) / 100).toFixed(2);
      } else if (item.feeType == "thirdDuty") {
        //三者
        if (item.feeRate == null) {
          item.feeRate = 0;
        }
        if (item.insuranceMoney == null) {
          item.insuranceMoney = "100000";
        }
        item.actualFee = item.baseFee;
      } else if (item.feeType == "driver") {
        //驾驶员
        if (item.insuranceMoney != null) {
          if (item.feeRate == null) {
            item.feeRate = 1;
          }
          item.baseFee = 0;
          item.actualFee =
            ((parseFloat(item.feeRate) * parseInt(item.insuranceMoney)) / 100).toFixed(2);
        }
      }
      this.calculateFees1();
    },

    //直接修改应收统筹费触发   计算总和统筹费
    calculateFees1 () {
      var dtls = this.form.orderDetails;
      if (dtls == null) {
        return;
      }
      //计算总的统筹费
      let totalFee = 0;
      dtls.forEach((item) => {
        if (item.isChecked) {
          //已勾选此统筹
          if (item.actualFee == null) {
            item.actualFee = 0;
          }
          if (item.deductibleFee == null) item.deductibleFee = 0;
          totalFee += parseFloat(item.actualFee) + item.deductibleFee;
        }
      });
      if (this.form.order.shortDiscount == null)
        this.form.order.shortDiscount = 1;
      this.form.order.totalFee =
        (totalFee * parseFloat(this.form.order.shortDiscount)).toFixed(2);
      if (this.form.order.feeFloatRate == null)
        this.form.order.feeFloatRate = 1;
      this.form.order.feeFloat =
        (this.form.order.totalFee * this.form.order.feeFloatRate).toFixed(2);
      this.$forceUpdate();
    },

    youhuiSelected (row) {
      this.form.order.feeFloatReason = row.feeFloatReason;
      this.form.order.feeFloatRate = row.feeFloatRate;
      this.$forceUpdate();
      this.calculateFees1();
    },
    openSelectYouhui () {
      this.selectYouhui = true;
    },
    openSelectCarTypeName () {
      this.carTypeBs = this.$store.state.dict.carTypeBs
      let bound = this.$refs.carTypeName.$el.getBoundingClientRect();
      console.log(bound);
      this.selectCarTypeNameTop = bound.top + 35 + "px";
      this.selectCarTypeName = true;
    },
    carTypeNameSelected (row) {
      this.selectCarTypeName = false;
      var that = this;
      this.form.order.carTypeA = row.carTypeA;

      this.form.order.carCode = row.code;
      this.form.order.carTypeName = row.carTypeName;
      this.form.order.carNewPrice = row.carNewPrice;
      this.form.order.buyPrice = row.buyPrice;
      this.form.order.useType = row.useType;
      this.form.order.belongType = row.belongType;
      this.form.order.carSieralType = row.carSieralType;
      this.form.order.siteCount = row.siteCount;
      this.form.order.kws = row.kws;
      this.form.order.loadCapacity = row.loadCapacity;
      this.form.order.displacement = row.displacement;
      if (this.form.order.carTypeA != null) {
        this.getCartypeB(this.form.order.carTypeA);
      }
      this.form.order.carTypeB = row.carTypeB;
      let query = {};
      query.useType = this.form.order.useType;
      query.carTypeA = this.form.order.carTypeA;
      query.carTypeB = this.form.order.carTypeB;
      getCarDepreciationListData(query).then((resp) => {
        if (resp.success) {
          if (resp.result.totalElements == 0) {
            //没有折旧信息
            if (this.form.order.negotiatePrice == null) {
              this.$Message.error("请输入协议车价");
              return;
            }
            this.form.order.monthOldRate = 0;
          } else {
            //补全配置信息
            var zhejiu = resp.result.content[0];
            this.form.order.monthOldRate = zhejiu.monthRate;
          }
          that.calculateMonthOldPrice();
          that.calculateFees1();
        }
      });
    },

    openSelectRunCardName () {
      let bound = this.$refs.clientName.$el.getBoundingClientRect();
      console.log(bound);
      this.selectrunCardNameTop = bound.top + 35 + "px";
      this.selectrunCardName = true;
    },
    runCardNameSelected (row) {
      this.form.order.runCardName = row.name;
      this.form.order.runCardPhone = row.telephone;
      this.form.order.runCardCardNo = row.idNo;
      this.$forceUpdate();
    },
    openSelectHandleName () {
      let bound = this.$refs.clientName.$el.getBoundingClientRect();
      console.log(bound);
      this.selectHandleNameTop = bound.top + 35 + "px";
      this.selectHandleName = true;
    },
    handleNameSelected (row) {
      this.form.order.handleName = row.name;
      this.form.order.handlePhone = row.telephone;
      this.form.order.handleCardNo = row.idNo;
      this.$forceUpdate();
    },
    openSelectClientName () {
      let bound = this.$refs.clientName.$el.getBoundingClientRect();
      console.log(bound);
      this.selectclientNameTop = bound.top + 35 + "px";
      this.selectclientName = true;
    },
    clientNameSelected (row) {
      this.form.order.clientName = row.name;
      this.form.order.clientCardNo = row.idNo;
      this.form.order.clientType = row.clientType;
      this.form.order.clientPhone = row.telephone;
      this.$forceUpdate();
    },
    timeLimitTypeChange (select) {
      if (select == "year") {
        this.form.order.month = "12";
        this.monthDisable = true;
        this.form.order.shortDiscount = parseFloat(1);
      } else {
        this.monthDisable = false;
        this.form.order.shortDiscount = parseFloat(1);
        this.form.order.month = "12";
      }
      this.calculateTimeLimit();
      this.$forceUpdate();

    },
    getDictData (feeType) {
      let dict = dictUtil.reflectDictData(
        feeType,
        this.$store.state.dict.feeTypes
      );
      return dict.title;
    },
    init () {
      this.carTypeBs = this.$store.state.dict.carTypeBs
    },
    startTimeChange (refName) {
      this.$refs[refName].onSelectionModeChange("time");
      //获取选中值
      this.form.order.startDate = this.$refs[refName].visualValue;
      // this.startTimeVal = this.$refs[refName].visualValue
    },
    endDateChange (refName) {
      this.$refs[refName].onSelectionModeChange("time");
      this.form.order.endDate = this.$refs[refName].visualValue;
      this.$forceUpdate();
    },
    firstLogDateChange () {
      this.form.order.firstLogDate = this.$refs.firstLogDate.visualValue;
      this.$forceUpdate();
      this.calculateMonthOldPrice();
    },
    handleReset () {
      this.$refs.form.resetFields();
    },
    handleSubmit () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          // this.postRequest("请求路径", this.form).then(res => {
          //   this.submitLoading = false;
          //   if (res.success) {
          //     this.$Message.success("添加成功");
          //     this.closeCurrentPage();
          //   }
          // });
          // 模拟成功
          this.submitLoading = false;
          this.$Message.success("添加成功");
          this.closeCurrentPage();
        }
      });
    },
  },

  mounted () {
    this.init();
  },
};
</script>