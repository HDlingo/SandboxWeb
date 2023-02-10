<template>
  <div :style="background" class="bgBackground">
    <div class="common-layout">
      <el-container>
        <el-header height="365px"></el-header>
        <el-container>
          <el-aside width="0px"></el-aside>
          <el-main>
            <el-button :style="button1" @click="show" class="button1"></el-button>
            <template v-if="bol">
              <tips @ok="getData($event)" @cancel="close"></tips>
            </template>

          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<script>
import ImgRotatable from "@/components/ImgRotatable";
import router from "@/router";
import { ref } from 'vue'
import { ElButton, ElDialog } from 'element-plus'
import tips from "./tips.vue";
export default {
  name: "FirstPage",
  data() {
    return {
      bol: false,
      str: '',
      button1:{
        backgroundImage: 'url(' + require('../assets/first/kaishi_start.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top'
      },
      // 顶部导航背景图片配置
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
        backgroundPosition: 'center top'
      }
    };
  },
  components: {
    tips,
    ImgRotatable,
  },
  methods: {
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
  },
};
</script>

<style scoped>
.bgBackground{
  width:100%;
  height:600px;
}
.button1{
  position: relative;
  left: 0;
  top:0;
  width:180px;
  height:60px;
  border:none;
}

</style>
