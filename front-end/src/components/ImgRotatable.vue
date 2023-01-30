<template>
  <DDR v-model:value=this.transform
       :acceptRatio=true
       :style="{'background-image':'url('+this.url+')'}"
       class="img_field"
       :active=this.isActive
       @dblclick.stop="removeSelf()"
       @click.right.stop="changeActive()"
       @contextmenu.prevent=""
       v-if="isAlive"
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
    "imgUrl",
    "componentId"
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
      isAlive:true,
    }
  },
  methods:{
    changeActive(){
      if(this.isActive===false){
        this.isActive=true;
      }
      else{
        this.$emit("savePos",{
          componentId : this.componentId,
          transform : this.transform
        })
        this.isActive=false;
      }
    },
    removeSelf(){
      this.$emit("removeToy",{
        componentId : this.componentId,
      })
      this.isAlive=false
    }
  }
}
</script>
<style scoped>
.img_field {
  width:100%;
  height:100%;
  background-repeat: no-repeat;
  background-size:100% 100%;
}
</style>