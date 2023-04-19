<template>
<!--  src/components/ImgRotatable.vue  -->
  <DDR v-model:value=this.transform
       :acceptRatio=true
       :style="{'background-image':'url('+this.url+')'}"
       class="img_field"
       :active=this.isActive
       @click.right.stop="removeSelf()"
       @contextmenu.prevent=""
       @dblclick.stop="changeActive()"
       v-if="isAlive"
       @dragstart="editStart"
       @rotatestart="editStart"
       @resizestart="editStart"
       @dragend="editEnd"
       @rotateend="editEnd"
       @resizeend="editEnd"
  >
    <p></p>
  </DDR>
</template>
<script>
import DDR from 'yoyoo-ddr-vue3'
import 'yoyoo-ddr-vue3/dist/yoyoo-ddr-vue3.css'

export default {
  components: {
    DDR
  },
  props: [
    "imgUrl",
    "componentId"
  ],
  data() {
    return {
      transform: {
        x: 100,
        y: 100,
        width: 100,
        height: 100,
        rotation: 0
      },
      url: "https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/toys/" + this.imgUrl,
      isActive: false,
      isAlive: true,
    }
  },
  methods: {
    editStart() {
      this.$emit("editStart")
    },
    editEnd() {
      this.$emit("editEnd")
    },
    changeActive() {
      if (this.isActive === false) {
        this.isActive = true;
      } else {
        this.$emit("savePos", {
          componentId: this.componentId,
          transform: this.transform
        })
        this.isActive = false;
      }
    },
    removeSelf() {
      this.$emit("removeToy", {
        componentId: this.componentId,
      })
      this.isAlive = false
    }
  }
}
</script>
<style scoped>
.img_field {
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 1;
}
</style>