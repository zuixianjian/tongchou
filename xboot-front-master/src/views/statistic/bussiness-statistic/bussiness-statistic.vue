<style lang="less">
.colContent {
  display: flex;
  //   justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
  margin-bottom: 10px;
}
</style>
<template>
  <div class="search">
    <Card>
      <Row type="flex" justify="space-between">
        <Col v-if="expand" span="4">
          <Row type="flex" class="colContent">
            <i-switch v-model="strict" size="large" style="margin-left: 5px">
              <span slot="open">级联</span>
              <span slot="close">单选</span>
            </i-switch>
            <Button @click="getParentList" icon="md-refresh">刷新</Button>
          </Row>
          <Row type="flex">
            <Alert show-icon style="width: 100%">
              当前选择：
              <span class="select-title">{{ editTitle }}</span>
              <a class="select-clear" v-if="form.id" @click="cancelEdit"
                >取消选择</a
              >
            </Alert>
            <Input
              v-model="searchKey"
              suffix="ios-search"
              @on-change="search"
              placeholder="输入部门名搜索"
              clearable
            />
            <div class="tree-bar" :style="{ maxHeight: maxHeight }">
              <Tree
                ref="tree"
                :data="data"
                :load-data="loadData"
                show-checkbox
                @on-check-change="changeSelect"
                @on-select-change="selectTree"
                :check-strictly="!strict"
              ></Tree>
              <Spin size="large" fix v-if="treeLoading"></Spin>
            </div>
          </Row>
        </Col>

        <Col :span="span">
          <Row @keydown.enter.native="handleSearch">
            <Form ref="searchForm" :model="searchForm" inline :label-width="70">
              <Col v-show="openSearch">
                <!-- <Form-item label="收款方式" prop="recieveType">
                  <Select
                    v-model="searchForm.recieveType"
                    placeholder="请选择"
                    clearable
                    style="width: 200px"
                  >
                    <Option
                      v-for="(item, index) in this.$store.state.dict
                        .recieveTypes"
                      :value="item.value"
                      :key="index"
                      >{{ item.title }}</Option
                    >
                  </Select>
                </Form-item>
                <Form-item label="业务类型" prop="bussinessType">
                  <Select
                    v-model="searchForm.bussinessType"
                    placeholder="请选择"
                    clearable
                    style="width: 200px"
                  >
                    <Option
                      v-for="(item, index) in bussinessTypes"
                      :value="item.value"
                      :key="index"
                      >{{ item.title }}</Option
                    >
                  </Select>
                </Form-item> -->
                <Form-item label="统计分类" prop="statisticType">
                  <Select
                    v-model="searchForm.statisticType"
                    placeholder="请选择"
                    clearable
                    style="width: 200px"
                  >
                    <Option
                      v-for="(item, index) in statisticTypes"
                      :value="item.value"
                      :key="index"
                      >{{ item.title }}</Option
                    >
                  </Select>
                </Form-item>
                <Form-item label="收款时间">
                  <DatePicker
                    v-model="selectDate"
                    type="datetimerange"
                    format="yyyy-MM-dd HH:mm"
                    clearable
                    @on-change="selectDateRange"
                    placeholder="选择起始时间"
                    style="width: 300px"
                  ></DatePicker>
                </Form-item>
                <Form-item style="margin-left: -35px" class="br">
                  <Button @click="handleSearch" type="primary" icon="ios-search"
                    >统计</Button
                  >
                  <Button @click="handleReset">重置</Button>
                </Form-item>
              </Col>
              <Col>
                <Form-item style="margin-left: -35px" class="br">
                  <Button @click="getDataList" icon="md-refresh"
                    >刷新数据</Button
                  >
                  <Button type="dashed" @click="openSearch = !openSearch">{{
                    openSearch ? "关闭搜索" : "开启搜索"
                  }}</Button>
                  <Button type="dashed" @click="openTip = !openTip">{{
                    openTip ? "关闭提示" : "开启提示"
                  }}</Button>
                </Form-item>
              </Col>
            </Form>
          </Row>
          <Row class="operation" style="margin-bottom: 10px"> </Row>
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
              :data="statsticDatas"
              sortable="custom"
              @on-sort-change="changeSort"
              @on-selection-change="showSelect"
              ref="table"
            ></Table>
          </Row>
          <!-- <Row type="flex" justify="end" class="page">
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
          </Row> -->
        </Col>
      </Row>
    </Card>
  </div>
</template>

