<style lang="less">
@import "../../../styles/table-common.less";
.th {
  background: #f7f7f7;
  white-space: nowrap;
  color: #5c6b77;
  font-weight: 600;
  display: table-cell;
  vertical-align: inherit;
  font-weight: bold;
  text-align: -internal-center;
}
.tab {
  display: table;
  border-collapse: separate;
  box-sizing: border-box;
  text-indent: initial;
  border-spacing: 2px;
  border-color: grey;
  width: 100%;
}
.thrd {
  display: table-header-group;
  vertical-align: middle;
  border-color: inherit;
}
.tr {
  display: table-row;
  vertical-align: inherit;
  border-color: inherit;
}
.tbody {
  display: table-row-group;
  vertical-align: middle;
  border-color: inherit;
}
.td {
  border: 1px solid #e9e9e9;
  padding: 8px 16px;
  text-align: left;
  display: table-cell;
  vertical-align: inherit;
}
</style>
<template>
  <div class="search">
    <Card>
      <Row @keydown.enter.native="handleSearch">
        <Form ref="searchForm" :model="searchForm" inline :label-width="140">
          <Form-item label="被统筹人姓名" prop="clientName">
            <Input
              type="text"
              v-model="searchForm.clientName"
              clearable
              placeholder="请输入被统筹人姓名"
              style="width: 200px"
            />
          </Form-item>
          <Form-item label="统筹单id" prop="queryId">
            <Input
              type="text"
              v-model="searchForm.queryId"
              clearable
              placeholder="请输入统筹单id"
              style="width: 200px"
            />
          </Form-item>
          <Form-item label="被统筹人证件号码" prop="clientCardNo">
            <Input
              type="text"
              v-model="searchForm.clientCardNo"
              clearable
              placeholder="请输入车型名称"
              style="width: 200px"
            />
          </Form-item>
          <Form-item label="被统筹人电话" prop="clientPhone">
            <Input
              type="text"
              v-model="searchForm.clientPhone"
              clearable
              placeholder="请输入车型名称"
              style="width: 200px"
            />
          </Form-item>
          <Form-item label="车辆种类" prop="carTypeA">
            <Select
              v-model="searchForm.carTypeA"
              placeholder="请选择"
              clearable
              style="width: 200px"
              @on-change="getCartypeB(searchForm.carTypeA)"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.carTypeAs"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Form-item>
          <Form-item label="细分类目" prop="carTypeB">
            <Select
              v-model="searchForm.carTypeB"
              placeholder="请选择"
              clearable
              style="width: 200px"
            >
              <Option
                v-for="(item, index) in carTypeBs"
                :value="item.carTypeB"
                :key="index"
                >{{ getDict(item.carTypeB) }}</Option
              >
            </Select>
          </Form-item>
          <Form-item label="所属性质" prop="belongType">
            <Select
              v-model="searchForm.belongType"
              placeholder="请选择"
              clearable
              style="width: 200px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.belongTypess"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Form-item>
          <Form-item label="使用性质" prop="useType">
            <Select
              v-model="searchForm.useType"
              placeholder="请选择"
              clearable
              style="width: 200px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.useTypess"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Form-item>
          <Form-item label="车系分类" prop="carSieralType">
            <Select
              v-model="searchForm.carSieralType"
              placeholder="请选择"
              clearable
              style="width: 200px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.carSieralTypess"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Form-item>

          <Form-item label="状态" prop="orderStatus">
            <Select
              v-model="searchForm.orderStatus"
              placeholder="请选择"
              clearable
              style="width: 200px"
            >
              <Option
                v-for="(item, index) in orderStatus"
                :value="item.value"
                :key="index"
                >{{ item.lable }}</Option
              >
            </Select>
          </Form-item>

          <Form-item style="margin-left: -35px" class="br">
            <Button @click="handleSearch" type="primary" icon="ios-search"
              >搜索</Button
            >
            <Button @click="handleReset">重置</Button>
          </Form-item>
        </Form>
      </Row>
      <Row class="operation">
        <Button @click="add" type="primary" icon="md-add" v-has="'orderAdd'"
          >添加</Button
        >
        <Button
          @click="auditSuccess"
          type="success"
          icon="md-checkmark"
          v-has="'orderCheck'"
          >审核通过</Button
        >
        <Button
          @click="auditFailure"
          type="error"
          icon="md-close"
          v-has="'orderCheck'"
          >审核不通过</Button
        >
        <Button @click="delAll" icon="md-trash" v-has="'orderDelete'"
          >批量删除</Button
        >
        <Button @click="getDataList" icon="md-refresh">刷新</Button>
        <Button type="dashed" @click="openTip = !openTip">{{
          openTip ? "关闭提示" : "开启提示"
        }}</Button>
      </Row>
      <Row v-show="openTip">
        <Alert show-icon>
          已选择
          <span class="select-count">{{ selectCount }}</span> 项
          <a class="select-clear" @click="clearSelectAll">清空</a>
        </Alert>
      </Row>
      <Row>
        <Table
          :loading="loading"
          border
          :columns="columns"
          :data="data"
          ref="table"
          sortable="custom"
          @on-sort-change="changeSort"
          @on-selection-change="changeSelect"
        ></Table>
      </Row>
      <Row type="flex" justify="end" class="page">
        <Page
          :current="searchForm.pageNumber"
          :total="total"
          :page-size="searchForm.pageSize"
          @on-change="changePage"
          @on-page-size-change="changePageSize"
          :page-size-opts="[10, 20, 50]"
          size="small"
          show-total
          show-elevator
          show-sizer
        ></Page>
      </Row>
    </Card>
    <Modal
      title="查看报价单"
      v-model="baojiaDialog"
      :styles="{ top: baojiaTop }"
      :width="1500"
      ok-text="下载报价单"
      @on-ok="downloadBaojia"
    >
      <doc-view :docUrl="docUrl" />
    </Modal>
    <Modal
      v-model="checkFailRemarkModal"
      title="审核不通过特别说明"
      @on-ok="checkFailRemarkOk"
    >
      <Input
        v-model="checkFailRemark"
        style="margin-right: 10px"
        type="textarea"
        :rows="6"
      />
    </Modal>
    <Modal v-model="orderLogModal" title="操作日志">
      <div class="orderLogs">
        <table class="tab">
          <thead class="thrd">
            <tr class="tr">
              <th class="th">操作人</th>
              <th class="th">操作名称</th>
              <th class="th">特别说明</th>
            </tr>
          </thead>
          <tbody class="tbody">
            <tr class="tr" v-for="(item, index) in orderLogs" :key="index">
              <td class="td">{{ item.username }}</td>
              <td class="td">{{ item.name }}</td>
              <td class="td">{{ item.specialDesc }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Modal>
  </div>
</template>

<script>
import {
  getOrderListData,
  deleteOrderData,
  getBaojia,
  getFile,
  orderAudit,
  getInsuranceCert,
  getCarTypeBByCartypeA,
  getOrderLogs
} from "@/api/index";
import orderDetail from "./order-detail.vue";
import docView from "./doc-view.vue";
import dictUtil from "@/libs/dictUtil";
export default {
  components: { orderDetail, docView },
  name: "order-manager",
  data () {
    return {
      orderLogModal: false,
      checkFailRemarkModal: false,
      checkFailRemark: '',
      orderStatus: [
        {
          lable: "暂存",
          value: "ORDER_STATUS_ZANCUN"
        },
        {
          lable: "待审核",
          value: "ORDER_STATUS_WAIT_AUDIT"
        },
        {
          lable: "审核不通过",
          value: "ORDER_STATUS_AUDIT_FAILURE"
        },
        {
          lable: "待付款",
          value: "ORDER_STATUS_WAITE_PAY"
        },
        {
          lable: "已生效",
          value: "ORDER_STATUS_FINISH"
        },
        {
          lable: "已过期",
          value: "ORDER_STATUS_INVILADE"
        },
      ],

      downLoadDocUrl: "",
      docUrl: "",
      baojiaTop: "20px",
      baojiaDialog: false,
      openTip: true, // 显示提示
      loading: true, // 表单加载状态
      searchForm: {
        // 搜索框对应data对象
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc", // 默认排序方式
        clientName: "",
        clientCardNo: "",
        clientPhone: "",
      },
      selectList: [], // 多选数据
      selectCount: 0, // 多选计数
      columns: [
        // 表头
        {
          type: "selection",
          width: 60,
          align: "center",
          fixed: "left",
        },

        {
          type: "expand",
          title: "",
          width: 50,
          render: (h, params) => {
            return h(orderDetail, {
              props: { row: params.row },
              // on:    {  click: () => { this.expand(params.row, params.index)} }
            });
          },
        },
        {
          type: "index",
          width: 60,
          align: "center",
        },
        {
          title: "期限类型",
          key: "timeLimitType",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.timeLimitType,
              this.$store.state.dict.timeLimitTypes
            );
            return h("div", dict.title);
          },
        },
        {
          title: "自然月数",
          key: "month",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.month,
              this.$store.state.dict.months
            );
            return h("div", dict.title);
          },
        },
        {
          title: "统筹单号",
          key: "id",
          align: "center",
          width: 300,
          render: (h, params) => {
            return h("div", params.row.order.id);
          },
        },
        {
          title: "统筹单状态",
          key: "orderStatus",
          align: "center",
          width: 120,
          render: (h, params) => {
            if (params.row.order.orderStatus == "ORDER_STATUS_ZANCUN") {
              return h("div", "暂存");
            } else if (
              params.row.order.orderStatus == "ORDER_STATUS_WAIT_AUDIT"
            ) {
              return h("div", "待审核");
            } else if (
              params.row.order.orderStatus == "ORDER_STATUS_AUDIT_FAILURE"
            ) {
              return h("div", "审核不通过");
            } else if (
              params.row.order.orderStatus == "ORDER_STATUS_WAITE_PAY"
            ) {
              return h("div", "待付款");
            } else if (params.row.order.orderStatus == "ORDER_STATUS_FINISH") {
              return h("div", "已生效");
            }
            return h("div", "未知状态");
          },
        },
        {
          title: "被统筹人姓名",
          key: "clientName",
          align: "center",
          width: 200,
          render: (h, params) => {
            return h("div", params.row.order.clientName);
          },
        },
        {
          title: "被统筹人证件号码",
          key: "clientCardNo",
          align: "center",
          width: 200,
          render: (h, params) => {
            return h("div", params.row.order.clientCardNo);
          },
        },

        {
          title: "被统筹人电话",
          key: "clientPhone",
          align: "center",
          width: 200,
          render: (h, params) => {
            return h("div", params.row.order.clientPhone);
          },
        },
        {
          title: "被统筹人类型",
          key: "clientType",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.clientType,
              this.$store.state.dict.clientTypes
            );
            return h("div", dict.title);
          },
        },
        {
          title: "生效时间",
          key: "startDate",
          align: "center",
          width: 300,
          render: (h, params) => {
            return h("div", params.row.order.startDate);
          },
        },
        {
          title: "到期时间",
          key: "endDate",
          align: "center",
          width: 300,
          render: (h, params) => {
            return h("div", params.row.order.endDate);
          },
        },
        {
          title: "出单员",
          key: "createBy",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.createBy);
          },
        },
        {
          title: "初登日期",
          key: "firstLogDate",
          align: "center",
          width: 300,
          render: (h, params) => {
            return h("div", params.row.order.firstLogDate);
          },
        },
        {
          title: "车牌号码",
          key: "carNo",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h(
              "div",
              params.row.order.carNoHead + params.row.order.carNo
            );
          },
        },
        {
          title: "所属性质",
          key: "belongType",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.belongType,
              this.$store.state.dict.belongTypess
            );
            return h("div", dict.title);
          },
        },
        {
          title: "使用性质",
          key: "useType",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.useType,
              this.$store.state.dict.useTypess
            );
            return h("div", dict.title);
          },
        },
        {
          title: "车系分类",
          key: "carSieralType",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.carSieralType,
              this.$store.state.dict.carSieralTypess
            );
            return h("div", dict.title);
          },
        },
        {
          title: "车型名称",
          key: "carTypeName",
          align: "center",
          width: 400,
          render: (h, params) => {
            return h("div", params.row.order.carTypeName);
          },
        },
        {
          title: "车辆种类",
          key: "carTypeA",
          align: "center",
          width: 200,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.carTypeA,
              this.$store.state.dict.carTypeAs
            );
            return h("div", dict.title);
          },
        },
        {
          title: "细分类目",
          key: "carTypeB",
          align: "center",
          width: 200,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.carTypeB,
              this.$store.state.dict.carTypeBs
            );
            return h("div", dict.title);
          },
        },
        {
          title: "行驶证车辆类型",
          key: "runCardCarType",
          align: "center",
          width: 200,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.order.runCardCarType,
              this.$store.state.dict.runCardCarTypes
            );
            return h("div", dict.title);
          },
        },
        {
          title: "发动机号",
          key: "motorNo",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.motorNo);
          },
        },
        {
          title: "车架号",
          key: "carframeNo",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.carframeNo);
          },
        },
        {
          title: "原车价",
          key: "carNewPrice",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.carNewPrice);
          },
        },
        {
          title: "月折旧率",
          key: "monthOldRate",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.monthOldRate);
          },
        },
        {
          title: "折旧后车价",
          key: "monthOldRatePrice",
          align: "center",
          width: 200,
          render: (h, params) => {
            return h("div", params.row.order.monthOldRatePrice);
          },
        },
        {
          title: "协商车价",
          key: "negotiatePrice",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.negotiatePrice);
          },
        },
        {
          title: "统筹总金额",
          key: "totalFee",
          align: "center",
          width: 200,
          render: (h, params) => {
            return h("div", params.row.order.totalFee);
          },
        },
        // {
        //   title: "优惠原因",
        //   key: "feeFloatReason",
        //   align: "center",
        //   width: 100,
        //   tooltip: true,
        //   render: (h, params) => {
        //     return h("div", params.row.order.feeFloatReason);
        //   },
        // },
        {
          title: "优惠系数",
          key: "feeFloatRate",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.feeFloatRate);
          },
        },
        {
          title: "应收金额",
          key: "feeFloat",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.feeFloat);
          },
        },
        {
          title: "渠道折扣",
          key: "discountRate",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.order.discountRate);
          },
        },
        {
          title: "折扣后应收",
          key: "afterDiscountMoney",
          align: "center",
          width: 200,
          render: (h, params) => {
            return h("div", params.row.order.afterDiscountMoney);
          },
        },

        {
          title: "创建时间",
          key: "createTime",
          align: "center",
          width: 300,
          render: (h, params) => {
            return h("div", params.row.order.createTime);
          },
        },
        {
          title: "更新时间",
          key: "updateTime",
          align: "center",
          width: 300,
          render: (h, params) => {
            return h("div", params.row.order.updateTime);
          },
        },
        {
          title: "操作",
          key: "action",
          align: "center",
          fixed: "right",
          width: 250,
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small",
                    icon: "md-eye",

                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'orderEdit'
                    }
                  ],
                  style: {
                    marginRight: "5px",
                  },
                  on: {
                    click: () => {
                      this.edit(params.row);
                    },
                  },
                },
                "查看"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "error",
                    size: "small",
                    icon: "md-trash",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'orderDelete'
                    }
                  ],
                  on: {
                    click: () => {
                      this.remove(params.row);
                    },
                  },
                },
                "删除"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small",
                    icon: "md-eye",
                  },
                  style: {
                    marginRight: "5px",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'orderBaojia'
                    }
                  ],
                  on: {
                    click: () => {
                      this.baojia(params.row);
                    },
                  },
                },
                "报价单"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small",
                    icon: "md-eye",
                  },
                  style: {
                    marginRight: "5px",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'orderTongchou'
                    }
                  ],
                  on: {
                    click: () => {
                      this.insuranceCert(params.row);
                    },
                  },
                },
                "统筹单"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small",
                    icon: "md-eye",
                  },
                  style: {
                    marginRight: "5px",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'orderBaojia'
                    }
                  ],
                  on: {
                    click: () => {
                      this.orderLog(params.row);
                    },
                  },
                },
                "操作记录"
              ),
            ]);
          },
        },
      ],
      data: [], // 表单数据
      total: 0, // 表单数据总数
      carTypeBs: [],
      orderLogs: []
    };
  },
  methods: {
    orderLog (order) {
      //获取操作日志并展示
      this.orderLogs = []
      this.orderLogModal = true
      getOrderLogs({ orderId: order.order.id }).then((res) => {

        if (res.success) {
          this.orderLogs = res.result;
        }
      });
    },
    checkFailRemarkOk () {
      let ids = "";
      this.selectList.forEach(function (e) {
        ids += e.order.id + ",";
      });
      ids = ids.substring(0, ids.length - 1);
      orderAudit({ ids: ids, auditResult: false, result: "不通过", remark: this.checkFailRemark }).then((res) => {
        this.$Modal.remove();
        if (res.success) {
          this.$Message.success("操作成功");
          this.clearSelectAll();
          this.getDataList();
        }
      });
    },
    getDict (carTypeB) {

      let dic = dictUtil.reflectDictData(
        carTypeB,
        this.$store.state.dict.carTypeBs
      );
      return dic.title;
    },
    getCartypeB (carTypeA) {
      this.searchForm.carTypeB = "";
      if (carTypeA == null || carTypeA.length == 0) {
        this.carTypeBs = [];
        return
      }
      getCarTypeBByCartypeA({ carTypeA: carTypeA }).then(res => {
        if (res.success) {
          this.carTypeBs = res.result;
        }
      })
    },
    handleSearch () {
      this.searchForm.pageNumber = 1;
      this.searchForm.pageSize = 10;
      this.getDataList();
    },
    handleReset () {
      this.$refs.searchForm.resetFields();
      this.searchForm.pageNumber = 1;
      this.searchForm.pageSize = 10;
      this.selectDate = null;
      this.searchForm.startDate = "";
      this.searchForm.endDate = "";
      this.selectDep = [];
      this.searchForm.departmentId = "";
      // 重新加载数据
      this.getDataList();
    },
    init () {
      this.getDataList();
    },
    changePage (v) {
      this.searchForm.pageNumber = v;
      this.getDataList();
      this.clearSelectAll();
    },
    changePageSize (v) {
      this.searchForm.pageSize = v;
      this.getDataList();
    },
    changeSort (e) {
      this.searchForm.sort = e.key;
      this.searchForm.order = e.order;
      if (e.order == "normal") {
        this.searchForm.order = "";
      }
      this.getDataList();
    },
    clearSelectAll () {
      this.$refs.table.selectAll(false);
    },
    changeSelect (e) {
      this.selectList = e;
      this.selectCount = e.length;
    },
    getDataList () {
      this.loading = true;
      // 请求后端获取表单数据 请自行修改接口
      getOrderListData(this.searchForm).then((res) => {
        this.loading = false;
        if (res.success) {
          this.data = res.result.content;
          this.total = res.result.totalElements;
        }
      });
    },
    add () {
      // 记录返回路由
      let query = { backRoute: this.$route.name };
      this.$router.push({
        // 该路由已在/router/router.js中定义好
        name: "orderadd",
        query: query,
      });
    },
    edit (v) {
      // 记录返回路由
      let query = { order: v, backRoute: this.$route.name };
      console.log(v);
      this.$router.push({
        // 该路由已在/router/router.js中定义好 携带id参数
        name: "orderedit",
        query: query,
      });
    },
    baojia (v) {
      //报价单页面
      getBaojia({ orderId: v.order.id }).then((res) => {

        if (res.success) {
          let files = res.result.split(",");
          this.docUrl = files[1];
          this.downLoadDocUrl = files[0];
          this.baojiaDialog = true;
        }

      });
    },
    insuranceCert (v) {
      getInsuranceCert({ orderId: v.order.id }).then((res) => {
        if (res.success) {
          let files = res.result.split(",");
          this.docUrl = files[1];
          this.downLoadDocUrl = files[0];
          this.baojiaDialog = true;
        }

      });
    },
    remove (v) {
      this.$Modal.confirm({
        title: "确认删除",
        // 记得确认修改此处
        content: "您确认要删除 " + v.order.id + " ?",
        loading: true,
        onOk: () => {
          // 删除
          deleteOrderData({ ids: v.order.id }).then((res) => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.getDataList();
            }
          });
        },
      });
    },
    auditSuccess () {
      if (this.selectCount <= 0) {
        this.$Message.warning("您还未选择要审核的数据");
        return;
      }
      this.$Modal.confirm({
        title: "确认删除",
        content: "您确认要审核通过所选的 " + this.selectCount + " 条数据?",
        loading: true,
        onOk: () => {
          let ids = "";
          this.selectList.forEach(function (e) {
            ids += e.order.id + ",";
          });
          ids = ids.substring(0, ids.length - 1);
          orderAudit({ ids: ids, auditResult: true, result: "通过" }).then((res) => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.clearSelectAll();
              this.getDataList();
            }
          });
        },
      });

    },
    auditFailure () {
      if (this.selectCount <= 0) {
        this.$Message.warning("您还未选择要审核的数据");
        return;
      }
      this.checkFailRemarkModal = true;
    },
    delAll () {
      if (this.selectCount <= 0) {
        this.$Message.warning("您还未选择要删除的数据");
        return;
      }
      this.$Modal.confirm({
        title: "确认删除",
        content: "您确认要删除所选的 " + this.selectCount + " 条数据?",
        loading: true,
        onOk: () => {
          let ids = "";
          this.selectList.forEach(function (e) {
            ids += e.order.id + ",";
          });
          ids = ids.substring(0, ids.length - 1);
          // 批量删除
          deleteOrderData({ ids: ids }).then((res) => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.clearSelectAll();
              this.getDataList();
            }
          });
        },
      });
    },
    downloadBaojia () {
      var that = this;
      getFile(this.downLoadDocUrl).then((res) => {
        that.downloadFile(res);
      });
    },
    downloadFile (data) {
      // 文件导出
      if (!data) {
        return;
      }
      let url = window.URL.createObjectURL(new Blob([data]));
      let link = document.createElement("a");
      link.style.display = "none";
      link.href = url;
      link.setAttribute("download", this.docUrl);

      document.body.appendChild(link);
      link.click();
    },
  },
  mounted () {
    this.init();
  },
};
</script>