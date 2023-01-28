<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px" id="aside">
        <ImgRotatable img-url="logo.png"></ImgRotatable>
      </el-aside>
      <el-main id="main">Main</el-main>
    </el-container>
  </div>
</template>
<script>
  import ImgRotatable from "@/components/ImgRotatable";
  export default {
    components:{
      ImgRotatable
    },
    data(){
      return{

      }
    }
  }
</script>
<style>
  #aside{
    background-color: aquamarine;
  }
  #main{
    background-color: antiquewhite;
  }
</style>