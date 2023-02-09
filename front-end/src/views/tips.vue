<template>
  <div class="mark">
    <div class="alert">
      <!-- 按钮 -->
      <div class="common-layout">
        <el-container>
          <el-header>
            <el-button :style="button2" @click="cancel" class="button2"></el-button>
            <div style="color:BLACK;font-size:250%;line-height:2em;margin-left:50px">沙盘探索</div>
          </el-header>
          <el-main>
            <div style="line-height:2em;font-size: 20px">这个沙盘是给iland做测试用的</div>
            <div style="line-height:2em;font-size: 20px">你可以在沙盘中随意摆放喜欢的沙具</div>
            <div style="line-height:2em;font-size: 20px">在解压的过程中进行心里评测</div>
            <div style="line-height:2em;font-size: 20px">摆放结束后点击做下角的提交</div>
            <div style="line-height:2em;font-size: 20px">提交后将有一份问卷功能</div>
            <div style="line-height:2em;font-size: 20px">大概是这样，文字后续再琢磨</div>
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
    }
  },
  methods: {
    // 点击确定
    sure() {
      router.push('/SandBox')

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
  opacity:0.7;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  display: flex;
  align-items: center;
  justify-content: center;
}

.alert{
  border-radius: 25px;
  padding: 20px;
  background: white;
  width: 800px;
  height: 400px;
}

.button1{
  position: relative;
  left: 0;
  top:0;
  width:220px;
  height:60px;
  border:none;
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
