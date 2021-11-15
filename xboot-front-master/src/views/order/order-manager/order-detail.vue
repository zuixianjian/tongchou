// table-expand.vue
<style scoped>
.detail {
  padding-left: 16px;
  width: 25%;
  background-color: aqua;
}
</style>
<template>
  <div class="detail">
    <Row class="expand-row">
      <Col span="3">
        <span class="expand-key">统筹类型</span>
      </Col>
      <Col span="3">
        <span class="expand-key">基础统筹费(元)</span>
      </Col>
      <Col span="3">
        <span class="expand-key">费率</span>
      </Col>
      <Col span="3">
        <span class="expand-key">不计免赔(元)</span>
      </Col>
      <Col span="3">
        <span class="expand-key">统筹额(元)</span>
      </Col>

      <Col span="3">
        <span class="expand-key">每座统筹费(元)</span>
      </Col>
      <Col span="3">
        <span class="expand-key">座位数</span>
      </Col>
      <Col span="3">
        <span class="expand-key">应收统筹费(元)</span>
      </Col>
    </Row>
    <Row class="expand-row" v-for="item in orderDetails" :key="item.id">
      <a-divider></a-divider>
      <Col span="3">
        <span class="expand-key">{{ getDict(item.feeType) }}</span>
      </Col>
      <Col span="3">
        <span class="expand-key">{{ item.baseFee }}</span>
      </Col>
      <Col span="3">
        <span class="expand-key">{{ item.feeRate }}</span>
      </Col>
      <Col span="3">
        <span class="expand-key">{{ item.deductibleFee }}</span>
      </Col>
      <Col span="3">
        <span class="expand-key">{{ item.insuranceMoney }}</span>
      </Col>
      <Col span="3">
        <span class="expand-key">{{ item.everyFee }}</span>
      </Col>
      <Col span="3">
        <span class="expand-key">{{ item.siteCount }}</span>
      </Col>
      <Col span="3">
        <span class="expand-key">{{ item.actualFee }}</span>
      </Col>
    </Row>
  </div>
</template>
<script>
import dictUtil from "@/libs/dictUtil";
export default {
  name: "order-detail",
  props: {
    row: Object,
  },

  data () {
    return {
      orderDetails: [],
      screenWidth: 500
    };
  },

  created () {
    this.orderDetails = this.row.orderDetails;
  },
  methods: {
    getDict (feeType) {
      let dic = dictUtil.reflectDictData(
        feeType,
        this.$store.state.dict.feeTypes
      );
      return dic.title;
    },
  },
  mounted () {
    window.onresize = () => {
      return (() => {
        this.screenWidth = document.body.clientWidth

      })()
    }

  }
};
</script>