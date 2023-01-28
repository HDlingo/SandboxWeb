<template>
  <DDR v-model:value=this.transform
       :style="{'background-image':'url('+this.url+')'}"
       class="img_field"
       :active=this.isActive
       @dblclick="this.isActive===false?isActive=true:isActive=false"
  >
    <p></p>
  </DDR>
</template>
<script>
import DDR from 'yoyoo-ddr-vue3'
import 'yoyoo-ddr-vue3/dist/yoyoo-ddr-vue3.css'
export default {
  components:{
    DDR
  },
  props:[
    "imgUrl"
  ],
  data(){
    return{
      transform: {
        x: 100,
        y: 100,
        width: 100,
        height: 100,
        rotation: 0
      },
      url:require("../assets/"+this.imgUrl),
      isActive:false,
    }
  }
}
</script>
<style>
.img_field {
  width:100%;
  height:100%;
  background-repeat: no-repeat;
  background-size:100% 100%;
}
</style>