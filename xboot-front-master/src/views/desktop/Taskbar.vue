<template>
  <div>
    <div class="application-section scope">
      <window v-for="app in apps" :key="app.id" :setting="app"></window>
    </div>
    <div class="taskbar-section scope">
      <div class="dog-start">
        <div class="dog-icons" @click="onStart">
          <a href="#"><i class="fa fa-futbol-o"></i></a>
        </div>
      </div>
      <div class="task-list">
        <div
          class="task-item"
          :class="{ actived: task.focus }"
          v-for="task in tasklist"
          :key="task.id"
          @click="onClick(task.id)"
          :style="{ width: itemWidth + 'px' }"
        >
          <span class="icon" :class="task.icon"></span>
          {{ task.title }}
        </div>
      </div>
      <div class="dog-tool">
        <div class="dog-task-icon">
          <i class="fa fa-television" aria-hidden="true"></i>
        </div>
        <div class="dog-task-icon" @click="onSidebar">
          <i class="fa fa-commenting-o" aria-hidden="true"></i>
        </div>
      </div>
      <transition
        enter-active-class="animate zoomIn"
        leave-active-class="animated bounceOutLeft"
      >
        <div class="dog-menu" v-show="showmenu">
          <div class="dog-menu-list">
            <ul>
              <li>应用列表</li>
              <li v-for="item in menulist" :key="item.id">
                <div class="menu-item" @click="onClickItem(item)">
                  <i class="icon" :class="item.icon"></i>
                  <span>{{ item.name }}</span>
                </div>
                <ul v-if="item.sublist" v-show="!item.subhidden">
                  <li v-for="sub in item.sublist" :key="sub.id">
                    <div
                      class="menu-item"
                      style="margin-left: 20px; text-align: left"
                      @click="onClickItem(sub)"
                    >
                      <i class="icon" :class="sub.icon"></i>
                      <span>{{ sub.name }}</span>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
          <div class="dog-menu-links">
            <ul>
              <li>
                <a href="#" @click="onLink('0000')"><span>使用帮助</span></a>
              </li>
              <li>
                <a href="#" @click="onLink('0001')"><span>壁纸</span></a>
              </li>
              <li>
                <a href="#" @click="onLink('0002')"><span>计算器</span></a>
              </li>
              <li>
                <a href="#" @click="onLockScreen()"><span>锁屏</span></a>
              </li>
            </ul>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import Window from './Window.vue';
import REGISTER from '@/libs/register.js'
export default {
  components: {
    Window
  },
  data () {
    return {
      menulist: [],
      setting: {}
    };
  },
  created () {
    REGISTER.application.forEach(item => {
      let object = {};
      if (item.sublist) {
        object.sublist = [];
        item.sublist.forEach(o => {
          let sub = {};
          sub.id = o.id;
          sub.name = o.name;
          sub.icon = o.icon;
          object.sublist.push(sub);
        });
        object.subhidden = true;
      }
      object.id = item.id;
      object.name = item.name;
      object.icon = item.icon;
      this.menulist.push(object);
    });
  },
  computed: {
    apps () {
      let apps = [];
      if (this.$store.state.core.tasks.length > 0) {
        this.$store.state.core.tasks.forEach(app => {
          if (app != null) {
            apps.push(app);
          }
        });
        return apps.sort((a, b) => {
          return a.date - b.date;
        });
      } else {
        return [];
      }

    },
    tasklist () {
      let tasks = [];
      this.$store.state.core.tasks.forEach((task, index) => {
        if (task != null) {
          if (index == 0 && task.min == false) {
            task.focus = true
          } else {
            task.focus = false
          }
          tasks.push(task);
        }
      });
      return tasks.sort((a, b) => { return a.date - b.date });
    },
    itemWidth () {
      if (this.$store.state.core.tasks.length < 5) {
        return 100;
      } else {
        let ww = document.body.clientWidth * 0.95;
        return Math.floor(ww / this.$store.state.core.tasks.length);
      }
    },
    showmenu () {
      return this.$store.state.core.startMenu;
    }
  },
  methods: {
    onClickItem (object) {
      //console.log('object====>' + JSON.stringify(object));
      if (object.sublist) {
        object.subhidden = !object.subhidden;
      } else {
        this.$store.dispatch('core/openApp', object.id);
        this.$store.commit('core/SELECT_ICON', '');
      }
    },
    onLink (id) {
      this.$store.dispatch('core/openApp', id);
      this.$store.commit('core/SELECT_ICON', '');
    },
    onClick (id) {
      this.$store.dispatch('core/minSwitch', id);
    },
    onStart () {
      this.$store.commit('core/OPEN_START_MENU');
    },
    onSidebar () {
      this.$store.dispatch('core/sidebarSwitch');
    },
    onLockScreen () {
      //this.$store.dispatch('session/lockScreen');
    }
  }
};
</script>

<style lang="less">
@import "../../styles/global";

.taskbar-section {
  width: 100%;
  height: @taskHeight;
  background: @taskbackground;
  position: absolute;
  bottom: 0;
  top: auto !important;
  left: 0;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.4);
}

.dog-start {
  min-width: 60px;
  margin: 0 auto;
  float: left;
  height: @taskHeight;
}

.dog-tool {
  min-width: 40px;
  margin: 0 auto;
  float: right;
  //border-left: 2px double #333333;
  height: @taskHeight;
  text-align: center;
}

