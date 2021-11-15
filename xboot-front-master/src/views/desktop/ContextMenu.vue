<template>
  <div
    class="context-menu-section"
    v-show="isShow"
    :style="{
      top: position.y + 'px',
      left: position.x + 'px',
    }"
  >
    <div class="context-menu">
      <ul>
        <li
          v-for="item in list"
          :key="item.id"
          :class="{
            disabled: item.disabled,
            divide: item.type == 'divide',
            'list-item': item.type !== 'divide',
          }"
          @click="onClick(item)"
        >
          <i
            v-show="item.type != 'divide'"
            class="fa"
            :class="item.icon"
            aria-hidden="true"
          ></i
          >{{ item.text }}
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: 'ContextMenu',
  data () {
    return {
      icon: [
        {
          id: '1',
          text: '打开',
          type: 'default',
          disabled: false,
          icon: 'fa-magic',
          action: 'core/openTask'
        },
        {
          type: 'divide'
        },
        {
          id: '2',
          text: '使用帮助',
          type: 'default',
          disabled: false,
          icon: 'fa-android',
          action: 'core/help'
        }
      ],
      wall: [
        {
          id: '1',
          text: '使用帮助',
          type: 'default',
          disabled: false,
          icon: '',
          action: 'core/help'
        },
        {
          type: 'divide'
        },
        {
          id: '2',
          text: '锁屏',
          type: 'default',
          icon: 'fa-lock',
          disabled: false,
          action: 'session/lockScreen'
        },
        {
          id: '3',
          text: '壁纸设置',
          type: 'default',
          icon: 'fa-cog',
          disabled: false,
          action: 'core/display'
        }
      ]
    };
  },
  computed: {
    list () {
      if (this.$store.state.core.contextMenu.type === 'wall') {
        return this.wall;
      } else {
        return this.icon;
      }
    },
    position () {
      return this.$store.state.core.contextMenu;
    },
    isShow () {
      return this.position.x > 0 && this.position.y > 0;
    }
  },
  methods: {
    onClick (item) {
      let date = this.$store.state.core.contextMenu.data
      this.$store.dispatch(item.action, date);
      this.$store.commit('core/CLEAN_CONTEXT_MENU');
    }
  }
};
</script>

<style lang="less" rel="stylesheet/less">
.context-menu-section {
  position: absolute;
}
.context-menu {
  margin: 0;
  padding: 0;
  ul {
    background: rgba(255, 255, 255, 0.8);
    width: 100%;
    padding: 5px 0;
    list-style: none;
    border-radius: 5px;
    .list-item {
      cursor: default;
      color: #333;
      padding: 0 10px;
      &:hover {
        background: #4b8de4;
        color: #fff;
      }
      &.disabled {
        color: #999;
      }
    }
    .divide {
      border-top: 1px solid #ccc;
      margin: 5px;
    }
  }
}
</style>
