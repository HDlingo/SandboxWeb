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

            <el-button @click="show" class="full-rounded">
              <span>沙 盘 探 索</span>
              <div class="border full-rounded"></div>
            </el-button>

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

button {
  width: 9.9em;
  height: 3em;
  font-size: 16px;
  position: relative;
  margin: auto;
  padding: 1em 2.5em 1em 2.5em;
  border: none;
  background: linear-gradient(to right,#fce9ff,#dad9ff);
  transition: all 0.1s linear;
  box-shadow: 0 0.4em 1em rgba(0, 0, 0, 0.1);
  z-index: 11;
}

button:hover{
  background: linear-gradient(to right,#fce9ff,#dad9ff);
}
button:active {
  transform: scale(0.95);
}

button span {
  color: #5d5bb6;
}

button .border {
  position: absolute;
  border: 0.15em solid #e9e0ff;
  transition: all 0.3s 0.08s linear;
  top: 50%;
  left: 50%;
  width: 9.7em;
  height: 3em;
  transform: translate(-50%, -50%);
  z-index: 10;
}

button:hover .border {
  display: block;
  width: 11em;
  height: 4em;
}

.full-rounded {
  border-radius: 2em;
}

.gif1{
  position: relative;
  left: 0;
  top:0;
  width:800px;
  height:500px;

}
</style>
