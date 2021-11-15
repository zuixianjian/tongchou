<style lang="less">
@import "../../../styles/tree-common.less";
.colContent {
  display: flex;
  justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
}
</style>
<template>
  <div class="search">
    <Card>
      <Row class="operation" type="flex" justify="center">
        <Button type="primary">总损失金额: ￥{{ totalDamage }}</Button>
        <div
          v-if="
            helpOrder.status == 'HELPORDER_STATUS_DIAOCHA' ||
            helpOrder.status == 'HELPORDER_STATUS_CHECK' ||
            helpOrder.status == 'HELPORDER_STATUS_CHECK_FAIL'
          "
        >
          <Button
            @click="add"
            type="primary"
            icon="md-add"
            v-has="'helpOrderDiaochaAddchild'"
            >添加子数据</Button
          >
        </div>

        <!-- <Button @click="addRoot" icon="md-add">添加一级节点</Button> -->
        <Button @click="delAll" icon="md-trash">删除</Button>
        <Button @click="getParentList" icon="md-refresh">刷新</Button>
        <!-- <i-switch v-model="strict" size="large" style="margin-left: 5px">
          <span slot="open">级联</span>
          <span slot="close">单选</span>
        </i-switch> -->
      </Row>
      <Row type="flex" justify="center">
        <Col :md="10" :lg="10" :xl="8">
          <Alert show-icon>
            当前选择编辑：
            <span class="select-title">{{ editTitle }}</span>
            <a class="select-clear" v-if="form.id" @click="cancelEdit"
              >取消选择</a
            >
          </Alert>

          <div class="tree-bar" :style="{ maxHeight: maxHeight }">
            <Tree
              ref="tree"
              :data="data"
              :render="renderContent"
              @on-select-change="selectTree"
              :check-strictly="!strict"
            ></Tree>
            <Spin size="large" fix v-if="loading"></Spin>
          </div>
        </Col>
        <Col :md="10" :lg="10" :xl="8" style="margin-left: 10px">
          <Form
            v-if="node == 0"
            ref="formSecound"
            :model="form"
            :label-width="100"
          >
            <FormItem label="费率类型" prop="feeType">
              {{ getDict(form.feeType) }}
            </FormItem>
            <FormItem label="总保额" prop="insuranceMoney">
              <InputNumber
                :max="1000"
                :min="0"
                v-model="form.insuranceMoney"
                readonly
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <FormItem label="不计免责" prop="deductibleFee">
              <InputNumber
                :max="1000"
                :min="0"
                v-model="form.deductibleFee"
                readonly
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <FormItem label="损失总金额" prop="damangeTotalMoney">
              <InputNumber
                :max="1000"
                :min="0"
                v-model="form.totalDamage"
                readonly
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <Row type="flex" justify="center" v-if="op_type == 'check'">
              <div class="colContent">
                <input
                  type="checkbox"
                  ref="check"
                  v-on:click="isDecideChange(form)"
                  v-model="form.isDecide"
                />已决
              </div>
            </Row>
          </Form>

          <Form
            v-if="node == 1"
            ref="formSecound"
            :model="form"
            :label-width="100"
          >
            <FormItem label="车牌号" prop="carNo">
              <Input v-model="form.carNo" style="width: 400px" />
            </FormItem>
            <FormItem label="证件号" prop="idNo">
              <Input v-model="form.idNo" style="width: 400px" />
            </FormItem>
            <div
              v-if="
                helpOrder.status == 'HELPORDER_STATUS_DIAOCHA' ||
                helpOrder.status == 'HELPORDER_STATUS_CHECK' ||
                helpOrder.status == 'HELPORDER_STATUS_CHECK_FAIL'
              "
            >
              <Form-item>
                <Button
                  @click="submitEditSecound"
                  :loading="submitLoading"
                  type="primary"
                  icon="ios-create-outline"
                  style="margin-right: 5px"
                  v-has="'helpOrderDamagelistCommit'"
                  >修改并保存</Button
                >
              </Form-item>
            </div>
          </Form>
          <Form
            v-if="node == 2"
            ref="formSecound"
            :model="form"
            :label-width="100"
          >
            <FormItem label="损失项目名称" prop="damageProjectName">
              <Input v-model="form.damageProjectName" style="width: 400px" />
            </FormItem>
            <FormItem label="损失项目编码" prop="damageProjectId">
              <Input v-model="form.damageProjectId" style="width: 400px" />
            </FormItem>
            <FormItem label="供应商级别" prop="supplierLevel">
              <Select
                v-model="form.supplierLevel"
                placeholder="请选择"
                style="width: 400px"
              >
                <Option
                  v-for="(op, opindx) in this.$store.state.dict.supplierLevels"
                  :value="op.value"
                  :key="opindx"
                  >{{ op.title }}</Option
                >
              </Select>
            </FormItem>
            <FormItem label="单价" prop="price">
              <InputNumber
                @on-change="calculateTotalEdit"
                :max="10000000"
                :min="0"
                v-model="form.price"
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <FormItem label="损失数量" prop="count">
              <InputNumber
                @on-change="calculateTotalEdit"
                :max="10000000"
                :min="0"
                v-model="form.count"
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <FormItem label="损失小计" prop="totalPrice">
              <InputNumber
                @on-change="calculateTotalEdit"
                :max="10000000"
                :min="0"
                v-model="form.totalPrice"
                readonly
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <FormItem
              label="审核单价"
              prop="checkPrice"
              v-if="op_type == 'check'"
            >
              <InputNumber
                @on-change="calculateTotalEdit"
                :max="10000000"
                :min="0"
                v-model="form.checkPrice"
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <FormItem
              label="审核损失项目数量"
              prop="checkCount"
              v-if="op_type == 'check'"
            >
              <InputNumber
                @on-change="calculateTotalEdit"
                :max="10000000"
                :min="0"
                v-model="form.checkCount"
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <FormItem
              label="审核小计"
              prop="checkTotalPrice"
              v-if="op_type == 'check'"
            >
              <InputNumber
                @on-change="calculateTotalEdit"
                :max="10000000"
                :min="0"
                v-model="form.checkTotalPrice"
                readonly
                style="width: 400px"
              ></InputNumber>
            </FormItem>
            <div
              v-if="
                helpOrder.status == 'HELPORDER_STATUS_DIAOCHA' ||
                helpOrder.status == 'HELPORDER_STATUS_CHECK' ||
                helpOrder.status == 'HELPORDER_STATUS_CHECK_FAIL'
              "
            >
              <Form-item>
                <Button
                  @click="submitEditThird"
                  :loading="submitLoading"
                  type="primary"
                  icon="ios-create-outline"
                  style="margin-right: 5px"
                  v-has="'helpOrderDamagelistCommitSecound'"
                  >修改并保存</Button
                >
              </Form-item>
            </div>
          </Form>
        </Col>
      </Row>
    </Card>
    <!-- 添加二级数据 -->
    <Modal
      :title="modalTitle"
      v-model="addSecoundDialog"
      :mask-closable="false"
      :width="500"
    >
      <Form ref="formAddSecound" :model="formAdd" :label-width="100">
        <FormItem label="车牌号" prop="carNo">
          <Input v-model="formAdd.carNo" />
        </FormItem>
        <FormItem label="证件号" prop="idNo">
          <Input v-model="formAdd.idNo" />
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="addSecoundDialog = false">取消</Button>
        <Button
          type="primary"
          :loading="submitLoading"
          @click="submitAddSecound"
          >提交</Button
        >
      </div>
    </Modal>

    <!-- 添加三级数据 -->
    <Modal
      :title="modalTitle"
      v-model="addThirdDialog"
      :mask-closable="false"
      :width="550"
    >
      <Form ref="formAddThird" :model="formAdd" :label-width="100">
        <FormItem label="损失项目名称" prop="damageProjectName">
          <Input v-model="formAdd.damageProjectName" />
        </FormItem>
        <FormItem label="损失项目编码" prop="damageProjectId">
          <Input v-model="formAdd.damageProjectId" />
        </FormItem>
        <FormItem label="供应商级别" prop="supplierLevel">
          <Select v-model="formAdd.supplierLevel" placeholder="请选择">
            <Option
              v-for="(op, opindx) in this.$store.state.dict.supplierLevels"
              :value="op.value"
              :key="opindx"
              >{{ op.title }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="单价" prop="price">
          <InputNumber
            @on-change="calculateTotalAdd"
            :max="10000000"
            :min="0"
            v-model="formAdd.price"
            style="width: 400px"
          ></InputNumber>
        </FormItem>
        <FormItem label="损失数量" prop="count">
          <InputNumber
            @on-change="calculateTotalAdd"
            :max="10000000"
            :min="0"
            v-model="formAdd.count"
            style="width: 400px"
          ></InputNumber>
        </FormItem>
        <FormItem label="损失小计" prop="totalPrice">
          <InputNumber
            @on-change="calculateTotalAdd"
            :max="10000000"
            :min="0"
            v-model="formAdd.totalPrice"
            readonly
            style="width: 400px"
          ></InputNumber>
        </FormItem>
        <FormItem label="审核单价" prop="checkPrice">
          <InputNumber
            @on-change="calculateTotalAdd"
            :max="10000000"
            :min="0"
            v-model="formAdd.checkPrice"
            style="width: 400px"
          ></InputNumber>
        </FormItem>
        <FormItem label="审核损失项目数量" prop="checkCount">
          <InputNumber
            @on-change="calculateTotalAdd"
            :max="10000000"
            :min="0"
            v-model="formAdd.checkCount"
            style="width: 400px"
          ></InputNumber>
        </FormItem>
        <FormItem label="审核小计" prop="checkTotalPrice">
          <InputNumber
            @on-change="calculateTotalAdd"
            :max="10000000"
            :min="0"
            v-model="formAdd.checkTotalPrice"
            readonly
            style="width: 400px"
          ></InputNumber>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="addThirdDialog = false">取消</Button>
        <Button type="primary" :loading="submitLoading" @click="submitAddThird"
          >提交</Button
        >
      </div>
    </Modal>
  </div>
</template>

<script>
import { getDamageListData, addHelperDamageData, addHelperDamageDetailData, editHelperDamageData, editHelperDamageDetailData, helperDamageClassifyDecide } from "@/api/index";
import dictUtil from "@/libs/dictUtil";
export default {
  name: "damage-list",
  props: {
    helpOrder: {
      type: Object,
    },
    op_type: {
      type: String,
    }
  },
  watch: {
    helpOrder: function () {
      if (this.helpOrder == null) {
        this.horder = {};
      } else {
        this.horder = this.helpOrder;
        this.init();
      }
    },
    data: {
      handler (newValue, oldValue) {
        console.log("变了========= 计算总金额")
        this.calculateTotalMoney();
      },
      deep: true,
    }
  },
  data () {
    return {
      horder: {},
      loading: false, // 树加载状态
      maxHeight: "500px",
      strict: true,
      loadingEdit: false, // 编辑上级树加载状态
      formValidate: {},
      modalTitle: "", // 添加标题
      editTitle: "", // 编辑节点名称
      searchKey: "", // 搜索树
      form: {
        // 编辑对象初始化数据
        id: "",
        title: "",
        parentId: "",
        parentTitle: "",
        sortOrder: 0,
        status: 0,
      },
      formAdd: {
        // 添加对象初始化数据
      },

      submitLoading: false,
      data: [],
      dataEdit: [],
      addSecoundDialog: false,
      addThirdDialog: false,
      node: 0, //当前编辑节点层级
      totalDamage: 0
    };
  },
  methods: {
    calculateTotalMoney () {
      this.totalDamage = 0
      if (this.data != null) {
        let total = 0
        this.data.forEach(node0 => {
          //start node0
          node0.totalDamage = 0
          if (node0 != null && node0.children != null && node0.children.length > 0) {
            let node0Total = 0

            node0.children.forEach(node1 => {
              //start node1
              node1.totalDamage = 0
              if (node1 != null && node1.children != null && node1.children.length > 0) {
                let node1Total = 0
                node1.children.forEach(node2 => {
                  if (node2.price == null) node2.price = 0
                  if (node2.count == null) node2.count = 0
                  node2.totalPrice = node2.price * node2.count
                  if (node2.checkPrice == null) node2.checkPrice = node2.price
                  if (node2.checkCount == null) node2.checkCount = node2.count
                  node2.checkTotalPrice = node2.checkPrice * node2.checkCount
                  node1Total += node2.totalPrice
                })
                node1.totalDamage = node1Total
              }
              node0Total += node1.totalDamage
            })
            node0.totalDamage = node0Total
          }
          total += node0.totalDamage
        })
        this.totalDamage = total
      }
    },
    getDict (feeType) {
      let dic = dictUtil.reflectDictData(
        feeType,
        this.$store.state.dict.feeTypes
      );
      return dic.title;
    },
    calculateTotalAdd () {
      //todo  如果是调查操作  这两个价格需要同步
      if (this.formAdd.price == null) {
        this.formAdd.price = 0
      }
      if (this.formAdd.count == null) {
        this.formAdd.count = 0
      }
      if (this.formAdd.checkPrice == null) {
        this.formAdd.checkPrice = 0
      }
      if (this.formAdd.checkCount == null) {
        this.formAdd.checkCount = 0
      }
      this.formAdd.totalPrice = this.formAdd.price * this.formAdd.count
      this.formAdd.checkTotalPrice = this.formAdd.checkPrice * this.formAdd.checkCount
      this.$forceUpdate()
    },
    calculateTotalEdit () {
      //todo  如果是调查操作  这两个价格需要同步
      if (this.form.price == null) {
        this.form.price = 0
      }
      if (this.form.count == null) {
        this.form.count = 0
      }
      if (this.form.checkPrice == null) {
        this.form.checkPrice = 0
      }
      if (this.form.checkCount == null) {
        this.form.checkCount = 0
      }
      this.form.totalPrice = this.form.price * this.form.count
      this.form.checkTotalPrice = this.form.checkPrice * this.form.checkCount
      this.$forceUpdate()
    },
    renderContent (h, { root, node, data }) {
      console.log(node);
      if (data.node == 0) {
        //损失分类
        let dict = dictUtil.reflectDictData(
          data.feeType,
          this.$store.state.dict.feeTypes
        );
        return h(
          "span",
          {
            style: {
              display: "inline-block",
              width: "100%",
            },
          },
          [
            h("span", [h("span", dict.title)]),
            h(
              "span",
              {
                style: {
                  display: "inline-block",
                  float: "right",
                  marginRight: "32px",
                  marginLeft: "32px",
                },
              },
              "-￥" + data.totalDamage
            ),
          ]
        );
      } else if (data.node == 1) {
        //二级损失分类
        return h(
          "span",
          {
            style: {
              display: "inline-block",
              width: "100%",
            },
          },
          [
            h("span", [h("span", data.carNo + "-" + data.idNo)]),
            h(
              "span",
              {
                style: {
                  display: "inline-block",
                  float: "right",
                  marginRight: "32px",
                  marginLeft: "32px",
                },
              },
              "-￥" + data.totalDamage
            ),
          ]
        );
      } else {
        //三级损失分类
        return h(
          "span",
          {
            style: {
              display: "inline-block",
              width: "100%",
            },
          },
          [
            h("span", [h("span", data.damageProjectName)]),
            h(
              "span",
              {
                style: {
                  display: "inline-block",
                  float: "right",
                  marginRight: "32px",
                  marginLeft: "32px",
                },
              },
              "-￥" + data.totalPrice
            ),
          ]
        );
      }
    },
    init () {
      this.horder = this.helpOrder;
      // 初始化一级节点
      this.getParentList();
      // 初始化一级节点为编辑上级节点使用
      // this.getParentListEdit();
    },
    getParentList () {
      this.loading = true;
      getDamageListData({ helpOrderId: this.horder.id }).then((res) => {
        this.loading = false;
        if (res.success) {
          if (res.result.damageListClassifyItems != null) {
            this.data = res.result.damageListClassifyItems;
          } else {
            this.data = [];
          }
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
        this.form = data
        console.log(data)
        if (data.node == 0) {
          //顶层节点
          let dict = dictUtil.reflectDictData(
            data.feeType,
            this.$store.state.dict.feeTypes
          );
          this.editTitle = dict.title;
          this.node = 0;
        } else if (data.node == 1) {
          //二级节点
          this.editTitle = data.carNo + "-" + data.idNo;
          this.node = 1;
        } else {
          //三级节点
          this.editTitle = data.damageProjectName;
          this.node = 2;
        }
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
    submitEditSecound () {
      if (!this.form.id) {
        this.$Message.warning("请先点击选择要修改的节点");
        return;
      }
      if (this.form.carNo == null || this.form.carNo.length == 0) {
        this.$Message.warning("车牌号不能为空");
        return
      }
      if (this.form.idNo == null || this.form.idNo.length == 0) {
        this.$Message.warning("证件号不能为空");
        return
      }

      this.submitLoading = true;
      // 避免传入null字符串
      editHelperDamageData(this.form).then(res => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("编辑成功");
          //更新树结构中的数据
          let data = this.$refs.tree.getSelectedNodes()[0];
          data.carNo = res.result.carNo;
          data.idNo = res.result.idNo;
        }
      });

    },
    submitEditThird () {
      if (!this.form.id) {
        this.$Message.warning("请先点击选择要修改的节点");
        return;
      }
      if (this.form.damageProjectName == null || this.form.damageProjectName.length == 0) {
        this.$Message.warning("损失项目名称不能为空");
        return
      }
      if (this.form.damageProjectId == null || this.form.damageProjectId.length == 0) {
        this.$Message.warning("损失项目编码不能为空");
        return
      }
      if (this.form.supplierLevel == null || this.form.supplierLevel.length == 0) {
        this.$Message.warning("供应商级别不能为空");
        return
      }
      if (this.form.price == null) {
        this.$Message.warning("单价不能为空");
        return
      }
      if (this.form.count == null) {
        this.$Message.warning("损失项目数量不能为空");
        return
      }
      this.submitLoading = true;
      // 避免传入null字符串
      editHelperDamageDetailData(this.form).then(res => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("编辑成功");
          //更新树结构中的数据
          let data = this.$refs.tree.getSelectedNodes()[0];
          data.damageProjectName = res.result.damageProjectName;
          data.damageProjectId = res.result.damageProjectId;
          data.supplierLevel = res.result.supplierLevel;
          data.price = res.result.price;
          data.count = res.result.count;
          data.totalPrice = res.result.totalPrice;
          data.checkPrice = res.result.checkPrice;
          data.checkCount = res.result.checkCount;
          data.checkTotalPrice = res.result.checkTotalPrice;
        }
      });
    },
    submitAddSecound () {

      if (this.formAdd.carNo == null || this.formAdd.carNo.length == 0) {
        this.$Message.warning("车牌号不能为空");
        return
      }
      if (this.formAdd.idNo == null || this.formAdd.idNo.length == 0) {
        this.$Message.warning("证件号不能为空");
        return
      }
      this.submitLoading = true;
      addHelperDamageData(this.formAdd).then(res => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("添加成功");
          this.addSecoundDialog = false;
          let data = this.$refs.tree.getSelectedNodes()[0];
          const children = data.children || [];
          res.result.node = 1
          children.push(res.result);
          this.$set(data, 'children', children);
        }
      });
    },
    submitAddThird () {
      if (this.formAdd.damageProjectName == null || this.formAdd.damageProjectName.length == 0) {
        this.$Message.warning("损失项目名称不能为空");
        return
      }
      if (this.formAdd.damageProjectId == null || this.formAdd.damageProjectId.length == 0) {
        this.$Message.warning("损失项目编码不能为空");
        return
      }
      if (this.formAdd.supplierLevel == null || this.formAdd.supplierLevel.length == 0) {
        this.$Message.warning("供应商级别不能为空");
        return
      }
      if (this.formAdd.price == null) {
        this.$Message.warning("单价不能为空");
        return
      }
      if (this.formAdd.count == null) {
        this.$Message.warning("损失项目数量不能为空");
        return
      }
      this.submitLoading = true;
      addHelperDamageDetailData(this.formAdd).then(res => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("添加成功");
          this.addThirdDialog = false;
          let data = this.$refs.tree.getSelectedNodes()[0];
          const children = data.children || [];
          res.result.node = 2
          children.push(res.result);
          this.$set(data, 'children', children);
        }
      });
    },
    add () {
      if (!this.form.id) {
        this.$Message.warning("请先点击选择一个节点");
        return;
      }
      let data = this.$refs.tree.getSelectedNodes()[0];
      if (data.node == 0) {
        //添加损失二级数据
        this.addSecoundDialog = true;
        this.formAdd = {
          helperOrderId: this.horder.id,
          helperDamageClassifyId: data.id
        };
      } else if (data.node == 1) {
        //添加损失三级数据
        this.addThirdDialog = true;
        this.formAdd = {
          helperDamageId: data.id
        };

      } else {
        //三级数据不允许添加子数据
        this.$Message.warning("该数据下不允许添加子数据");
        return;
      }
      this.modalTitle = "添加子数据";

    },
    addRoot () {
      this.modalTitle = "添加一级节点";
      this.showParent = false;
      this.formAdd = {
        parentId: 0,
        sortOrder: 0,
        status: 0,
      };
      this.modalVisible = true;
    },

    delAll () {
      if (this.selectCount <= 0) {
        this.$Message.warning("您还未勾选要删除的数据");
        return;
      }
      this.$Modal.confirm({
        title: "确认删除",
        content:
          "您确认要删除所选的 " + this.selectCount + " 条数据及其下级所有数据?",
        loading: true,
        onOk: () => {
          let ids = "";
          this.selectList.forEach(function (e) {
            ids += e.id + ",";
          });
          ids = ids.substring(0, ids.length - 1);
          // this.deleteRequest("请求路径，如/tree/delByIds/" + ids).then(res => {
          //   this.$Modal.remove();
          //   if (res.success) {
          //     this.$Message.success("删除成功");
          //     this.selectList = [];
          //     this.selectCount = 0;
          //     this.cancelEdit();
          //     this.init();
          //   }
          // });
          // 模拟成功
          this.$Modal.remove();
          this.$Message.success("删除成功");
          this.selectList = [];
          this.selectCount = 0;
          this.cancelEdit();
        },
      });
    },
    isDecideChange (item) {
      if (item == null || item.id == null || item.id.length == 0) {
        return;
      }
      item.isDecide = !item.isDecide
      helperDamageClassifyDecide(item).then(resp => {
        if (resp.success) {
          this.$Message.success("操作成功");

          this.$forceUpdate();
        }
      })
    }
  },
  mounted () {
    // 计算高度
    let height = document.documentElement.clientHeight;
    this.maxHeight = Number(height - 287) + "px";
    // this.init();
  },
};
</script>