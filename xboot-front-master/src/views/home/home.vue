<style lang="less">
@import "./home.less";
@import "../../styles/common.less";
.colContent {
  display: flex;
  justify-content: center; /*主轴上居中*/
  align-items: center; /*侧轴上居中*/
  font-weight: bold;
  font-size: 250%;
}
</style>

<template>
  <div>
     <div class="colContent">今日验车码:{{ checkCode }}</div>
    <div v-show="currNav == 'xboot'" class="home">
     
    </div>
    <div
      v-show="
        currNav == 'doc' ||
        currNav == 'xboot-show' ||
        currNav == 'xpay' ||
        currNav == 'xmall'
      "
    ></div>
    <div v-show="currNav == 'app'"></div>
  </div>
</template>

<script>
import { ipInfo, getCheckCode } from "@/api/index";
import visitVolume from "./components/visitVolume.vue";
import visitSeparation from "./components/visitSeparation.vue";
import infoCard from "./components/infoCard.vue";
import show from "./show.vue";

import Cookies from "js-cookie";
import "gitalk/dist/gitalk.css";
import Gitalk from "gitalk";

export default {
  name: "home",
  components: {
    visitVolume,
    visitSeparation,
    infoCard,
    show
  },
  data () {
    return {
      showVideo: false,
      count: {
        createUser: 496,
        visit: 3264,
        collection: 24389305,
        transfer: 39503498
      },
      city: "",
      username: "",
      checkCode: ""
    };
  },
  computed: {

  },
  methods: {
    init () {
      let userInfo = JSON.parse(Cookies.get("userInfo"));
      this.username = userInfo.nickname;
      ipInfo().then(res => {
        if (res.success) {
          this.city = res.result;
        }
      });
      getCheckCode().then(res => {
        if (res.success) {
          this.checkCode = res.result.code;
        }
      });
    },

  },
  mounted () {
    this.init();
  }
};
</script>
