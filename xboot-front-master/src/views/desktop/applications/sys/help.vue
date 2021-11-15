<template>
  <iframe
    v-show="iframeState"
    id="show-iframe"
    frameborder="0"
    name="showHere"
    target="show-iframe"
    scrolling="auto"
    src="help"
    width="100%"
    height="100%"
  ></iframe>
</template>
<script>
import { defineComponent } from '@vue/composition-api';
export default defineComponent({
  name: 'Help',
  data () {
    return {
      iframeState: true
    };
  },
  created () { },
  methods: {}
});
</script>
