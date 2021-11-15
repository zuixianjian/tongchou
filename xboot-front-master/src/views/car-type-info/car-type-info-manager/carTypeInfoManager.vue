<style lang="less">
@import "../../../styles/table-common.less";
</style>
<template>
  <div class="search">
    <Card>
      <Row @keydown.enter.native="handleSearch">
        <Form ref="searchForm" :model="searchForm" inline :label-width="70">
          <Form-item label="车型名称" prop="carTypeName">
            <Input
              type="text"
              v-model="searchForm.carTypeName"
              clearable
              placeholder="请输入车型名称"
              style="width: 200px"
            />
          </Form-item>

          <Form-item label="车型代码" prop="code">
            <Input
              type="text"
              v-model="searchForm.code"
              clearable
              placeholder="请输入车型代码"
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

          <Form-item style="margin-left: -35px" class="br">
            <Button
              @click="handleSearch"
              type="primary"
              icon="ios-search"
              v-has="'car-type-info-manager-search'"
              >搜索</Button
            >
            <Button @click="handleReset">重置</Button>
          </Form-item>
        </Form>
      </Row>
      <Row class="operation">
        <Button
          @click="add"
          v-if="!singgleSelect"
          type="primary"
          icon="md-add"
          v-has="'car-type-info-manager-add'"
          >添加</Button
        >
        <Button
          @click="delAll"
          v-if="!singgleSelect"
          icon="md-trash"
          v-has="'car-type-info-manager-delete'"
          >批量删除</Button
        >
        <Button
          @click="getDataList"
          icon="md-refresh"
          v-has="'car-type-info-manager-search'"
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
          :highlight-row="singgleSelect"
          @on-current-change="onCurrentChange"
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
      <Form ref="form" :model="form" :label-width="100" :rules="formValidate">
        <FormItem label="车型名称" prop="carTypeName">
          <Input v-model="form.carTypeName" />
        </FormItem>
        <FormItem label="车型别名" prop="carTypeNameAlaise">
          <Input v-model="form.carTypeNameAlaise" />
        </FormItem>
        <FormItem label="生产厂家" prop="productorCompany">
          <Input v-model="form.productorCompany" />
        </FormItem>
        <FormItem label="品牌" prop="brandName">
          <Input v-model="form.brandName" />
        </FormItem>
        <FormItem label="上市年份" prop="saleYear">
          <Input v-model="form.saleYear" />
        </FormItem>
        <FormItem label="整备质量(kg)" prop="weight">
          <InputNumber
            :max="1000000"
            :min="0"
            v-model="form.weight"
            style="width: 380px"
          ></InputNumber>
        </FormItem>
        <FormItem label="功率(kw)" prop="kws">
          <InputNumber
            :max="1000000"
            :min="0"
            v-model="form.kws"
            style="width: 380px"
          ></InputNumber>
        </FormItem>
        <FormItem label="车型代码" prop="code">
          <Input v-model="form.code" />
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
        <FormItem label="官方指导价" prop="carNewPrice">
          <Tooltip
            trigger="hover"
            placement="top"
            max-width="300"
            content="官方指导价(元)"
          >
            <InputNumber
              :max="10000000"
              :min="0"
              v-model="form.carNewPrice"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>
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
              v-model="form.siteCount"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>
        <FormItem label="载重(kg)" prop="loadCapacity">
          <Tooltip
            trigger="hover"
            placement="top"
            max-width="300"
            content="载重(kg)"
          >
            <InputNumber
              :max="100000"
              :min="0"
              v-model="form.loadCapacity"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>
        <FormItem label="排量" prop="displacement">
          <Tooltip
            trigger="hover"
            placement="right-start"
            max-width="300"
            content="排量"
          >
            <InputNumber
              :max="100000"
              :min="0"
              v-model="form.displacement"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>

        <FormItem label="购置价" prop="buyPrice">
          <Tooltip
            trigger="hover"
            placement="right-start"
            max-width="300"
            content="实际购置价(元)"
          >
            <InputNumber
              :max="100000000"
              :min="0"
              v-model="form.buyPrice"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>
        <FormItem label="含税购置价" prop="andRateBuyPrice">
          <Tooltip
            trigger="hover"
            placement="right-start"
            max-width="300"
            content="实际含税购置价(元)"
            style="width: 380px"
          >
            <InputNumber
              :max="100000"
              :min="0"
              v-model="form.andRateBuyPrice"
              style="width: 380px"
            ></InputNumber>
          </Tooltip>
        </FormItem>
        <FormItem label="备注" prop="remark">
          <Input v-model="form.remark" />
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="handleCancel">取消</Button>
        <Button
          type="primary"
          :loading="submitLoading"
          @click="handleSubmit"
          v-has="'car-type-info-manager-add-commit'"
          >提交</Button
        >
      </div>
    </Modal>
  </div>
</template>

