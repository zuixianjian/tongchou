<template>
  <div
    class="window animated"
    :class="{
      focus: setting.focus,
      animating: this.animating,
      maximized: this.maximized,
      bounceIn: this.animatedIn,
      zoomOut: this.animatedOut,
    }"
    v-show="!setting.min"
    :style="{
      top: position.y + 'px',
      left: position.x + 'px',
      width: width + 'px',
      height: height + 'px',
      'z-index': setting.index,
    }"
    @mousedown="onFocus(setting.id)"
  >
    <header
      class="window-title"
      @mousedown.self="onMousedown($event)"
      @dblclick.self="onMax()"
    >
      <div class="icon" :class="setting.icon"></div>
      {{ setting.title }}
      <div class="window-control">
        <span class="button min" @mousedown="onMin(setting.id)"></span>
        <span class="button max" @mousedown="onMax()"></span>
        <span class="button close" @mousedown="onClose(setting.id)"></span>
      </div>
    </header>
    <div class="window-body" :ref="'window_' + setting.id">
      <keep-alive
        ><component :is="subComponent" :window="window"></component
      ></keep-alive>
    </div>
    <div class="resize-overlay" v-show="overlayShow"></div>
    <div
      class="resize-side"
      v-for="(value, index) in resizeSide"
      :key="index"
      v-show="resizable"
      :class="value"
      @mousedown.self="onResize(value)"
    ></div>
  </div>
</template>

