<template>
  <div class="mark">
    <div class="alert">
      <!-- 按钮 -->
      <div class="common-layout">
        <el-container>
          <el-header>
            <el-button :style="button2" @click="cancel" class="button2"></el-button>
            <div style="color:BLACK;font-size:250%;line-height:2em;margin-left:50px">操作教程</div>
          </el-header>
          <el-main>
            <div style="line-height:2em;font-size: 20px">涉及到右侧抽屉的拖拽、鼠标左中右键选择(删除会上初步说是双击右键)</div>
            <div style="line-height:2em;font-size: 20px">设置里是控制bgm吗</div>
            <div style="line-height:2em;font-size: 20px">具体再议，主要是先放这个面板</div>
            <div style="line-height:2em;font-size: 20px">面板内容不需要点击，后续替换即可</div>
            <div style="line-height:2em;font-size: 20px">初次进入点击“开始探索”关闭面板</div>
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
        backgroundImage: 'url(' + require('../assets/first/jiaocheng_start.png') + ')',
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
  width:200px;
  height:50px;
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
