<template>
  <div :style="background" class="bgBackground">
    <div class="common-layout">
      <el-container>
        <el-header height="365px">
          <img class="gif1" alt="logo" :src="require('../assets/first/begin1.gif')">
        </el-header>
        <el-container>
          <el-aside width="0px"></el-aside>
          <el-main>

            <el-button @click="show" class="button1"></el-button>
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

      // 顶部导航背景图片配置
      background: {
        // 背景图片地址
        backgroundImage: 'url(' + require('../assets/first/kaishibg2.10.png') + ')',
        // 背景图片是否重复
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'white',
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
  background-image: url("../assets/first/kaishi_start.png");
  background-repeat:no-repeat;
  background-size:100% 100%;
  -moz-background-size:100% 100%;
  background-color: transparent;
}
.button1:hover{
  position: relative;
  left: 0;
  top:0;
  width:180px;
  height:60px;
  border:none;
  background-image: url("../assets/first/click_motion2.10.gif");
  background-repeat:no-repeat;
  background-size:100% 100%;
  -moz-background-size:100% 100%;
  background-color: transparent;
}
.gif1{
  position: relative;
  left: 0;
  top:0;
  width:800px;
  height:500px;

}
</style>
