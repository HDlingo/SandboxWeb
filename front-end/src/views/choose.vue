<template>
  <div :style="background" class="mark">
    <div class="alert">
      <!-- 按钮 -->
      <div class="common-layout">
        <el-container>
          <el-header height="300px">
          </el-header>
            <div class="box">
              <div v-for="(blockType,index) in this.blockSelectList"
                   :key="index"
                   :style="{'background-color': this.blockColorMap[blockType]}"
                   @click="this.blockSelectList[index] = (blockType + 1)%this.blockTypeNum"
              ></div>
            </div>
          <el-footer>

            <button @click="sure">
              <span class="text">下 一 步</span>
            </button>
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
        backgroundPosition: 'center',

      }
    }
  },
  methods: {
    // 点击确定
    sure() {
      let blockSelectList = this.blockSelectList
      let blockSelectList_3x3 = []
      for(let i=0;i<3;i++){
        blockSelectList_3x3[i] = []
        for(let j=0;j<3;j++){
          blockSelectList_3x3[i][j] = blockSelectList[i*3+j]
        }
      }
      let blockSelectList_3x3_2 = []
      for(let i=0;i<3;i++){
        blockSelectList_3x3_2[i] = []
        for(let j=0;j<3;j++){
          blockSelectList_3x3_2[i][j] = blockSelectList_3x3[i][j].toString()
          if(blockSelectList_3x3[i][j] === 0){
            if(i-1>=0 && blockSelectList_3x3[i-1][j] === 1){
              blockSelectList_3x3_2[i][j] += '_1'
            }else{
              blockSelectList_3x3_2[i][j] += '_0'
            }
            if(j-1>=0 && blockSelectList_3x3[i][j-1] === 1){
              blockSelectList_3x3_2[i][j] += '1'
            }else{
              blockSelectList_3x3_2[i][j] += '0'
            }
            if(i+1<=2 && blockSelectList_3x3[i+1][j] === 1){
              blockSelectList_3x3_2[i][j] += '1'
            }else{
              blockSelectList_3x3_2[i][j] += '0'
            }
            if(j+1<=2 && blockSelectList_3x3[i][j+1] === 1){
              blockSelectList_3x3_2[i][j] += '1'
            }else{
              blockSelectList_3x3_2[i][j] += '0'
            }
          }
        }
      }
      // 将blockSelectList_3x3_2转为一维数组
      blockSelectList_3x3_2 = blockSelectList_3x3_2.flat()
      this.$store.commit('setBlockSelectList',blockSelectList_3x3_2)
      router.push({
        name: 'SandBox',
      })
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
  left: 23%;
  top: 30%;
  width: 54%;
  height: 64%;
  z-index: 1000;
}


.box {
  position: absolute;
  left: 43%;
  top: 37%;
  display: grid;
  height: 13vw;
  width: 13vw;
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


button {
  position: absolute;
  left: 42.3%;
  top: 67%;
  border:none;
  z-index:10;
  align-items: center;
  background-image: linear-gradient(144deg,#a280ff, #6b65d6,#5b4cc9);
  border: 0;
  border-radius: 80px;
  box-shadow: rgba(151, 65, 252, 0.2) 0 15px 30px -5px;
  box-sizing: border-box;
  color: #FFFFFF;
  display: flex;
  font-family: Phantomsans, sans-serif;
  font-size: 18px;
  justify-content: center;
  line-height: 1em;
  width: 15vw;
  padding: 3px;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  cursor: pointer;
  transition: all .3s;

}

button:active,
button:hover {
  outline: 0;
}

button span {
  padding: 16px 24px;
  border-radius: 80px;
  width: 100%;
  height: 100%;
  transition: 300ms;
}

button:hover span {
  background: none;
}

button:active {
  transform: scale(0.9);
}

</style>
