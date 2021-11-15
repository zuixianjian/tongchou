<style lang="less">
@import "../../../styles/single-common.less";
.colContent {
  display: flex;
  justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
}
</style>
<template>
  <div>
    <Card>
      <Form ref="form" :label-width="90">
        <Row
          type="flex"
          justify="center"
          :gutter="8"
          v-for="(item, index) in data"
          :key="index"
        >
          <Col span="12">
            <FormItem :label="getDict(item.classify)">
              <mine-upload-pic-thumb
                v-model="item.pics"
                :uploadFileUrl="uploadFileUrl"
              />
            </FormItem>
          </Col>
        </Row>

        <div
          class="colContent"
          v-if="
            order.status == 'HELPORDER_STATUS_DIAOCHA' ||
            order.status == 'HELPORDER_STATUS_CHECK' ||
            order.status == 'HELPORDER_STATUS_CHECK_FAIL'
          "
        >
          <Button
            @click="submit"
            :loading="submitLoading"
            type="primary"
            icon="ios-create-outline"
            style="margin-right: 5px"
            v-has="'helpOrderFileCommit'"
            >修改并保存</Button
          >
        </div>
      </Form>
    </Card>
  </div>
</template>

<script>
import { getHelpOrderPicDatas, saveHelpOrderPics, uploadMineFile } from "@/api/index";
import mineUploadPicThumb from "../../order/order-manager/mine-upload-pic-thumb.vue";
import dictUtil from "@/libs/dictUtil";
export default {
  name: "help-order-pics",
  components: { mineUploadPicThumb },
  watch: {
    horder: {
      handler (newValue, oldValue) {
        if (this.horder != null) {
          this.order = this.horder;
          this.getData();
        }
      },
      deep: true,
    },
  },
  props: ["horder"],
  data () {
    return {
      uploadFileUrl: uploadMineFile,
      order: {},
      data: [],
      submitLoading: false,
    };
  },
  methods: {
    getDict (helperOrderPicSort) {
      let dic = dictUtil.reflectDictData(
        helperOrderPicSort,
        this.$store.state.dict.helperOrderPicSorts
      );
      return dic.title;
    },
    init () {
      this.getData();
    },
    getData () {
      if (this.order.id == null) {
        return;
      }
      getHelpOrderPicDatas({ helpOrderId: this.order.id }).then((resp) => {
        if (resp.success) {
          if (resp.result == null) {
            this.data = [];
          } else {
            this.data = resp.result;
          }
        }
      });
    },
    submit () {
      this.submitLoading = true;
      let req = {};
      req.helpOrderPicsResps = this.data;
      req.helpOrderId = this.order.id;
      saveHelpOrderPics(req).then((resp) => {
        if (resp.success) {
          this.$Message.success("附件照片保存成功");
          this.submitLoading = false;
        }
      });
    },


  },
  mounted () {
    this.init();
  },
};
</script>
