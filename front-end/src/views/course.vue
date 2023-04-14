<template>
  <div class="mark">
    <div class="alert">
      <!-- 按钮 -->
      <div class="common-layout">
        <el-container>
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
        backgroundImage: 'url(https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/first/jiaocheng_close.png )',
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
  left: 23%;
  top: 30%;
  width: 54%;
  height: 64%;
  z-index: 1000;
}

button {
  position: absolute;
  left: 42.5%;
  top: 66%;
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
  font-size: 1.2vw;
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
