<style lang="less">
@import "../../../styles/table-common.less";
</style>
<template>
  <div class="search">
    <Card>
      <Row @keydown.enter.native="handleSearch">
        <Form ref="searchForm" :model="searchForm" inline :label-width="100">
          <FormItem label="收款单类型" prop="bussinessType">
            <Select v-model="searchForm.bussinessType" style="width: 200px">
              <Option
                v-for="item in bussinessTypes"
                :value="item.value"
                :key="item"
                >{{ item.label }}</Option
              >
            </Select> </FormItem
          ><FormItem label="收款单状态" prop="recieveStatus">
            <Select
              v-model="searchForm.recieveStatus"
              style="width: 200px"
              clearable
            >
              <Option
                v-for="item in recieveStatuss"
                :value="item.value"
                :key="item"
                >{{ item.label }}</Option
              >
            </Select>
          </FormItem>
          <FormItem label="业务编码" prop="bussinessId">
            <Input v-model="searchForm.bussinessId" style="width: 300px" />
          </FormItem>

          <Form-item style="margin-left: -35px" class="br">
            <Button
              @click="handleSearch"
              type="primary"
              icon="ios-search"
              v-has="'order-payinfo-manager-search'"
              >搜索</Button
            >
            <Button @click="handleReset">重置</Button>
          </Form-item>
        </Form>
      </Row>
      <Row class="operation">
        <!-- <Button @click="add" type="primary" icon="md-add">添加</Button> -->
        <!-- <Button @click="delAll" icon="md-trash">批量删除</Button> -->
        <Button
          @click="getDataList"
          icon="md-refresh"
          v-has="'order-payinfo-manager-search'"
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

          <span style="float: right">点击右上角按钮配置动态列↑</span>
        </Alert>
      </Row>
      <Row>
        <Table
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
      :width="600"
    >
      <Form ref="form" :model="form" :label-width="100" :rules="formValidate">
        <FormItem label="业务编码" prop="bussinessId">
          <Input v-model="form.bussinessId" disabled />
        </FormItem>
        <FormItem label="收款单类型" prop="bussinessType">
          <Select v-model="form.bussinessType" style="width: 200px" disabled>
            <Option
              v-for="item in bussinessTypes"
              :value="item.value"
              :key="item"
              >{{ item.label }}</Option
            >
          </Select> </FormItem
        ><FormItem label="收款单状态" prop="recieveStatus">
          <Select
            v-model="form.recieveStatus"
            style="width: 200px"
            placeholder=""
            disabled
          >
            <Option
              v-for="item in recieveStatuss"
              :value="item.value"
              :key="item"
              >{{ item.label }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="应收全款" prop="shouldRecieve">
          <InputNumber
            :max="10000000"
            :min="-10000000"
            v-model="form.shouldRecieve"
            disabled
          ></InputNumber>
        </FormItem>
        <FormItem label="应收净费" prop="shouldCleanRecieve">
          <InputNumber
            :max="10000000"
            :min="-10000000"
            v-model="form.shouldCleanRecieve"
            disabled
          ></InputNumber>
        </FormItem>
        <FormItem label="实收款项" prop="realRecieve">
          <InputNumber
            :max="10000000"
            :min="-10000000"
            v-model="form.realRecieve"
            style="width: 450px"
            :disabled="disableInput"
          ></InputNumber>
        </FormItem>
        <FormItem label="付款方式" prop="recieveType">
          <Select
            v-model="form.recieveType"
            placeholder="请选择"
            :disabled="disableInput"
          >
            <Option
              v-for="(op, opindx) in this.$store.state.dict.recieveTypes"
              :value="op.value"
              :key="opindx"
              >{{ op.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="付款账号" prop="sendNo">
          <Input v-model="form.sendNo" :disabled="disableInput" />
        </FormItem>

        <FormItem label="收款凭证" prop="recieveEvidence">
          <mine-upload-pic-input
            :disabled="disableInput"
            v-model="form.recieveEvidence"
            :uploadFileUrl="uploadFileUrl"
            style="width: 400px"
          />
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="handleCancel">取消</Button>
        <Button
          type="primary"
          :loading="submitLoading"
          @click="handleSubmit"
          v-has="'order-payinfo-manager-recieve-commit'"
          >{{ editTitle }}}</Button
        >
      </div>
    </Modal>
  </div>
</template>

<script>
import mineUploadPicInput from "./mine-upload-pic-input.vue";
import { getOrderPayInfoListData, orderPayInfoPaySuccess, uploadMineFile } from "@/api/index";
export default {
  name: "order-payinfo-manager",
  components: { mineUploadPicInput },
  data () {
    return {
      editTitle: "确认收款",
      uploadFileUrl: uploadMineFile,
      bussinessTypes: [
        {
          value: "ORDER_TYPE_COMMON",
          label: "统筹单",
        },
        {
          value: "ORDER_TYPE_EDIT",
          label: "批单",
        },
        {
          value: "ORDER_TYPE_HELP",
          label: "互助单",
        },
      ],
      recieveStatuss: [
        {
          value: "PAYINFO_STATUS_WAIT",
          label: "待收款",
        },
        {
          value: "PAYINFO_STATUS_SUCCESS",
          label: "已收款",
        },
        {
          value: "PAYINFO_STATUS_WAIT_TO",
          label: "互助待付款",
        },
        {
          value: "PAYINFO_STATUS_SUCCESS_TO",
          label: "互助已结案",
        },
      ],
      openTip: true, // 显示提示
      loading: true, // 表单加载状态
      searchForm: {
        // 搜索框对应data对象
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc", // 默认排序方式
      },
      modalType: 0, // 添加或编辑标识
      modalVisible: false, // 添加或编辑显示
      modalTitle: "", // 添加或编辑标题
      form: {
        // 添加或编辑表单对象初始化数据
        bussinessId: "", //业务编码
      },
      // 表单验证规则
      formValidate: {
        realRecieve: [
          {
            required: true,
            message: "实收金额不能为空",
            trigger: "blur",
            type: "number",
          },
        ],
        recieveType: [
          { required: true, message: "收款方式不能为空", trigger: "blur" },
        ],
        sendNo: [
          { required: true, message: "付款账号不能为空", trigger: "blur" },
        ],
        recieveEvidence: [
          { required: true, message: "付款凭证不能为空", trigger: "blur" },
        ],
      },
      submitLoading: false, // 添加或编辑提交状态
      selectList: [], // 多选数据
      selectCount: 0, // 多选计数
      // 表格动态列 默认勾选显示的列的key
      columnSettings: [
        "bussinessId",
        "bussinessType",
        "recieveStatus",
        "recieveTime",
        "createTime",
        "updateTime",
      ],
      // 不能配置的列（不显示）
      whiteColumns: ["action"],
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
          title: "业务编码",
          key: "bussinessId",
          width: 200,
        },
        {
          title: "业务类型",
          key: "bussinessType",
          width: 100,
          render: (h, params) => {
            if (params.row.bussinessType == "ORDER_TYPE_COMMON") {
              return h("div", "统筹单");
            } else if (params.row.bussinessType == "ORDER_TYPE_EDIT") {
              return h("div", "批单");
            } else if (params.row.bussinessType == "ORDER_TYPE_HELP") {
              return h("div", "互助单");
            }
            return h("div", "未知业务");
          },
        },
        {
          title: "收款状态",
          key: "recieveStatus",
          width: 120,
          render: (h, params) => {
            if (params.row.recieveStatus == "PAYINFO_STATUS_WAIT") {
              return h("div", "待收款");
            } else if (
              params.row.recieveStatus == "PAYINFO_STATUS_SUCCESS"
            ) {
              return h("div", "已收款");
            } else if (
              params.row.recieveStatus == "PAYINFO_STATUS_WAIT_TO"
            ) {
              return h("div", "互助待付款");
            } else if (
              params.row.recieveStatus == "PAYINFO_STATUS_SUCCESS_TO"
            ) {
              return h("div", "互助已结案");
            }
            return h("div", "未知状态");
          },
        },
        {
          title: "应收全款",
          key: "shouldRecieve",
          width: 100,

        },
        {
          title: "应收净费",
          key: "shouldCleanRecieve",
          width: 100,
        },
        {
          title: "实收款项",
          key: "realRecieve",
          width: 100,
        },
        {
          title: "收款方式",
          key: "recieveType",
          width: 100,
        },
        {
          title: "付款账号",
          key: "sendNo",
          width: 100,
        },
        {
          title: "收款人",
          key: "recieveMan",
          width: 100,
        },
        {
          title: "收款时间",
          key: "recieveTime",
          sortable: true,
          sortType: "desc",
          width: 200,
        },
        {
          title: "创建时间",
          key: "createTime",
          sortable: true,
          sortType: "desc",
          width: 200,
        },
        {
          title: "更新时间",
          key: "updateTime",
          sortable: true,
          // 禁止配置的列
          disabled: true,
          width: 200,
        },
        {
          title: "操作",
          key: "action",
          align: "center",
          width: 150,
          fixed: "right",
          render: (h, params) => {

            if (params.row.recieveStatus == "PAYINFO_STATUS_WAIT") {
              return h("div", [
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small",
                      icon: "ios-create-outline",
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'order-payinfo-manager-recieve'
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
                  "收款"
                ),

              ]);
            } else if (
              params.row.recieveStatus == "PAYINFO_STATUS_SUCCESS"
            ) {
              return h("div", [

                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small",
                      icon: "ios-create-outline",
                    },
                    directives: [
                      {
                        name: 'has',
                        value: 'order-payinfo-manager-view'
                      }
                    ],
                    style: {
                      marginRight: "5px",
                    },
                    on: {
                      click: () => {
                        this.view(params.row);
                      },
                    },
                  },
                  "查看"
                ),
              ]);
            } else if (
              params.row.recieveStatus == "PAYINFO_STATUS_WAIT_TO"
            ) {
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
                    on: {
                      click: () => {
                        this.edit(params.row);
                      },
                    },
                  },
                  "付款"
                ),

              ]);
            } else if (
              params.row.recieveStatus == "PAYINFO_STATUS_SUCCESS_TO"
            ) {
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
                    on: {
                      click: () => {
                        this.view(params.row);
                      },
                    },
                  },
                  "查看"
                ),
              ]);
            }
          },
        },
      ],
      columnChange: false,
      data: [], // 表单数据
      total: 0, // 表单数据总数
      disableInput: false
    };
  },
  // 表格动态列 计算属性
  computed: {
    dynamicColums: function () {
      this.columnChange;
      return this.columns.filter((item) => item.hide != true);
    },
  },
  methods: {
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
      this.loading = true;
      // 请求后端获取表单数据 请自行修改接口
      getOrderPayInfoListData(this.searchForm).then((res) => {
        this.loading = false;
        if (res.success) {
          this.data = res.result.content;
          this.total = res.result.totalElements;
        }
      });
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
            // this.postRequest("请求地址", this.form).then(res => {
            //   this.submitLoading = false;
            //   if (res.success) {
            //     this.$Message.success("操作成功");
            //     this.getDataList();
            //     this.modalVisible = false;
            //   }
            // });

            this.submitLoading = false;
            // this.$Message.success("操作成功");
            // this.getDataList();
            this.modalVisible = false;
          } else {
            // 编辑
            orderPayInfoPaySuccess(this.form).then((res) => {
              this.submitLoading = false;
              if (res.success) {
                this.$Message.success("收款成功");
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
      this.disableInput = false;
      this.modalType = 1;
      this.modalTitle = "确认收款";
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
    view (v) {
      this.modalType = 0;
      this.modalTitle = "确认收款";
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
      this.editTitle = "确认";
      this.disableInput = true;
    },
    remove (v) {
      this.$Modal.confirm({
        title: "确认删除",
        // 记得确认修改此处
        content: "您确认要删除 " + v.name + " ?",
        loading: true,
        onOk: () => {
          // 删除
          // this.deleteRequest({ ids: v.id }).then(res => {
          //   this.$Modal.remove();
          //   if (res.success) {
          //     this.$Message.success("操作成功");
          //     this.getDataList();
          //   }
          // });
          // 模拟请求成功
          this.$Message.success("操作成功");
          this.$Modal.remove();
          this.getDataList();
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
          // this.deleteRequest("请求地址，如/deleteByIds/" + ids).then(res => {
          //   this.$Modal.remove();
          //   if (res.success) {
          //     this.$Message.success("操作成功");
          //     this.clearSelectAll();
          //     this.getDataList();
          //   }
          // });
          // 模拟请求成功
          this.$Message.success("操作成功");
          this.$Modal.remove();
          this.clearSelectAll();
          this.getDataList();
        },
      });
    },
  },
  mounted () {
    this.init();
  },
};
</script>