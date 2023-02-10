<template>
  <div :style="background" class="mark">
    <div class="alert">
      <!-- 按钮 -->
      <div class="common-layout">
        <el-container>
          <el-header>
            <el-button :style="button2" @click="cancel" class="button2"></el-button>
            <div style="color:BLACK;font-size:250%;line-height:2em;margin-left:50px">为你的沙盘设置一个喜欢的地形吧</div>
          </el-header>
          <el-main>
            <div style="line-height:2em;font-size: 20px">地形</div>
            <div style="line-height:2em;font-size: 20px">地形</div>
            <div style="line-height:2em;font-size: 20px">地形</div>
            <div style="line-height:2em;font-size: 20px">地形</div>
            <div style="line-height:2em;font-size: 20px">地形</div>
            <div style="line-height:2em;font-size: 20px">单击</div>
          </el-main>
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
        backgroundImage: 'url(' + require('../assets/first/kaishi_bgnew.png') + ')',
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
  border-radius: 25px;
  padding: 20px;
  background: white;
  width: 800px;
  height: 400px;
  z-index: 1000;
}

.button1{
  position: relative;
  left: 0;
  top:0;
  width:220px;
  height:60px;
  border:none;
  z-index:10;
}
.button2{
  position: relative;
  left: 0;
  top:0;
  width:60px;
  height:60px;
  border:none;
  float:right;
}
</style>
