<style lang="less">
@import "../../../styles/table-common.less";
</style>
<template>
  <div class="search">
    <Card>
       <Row  @keydown.enter.native="handleSearch">
        <Form ref="searchForm" :model="searchForm" inline :label-width="70">
          <Form-item label="客户姓名" prop="name">
            <Input
              type="text"
              v-model="searchForm.name"
              clearable
              placeholder="请输入客户姓名"
              style="width: 200px"
            />
          </Form-item>

          <Form-item label="证件号码" prop="idNo">
            <Input
              type="text"
              v-model="searchForm.idNo"
              clearable
              placeholder="请输入证件号码"
              style="width: 200px"
            />
          </Form-item>
    
          <Form-item label="手机号码" prop="telephone">
            <Input
              type="text"
              v-model="searchForm.telephone"
              clearable
              placeholder="请输入手机号码"
              style="width: 200px"
            />
          </Form-item>
        
          <Form-item style="margin-left:-35px;" class="br">
            <Button @click="handleSearch" type="primary" icon="ios-search">搜索</Button>
            <Button @click="handleReset">重置</Button>
            
          </Form-item>
        </Form>
      </Row>
      <Row class="operation">
        <Button @click="add" type="primary" icon="md-add">添加</Button>
        <Button @click="delAll" icon="md-trash">批量删除</Button>
        <Button @click="getDataList" icon="md-refresh">刷新</Button>
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
          @on-current-change="onCurrentChange"
          :highlight-row="singgleSelect"
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
        <FormItem label="客户姓名" prop="name">
          <Input v-model="form.name" />
        </FormItem>
        <FormItem label="客户性别" prop="sex">
          <Select v-model="form.sex" placeholder="请选择">
            <Option
              v-for="(item, index) in this.$store.state.dict.sex"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="客户性质" prop="clientType">
          <Select v-model="form.clientType" placeholder="请选择">
            <Option
              v-for="(item, index) in this.$store.state.dict.clientTypes"
              :value="item.value"
              :key="index"
              >{{ item.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="证件号码" prop="idNo">
          <Input v-model="form.idNo" />
        </FormItem>
        <FormItem label="手机号码" prop="telephone">
          <Input v-model="form.telephone" />
        </FormItem>
      </Form>

      <div slot="footer">
        <Button type="text" @click="handleCancel">取消</Button>
        <Button type="primary" :loading="submitLoading" @click="handleSubmit"
          >提交</Button
        >
      </div>
    </Modal>
  </div>
</template>

<script>
import {
  getClientInfoListData,
  addClientInfoData,
  deleteClientInfoData,
  editClientInfoData,
} from "@/api/index";
import dictUtil from "@/libs/dictUtil";
export default {
  name: "client-info-manager",
    props: {
    singgleSelect: Boolean,
    default: false,
  },
  data() {
    return {
      openTip: true, // 显示提示
      loading: true, // 表单加载状态
      searchForm: {
        // 搜索框对应data对象
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc", // 默认排序方式
        name: "",
        idNo: "",
        telephone: "",
      },
      modalType: 0, // 添加或编辑标识
      modalVisible: false, // 添加或编辑显示
      modalTitle: "", // 添加或编辑标题
      form: {
        // 添加或编辑表单对象初始化数据
        name: "",
        clientType: "",
        idNo: "",
        telephone: "",
        sex: "",
      },
      // 表单验证规则
      formValidate: {
        name: [
          { required: true, message: "客户姓名不能为空", trigger: "blur" },
        ],
        clientType: [
          { required: true, message: "客户性质不能为空", trigger: "change" },
        ],
        idNo: [
          { required: true, message: "证件号码不能为空", trigger: "blur" },
        ],
        telephone: [
          { required: true, message: "手机号码不能为空", trigger: "blur" },
        ],
        sex: [{ required: true, message: "性别不能为空", trigger: "change" }],
      },
      submitLoading: false, // 添加或编辑提交状态
      selectList: [], // 多选数据
      selectCount: 0, // 多选计数
      // 表格动态列 默认勾选显示的列的key
      columnSettings: ["name", "clientType", "idNo", "telephone","sex","createTime"],
      // 不能配置的列（不显示）
      whiteColumns: ["action"],
      columns: [
        // 表头
        {
          type: "selection",
          width: 60,
          align: "center",
          hide:this.singgleSelect
        },
        {
          type: "index",
          width: 60,
          align: "center",
        },
        {
          title: "客户姓名",
          key: "name",
          align: "center",
          width: 200,
        },
        {
          title: "客户性质",
          key: "clientType",
          align: "center",
          width: 200,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.clientType,
              this.$store.state.dict.clientTypes
            );
            return h("div", dict.title);
          },
        },
          {
          title: "客户性别",
          key: "sex",
          align: "center",
          width: 200,
          render: (h, params) => {
            let dict = dictUtil.reflectDictData(
              params.row.sex,
              this.$store.state.dict.sex
            );
            return h("div", dict.title);
          },
        },
         {
          title: "证件号码",
          key: "idNo",
          sortable: false,
          width: 200,
        },
         {
          title: "手机号码",
          key: "telephone",
          sortable: false,
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
          width: 200,
          fixed: "right",
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
      return this.columns.filter((item) => {
        
        return item.hide != true
        
        });
    },
    
  },
  methods: {
   
    onCurrentChange(currentRow, oldCurrentRow){
        this.$emit('outSelect',currentRow);
    },
      handleSearch() {
      this.searchForm.pageNumber = 1;
      this.searchForm.pageSize = 10;
      this.getDataList();
    },
    handleReset() {
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
    init() {
      this.getDataList();
    },
    changeColumns(v) {
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
    changePage(v) {
      this.searchForm.pageNumber = v;
      this.getDataList();
      this.clearSelectAll();
    },
    changePageSize(v) {
      this.searchForm.pageSize = v;
      this.getDataList();
    },
    changeSort(e) {
      this.searchForm.sort = e.key;
      this.searchForm.order = e.order;
      if (e.order == "normal") {
        this.searchForm.order = "";
      }
      this.getDataList();
    },
    getDataList() {
      this.loading = true;
      // 请求后端获取表单数据 请自行修改接口
      getClientInfoListData(this.searchForm).then(res => {
        this.loading = false;
        if (res.success) {
          this.data = res.result.content;
          this.total = res.result.totalElements;
        }
      });

    },
    handleCancel() {
      this.modalVisible = false;
    },
    handleSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.submitLoading = true;
          if (this.modalType == 0) {
            // 添加 避免编辑后传入id等数据 记得删除
            delete this.form.id;
            addClientInfoData(this.form).then(res => {
              this.submitLoading = false;
              if (res.success) {
                this.$Message.success("操作成功");
                this.getDataList();
                this.modalVisible = false;
              }
            });
          } else {
            // 编辑
            editClientInfoData( this.form).then(res => {
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
    add() {
      this.modalType = 0;
      this.modalTitle = "添加";
      this.$refs.form.resetFields();
      delete this.form.id;
      this.modalVisible = true;
    },
    edit(v) {
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
    remove(v) {
      this.$Modal.confirm({
        title: "确认删除",
        // 记得确认修改此处
        content: "您确认要删除 " + v.name + " ?",
        loading: true,
        onOk: () => {
          // 删除
          deleteClientInfoData({ ids: v.id }).then(res => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.getDataList();
            }
          });
         
        },
      });
    },
    clearSelectAll() {
      this.$refs.table.selectAll(false);
    },
    changeSelect(e) {
      this.selectList = e;
      this.selectCount = e.length;
    },
    delAll() {
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
          deleteClientInfoData({ ids: ids }).then(res => {
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
  mounted() {
    this.init();
  },
};
</script>