<template>
  <transition name="slide">
    <div class="sidebar" v-show="sidebarShow">
      <div class="sidebar-header">
        <div class="item" :class="{ active: tag === 1 }" @click="onTag(1)">
          消息
        </div>
        <div class="item" :class="{ active: tag === 2 }" @click="onTag(2)">
          通讯录
        </div>
        <div class="item" :class="{ active: tag === 3 }" @click="onTag(3)">
          日历
        </div>
      </div>
      <div class="sidebar-body" v-if="tag === 1">
        <template>
          <ul>
            <li
              class="message-item"
              v-for="item in this.messages.list"
              :key="item.id"
            >
              <span class="close" @click="onClose(item.id)"></span>
              <div class="title">{{ item.title }}</div>
              <div class="text">{{ item.content }}</div>
            </li>
          </ul>
          <div class="text" @click="onCleanMessage()">清空消息</div>
        </template>
      </div>
      <div class="sidebar-body" v-if="tag === 2">
        <ul>
          <li class="address-item">
            <span class="address-item-cell">张三</span>
            <span class="address-item-cell">13800000001</span>
            <span class="address-item-cell">xxxx</span>
          </li>
          <li class="address-item">
            <span class="address-item-cell">张三</span>
            <span class="address-item-cell">13800000001</span>
          </li>
        </ul>
      </div>
      <div class="sidebar-body" v-if="tag === 3">
        <div class="sidebar-body-calendar">
          <div class="sidebar-body-calendar-date normal-date">2020</div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import moment from 'moment';
import 'moment/locale/zh-cn';
moment.locale('zh-cn');
export default {
  name: 'IconGroup',
  model: {
    prop: 'sidebarShow',
    event: 'onShow'
  },
  props: {
    sidebarShow: { type: Boolean, default: false }
  },
  data () {
    return {
      tag: 1
    };
  },
  computed: {
    date () {
      return moment(this.$store.state.session.date, this.dateFormat).calendar();
    },
    dateFormat () {
      return this.$store.state.core.dateFormat;
    },
    messages () {
      return { "list": this.$store.state.message.list, "count": this.$store.state.message.list.length };
    },
    addressbooks () {
      return { "list": this.$store.state.message.list, "count": this.$store.state.message.list.length };
    },
  },
  methods: {
    moment,
    onTag (tag) {
      this.tag = tag;
    },
    onClose (id) {
      //console.log('close =========>' + id);
      this.$store.dispatch('message/deleteMessage', id)
    },
    onCleanMessage () {
      this.$store.dispatch('message/cleanMessage')
    }
  }
};
</script>

<style lang="less" rel="stylesheet/less">
@import "../../styles/global";
.sidebar {
  position: absolute;
  right: 0;
  top: 0px;
  height: calc(100% - @taskHeight);
  width: 300px;
  background-color: rgba(200, 200, 200, 0.7);
}

.sidebar-header {
  height: 50px;
  text-align: center;
  border-bottom: 1px outset;
}

.sidebar-header .item {
  min-width: 30px;
  width: 20%;
  height: 20px;
  margin: 15px 0;
  display: inline-block;
  font-size: 14px;
  color: #eaeaea;
  background-color: rgba(121, 121, 121, 0.5);
}

.sidebar-header .item:hover {
  cursor: pointer;
}

.sidebar-header .item.active {
  background-color: rgba(255, 255, 255, 0.5);
  color: #000;
}

.sidebar-body {
  position: relative;
  height: calc(100% - 70px);
  overflow-y: auto;
  padding: 10px;
}

.sidebar-body-calendar {
  margin: 15px 0;
}

.sidebar-body-calendar .sidebar-body-calendar-date {
  padding: 0 33px;
  color: #585858;
}

.sidebar-body-calendar-date.normal-date {
  font-size: 30px;
}

.sidebar-body-calendar-date.lunar-date {
}

.address-item {
  border: solid 1px transparent;
  display: block;
  padding: 5px;
  margin: 5px;
  color: #4b4b4b;
  background-color: #f9f9f9;
  border-radius: 3px;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  text-align: left;
  text-decoration: none;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.4);
}

.address-item-cell {
  padding: 0rem 15px 0px 15px;
}

.address-item:hover {
  border: solid 1px #7da2ce;
  box-shadow: inset 0 0 1px #fff;
  -moz-box-shadow: inset 0 0 1px #fff;
  -webkit-box-shadow: inset 0 0 1px #fff;
  background-color: #cfe3fd;
  background: -moz-linear-gradient(top, #dcebfd, #c2dcfd);
  background: -webkit-gradient(
    linear,
    center top,
    center bottom,
    from(#dcebfd),
    to(#c2dcfd)
  );
}

.message-item {
  margin: 10px auto;
  background-color: snow;
  text-decoration: none;
  font-size: 12px;
  font-family: Arial;
  line-height: 18px;
  padding: 5px 12px 5px 12px;
  border-radius: 6px;
  -moz-border-radius: 6px;
  -webkit-border-radius: 6px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.4);

  .title {
    font-weight: bold;
    padding: 3px;
    text-align: left;
  }

  .text {
    padding: 5px 0px;
    left: 10px;
    word-wrap: break-word;
    text-align: left;
  }
  .close {
    color: black;
    border-radius: 6px;
    line-height: 10px;
    text-align: center;
    height: 10px;
    width: 10px;
    font-size: 9px;
    padding: 1px;
    right: 15px;
    position: absolute;
  }
  /* use cross as close button */
  .close::before {
    content: "\2716";
  }
  .close:hover {
    cursor: pointer;
  }
}

.slide-enter-active {
  transition: all 0.5s;
}

.slide-leave-active {
  transition: all 0.5s;
}

.slide-enter,
.slide-leave-to {
  transform: translateX(300px);
}
</style>
