<template>
  <div>
    <a-row>
      <a-col :span="12"
        ><a-statistic
          title="Active Users"
          :value="112893"
          style="margin-right: 50px"
      /></a-col>
      <a-col :span="12"
        ><a-statistic
          title="Account Balance (CNY)"
          :precision="2"
          :value="112893"
      /></a-col>
    </a-row>

    <a-alert
      message="Warning Text Warning Text Warning TextW arning Text Warning Text Warning TextWarning Text"
      type="warning"
      closable
      @close="onClose"
    />
    <a-alert
      message="Error Text"
      description="Error Description Error Description Error Description Error Description Error Description Error Description"
      type="error"
      closable
      @close="onClose"
    />
  </div>
</template>

<script></script>

<style></style>