<script>
import {
  initDepartment,
  loadDepartment,
  getStatisticOrders,
  getStatisticBussiness
} from "@/api/index";
import dictUtil from "@/libs/dictUtil";
export default {
  name: "bussiness-statistic",
  data () {
    return {
      strict: true,
      bussinessTypes: [{
        value: "ORDER_TYPE_COMMON",
        title: "统筹单"
      },
      {
        value: "ORDER_TYPE_EDIT",
        title: "批单"
      },
      {
        value: "ORDER_TYPE_HELP",
        title: "互助单",
      },
      ],
      statisticTypes: [{
        value: "timeDay",
        title: "按天统计"
      },
      {
        value: "timeMonth",
        title: "按月统计"
      },
      {
        value: "timeYear",
        title: "按年统计",
      },
      {
        value: "departMent",
        title: "按部门统计",
      }
      ],
      openSearch: true,
      openTip: true,
      treeLoading: false, // 树加载状态
      maxHeight: "500px",
      loading: false, // 表格加载状态
      editTitle: "", // 编辑节点名称
      searchKey: "", // 搜索树
      expand: true,
      span: 19,
      expandIcon: "ios-arrow-back",
      selectNode: {},
      treeData: [], // 树数据
      selectCount: 0, // 多选计数
      selectList: [], // 多选数据
      searchForm: {


      },
      modalType: 0, // 添加或编辑标识
      modalVisible: false, // 添加或编辑显示
      dictModalVisible: false,
      dictModalTitle: "",
      modalTitle: "", // 添加或编辑标题
      columns: [
        // 表头
        {
          type: "index",
          width: 60,
          align: "center",
        },
        {
          title: "统计分类",
          key: "dateFlag",
          width: 200,

        },
        {
          title: "参统车辆数",
          key: "carCount",
          minWidth: 120,

          align: 'center'
        },
        {
          title: "应收净费",
          key: "shouldCleanRecieveTotal",
          minWidth: 120,
          sortable: true,
          align: 'center'
        },
        {
          title: "应收全款",
          key: "shouldRecieveTotal",
          minWidth: 120,
          sortable: true,
          align: 'center'
        },
        {
          title: "实收款项",
          key: "realRecieveTotal",
          minWidth: 120,
          sortable: true,
          align: 'center'
        },

        {
          title: "事件登记数",
          key: "accidentLogCount",
          minWidth: 120,
          align: 'center'
        },
        {
          title: "事件结案数量",
          key: "accidentFinishCount",
          minWidth: 120,
          align: 'center'
        },
        {
          title: "总损失金额",
          key: "damageTotalMoney",
          minWidth: 120,
          align: 'center'
        },




      ],
      submitLoading: false, // 添加或编辑提交状态
      data: [], //表单数据
      total: 0, // 表单数据总数
      carTypeBs: [],
      selectDate: null,
      form: {},
      statsticDatas: []
    };
  },
  methods: {
    selectDateRange (v) {
      if (v) {
        this.searchForm.startDate = v[0];
        this.searchForm.endDate = v[1];
      }
    },
    getDict (carTypeB) {

      let dic = dictUtil.reflectDictData(
        carTypeB,
        this.$store.state.dict.carTypeBs
      );
      return dic.title;
    },
    init () {
      this.getParentList();
    },
    getParentList () {
      this.treeLoading = true;
      initDepartment().then(res => {
        this.treeLoading = false;
        if (res.success) {
          res.result.forEach(function (e) {
            if (e.isParent) {
              e.loading = false;
              e.children = [];
              e._loading = false;
            }
          });
          this.data = res.result;
        }
      });
    },

    loadData (item, callback) {
      loadDepartment(item.id).then(res => {
        if (res.success) {
          res.result.forEach(function (e) {
            if (e.isParent) {
              e.loading = false;
              e.children = [];
              e._loading = false;
            }
          });
          callback(res.result);
        }
      });
    },
    search () {
      if (this.searchKey) {
        this.treeLoading = true;
        searchDepartment({ title: this.searchKey }).then(res => {
          this.treeLoading = false;
          if (res.success) {
            this.data = res.result;
          }
        });
      } else {
        this.getParentList();
      }
    },
    selectTree (v) {
      if (v.length > 0) {
        // 转换null为""
        for (let attr in v[0]) {
          if (v[0][attr] == null) {
            v[0][attr] = "";
          }
        }
        let str = JSON.stringify(v[0]);
        let data = JSON.parse(str);
        this.selectNode = data;
        this.editTitle = data.title;
        // 重新加载表
        this.getDataList();
      } else {
        this.cancelEdit();
      }
    },
    getDataList () {
      this.searchForm.departmentIds = null
      this.loading = true;
      if (this.selectList != null && this.selectList.length > 0) {
        let ids = "";
        this.selectList.forEach(function (e) {
          ids += e.id + ",";
        });
        ids = ids.substring(0, ids.length - 1);
        this.searchForm.departmentIds = ids
      }

      if (this.selectNode.id) {
        this.searchForm.departmentIds = this.selectNode.id;
      }
      getStatisticBussiness(this.searchForm).then((res) => {
        this.loading = false;
        if (res.success) {
          this.statsticDatas = res.result.statisticBussinessItemResps;
          if (res.result.carCount != null) {
            this.statsticDatas.push({
              dateFlag: "合计",
              carCount: res.result.carCount,
              shouldCleanRecieveTotal: res.result.shouldCleanRecieveTotal,
              shouldRecieveTotal: res.result.shouldRecieveTotal,
              realRecieveTotal: res.result.realRecieveTotal,
              accidentLogCount: res.result.accidentLogCount,
              accidentFinishCount: res.result.accidentFinishCount,
              damageTotalMoney: res.result.damageTotalMoney,

            })
          }
          //   this.total = res.result.totalElements;
        }
      });
    },

    cancelEdit () {
      let data = this.$refs.tree.getSelectedNodes()[0];
      if (data) {
        data.selected = false;
      }
      // 取消选择后获取全部数据
      this.selectNode = {};
      this.editTitle = "";
      this.getDataList();
    },
    changeSelect (v) {
      this.selectCount = v.length;
      this.selectList = v;
    },
    changeSort (e) {
      this.searchForm.sort = e.key;
      this.searchForm.order = e.order;
      if (e.order == "normal") {
        this.searchForm.order = "";
      }
      this.getDataList();
    },


    handleSearch () {

      this.getDataList();
    },
    handleReset () {
      this.$refs.searchForm.resetFields();
      // 重新加载数据
      this.getDataList();
    },

    showSelect (e) {
      this.selectList = e;
      this.selectCount = e.length;
    },
    clearSelectAll () {
      this.$refs.table.selectAll(false);
    },
    refreshDict () {
      this.getParentList();
      this.selectNode = {};
      this.editTitle = "";
      this.selectList = [];
      this.getDataList();
    },


  },

  mounted () {
    // 计算高度
    let height = document.documentElement.clientHeight;
    this.maxHeight = Number(height - 287) + "px";
    this.init();
  },

};
</script>