<script>
import {
  defineComponent,
  defineAsyncComponent,
  markRaw,
} from "@vue/composition-api";
export default defineComponent({
  name: "Window",
  props: {
    setting: {
      id: String,
      focus: Boolean,
      hidden: Boolean,
      index: Number,
    },
  },
  watch: {

    setting: function (newValue, oldValue) {
      this.id = newValue.id;
      this.focus = newValue.focus;
      this.hidden = newValue.hidden;
      this.index = newValue.index;
    },
    // setting: {
    //   handler (newValue, oldValue) {
    //     this.id = newValue.id;
    //     this.focus = newValue.focus;
    //     this.hidden = newValue.hidden;
    //     this.index = newValue.index;
    //   },
    //   deep: true
    // }
  },
  data () {
    return {
      subComponent: {},
      timer: {},
      drag: false,
      resize: false,
      width: 200,
      height: 400,
      maximized: false,
      animating: false,
      animatedIn: false,
      animatedOut: false,
      resizable: true,
      position: {
        x: 0,
        y: 0,
      },
      oldposition: {},
      overlayShow: false,
      resizeSide: [
        "side-top",
        "side-bottom",
        "side-left",
        "side-right",
        "angle-top-left",
        "angle-top-right",
        "angle-bottom-left",
        "angle-bottom-right",
      ],
    };
  },
  computed: {
    window () {
      return { width: this.width, height: this.height, id: this.setting.id };
    },
  },
  created () {
    let w = document.body.clientWidth;
    let h = document.body.clientHeight;
    this.width = this.setting.width > 0 ? this.setting.width : w / 2;
    this.height = this.setting.height > 0 ? this.setting.height : w / 3;
    this.position.x = w / 2 - this.width / 2;
    this.position.y = (h - this.height) / 2;
    this.animatedIn = true;
    //console.log("created====>" +  this.setting.page)
    this.subComponent = markRaw(
      defineAsyncComponent(() =>
        import("./applications/" + this.setting.page + ".vue")
      )
    );
  },
  mounted () {
    //console.log("mounted====>")
    this.animatedIn = true;
    clearTimeout(this.timer);
    this.timer = setTimeout(() => {
      clearTimeout(this.timer);
      this.animatedIn = false;
    }, 400);
  },
  methods: {
    onFocus (id) {
      this.$store.dispatch("core/focus", id);
    },
    onClose (id) {
      this.animatedOut = true;
      clearTimeout(this.timer);
      this.timer = setTimeout(() => {
        clearTimeout(this.timer);
        this.$store.dispatch("core/close", id);
      }, 300);
    },
    onMin (id) {
      this.$store.dispatch("core/minOrShow", id);
    },
    onMax () {
      if (this.maximized && this.oldPosition) {
        this.animating = true;
        this.resizable = true;
        clearTimeout(this.timer);
        this.timer = setTimeout(() => {
          this.maximized = false;
          this.position.y = this.oldPosition.y;
          this.position.x = this.oldPosition.x;
          this.height = this.oldPosition.height;
          this.width = this.oldPosition.width;
          this.oldPosition = null;
          clearTimeout(this.timer);
          this.timer = setTimeout(() => {
            this.animating = false;
          }, 500);
        });
      } else {
        this.animating = true;
        this.resizable = false;
        clearTimeout(this.timer);
        this.timer = setTimeout(() => {
          this.maximized = true;
          this.oldPosition = {
            y: this.position.y,
            x: this.position.x,
            height: this.height,
            width: this.width,
          };
          this.position.y = 0;
          this.position.x = 0;
          this.height = document.body.clientHeight * 0.95;
          this.width = document.body.clientWidth;
          clearTimeout(this.timer);
          this.timer = setTimeout(() => {
            this.animating = false;
          }, 500);
        });
      }
    },
    onResize (direction) {
      this.resize = true;
      this.overlayShow = true;
      document.onmousemove = (event) => {
        if (this.resize) {
          let x = event.clientX;
          let y = event.clientY;
          let mx = document.body.clientWidth;
          let my = document.body.clientHeight * 0.95;
          x = x >= 0 ? (x >= mx ? mx : x) : 0;
          y = y >= 0 ? (y >= my ? my : y) : 0;
          if (direction.indexOf("top") >= 0) {
            this.height = this.height + (this.position.y - y);
            this.position.y = y;
          }
          if (direction.indexOf("left") >= 0) {
            this.width = this.width + (this.position.x - x);
            this.position.x = x;
          }
          if (direction.indexOf("bottom") >= 0) {
            this.height = this.height + (y - (this.position.y + this.height));
          }
          if (direction.indexOf("right") >= 0) {
            this.width = this.width + (x - (this.position.x + this.width));
          }
        }
      };
      document.onmouseup = () => {
        this.resize = false;
        this.overlayShow = false;
      };
    },
    onMousedown (e) {
      let w = e.clientX - this.position.x;
      let h = e.clientY - this.position.y;
      if (!this.maximized) {
        this.drag = true;
        document.onmousemove = (e) => {
          if (this.drag) {
            //let mx = document.body.clientWidth;
            //let my = document.body.clientHeight * 0.95;
            this.position.x = e.clientX - w;
            this.position.y = e.clientY - h;
            //this.position.x = this.position.x <= 0 ? 0 : this.position.x + this.width >= mx ? mx - this.width : this.position.x;
            //this.position.y = this.position.y <= 0 ? 0 : this.position.y + this.height >= my ? my - this.height : this.position.y;
          }
        };
        document.onmouseup = () => {
          //console.log("e===>" + this.drag)
          this.drag = false;
        };
      }
    },
  },
});
</script>

<style lang="less" rel="stylesheet/less">
@import "../../styles/global";

