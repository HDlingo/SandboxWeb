<template>
  <div :style="background" class="sandBox" >
    <div class="common-layout">
      <el-container>
        <el-header height="100px">
          <el-button :style="button3" @click="show" class="button3"></el-button>
          <el-button :style="button4" @click="show" class="button4"></el-button>

          <template v-if="bol">
            <course @ok="getData($event)" @cancel="close"></course>
          </template>
        </el-header>
        <el-container>
          <el-container>
            <el-main>
              <el-drawer
                  v-model="drawer"
                  direction="rtl"
              >
                <template #header>
                  <h3> Select </h3>
                </template>
                <el-scrollbar>
                  <div>
                    <el-row v-for="(toy,index) in toyList" :key="index">
                      <el-col :span="12" v-if="index*2<toyList.length">
                        <div class="img-field" @click="createNewImgRotate(toyList[2*index].name)">
                          <img class="img-item" :src="require('../assets/'+toyList[2*index].name)">
                        </div>
                      </el-col>
                      <el-col :span="12" v-if="index*2+1 < toyList.length">
                        <div class="img-field" @click="createNewImgRotate(toyList[2*index+1].name)">
                          <img class="img-item" :src="require('../assets/'+toyList[2*index+1].name)">
                        </div>
                      </el-col>
                    </el-row>
                  </div>
                </el-scrollbar>
              </el-drawer>
              <div>
                <div v-for="i in this.toyActiveNumber+1" :key="i" :id="i">
                </div>
                <div class="map">
                  <div class="gallery" >
                    <img :src="this.sandBoxBlockList[blockIndex].url" alt="a sandbox map block" v-for="blockIndex in this.sandBoxBlockOrder" :key="blockIndex">
                  </div>
                </div>
              </div>
            </el-main>
            <el-footer>
              <el-button :style="button5" @click="submitBox()" class="button5"></el-button>
            </el-footer>
          </el-container>

          <el-aside width="200px">
            <el-button :style="button6" @click="drawer=!drawer" class="button6"></el-button>
            <img class="img1" :src="require('../assets/box/shaju_drawer.png')">

          </el-aside>
        </el-container>
      </el-container>
    </div>
  </div>
</template>
<script>
import ImgRotatable from "@/components/ImgRotatable";
import {render, h} from "vue";
import {ElMessage} from 'element-plus'
import course from "./course.vue";

export default {
  components: {
    course,
    ImgRotatable
  },
  data() {
    return {
      bol: true,
      str: '',
      button3:{
        backgroundImage: 'url(' + require('../assets/first/shapan_help.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top'
      },
      button4:{
        backgroundImage: 'url(' + require('../assets/first/shapan_setting.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top'
      },
      button5:{
        backgroundImage: 'url(' + require('../assets/first/shapan_submit.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top'
      },
      button6:{
        backgroundImage: 'url(' + require('../assets/box/shaju_open.png') + ')',
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
        backgroundImage: 'url(' + require('../assets/first/shapan_bg.png') + ')',
        // 背景图片是否重复
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center top'
      },
      drawer: false,
      toyActiveNumber: 0,
      toyActiveList: [],
      sandBoxBlockOrder: [
        0, 0, 0, 0, 1, 1, 1, 0, 2
      ],
      toyList: [
        {
          name: 'logo.png'
        },
        {
          name: 'morty.png'
        },
        {
          name: '3.png'
        },
        {
          name: '4.gif'
        },
        {
          name: '5.png'
        },
        {
          name: '6.png'
        },
        {
          name: '7.png'
        },
        {
          name: '8.jpg'
        },
        {
          name: '9.png'
        },
        {
          name: '10.png'
        },
        {
          name: '11.gif'
        },
        {
          name: '12.png'
        },
        {
          name: '13.png'
        },
      ],
      sandBoxBlockList: [
        {
          url: require("../assets/sandBoxBlocks/grass.jpg")
        },
        {
          url: require("../assets/sandBoxBlocks/ground.jpg")
        },
        {
          url: require("../assets/sandBoxBlocks/sand.png")
        }
      ],
    }
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

    createNewImgRotate(name) {
      //生成唯一ID并记录
      let newId = Symbol();
      this.toyActiveList.push({
        componentId: newId, //用于标识的唯一ID
        name: name,
        transform: {}
      })
      this.toyActiveNumber++;
      let parent = document.getElementById(this.toyActiveNumber);
      let instance = h(ImgRotatable, {
        imgUrl: name,
        componentId: newId,
        key: this.toyActiveNumber,
        onSavePos: this.savePosHandler,
        onRemoveToy: this.removeToyComponent,
      },);
      instance.appContext = app._context;
      render(instance, parent);
      ElMessage({
        message: "成功添加 " + name.split('.')[0] + " !",
        type: 'success',
        duration: 2000,
        showClose: true
      })
    },
    savePosHandler(e) {
      let {componentId, transform} = e;
      for (let i = 0; i < this.toyActiveList.length; ++i) {
        if (this.toyActiveList[i].componentId === componentId) {
          this.toyActiveList[i].transform = transform;
          return
        }
      }
    },
    removeToyComponent(e) {
      let {componentId} = e;
      for (let i = 0; i < this.toyActiveList.length; ++i) {
        // 删除对应id元素
        if (this.toyActiveList[i].componentId === componentId) {
          let name = this.toyActiveList[i].name;
          this.toyActiveList.splice(i, 1);
          ElMessage({
            message: "成功删除 " + name.split('.')[0] + " !",
            type: 'success',
            duration: 2000,
            showClose: true
          })
          return
        }
      }
    },
    submitBox() {
      console.log(this.toyActiveList)
    }
  }
}
</script>
<style >
.sandBox {
  width:100%;
  height:600px;
}
.map{
  margin: 20px;
  display: grid;
  place-content: center;
}
.gallery {
  box-shadow:  25px 25px 30px #b8b8b8,
  -25px -25px 30px #d1e1fe;
  --s: 80px; /* control the size */
  display: grid;
  grid: auto-flow var(--s) / repeat(3, var(--s));
  gap: 0px;
  place-items: center;
  margin: calc(var(--s) / 3);
  transform: rotate(35deg) skew(-10deg, -10deg);
}

.gallery > img {
  display: block;
  width: 142%; /* sqrt(2)*100% */
  aspect-ratio: 1;
  transform: rotate(-45deg);
  clip-path: polygon(50% 0%, 100% 50%, 50% 100%, 0 50%);
  cursor: pointer;
  transition: .2s linear;
}

.img-field {
  width: 60%;
  margin-left: 20%;
}

.img-item {
  width: 100%;
}
.button3{
  position: absolute;
  margin-top: 25px;
  margin-left: 20px;
  left: 0;
  top:0;
  width:50px;
  height:50px;
  border:none;
}

.button4{
  position: absolute;
  margin-top: 35px;
  margin-left: 90px;
  left: 8px;
  top:40px;
  width:50px;
  height:50px;
  border:none;
}

.button5{
  position: absolute;
  margin-top: 0;
  margin-left: 90px;
  left: 8px;
  top:480px;
  width:200px;
  height:55px;
  border:none;
}
.button6{
  position: absolute;
  margin-left: 10px;
  top:40px;
  width:50px;
  height:50px;
  border:none;
}
.img1{
  position: absolute;
  margin-left: 50px;
  top:0;
  width:50px;
  height:100%;
  border:none;
}
</style>