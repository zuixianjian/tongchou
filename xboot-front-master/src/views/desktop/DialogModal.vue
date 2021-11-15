<template>
  <div class="dialog-container">
    <div class="modal-mask" :class="modalClass"></div>
    <transition
      enter-active-class="animated zoomIn"
      leave-active-class="animated zoomOut"
      @after-leave="onAfter"
      @after-appear="onBefore"
    >
      <div
        class="dialog-modal"
        v-show="display"
        :class="{ inside: this.inside }"
        :style="{ width: this.width + 'px' }"
      >
        <header class="dialog-title">
          <div class="icon" :class="icon"></div>
          {{ title }}
          <div class="dialog-control">
            <span class="button close" @mousedown="onClose()"></span>
          </div>
        </header>
        <div class="dialog-body"><slot></slot></div>
        <div class="dialog-footer">
          <div>
            <button type="button" class="dogstar-btn" @click="onClose">
              <span>按 钮</span>
            </button>
            <button
              type="button"
              class="dogstar-btn dogstar-btn-primary"
              @click="onOk"
            >
              <span>按 钮</span>
            </button>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api"
export default defineComponent({
  name: 'DialogModal',
  props: {
    display: { type: Boolean, default: false },
    title: { type: String, default: '' },
    icon: { type: String, default: '' },
    width: { type: String, default: '400' },
    //height: { type: String, default: '400' },
    inside: {
      type: Boolean,
      default: false
    }
  },
  mounted () {
    if (this.inside) {
      let box = this.$el.parentNode;
      box.style.position = 'relative';
    }
  },
  computed: {
    modalClass () {
      return {
        'modal-hidden': !this.display,
        'modal-show': this.display,
        'inside': this.inside
      };
    }
  },
  created () { },
  methods: {
    onClose () {
      this.$emit('update:display', false);
      this.$emit('onClose');
      //console.log("onClose========>")
    },
    onOk () {
      this.$emit('update:display', false);
      //this.$emit('onOk');
      //console.log("onOk========>")
    },
    onBefore () {
      //this.maskStatus = true;
    },
    onAfter () {

    }
  }
})
</script>

<style lang="less" rel="stylesheet/less">
@import "../../styles/global";

.dialog-container {
  .modal-hidden {
    display: none;
    opacity: 0;
  }
  .modal-show {
    display: block;
    opacity: 1;
  }

  .modal-mask {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 100;
    background-color: rgba(0, 0, 0, 0.45);
  }

  .dialog-modal {
    top: 50%;
    left: 50%;
    -ms-transform: translate(-50%, -50%); /* IE 9 */
    -webkit-transform: translate(-50%, -50%); /* Safari and Chrome */
    transform: translate(-50%, -50%);
    position: fixed;
    border-radius: 5px;
    overflow: hidden;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.6);
    z-index: 101;

    &.animating {
      transition: all ease 0.5s;
    }
    .dialog-body {
      background: #f9f9f9;
      right: 0;
      bottom: 40px;
      left: 0;
      top: @titleHeight;
      width: 100%;
    }

    .dialog-footer {
      border-top: 1px solid #e8e8e8;
      background: #f9f9f9;
      text-align: right;
      width: 100%;
      height: 40px;
      padding: 5px;
    }

    .dialog-title {
      background: rgba(255, 255, 255, 0.4);
      color: #333;
      //.Filter(saturate(1.2));
      text-align: center;
      line-height: @titleHeight;
      height: @titleHeight;
      cursor: default;
      .icon {
        position: absolute;
        top: 4px;
        left: 5px;
        height: 18px;
        width: 18px;
        background-size: cover;
      }
    }

    .dialog-control {
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
          left: ((@titleHeight - 2 * @margin - @w) ./ 2);
          top: ((@titleHeight - 2 * @margin - @h) ./ 2);
          background: #fff;
          .Rotate(45deg);
        }
        &:after {
          .Rotate(-45deg);
        }
        background: #d26262;
      }
    }
  }
  /* 某个元素内部显示 */
  .inside {
    position: absolute;
  }
}
</style>