.window {
  position: absolute;
  border-radius: 5px;
  overflow: hidden;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.4);

  &.animating {
    transition: all ease 0.5s;
  }
  .window-body {
    background: #f9f9f9;
    right: 0;
    bottom: 0;
    left: 0;
    position: absolute;
    top: @titleHeight;
    width: 100%;
  }

  .window-title {
    width: 100%;
    background: rgba(255, 255, 230, 0.8);
    text-align: center;
    line-height: @titleHeight;
    height: @titleHeight;

    cursor: default;
    color: #aaa;
    .icon {
      position: absolute;
      top: 4px;
      left: 5px;
      height: 18px;
      width: 18px;
      background-size: cover;
    }
  }

  &.focus {
    box-shadow: 0 10px 35px rgba(0, 0, 0, 0.6);
    .window-title {
      //background: #f5f8ff;
      background: rgba(255, 255, 255, 0.4);
      color: #333;
    }
    z-index: 200;
  }

  &.maximized {
    .window-control .button.max {
      @margin: 5px;
      @diff: 2px;
      @w: 8px;
      @b: 1px;
      @top: 0px;
      @left: 0px;

      &:before {
        box-sizing: border-box;
        content: "";
        display: block;
        position: absolute;
        height: @w;
        width: @w;
        left: ((@titleHeight - 2 * @margin - @w) ./ 2)- @diff + @left;
        top: ((@titleHeight - 2 * @margin - @w) ./ 2) + @diff + @top;
        border: @b solid #fff;
        border-top-width: 2 * @b;
      }

      &:after {
        box-sizing: border-box;
        content: "";
        display: block;
        position: absolute;
        height: @w;
        width: @w;
        left: ((@titleHeight - 2 * @margin - @w) ./ 2) + @diff + @left;
        top: ((@titleHeight - 2 * @margin - @w) ./ 2)- @diff + @top;
        border: @b solid #fff;
        border-top-width: 2 * @b;
      }
    }
  }

  .window-control {
    @margin: 5px;
    position: absolute;
    right: 0;
    top: 0;
    height: @titleHeight;
    .button {
      display: block;
      position: relative;
      width: @titleHeight - 2 * @margin;
      height: @titleHeight - 2 * @margin;
      margin: @margin @margin 0 0;
      float: left;
      border-radius: 3px;
    }

    .min {
      background: #81c2d0;
      &:before {
        box-sizing: border-box;
        @w: 12px;
        @h: 2px;
        content: "";
        display: block;
        position: absolute;
        height: @h;
        width: @w;
        left: (@titleHeight - 2 * @margin - @w) ./ 2;
        bottom: ((@titleHeight - 2 * @margin - @h) ./ 2) - 4px;
        background: #fff;
      }
    }
    .max {
      background: #659a65;
      &:before {
        box-sizing: border-box;
        @w: 12px;
        @b: 1px;
        content: "";
        display: block;
        position: absolute;
        height: @w;
        width: @w;
        left: ((@titleHeight - 2 * @margin - @w) / 2);
        top: ((@titleHeight - 2 * @margin - @w) / 2);
        border: @b solid #fff;
        border-top-width: 2 * @b;
      }
    }

    .Rotate(@deg) {
      transform: rotate(@deg);
      -webkit-transform: rotate(@deg);
      -moz-transform: rotate(@deg);
    }

    .close {
      &:before,
      &:after {
        @w: 14px;
        @h: 2px;
        content: "";
        display: block;
        position: absolute;
        height: @h;
        width: @w;
        left: ((@titleHeight - 2 * @margin - @w) / 2);
        top: ((@titleHeight - 2 * @margin - @h) / 2);
        background: #fff;
        .Rotate(45deg);
      }
      &:after {
        .Rotate(-45deg);
      }
      background: #d26262;
    }
  }

  @reactionWidth: 4px;
  .resize-side {
    position: absolute;
  }
  .resize-overlay {
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
  }
  .side-right {
    right: 0;
    bottom: 0;
    height: 100%;
    width: @reactionWidth;
    cursor: e-resize;
  }
  .side-bottom {
    right: 0;
    bottom: 0;
    width: 100%;
    height: @reactionWidth;
    cursor: s-resize;
  }
  .side-left {
    left: 0;
    top: 0;
    height: 100%;
    width: @reactionWidth;
    cursor: w-resize;
  }
  .side-top {
    left: 0;
    top: 0;
    width: 100%;
    height: @reactionWidth;
    cursor: n-resize;
  }
  .angle-bottom-right {
    right: 0;
    bottom: 0;
    width: @reactionWidth*2;
    height: @reactionWidth*2;
    cursor: se-resize;
  }
  .angle-bottom-left {
    left: 0;
    bottom: 0;
    width: @reactionWidth*2;
    height: @reactionWidth*2;
    cursor: sw-resize;
  }
  .angle-top-right {
    top: 0;
    right: 0;
    width: @reactionWidth*2;
    height: @reactionWidth*2;
    cursor: ne-resize;
  }
  .angle-top-left {
    top: 0;
    left: 0;
    width: @reactionWidth*2;
    height: @reactionWidth*2;
    cursor: nw-resize;
  }
}
</style>
