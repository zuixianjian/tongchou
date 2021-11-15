<template>
  <div>
    <Row class="header">
      <img src="../../assets/xboot.png" width="220px">
      <!-- <div class="description">{{ $t('xboot') }}</div> -->
    </Row>
  </div>
</template>

<script>
export default {
  name: "header"
};
</script>

<style lang="less">
.header {
  margin-top: 8vh;
  margin-bottom: 5vh;
  text-align: center;
  align-items: center;
  display: flex;
  flex-direction: row;/*这里可以不写，flex布局默认方向横向即row*/
  justify-content: center;
  align-items: center;
  .description {
    font-size: 30px;
    color: rgba(0, 0, 0, 0.45);
    margin-top: 1vh;
  }
}
</style>
