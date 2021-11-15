<style lang="less">
@import "../../../styles/tree&table-common.less";
@import "./feeRateManager.less";
</style>
<template>
  <div class="search">
    <Card>
      <Row type="flex" justify="space-between">
        <Col v-if="expand" span="5">
          <Row class="operation">
            <Button @click="getTypeDict" type="primary" icon="md-refresh"
              >刷新</Button
            >
          </Row>
          <Alert show-icon>
            当前选择：
            <span class="select-title">{{ editTitle }}</span>
            <a class="select-clear" v-if="editTitle" @click="cancelEdit"
              >取消选择</a
            >
          </Alert>
          <div class="tree-bar" :style="{ maxHeight: maxHeight }">
            <Tree
              ref="tree"
              :data="treeData"
              @on-select-change="selectTree"
            ></Tree>
            <Spin size="large" fix v-if="treeLoading"></Spin>
          </div>
        </Col>

        <Col :span="span">
          <Row v-show="openSearch" @keydown.enter.native="handleSearch">
            <Form ref="searchForm" :model="searchForm" inline :label-width="70">
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
                    v-for="(item, index) in this.$store.state.dict
                      .carSieralTypess"
                    :value="item.value"
                    :key="index"
                    >{{ item.title }}</Option
                  >
                </Select>
              </Form-item>
              <Form-item label="年限" prop="yearType">
                <Select
                  v-model="searchForm.yearType"
                  placeholder="请选择"
                  clearable
                  style="width: 200px"
                >
                  <Option
                    v-for="(item, index) in this.$store.state.dict.yearTypess"
                    :value="item.value"
                    :key="index"
                    >{{ item.title }}</Option
                  >
                </Select>
              </Form-item>
              <Form-item label="费率组" prop="regionGroup">
                <Select
                  v-model="searchForm.regionGroup"
                  placeholder="请选择"
                  clearable
                  style="width: 200px"
                >
                  <Option
                    v-for="(item, index) in this.$store.state.dict
                      .regionGroupss"
                    :value="item.value"
                    :key="index"
                    >{{ item.title }}</Option
                  >
                </Select>
              </Form-item>
              <Form-item style="margin-left: -35px" class="br">
                <Button
                  @click="handleSearch"
                  type="primary"
                  icon="ios-search"
                  v-has="'fee-rate-manager-search'"
                  >搜索</Button
                >
                <Button @click="handleReset">重置</Button>
              </Form-item>
            </Form>
          </Row>
          <Row class="operation">
            <Button
              @click="add"
              type="primary"
              icon="md-add"
              v-has="'fee-rate-manager-add'"
              >添加数据</Button
            >
            <Button
              @click="delAll"
              icon="md-trash"
              v-has="'fee-rate-manager-delete'"
              >批量删除</Button
            >
            <Button
              @click="getDataList"
              icon="md-refresh"
              v-has="'fee-rate-manager-search'"
              >刷新数据</Button
            >
            <Button type="dashed" @click="openSearch = !openSearch">{{
              openSearch ? "关闭搜索" : "开启搜索"
            }}</Button>
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
              sortable="custom"
              @on-sort-change="changeSort"
              @on-selection-change="showSelect"
              ref="table"
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
        </Col>
      </Row>
    </Card>

    <Modal
      :title="modalTitle"
      v-model="modalVisible"
      :mask-closable="false"
      :width="500"
    >
      <Form ref="form" :model="form" :label-width="80" :rules="formValidate">
        <FormItem label="车系分类" prop="carSieralType">
          <Select v-model="form.carSieralType" placeholder="请选择">
            <Option
              v-for="(item, index) in this.$store.state.dict.carSieralTypess"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="所属性质" prop="belongType">
          <Select v-model="form.belongType" placeholder="请选择">
            <Option
              v-for="(item, index) in this.$store.state.dict.belongTypess"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="车辆种类" prop="carTypeA">
          <Select
            v-model="form.carTypeA"
            placeholder="请选择"
            @on-change="gerCarTypeB1(form.carTypeA)"
          >
            <Option
              v-for="(item, index) in this.$store.state.dict.carTypeAs"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="细分类目" prop="carTypeB">
          <Select v-model="form.carTypeB" placeholder="请选择">
            <Option
              v-for="(item, index) in carTypeBs"
              :value="item.carTypeB"
              :key="index"
              >{{ getDict(item.carTypeB) }}</Option
            >
          </Select>
        </FormItem>

        <FormItem label="使用性质" prop="useType">
          <Select v-model="form.useType" placeholder="请选择">
            <Option
              v-for="(item, index) in this.$store.state.dict.useTypess"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>

        <FormItem label="费率组" prop="regionGroup">
          <Select v-model="form.regionGroup" placeholder="请选择">
            <Option
              v-for="(item, index) in this.$store.state.dict.regionGroupss"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="年限" prop="yearType">
          <Tooltip
            trigger="hover"
            placement="top"
            content="车辆损失统筹此项必填，其他统筹不要填写此项"
            style="width: 380px"
            max-width="600"
          >
            <Select v-model="form.yearType" placeholder="请选择">
              <Option
                v-for="(item, index) in this.$store.state.dict.yearTypess"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Tooltip>
        </FormItem>
        <FormItem label="基础保费" prop="baseFee">
          <Tooltip
            trigger="hover"
            placement="top"
            max-width="600"
            content="机动车损失统筹和第三者责任统筹此项必填，其他统筹不要填写此项"
            style="width: 380px"
          >
            <InputNumber
              :max="100000"
              :min="0"
              v-model="form.baseFee"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>
        <FormItem label="费率" prop="feeRate">
          <Tooltip
            trigger="hover"
            placement="top"
            max-width="600"
            content="第三者责任统筹不要填写此项,其他统筹必填此项"
            style="width: 380px"
          >
            <InputNumber
              :max="100000"
              :min="0"
              v-model="form.feeRate"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>

        <FormItem label="保额" prop="insuranceMoney">
          <Tooltip
            trigger="hover"
            placement="top"
            content="第三者责任统筹此项必填，其他统筹不要填写此项"
            max-width="600"
            style="width: 380px"
          >
            <Select v-model="form.insuranceMoney" placeholder="请选择">
              <Option
                v-for="(item, index) in this.$store.state.dict.insuranceMoneys"
                :value="item.value"
                :key="index"
                >{{ item.title }}</Option
              >
            </Select>
          </Tooltip>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="modalVisible = false">取消</Button>
        <Button
          type="primary"
          :loading="submitLoading"
          @click="handelSubmit"
          v-has="'fee-rate-manager-edit-commit'"
          >提交</Button
        >
      </div>
    </Modal>
  </div>
