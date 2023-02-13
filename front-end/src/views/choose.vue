<template>
  <div :style="background" class="mark">
    <div class="alert">
      <!-- 按钮 -->
      <div class="common-layout">
        <el-container>
          <el-header height="300px">
            <el-button :style="button2" @click="cancel" class="button2"></el-button>
          </el-header>
            <div class="box">
              <div v-for="(blockType,index) in this.blockSelectList"
                   :key="index"
                   :style="{'background-color': this.blockColorMap[blockType]}"
                   @click="this.blockSelectList[index] = (blockType + 1)%this.blockTypeNum"
              ></div>
            </div>
          <el-footer>
            <el-button :style="button1" @click="sure" class="button1"></el-button>
          </el-footer>
        </el-container>
      </div>

    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  data() {
    return {
      // 目前有两种地形，0为海洋，1为陆地
      blockTypeNum: 2,
      blockColorMap: ["#8cb4f2", "#fff1dc"],
      blockSelectList:[0,0,0,0,0,0,0,0,0],
      button1:{
        backgroundImage: 'url(' + require('../assets/first/daoyu_next.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top'
      },
      button2:{
        backgroundImage: 'url(' + require('../assets/first/jiaocheng_close.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top'
      },
      background: {
        // 背景图片地址
        backgroundImage: 'url(' + require('../assets/first/kaishibg2.10.png') + ')',
        // 背景图片是否重复
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top',

      }
    }
  },
  methods: {
    // 点击确定
    sure() {
      router.push('/SandBox')
      alert(this.blockSelectList)
      this.$emit('cancel')
    },
    // 点击取消
    cancel() {
      this.$emit('cancel')
    },
  }
};
</script>

<style scoped>
.mark {
  width: 100%;
  height: 100%;
  background: transparent;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 100;
  display: flex;
  align-items: center;
  justify-content: center;

}
.mark:after {
  content: "";
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
  /* 从父元素继承 background 属性的设置 */
  background: inherit;
  filter: blur(3px);
  z-index: 99;
}

.alert{

  background-image: url("../assets/first/hailu2.10.png");
  background-repeat:no-repeat;
  background-size:100% 100%;
  -moz-background-size:100% 100%;
  background-color: transparent;
  width: 695px;
  height: 450px;
  z-index: 1000;
}

.button1{
  position: relative;
  left: -0.5vw;
  top:-17vh;
  width:220px;
  height:60px;
  border:none;
  z-index:10;
}
.button2{
  position: relative;
  left: -20px;
  top:20px;
  width:60px;
  height:60px;
  border:none;
  float:right;
}

.box {
  position: relative;
  left: 16.5vw;
  top:-22vh;
  display: grid;
  height: 21vh;
  width: 21vh;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
}
.box > div {
  width: 100%;
  text-align: center;
}
.box > div:nth-child(1) {
  border-radius: 10px 0 0 0;
}
.box > div:nth-child(3) {
  border-radius: 0 10px 0 0;
}
.box > div:nth-child(7) {
  border-radius: 0 0 0 10px;
}
.box > div:nth-child(9) {
  border-radius: 0 0 10px 0;
}

</style>
