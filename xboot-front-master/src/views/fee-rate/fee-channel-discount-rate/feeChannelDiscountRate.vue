<style lang="less">
@import "../../../styles/tree-common.less";
</style>
<template>
  <div class="search">
    <Card>
      <Row class="operation">
        <Button
          @click="getParentList"
          icon="md-refresh"
          v-has="'fee-channel-discount-rate-search'"
          >刷新</Button
        >
        <i-switch v-model="strict" size="large" style="margin-left: 5px">
          <span slot="open">级联</span>
          <span slot="close">单选</span>
        </i-switch>
      </Row>
      <Row type="flex" justify="start">
        <Col :md="8" :lg="8" :xl="6">
          <Alert show-icon>
            当前选择编辑：
            <span class="select-title">{{ editTitle }}</span>
            <a class="select-clear" v-if="form.id" @click="cancelEdit"
              >取消选择</a
            >
          </Alert>
          <Input
            v-model="searchKey"
            suffix="ios-search"
            @on-change="search"
            placeholder="输入节点名搜索"
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
            <Spin size="large" fix v-if="loading"></Spin>
          </div>
        </Col>
        <Col :md="15" :lg="13" :xl="9" style="margin-left: 10px">
          <Form
            ref="form"
            :model="form"
            :label-width="100"
            :rules="formValidate"
          >
            <FormItem label="上级部门" prop="parentTitle">
              <div style="display: flex">
                <Input
                  v-model="form.parentTitle"
                  readonly
                  style="margin-right: 10px"
                  disabled
                />
              </div>
            </FormItem>
            <FormItem label="部门名称" prop="title">
              <Input v-model="form.title" disabled />
            </FormItem>
            <FormItem label="部门负责人" prop="mainHeader">
              <Select
                :loading="userLoading"
                not-found-text="该部门暂无用户数据"
                v-model="form.mainHeader"
                multiple
                filterable
                placeholder="请选择或输入搜索用户"
                disabled
              >
                <Option
                  v-for="item in users"
                  :value="item.id"
                  :key="item.id"
                  :label="item.nickname"
                >
                  <span style="margin-right: 10px">{{ item.nickname }}</span>
                  <span style="color: #ccc">{{ item.username }}</span>
                </Option>
              </Select>
            </FormItem>
            <FormItem label="副负责人" prop="viceHeader">
              <Select
                :loading="userLoading"
                not-found-text="该部门暂无用户数据"
                v-model="form.viceHeader"
                multiple
                filterable
                placeholder="请选择或输入搜索用户"
                disabled
              >
                <Option
                  v-for="item in users"
                  :value="item.id"
                  :key="item.id"
                  :label="item.nickname"
                >
                  <span style="margin-right: 10px">{{ item.nickname }}</span>
                  <span style="color: #ccc">{{ item.username }}</span>
                </Option>
              </Select>
            </FormItem>
            <FormItem label="渠道折扣" prop="discountRate">
              <Tooltip trigger="hover" placement="right" content="渠道折扣">
                <InputNumber
                  :max="10"
                  :min="0"
                  v-model="form.discountRate"
                  style="width: 500px"
                ></InputNumber>
              </Tooltip>
            </FormItem>
            <FormItem label="费率组" prop="rateRegionGroup">
              <Select v-model="form.rateRegionGroup" placeholder="请选择">
                <Option
                  v-for="(item, index) in this.$store.state.dict.regionGroupss"
                  :value="item.value"
                  :key="index"
                  >{{ item.title }}</Option
                >
              </Select>
            </FormItem>
            <FormItem label="折扣组" prop="discountRegionGroup">
              <Select v-model="form.discountRegionGroup" placeholder="请选择">
                <Option
                  v-for="(item, index) in this.$store.state.dict.regionGroupss"
                  :value="item.value"
                  :key="index"
                  >{{ item.title }}</Option
                >
              </Select>
            </FormItem>
            <Form-item class="br">
              <Button
                @click="submitEdit"
                :loading="submitLoading"
                type="primary"
                icon="ios-create-outline"
                v-has="'fee-channel-discount-rate-edit'"
                >修改并保存</Button
              >
              <Button @click="handleReset">重置</Button>
            </Form-item>
          </Form>
        </Col>
      </Row>
    </Card>
  </div>
</template>

<script>
import {
  getChannelDiscountListData,
  addChannelDiscountData,
  deleteChannelDiscountData,
  editChannelDiscountData,
  loadchannelDiscounts,
  searchChannelDiscount,
  getUserByDepartmentId
} from "@/api/index";