.dog-task-icon {
  height: 100%;
  padding: 10px 10px;
  display: inline-block;
}
.dog-task-icon:hover {
  background-color: #cfe3fd;
}
.task-list {
  min-width: 80%; //calc(100% - 100px);
  float: left;
  overflow: hidden;
  //border-left: 2px double #333333;
  height: @taskHeight;
  cursor: default;

  .task-item {
    float: left;
    height: @taskHeight;
    max-width: 120px;
    min-width: 20px;
    padding: 0 20px 0 15px;
    text-align: center;
    line-height: @taskHeight;
    font-size: 14px;
    border-right: 1px solid #e9e9e9;

    /*color:#fff;*/
    /*background:#ccc;*/
    color: #aaa;
    //.Filter(saturate(0.2));

    &.actived {
      /*background:#4b8de4;*/
      /*color:#fff;*/
      color: #111;
      background: #fff;
      //.Filter(saturate(1));
    }

    .icon {
      display: inline-block;
      width: 25px;
      height: 25px;
      margin-right: 5px;
      background-size: cover;
      vertical-align: top;
      position: relative;
      top: 7px;
    }
  }
}

.dog-icons {
  padding: 0;
  list-style: none;
  margin: 1em;
  display: inline-block;
  margin: 0.15em;
  position: relative;
  font-size: 1.2em;
}

.dog-icons i {
  color: #fff;
  position: absolute;
  top: 10px;
  left: 10px;
  transition: all 265ms ease-out;
}
.dog-icons a {
  display: inline-block;
}
.dog-icons a:before {
  transform: scale(1);
  -ms-transform: scale(1);
  -webkit-transform: scale(1);
  content: " ";
  width: @taskHeight;
  height: @taskHeight;
  border-radius: 100%;
  display: block;
  background: linear-gradient(45deg, #1da1f2, #c648c8);
  transition: all 265ms ease-out;
}
.dog-icons a:hover:before {
  transform: scale(0);
  transition: all 265ms ease-in;
}
.dog-icons a:hover i {
  transform: scale(2.2);
  -ms-transform: scale(2.2);
  -webkit-transform: scale(2.2);
  color: #1da1f2;
  background: -webkit-linear-gradient(45deg, #1da1f2, #c648c8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  transition: all 265ms ease-in;
}

.fa-magic:before {
  content: "狼";
}
.fa {
  display: inline-block;
  font: normal normal normal 14px/1 FontAwesome;
  font-size: inherit;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.dog-menu {
  bottom: @taskHeight;
  left: 0px;
  border: solid 1px #102a3e;
  overflow: visible;
  display: inline-block;
  min-width: 450px;
  //margin: 60px 0 0 20px;
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  position: fixed;
  box-shadow: inset 0 0 1px #fff;
  -moz-box-shadow: inset 0 0 1px #fff;
  -webkit-box-shadow: inset 0 0 1px #fff;
  opacity: 0.9;
  background-color: #619bb9;
  background: -moz-linear-gradient(
    top,
    rgba(50, 123, 165, 0.75),
    rgba(46, 75, 90, 0.75) 50%,
    rgba(92, 176, 220, 0.75)
  );
  background: -webkit-gradient(
    linear,
    center top,
    center bottom,
    from(#327aa4),
    color-stop(45%, #2e4b5a),
    to(#5cb0dc)
  );
  ul {
    list-style: none;
  }
  .icon {
    display: inline-block;
    height: 18px;
    width: 18px;
    background-size: cover;
    margin-right: 5px;
  }
}

.dog-menu-list {
  width: 50%;
  float: left;
  background: #fff;
  border: solid 1px #365167;
  margin: 7px 0 7px 7px;
  box-shadow: 0 0 1px #fff;
  -moz-box-shadow: 0 0 1px #fff;
  -webkit-box-shadow: 0 0 1px #fff;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  max-height: 400px;
  overflow-y: auto;
  .menu-item {
    border: solid 1px transparent;
    display: block;
    padding: 3px;
    margin: 3px;
    color: #4b4b4b;
    text-align: left;
    text-decoration: none;
  }
  .menu-item:hover {
    border: solid 1px #7da2ce;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
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
  a img {
    border: 0;
    vertical-align: middle;
    margin: 0 5px 0 0;
  }
}

.dog-menu-links {
  width: 45%;
  float: right;
  margin: 5px;
  li.icon {
    text-align: center;
  }
  a {
    border: solid 1px transparent;
    display: block;
    margin: 5px 0;
    position: relative;
    color: #fff;
    text-decoration: none;
    min-width: 120px;
  }
  a:hover {
    border: solid 1px #000;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    box-shadow: 0 0 1px #fff;
    -moz-box-shadow: inset 0 0 1px #fff;
    -webkit-box-shadow: inset 0 0 1px #fff;
    background-color: #658da0;
    background: -moz-linear-gradient(
      center left,
      rgba(81, 115, 132, 0.55),
      rgba(121, 163, 184, 0.55) 50%,
      rgba(81, 115, 132, 0.55)
    );
    background: -webkit-gradient(
      linear,
      0% 100%,
      100% 100%,
      from(#517384),
      color-stop(50%, #79a3b8),
      to(#517384)
    );
  }
  a span {
    padding: 5px;
    display: block;
  }
  a:hover span {
    background: -moz-linear-gradient(
      center top,
      transparent,
      transparent 49%,
      rgba(2, 37, 58, 0.5) 50%,
      rgba(63, 111, 135, 0.5)
    );
    background: -webkit-gradient(
      linear,
      center top,
      center bottom,
      from(transparent),
      color-stop(49%, transparent),
      color-stop(50%, rgba(2, 37, 58, 0.5)),
      to(rgba(63, 111, 135, 0.5))
    );
  }
}
</style>

