<style lang="less">
@import "../../../styles/table-common.less";
</style>
<template>
  <div class="search">
    <help-order-add
      v-if="currView == 'helpOrderAdd'"
      @close="currView = 'index'"
      @submited="submited"
    />
    <help-order-edit
      v-if="currView == 'helpOrderEdit'"
      @close="currView = 'index'"
      @submited="submited"
      :helpOrder="formData"
    />
    <help-order-diaocha
      v-if="currView == 'helpOrderDiaocha'"
      @close="currView = 'index'"
      @submited="submited"
      :helpOrder="formData"
    />
    <help-order-shenhe
      v-if="currView == 'helpOrderShenhe'"
      @close="currView = 'index'"
      @submited="submited"
      :helpOrder="formData"
    />
    <Card v-show="currView == 'index'">
      <Row class="operation">
        <Button
          @click="add"
          type="primary"
          icon="md-add"
          v-has="'helpOrderCommit'"
          >案件受理</Button
        >
        <Button @click="delAll" icon="md-trash" v-has="'helpOrderDelete'"
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
  </div>
</template>

<script>
import {
  getHelperOrderListData,
  addHelperOrderData,
  deleteHelperOrderData,
  editHelperOrderData,
} from "@/api/index";
import helpOrderAdd from "./helpOrderAdd.vue";
import helpOrderEdit from "./helpOrderEdt.vue";
import helpOrderDiaocha from "./helpOrderDiaocha.vue";
import helpOrderShenhe from "./helpOrderShenhe.vue";
export default {
  name: "help-order-manager",
  components: {
    helpOrderAdd,
    helpOrderEdit,
    helpOrderDiaocha,
    helpOrderShenhe
  },
  data () {
    return {
      openTip: true, // 显示提示
      formData: {},
      currView: "index",
      loading: true, // 表单加载状态
      searchForm: {
        // 搜索框对应data对象
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc" // 默认排序方式
      },
      selectList: [], // 多选数据
      selectCount: 0, // 多选计数
      columns: [
        // 表头
        {
          type: "selection",
          fixed: "left",
          width: 60,
          align: "center"
        },
        {
          type: "index",
          fixed: "left",
          width: 60,
          align: "center"
        },
        {
          align: "center",
          title: "互助单号",
          key: "id",
          width: 200,
        },
        {
          align: "center",
          title: "统筹单号",
          key: "orderId",
          width: 200,
        },
        {
          title: "互助单状态",
          key: "status",
          align: "center",
          width: 100,
          render: (h, params) => {
            if (params.row.status == 'HELPORDER_STATUS_ZANCUN') {
              return h("div", "暂存");
            } else if (params.row.status == 'HELPORDER_STATUS_DIAOCHA') {
              return h("div", "调查中");
            } else if (params.row.status == 'HELPORDER_STATUS_CHECK') {
              return h("div", "审核中");
            } else if (params.row.status == 'HELPORDER_STATUS_CHECK_FAIL') {
              return h("div", "审核不通过");
            } else if (params.row.status == 'HELPORDER_STATUS_WAITFORPAY') {
              return h("div", "待结案");
            } else if (params.row.status == 'HELPORDER_STATUS_FINISH') {
              return h("div", "已结案");
            }

          },
        },
        {
          align: "center",
          title: "被统筹人",
          key: "clientName",
          width: 100,
        },
        {
          align: "center",
          title: "车型名称",
          key: "carTypeName",
          width: 100,
        },
        {
          title: "车牌号",
          key: "carNo",
          align: "center",
          width: 100,
          render: (h, params) => {
            return h("div", params.row.carNoHead + params.row.carNo);
          },
        },
        {
          align: "center",
          title: "统筹开始时间",
          key: "startDate",
          width: 200,
        },
        {
          align: "center",
          title: "统筹结束时间",
          key: "endDate",
          width: 200,
        },
        {
          align: "center",
          title: "车架号",
          key: "carframeNo",
          width: 100,
        },
        {
          align: "center",
          title: "报案人",
          key: "reportName",
          width: 100,
        },
        {
          align: "center",
          title: "报案电话",
          key: "reportTel",
          width: 100,
        },
        {
          align: "center",
          title: "事故类型",
          key: "accidentType",
          width: 100,
        },
        {
          align: "center",
          title: "事故时间",
          key: "accidentTime",
          width: 100,
        },
        {
          align: "center",
          title: "三者车牌",
          key: "thirdCarNo",
          width: 100,
        },
        {
          align: "center",
          title: "三者车型",
          key: "thirdCarType",
          width: 100,
        },
        {
          align: "center",
          title: "三者电话",
          key: "thirdPhone",
          width: 100,
        },
        {
          align: "center",
          title: "事故地点",
          key: "accidentPlace",
          width: 100,
        },
        {
          align: "center",
          title: "调查员",
          key: "diaochaUserName",
          width: 100,
        },
        {
          title: "创建时间",
          align: "center",
          key: "createTime",
          width: 200,
          sortable: true,
          sortType: "desc"
        },
        {
          title: "更新时间",
          align: "center",
          key: "updateTime",
          width: 200,
          sortable: true
        },
        {
          title: "操作",
          key: "action",
          align: "center",
          fixed: "right",
          width: 200,
          render: (h, params) => {
            if (params.row.status == 'HELPORDER_STATUS_DIAOCHA') {
              //调查中
              return h("div", [
                h(
                  "Button",
                  {
                    props: {
                      type: "error",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderDelete'
                      }
                    ],
                    on: {
                      click: () => {
                        this.remove(params.row);
                      }
                    }
                  },
                  "删除"
                ),
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderDiaocha'
                      }
                    ],
                    on: {
                      click: () => {
                        this.opendiaocha(params.row);
                      }
                    }
                  },
                  "调查"
                )
              ]);
            } else if (params.row.status == 'HELPORDER_STATUS_CHECK') {
              return h("div", [

                h(
                  "Button",
                  {
                    props: {
                      type: "error",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderDelete'
                      }
                    ],
                    on: {
                      click: () => {
                        this.remove(params.row);
                      }
                    }
                  },
                  "删除"
                ),

                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderCheck'
                      }
                    ],
                    on: {
                      click: () => {
                        this.openShenhe(params.row);
                      }
                    }
                  },
                  "审核"
                )
              ]);
            } else if (params.row.status == 'HELPORDER_STATUS_CHECK_FAIL') {
              return h("div", [

                h(
                  "Button",
                  {
                    props: {
                      type: "error",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderDelete'
                      }
                    ],
                    on: {
                      click: () => {
                        this.remove(params.row);
                      }
                    }
                  },
                  "删除"
                ),
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderDiaocha'
                      }
                    ],
                    on: {
                      click: () => {
                        this.opendiaocha(params.row);
                      }
                    }
                  },
                  "调查"
                )
              ]);
            } else if (params.row.status == 'HELPORDER_STATUS_ZANCUN') {
              return h("div", [
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small",
                      icon: "ios-create-outline"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helperOrderEdit'
                      }
                    ],
                    on: {
                      click: () => {
                        this.edit(params.row);
                      }
                    }
                  },
                  "编辑"
                ),
                h(
                  "Button",
                  {
                    props: {
                      type: "error",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderDelete'
                      }
                    ],
                    on: {
                      click: () => {
                        this.remove(params.row);
                      }
                    }
                  },
                  "删除"
                ),

              ]);
            } else {
              return h("div", [
                h(
                  "Button",
                  {
                    props: {
                      type: "error",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'helpOrderDelete'
                      }
                    ],
                    on: {
                      click: () => {
                        this.remove(params.row);
                      }
                    }
                  },
                  "删除"
                ),
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small",
                      icon: "md-trash"
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'help-order-manager-view'
                      }
                    ],
                    on: {
                      click: () => {
                        this.opendiaocha(params.row);
                      }
                    }
                  },
                  "查看"
                )
              ]);
            }

          }
        }
      ],
      data: [], // 表单数据
      total: 0 // 表单数据总数
    };
  },
  methods: {
    init () {
      this.getDataList();
    },
    submited () {
      this.currView = "index";
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
      getHelperOrderListData(this.searchForm).then(res => {
        this.loading = false;
        if (res.success) {
          this.data = res.result.content;
          this.total = res.result.totalElements;
        }
      });

    },
    add () {
      this.currView = "helpOrderAdd";
    },
    edit (v) {
      // 转换null为""
      for (let attr in v) {
        if (v[attr] == null) {
          v[attr] = "";
        }
      }
      let str = JSON.stringify(v);
      let data = JSON.parse(str);
      this.formData = data;
      this.currView = "helpOrderEdit";
    },
    opendiaocha (v) {
      // 转换null为""
      for (let attr in v) {
        if (v[attr] == null) {
          v[attr] = "";
        }
      }
      let str = JSON.stringify(v);
      let data = JSON.parse(str);
      this.formData = data;
      this.currView = "helpOrderDiaocha";
    },
    openShenhe (v) {
      // 转换null为""
      for (let attr in v) {
        if (v[attr] == null) {
          v[attr] = "";
        }
      }
      let str = JSON.stringify(v);
      let data = JSON.parse(str);
      this.formData = data;
      this.currView = "helpOrderShenhe";
    },
    remove (v) {
      this.$Modal.confirm({
        title: "确认删除",
        // 记得确认修改此处
        content: "您确认要删除 " + v.id + " ?",
        loading: true,
        onOk: () => {
          // 删除
          deleteHelperOrderData({ ids: v.id }).then(res => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.getDataList();
            }
          });

        }
      });
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
            ids += e.id + ",";
          });
          ids = ids.substring(0, ids.length - 1);
          // 批量删除
          deleteHelperOrderData({ ids: ids }).then(res => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.clearSelectAll();
              this.getDataList();
            }
          });

        }
      });
    }
  },
  mounted () {
    this.init();
  }
};
</script>