<script>
import {
  getCarTypeListData,
  addCarTypeData,
  deleteCarTypeData,
  editCarTypeData,
  getCarTypeBByCartypeA
} from "@/api/index";
import dictUtil from "@/libs/dictUtil";
export default {
  props: {
    singgleSelect: Boolean,
    default: false,
  },
  name: "car-type-info-manager",
  data () {
    return {
      carTypeBs: [],
      openTip: true, // 显示提示
      loading: true, // 表单加载状态
      searchForm: {
        // 搜索框对应data对象
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc", // 默认排序方式
        carTypeName: "",
        code: "",
        carType: "",
        belongType: "",
        useType: "",
        carSieralType: "",
      },
      modalType: 0, // 添加或编辑标识
      modalVisible: false, // 添加或编辑显示
      modalTitle: "", // 添加或编辑标题
      form: {
        // 添加或编辑表单对象初始化数据
        carTypeName: "",
        code: "",
        carType: "",
        belongType: "",
        useType: "",
        carNewPrice: 1,
        siteCount: 2,
        loadCapacity: 1,
        displacement: 2.0,
        carSieralType: "",
        buyPrice: 1,
        andRateBuyPrice: 1,
      },
      // 表单验证规则
      formValidate: {
        carTypeName: [
          { required: true, message: "车型名称不能为空", trigger: "blur" },
        ],
        code: [
          { required: true, message: "车型代码不能为空", trigger: "blur" },
        ],
        carTypeA: [
          { required: true, message: "车型种类不能为空", trigger: "blur" },
        ],
        carTypeB: [
          { required: true, message: "细分类目不能为空", trigger: "blur" },
        ],
        weight: [
          { required: true, message: "整备质量不能为空", trigger: "blur", type: "number" },
        ],
        carNewPrice: [
          { required: true, message: "官方指导价不能为空", trigger: "blur", type: "number" },
        ],
        siteCount: [
          { required: true, message: "座位数不能为空", trigger: "blur", type: "number" },
        ],
        loadCapacity: [
          { required: true, message: "载重不能为空", trigger: "blur", type: "number" },
        ],
        displacement: [
          { required: true, message: "排量不能为空", trigger: "blur", type: "number" },
        ],

        buyPrice: [
          { required: true, message: "购置价不能为空", trigger: "blur", type: "number" },
        ],
        andRateBuyPrice: [
          { required: true, message: "含税购置价不能为空", trigger: "blur", type: "number" },
        ],
      },
      submitLoading: false, // 添加或编辑提交状态
      selectList: [], // 多选数据
      selectCount: 0, // 多选计数
      // 表格动态列 默认勾选显示的列的key
      columnSettings: ["carTypeName", "code", "carType", "belongType"],
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
          hide: this.singgleSelect
        },
        {
          title: "车型名称",
          width: 300,
          key: "carTypeName",
        },
        {
          title: "车型别名",
          width: 300,
          key: "carTypeNameAlaise",
        },
        {
          title: "车型代码",
          width: 100,
          key: "code",
        },
        {
          title: "生产厂家",
          width: 100,
          key: "productorCompany",
        },
        {
          title: "品牌",
          width: 100,
          key: "brandName",
        },
        {
          title: "整备质量(kg)",
          width: 100,
          key: "weight",
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
          title: "官方指导价(元)",
          key: "carNewPrice",
          width: 150,
        },
        {
          title: "座位数",
          width: 100,
          key: "siteCount",
        },
        {
          title: "载重",
          width: 100,
          key: "loadCapacity",
        },
        {
          title: "排量",
          width: 100,
          key: "displacement",
        },
        {
          title: "购置价",
          width: 100,
          key: "buyPrice",
        },
        {
          title: "含税购置价",
          key: "andRateBuyPrice",
          width: 150,
        },
        {
          title: "创建时间",
          key: "createTime",
          width: 200,
          sortable: true,
          sortType: "desc",
        },
        {
          title: "更新时间",
          key: "updateTime",
          width: 200,
          sortable: true,
          // 禁止配置的列
          disabled: true,
        },
        {
          hide: this.singgleSelect,
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
                  directives: [
                    {
                      name: 'has',
                      value: 'car-type-info-manager-edit'
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
                      value: 'car-type-info-manager-delete'
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
  methods: {
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
    onCurrentChange (currentRow, oldCurrentRow) {
      this.$emit('outSelect', currentRow);
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
      getCarTypeListData(this.searchForm).then(res => {
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
            addCarTypeData(this.form).then(res => {
              this.submitLoading = false;
              if (res.success) {
                this.$Message.success("操作成功");
                this.getDataList();
                this.modalVisible = false;
              }
            });

          } else {
            // 编辑
            editCarTypeData(this.form).then(res => {
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
          deleteCarTypeData({ ids: v.id }).then(res => {
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
          deleteCarTypeData({ ids: ids }).then(res => {
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