</template>

<script>
import {
  deleteFeeRateListData,
  editFeeRateListData,
  getDictDataByType,
  getFeeRateListData,
  addFeeRateListData,
  getCarTypeBByCartypeA,
} from "@/api/index";
import dictUtil from "@/libs/dictUtil";
export default {
  name: "fee-rate-manager",
  data () {
    return {
      openSearch: true,
      openTip: true,
      treeLoading: false, // 树加载状态
      maxHeight: "500px",
      loading: false, // 表格加载状态
      editTitle: "", // 编辑节点名称
      searchKey: "", // 搜索树
      expand: true,
      span: 18,
      expandIcon: "ios-arrow-back",
      selectNode: {},
      treeData: [], // 树数据
      selectCount: 0, // 多选计数
      selectList: [], // 多选数据
      searchForm: {
        // 搜索框对应data对象
        carTypeA: "",
        carTypeB: "",
        belongType: "",
        useType: "",
        carSieralType: "",
        yearType: "",
        regionGroup: "",

        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc", // 默认排序方式
      },
      modalType: 0, // 添加或编辑标识
      modalVisible: false, // 添加或编辑显示
      dictModalVisible: false,
      dictModalTitle: "",
      modalTitle: "", // 添加或编辑标题

      form: {
        // 添加或编辑表单对象初始化数据
        carTypeA: "",
        carTypeB: "",
        belongType: "",
        useType: "",
        carSieralType: "",
        yearType: "",
        feeRate: 0,
        regionGroup: "",
      },

      formValidate: {
        // 表单验证规则
        carTypeA: [
          { required: true, message: "车辆种类不能为空", trigger: "change" },
        ],
        carTypeB: [
          { required: true, message: "细分类目不能为空", trigger: "change" },
        ],
        // belongType: [
        //   { required: true, message: "所属性质不能为空", trigger: "change" },
        // ],
        useType: [
          { required: true, message: "使用性质不能为空", trigger: "change" },
        ],
        // carSieralType: [
        //   { required: true, message: "车系分类不能为空", trigger: "change" },
        // ],
        regionGroup: [
          { required: true, message: "费率组不能为空", trigger: "change" },
        ],
      },
      columns: [
        // 表头
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        {
          type: "index",
          width: 60,
          align: "center",
        },
        {
          title: "车辆种类",
          key: "carTypeA",
          align: "center",
          width: 200,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.carTypeA,
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
              params.row.carTypeB,
              this.$store.state.dict.carTypeBs
            );
            return h("div", dict.title);
          },
        },
        {
          title: "所属性质",
          key: "belongType",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.belongType,
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
              params.row.useType,
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
              params.row.carSieralType,
              this.$store.state.dict.carSieralTypess
            );
            return h("div", dict.title);
          },
        },
        {
          title: "年限",
          key: "yearType",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.yearType,
              this.$store.state.dict.yearTypess
            );
            return h("div", dict.title);
          },
        },
        {
          title: "费率组",
          key: "regionGroup",
          align: "center",
          width: 100,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.regionGroup,
              this.$store.state.dict.regionGroupss
            );
            return h("div", dict.title);
          },
        },
        {
          title: "基础保费",
          key: "baseFee",
          minWidth: 120,
          sortable: true,
          align: 'center'
        },
        {
          title: "费率",
          key: "feeRate",
          width: 120,
          sortable: true,
          align: 'center'
        },
        {
          title: "保额(万)",
          key: "insuranceMoney",
          width: 120,
          sortable: true,
          align: 'center'
        },

        {
          title: "创建时间",
          key: "createTime",
          width: 200,
          sortable: true,
          align: 'center'
        },
        {
          title: "费率类型",
          key: "feeType",
          align: "center",
          width: 200,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.feeType,
              this.treeData
            );
            return h("div", dict.title);
          },
        },
        {
          title: "操作",
          key: "action",
          width: 160,
          align: "center",
          fixed: "right",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small",
                  },
                  style: {
                    marginRight: "5px",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'fee-rate-manager-edit'
                    }
                  ],
                  on: {
                    click: () => {
                      this.edit(params.row);
                    },
                  },
                },
                "编辑"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "error",
                    size: "small",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'fee-rate-manager-delete'
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
            ]);
          },
        },
      ],
      submitLoading: false, // 添加或编辑提交状态
      data: [], //表单数据
      total: 0, // 表单数据总数
      carTypeBs: []

    };
  },
  methods: {
    getDict (carTypeB) {

      let dic = dictUtil.reflectDictData(
        carTypeB,
        this.$store.state.dict.carTypeBs
      );
      return dic.title;
    },
    init () {
      this.carTypeBs = [];
      // 获取树数据
      this.getTypeDict();
      // 获取表单数据
      this.getDataList();
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
    gerCarTypeB1 (carTypeA) {
      this.form.carTypeB = "";
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
    getTypeDict () {
      this.loading = true;
      getDictDataByType("feeType").then((res) => {
        this.loading = false;
        if (res.success) {
          this.treeData = res.result;
        }
      });
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
        this.editTitle = data.title + "(" + data.value + ")";
        // 重新加载表
        this.searchForm.pageNumber = 1;
        this.searchForm.pageSize = 10;
        this.getDataList();
      } else {
        this.cancelEdit();
      }
    },
    getDataList () {
      this.loading = true;
      if (this.selectNode.value) {
        this.searchForm.feeType = this.selectNode.value;
      } else {
        delete this.searchForm.feeType;
      }
      getFeeRateListData(this.searchForm).then((res) => {
        this.loading = false;
        if (res.success) {
          this.data = res.result.content;
          this.total = res.result.totalElements;
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
    changePage (v) {
      this.searchForm.pageNumber = v;
      this.getDataList();
      this.clearSelectAll();
    },
    changePageSize (v) {
      this.searchForm.pageSize = v;
      this.getDataList();
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
      this.getAllDict();
      this.selectNode = {};
      this.editTitle = "";
      this.getDataList();
    },

    add () {
      if (!this.selectNode.id) {
        this.$Message.warning("请先选择一个统筹类别");
        return;
      }
      this.modalType = 0;
      this.modalTitle = "添加统筹 " + this.editTitle + " 的数据";
      this.$refs.form.resetFields();
      this.modalVisible = true;
    },
    edit (v) {
      this.modalType = 1;
      this.modalTitle = "编辑费率配置 " + this.editTitle + " 的数据";
      this.$refs.form.resetFields();
      // 转换null为""
      for (let attr in v) {
        if (v[attr] == null) {
          v[attr] = "";
        }
      }
      let str = JSON.stringify(v);
      let data = JSON.parse(str);
      this.form = data;
      if (this.form.insuranceMoney != null) {
        this.form.insuranceMoney = this.form.insuranceMoney + ""
      }
      this.modalVisible = true;
    },

    handelSubmit () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.submitLoading = true;
          if (this.modalType == 0) {
            // 添加 避免编辑后传入id等数据 记得删除
            delete this.form.id;
            this.form.feeType = this.selectNode.value;
            addFeeRateListData(this.form).then((res) => {
              this.submitLoading = false;
              if (res.success) {
                this.$Message.success("操作成功");
                this.getDataList();
                this.modalVisible = false;
              }
            });
          } else if (this.modalType == 1) {
            // 编辑
            editFeeRateListData(this.form).then((res) => {
              this.submitLoading = false;
              if (res.success) {
                this.$Message.success("操作成功");
                this.getDataList();
                this.modalVisible = false;
              }
            });
          }
        }
      });
    },
    remove (v) {
      this.$Modal.confirm({
        title: "确认删除",
        content: "您确认要删除 " + v.id + " ?",
        loading: true,
        onOk: () => {
          // 删除
          deleteFeeRateListData({ ids: v.id }).then((res) => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.getDataList();
            }
          });
        },
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
          deleteFeeRateListData({ ids: ids }).then((res) => {
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
  },
  mounted () {
    // 计算高度
    let height = document.documentElement.clientHeight;
    this.maxHeight = Number(height - 287) + "px";
    this.init();
  },
};
</script>