export default {
  name: "fee-channel-discount-rate",
  data () {
    return {
      loading: false, // 树加载状态
      maxHeight: "500px",
      strict: true,
      loadingEdit: false, // 编辑上级树加载状态
      modalVisible: false, // 添加显示
      selectList: [], // 多选数据
      selectCount: 0, // 多选计数
      showParent: false, // 显示上级标识
      modalTitle: "", // 添加标题
      editTitle: "", // 编辑节点名称
      searchKey: "", // 搜索树
      form: {
        // 编辑对象初始化数据
        id: "",
        title: "",
        parentId: "",
        parentTitle: "",
        discountRate: 0
      },
      formValidate: {
        // 表单验证规则
        discountRate: [{ required: true, message: "渠道折扣不能为空", trigger: "blur", type: "number" }],
        rateRegionGroup: [{ required: true, message: "费率组不能为空", trigger: "blur", type: "number" }],
        discountRegionGroup: [{ required: true, message: "折扣组不能为空", trigger: "blur", type: "number" }],
      },
      submitLoading: false,
      data: [],
      dataEdit: [],
      userLoading: false,
      users: []
    };
  },
  methods: {
    init () {
      // 初始化一级节点
      this.getParentList();
      // 初始化一级节点为编辑上级节点使用
      this.getParentListEdit();
    },
    getParentList () {
      this.loading = true;
      loadchannelDiscounts(0).then(res => {
        this.loading = false;
        if (res.success) {
          res.result.forEach(function (e) {
            if (e.isParent) {
              e.loading = false;
              e.children = [];
            }
          });
          this.data = res.result;
        }
      });

    },
    getParentListEdit () {
      this.loadingEdit = true;
      loadchannelDiscounts(0).then(res => {
        this.loadingEdit = false;
        if (res.success) {
          res.result.forEach(function (e) {
            if (e.isParent) {
              e.loading = false;
              e.children = [];
            }
          });
          // 头部加入一级
          let first = {
            id: "0",
            title: "一级节点"
          };
          res.result.unshift(first);
          this.dataEdit = res.result;
        }
      });

    },
    loadData (item, callback) {
      // 异步加载树子节点数据
      loadchannelDiscounts(item.id).then(res => {
        if (res.success) {
          res.result.forEach(function (e) {
            if (e.isParent) {
              e.loading = false;
              e.children = [];
            }
          });
          callback(res.result);
        }
      });
    },
    search () {
      // 搜索树
      if (this.searchKey) {
        // 模拟请求
        this.loading = true;
        searchChannelDiscount({ title: this.searchKey }).then(res => {
          this.loading = false;
          if (res.success) {
            this.data = res.result;
          }
        });

      } else {
        // 为空重新加载
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
        this.form = data;
        this.editTitle = data.title;
        // 加载部门用户数据
        this.userLoading = true;
        getUserByDepartmentId(data.id).then(res => {
          this.userLoading = false;
          if (res.success) {
            this.users = res.result;
            // 回显
            this.form = data;
          }
        });
      } else {
        this.cancelEdit();
      }
    },
    cancelEdit () {
      let data = this.$refs.tree.getSelectedNodes()[0];
      if (data) {
        data.selected = false;
      }
      this.$refs.form.resetFields();
      this.form.id = "";
      this.editTitle = "";
    },
    selectTreeEdit (v) {
      if (v.length > 0) {
        // 转换null为""
        for (let attr in v[0]) {
          if (v[0][attr] == null) {
            v[0][attr] = "";
          }
        }
        let str = JSON.stringify(v[0]);
        let data = JSON.parse(str);
        this.form.parentId = data.id;
        this.form.parentTitle = data.title;
      }
    },
    handleReset () {
      this.$refs.form.resetFields();
      this.form.status = 0;
    },
    submitEdit () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (!this.form.id) {
            this.$Message.warning("请先点击选择要修改的节点");
            return;
          }
          this.submitLoading = true;
          // 避免传入null字符串
          addChannelDiscountData({
            departmentId: this.form.id, discountRate: this.form.discountRate,
            rateRegionGroup: this.form.rateRegionGroup, discountRegionGroup: this.form.discountRegionGroup
          }).then(res => {
            this.submitLoading = false;
            if (res.success) {
              this.$Message.success("编辑成功");
              this.init();
              this.modalVisible = false;
            }
          });

        }
      });
    },
    add () {
      if (!this.form.id) {
        this.$Message.warning("请先点击选择一个节点");
        return;
      }
      this.modalTitle = "添加子节点";
      this.showParent = true;
      this.formAdd = {
        parentId: this.form.id,
        sortOrder: 0,
        status: 0
      };
      this.modalVisible = true;
    },
    addRoot () {
      this.modalTitle = "添加一级节点";
      this.showParent = false;
      this.formAdd = {
        parentId: 0,
        sortOrder: 0,
        status: 0
      };
      this.modalVisible = true;
    },
    changeSelect (v) {
      this.selectCount = v.length;
      this.selectList = v;
    },
  },
  mounted () {
    // 计算高度
    let height = document.documentElement.clientHeight;
    this.maxHeight = Number(height - 287) + "px";
    this.init();
  }
};
</script>