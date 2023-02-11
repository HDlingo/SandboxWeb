<template>
  <div :style="background" class="mark">
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
            <el-button :style="button1" @click="show" class="button1"></el-button>
            <template v-if="bol">
              <choose @ok="getData($event)" @cancel="close"></choose>
            </template>
          </el-footer>
        </el-container>
      </div>

    </div>
  </div>
</template>

<script>
import router from "@/router";
import choose from "./choose.vue";
import ImgRotatable from "@/components/ImgRotatable";

export default {
  data() {
    return {
      bol: false,
      str: '',
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
  components: {
    choose,
    ImgRotatable,
  },
  methods: {
    // 点击确定
    show() {
      this.bol = true;
    },
    // 获取数据方法
    getData(data) {
      // 关闭弹出层
      this.bol = false;
      console.log(data);
      this.str = data;
    },
    // 关闭弹窗
    close() {
      // 关闭弹出层
      this.bol = false;
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
  background-image: url("../assets/first/daoyu2.10.png");
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
  left: 0;
  top:0;
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
</style>
