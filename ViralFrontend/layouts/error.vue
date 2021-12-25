<!-- Copyright © Viral Innovation - All Rights Reserved -->

<template>
  <div>
    <h1 class="title">{{ error.statusCode }} It Seems you are lost?</h1>
    <nuxt-link to="/" class="sub-title">Take Me Home</nuxt-link>
  </div>
</template>
<script>
export default {
  layout: "empty",
  props: {
    // eslint-disable-next-line vue/require-default-prop
    error: Object
  }
}
</script>
<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap');

  *
  {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
    scroll-behavior: smooth;
  }

  .title {
    color: #000;
    font-size: 60px;
    font-weight: 600;
    opacity: 1;
    padding-left:20px;
    padding-top: 30px;
    transition: all 0.5s ease;
    text-align: center;
  }

  .sub-title {
    color: #352ab8;
    font-size: 40px;
    font-weight: 600;
    opacity: 1;
    padding-left: 20px;
    padding-top: 30px;
    transition: all 0.5s ease;
  }

  .sub-title a {
    text-align: center;
  }
</style>
