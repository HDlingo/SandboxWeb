<template>
  <div class="mark">
    <div class="alert">
      <!-- 按钮 -->
      <div class="common-layout">
        <el-container>
          <el-header height="300px">
            <el-button :style="button2" @click="cancel" class="button2"></el-button>
          </el-header>
          <el-main>

          </el-main>
          <el-footer>
            <button @click="sure">
              <span class="text">开 始 旅 程</span>
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
    }
  },
  methods: {
    // 点击确定
    sure() {
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
  background-image: url("../assets/box/jiaocheng_panel.png");
  background-repeat:no-repeat;
  background-size:100% 100%;
  -moz-background-size:100% 100%;
  background-color: transparent;
  width: 695px;
  height: 450px;
  z-index: 1000;
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

button {
  position: relative;
  left: 20vw;
  top:0vh;
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
  max-width: 100%;
  min-width: 140px;
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
