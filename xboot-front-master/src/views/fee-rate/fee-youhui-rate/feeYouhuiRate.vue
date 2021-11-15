<style lang="less">
@import "../../../styles/table-common.less";
</style>
<template>
  <div class="search">
    <Card>
      <Row @keydown.enter.native="handleSearch" v-if="!singgleSelect">
        <Form ref="searchForm" :model="searchForm" inline :label-width="70">
          <Form-item label="费率组" prop="regionGroup">
            <Select
              v-model="searchForm.regionGroup"
              placeholder="请选择"
              clearable
              style="width: 200px"
            >
              <Option
                v-for="(item, index) in this.$store.state.dict.regionGroupss"
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
              v-has="'fee-youhui-rate-search'"
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
          v-if="!singgleSelect"
          icon="md-add"
          v-has="'fee-youhui-rate-add'"
          >添加</Button
        >
        <Button
          @click="delAll"
          v-if="!singgleSelect"
          icon="md-trash"
          v-has="'fee-youhui-rate-delete'"
          >批量删除</Button
        >
        <Button
          @click="getDataList"
          icon="md-refresh"
          v-has="'fee-youhui-rate-search'"
          >刷新</Button
        >
        <Button type="dashed" @click="openTip = !openTip">{{
          openTip ? "关闭提示" : "开启提示"
        }}</Button>
        <!-- 动态列按钮 -->
        <Poptip placement="bottom-end" width="360" style="float: right">
          <Button shape="circle" icon="md-apps"></Button>
          <div slot="content">
            <CheckboxGroup v-model="columnSettings" @on-change="changeColumns">
              <div v-for="(column, i) in columns" :key="i">
                <Col
                  span="8"
                  v-if="column.key && column.key.indexOf(whiteColumns) <= -1"
                >
                  <Checkbox :label="column.key" :disabled="column.disabled">
                    <span>{{ column.title }}</span>
                  </Checkbox>
                </Col>
              </div>
            </CheckboxGroup>
          </div>
        </Poptip>
      </Row>
      <Row v-show="openTip">
        <Alert show-icon>
          已选择
          <span class="select-count">{{ selectCount }}</span> 项
          <a class="select-clear" @click="clearSelectAll">清空</a>
          这里还可以做一些数据统计显示
          <span style="float: right">点击右上角按钮配置动态列↑</span>
        </Alert>
      </Row>
      <Row>
        <Table
          :highlight-row="singgleSelect"
          @on-current-change="onCurrentChange"
          :loading="loading"
          border
          :columns="dynamicColums"
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
      :title="modalTitle"
      v-model="modalVisible"
      :mask-closable="false"
      :width="500"
    >
      <Form ref="form" :model="form" :label-width="80" :rules="formValidate">
        <FormItem label="折扣组" prop="regionGroup">
          <Select v-model="form.regionGroup" placeholder="请选择">
            <Option
              v-for="(item, index) in this.$store.state.dict.regionGroupss"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="优惠描述" prop="feeFloatReason">
          <Input v-model="form.feeFloatReason" />
        </FormItem>
        <FormItem label="费率" prop="feeFloatRate">
          <Tooltip
            trigger="hover"
            placement="right-start"
            max-width="300"
            content="优惠折扣率"
          >
            <InputNumber
              :max="100000"
              :min="0"
              v-model="form.feeFloatRate"
            ></InputNumber>
          </Tooltip>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="handleCancel">取消</Button>
        <Button
          type="primary"
          :loading="submitLoading"
          @click="handleSubmit"
          v-has="'fee-youhui-rate-edit-commit'"
          >提交</Button
        >
      </div>
    </Modal>
  </div>
</template>

<script>
import {
  getYouhuiListData,
  addYouhuiData,
  deleteYouhuiData,
  editYouhuiData,
  getMineYouhui
} from "@/api/index";
import dictUtil from "@/libs/dictUtil";
export default {
  name: "fee-youhui-rate",
  props: {
    singgleSelect: {
      type: Boolean,
      default: false,
    },
    onlymine: {
      type: Boolean,
      default: false,
    },
    region: {
      default: "",
    },
  },
  data () {
    return {
      openTip: true, // 显示提示
      loading: true, // 表单加载状态
      searchForm: {
        // 搜索框对应data对象
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc", // 默认排序方式
        regionGroup: "",
      },
      modalType: 0, // 添加或编辑标识
      modalVisible: false, // 添加或编辑显示
      modalTitle: "", // 添加或编辑标题
      form: {
        // 添加或编辑表单对象初始化数据
        regionGroup: "",
        feeFloatReason: "",
        feeFloatRate: 1,
      },
      // 表单验证规则
      formValidate: {
        name: [{ required: true, message: "不能为空", trigger: "blur" }],
      },
      submitLoading: false, // 添加或编辑提交状态
      selectList: [], // 多选数据
      selectCount: 0, // 多选计数
      // 表格动态列 默认勾选显示的列的key
      columnSettings: [
        "regionGroup",
        "feeFloatReason",
        "feeFloatReason",
        "createTime",
      ],
      // 不能配置的列（不显示）
      whiteColumns: ["action"],
      columns: [
        // 表头
        {
          type: "selection",
          width: 60,
          align: "center",
          hide: this.singgleSelect,
        },
        {
          type: "index",
          width: 60,
          align: "center",
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
          title: "优惠描述",
          width: 600,
          key: "feeFloatReason",
          sortable: true,
        },
        {
          title: "折扣率",
          width: 100,
          key: "feeFloatRate",
          sortable: true,
        },
        {
          title: "创建时间",
          width: 200,
          key: "createTime",
          sortable: true,
          sortType: "desc",
        },
        {
          width: 200,
          title: "更新时间",
          key: "updateTime",
          sortable: true,
          // 禁止配置的列
          disabled: true,
        },
        {
          width: 200,
          hide: this.singgleSelect,
          title: "操作",
          key: "action",
          align: "center",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small",
                    icon: "ios-create-outline",
                  },
                  style: {
                    marginRight: "5px",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'fee-youhui-rate-edit'
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
                    icon: "md-trash",
                  },
                  directives: [
                    {
                      name: 'has',
                      value: 'fee-youhui-rate-delete'
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
      columnChange: false,
      data: [], // 表单数据
      total: 0, // 表单数据总数
    };
  },
  // 表格动态列 计算属性
  computed: {
    dynamicColums: function () {
      this.columnChange;
      return this.columns.filter((item) => item.hide != true);
    },
  },
  watch: {
    region: function () {
      this.getDataList();
    },
    onlymine: function () {
      this.getDataList();
    }
  },
  methods: {
    onCurrentChange (currentRow, oldCurrentRow) {
      this.$emit("outSelect", currentRow);
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
    init () {
      this.getDataList();
    },
    changeColumns (v) {
      this.columns.map((item) => {
        let hide = true;
        for (let i = 0; i < v.length; i++) {
          if (!item.key) {
            hide = false;
            break;
          }
          if (item.key == v[i] || item.key.indexOf(this.whiteColumns) > -1) {
            hide = false;
            break;
          }
        }
        item.hide = hide;
        return item;
      });
      // 触发计算方法
      this.columnChange = !this.columnChange;
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
    getDataList () {
      //   if (this.singgleSelect && this.region.length > 0) {
      //     this.searchForm.regionGroup = this.region;
      //   } else if (this.singgleSelect && this.region.length == 0) {
      //     this.searchForm.regionGroup = "不让你查到";
      //   }
      this.loading = true;
      // 请求后端获取表单数据 请自行修改接口
      if (this.onlymine) {
        getMineYouhui(this.searchForm).then((res) => {
          this.loading = false;
          if (res.success) {
            this.data = res.result.content;
            this.total = res.result.totalElements;
          }
        });
      } else {
        getYouhuiListData(this.searchForm).then((res) => {
          this.loading = false;
          if (res.success) {
            this.data = res.result.content;
            this.total = res.result.totalElements;
          }
        });
      }

    },
    handleCancel () {
      this.modalVisible = false;
    },
    handleSubmit () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.submitLoading = true;
          if (this.modalType == 0) {
            // 添加 避免编辑后传入id等数据 记得删除
            delete this.form.id;
            addYouhuiData(this.form).then((res) => {
              this.submitLoading = false;
              if (res.success) {
                this.$Message.success("操作成功");
                this.getDataList();
                this.modalVisible = false;
              }
            });
          } else {
            // 编辑
            editYouhuiData(this.form).then((res) => {
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
    add () {
      this.modalType = 0;
      this.modalTitle = "添加";
      this.$refs.form.resetFields();
      delete this.form.id;
      this.modalVisible = true;
    },
    edit (v) {
      this.modalType = 1;
      this.modalTitle = "编辑";
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
      this.modalVisible = true;
    },
    remove (v) {
      this.$Modal.confirm({
        title: "确认删除",
        // 记得确认修改此处
        content: "您确认要删除 " + v.name + " ?",
        loading: true,
        onOk: () => {
          // 删除
          deleteYouhuiData({ ids: v.id }).then((res) => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.getDataList();
            }
          });
        },
      });
    },
    clearSelectAll () {
      this.$refs.table.selectAll(false);
    },
    changeSelect (e) {
      this.selectList = e;
      this.selectCount = e.length;
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
          deleteYouhuiData({ ids: ids }).then((res) => {
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
    this.init();
  },
};
</script>