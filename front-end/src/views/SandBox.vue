<template>
  <div :style="background" class="sandBox">
    <div id="box" class="canvasField vue-drag-scroll-out-wrapper"
         @wheel="editCanvasScale">
      <!--         v-dragscroll="{'active':this.canvasDraggable}"-->
      <DDR :active = this.boxMovable :resizable="false" :rotatable="false" @dblclick="changeBoxMovable">
        <div v-for="i in this.toyActiveNumber+1" :key="i" :id="i">
        </div>
        <div class="map" >
          <div class="gallery">
            <img :src="this.sandBoxBlockList[blockIndex].url" alt="a sandbox map block"
                 v-for="blockIndex in this.sandBoxBlockOrder" :key="blockIndex">
          </div>
        </div>
      </DDR>
    </div>
    <div class="common-layout">
      <el-container>
        <el-header height="100px">
          <el-button :style="button3" @click="show" class="button3"></el-button>
          <el-button :style="button4" @click="show" class="button4"></el-button>
          <el-button :style="{
            backgroundImage: 'url('+this.fixButtonImage+')'
          }" id="myImage" class="fix_button" @click="this.boxMovable=!this.boxMovable" ></el-button>
          <template v-if="bol">
            <course @ok="getData($event)" @cancel="close"></course>
          </template>
        </el-header>
        <el-container>
          <el-container>
            <el-drawer
                v-model="drawer"
                direction="rtl"
                style="user-select: none"
            >
              <template #header>
                <h3> Select </h3>
              </template>
              <el-scrollbar>
                <div>
                  <el-row v-for="(toy,index) in toyList" :key="index">
                    <el-col :span="12" v-if="index*2<toyList.length">
                      <div class="img-field" @click="createNewImgRotate(toyList[2*index])">
                        <img class="img-item" :src="require('../assets/toys/'+toyList[2*index].name+'.png')">
                      </div>
                    </el-col>
                    <el-col :span="12" v-if="index*2+1 < toyList.length">
                      <div class="img-field" @click="createNewImgRotate(toyList[2*index+1])">
                        <img class="img-item" :src="require('../assets/toys/'+toyList[2*index+1].name+'.png')">
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-scrollbar>
            </el-drawer>

            <el-footer>
              <el-button :style="button5" @click="submitBox()" class="button5"></el-button>
            </el-footer>
          </el-container>

          <el-aside width="200px">
            <el-button :style="button6" @click="drawer=!drawer" class="button6"></el-button>
<!--            <img class="img1" :src="require('../assets/box/shaju_drawer.png')">-->
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
import {dragscroll} from "vue-dragscroll";
import 'yoyoo-ddr-vue3/dist/yoyoo-ddr-vue3.css'
import DDR from 'yoyoo-ddr-vue3'
export default {
  directives: {
    'dragscroll': dragscroll
  },
  components: {
    course,
    ImgRotatable,
    DDR,
    ElMessage
  },
  data() {
    return {
      fixButtonImage:require("../assets/first/editing0.png"),
      boxMovable: false,
      canvasDraggable: true,
      bol: true,
      str: '',
      canvasScale: 1,
      button3: {
        backgroundImage: 'url(' + require('../assets/first/shapan_help.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center'
      },
      button4: {
        backgroundImage: 'url(' + require('../assets/first/shapan_setting.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center'
      },
      button5: {
        backgroundImage: 'url(' + require('../assets/first/shapan_submit.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center'
      },
      button6: {
        backgroundImage: 'url(' + require('../assets/box/shaju_open.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center',
        right: this.drawer===false? '30%':'0px'
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
        backgroundPosition: 'center'
      },
      drawer: false,
      toyActiveNumber: 0,
      toyActiveList: [],
      sandBoxBlockOrder: [
        2, 0, 0, 0, 1, 2, 1, 2, 0
      ],
      toyList: [
        {
          id: 1,
          class: "animal",
          name: "chicken"
        },
        {
          id: 2,
          class: "animal",
          name: "tiger"
        },
        {
          id: 3,
          class: "animal",
          name: "horse"
        },
        {
          id: 5,
          class: "animal",
          name: "sheep"
        },
        {
          id: 6,
          class: "animal",
          name: "cow"
        },
        {
          id: 7,
          class: "animal",
          name: "rabbit"
        },
        {
          id: 8,
          class: "animal",
          name: "dog"
        },
        {
          id: 9,
          class: "animal",
          name: "duck"
        },
        {
          id: 10,
          class: "animal",
          name: "giraffe"
        },
        {
          id: 11,
          class: "animal",
          name: "pig"
        },
        {
          id: 12,
          class: "building",
          name: "castle"
        },
        {
          id: 13,
          class: "building",
          name: "house"
        },
        {
          id: 14,
          class: "building",
          name: "house2"
        },
        {
          id: 15,
          class: "building",
          name: "bridge"
        },
        {
          id: 16,
          class: "building",
          name: "tower"
        },
        {
          id: 17,
          class: "building",
          name: "library"
        },
        {
          id: 18,
          class: "traffic",
          name: "car"
        },
        {
          id: 19,
          class: "traffic",
          name: "plane"
        },
        {
          id: 20,
          class: "traffic",
          name: "plane2"
        },
        {
          id: 21,
          class: "traffic",
          name: "plane3"
        },
        {
          id: 22,
          class: "traffic",
          name: "plane4"
        },
        {
          id: 23,
          class: "other",
          name: "shell"
        },
        {
          id: 24,
          class: "other",
          name: "stone"
        },
        {
          id: 25,
          class: "other",
          name: "stone2"
        },
        {
          id: 26,
          class: "other",
          name: "fence"
        },
        {
          id: 27,
          class: "character",
          name: "chef"
        },
        {
          id: 28,
          class: "character",
          name: "teacher"
        },
        {
          id: 29,
          class: "character",
          name: "elder"
        },
        {
          id: 30,
          class: "character",
          name: "fairy"
        },
        {
          id: 31,
          class: "character",
          name: "girl"
        },
        {
          id: 32,
          class: "character",
          name: "pupil"
        },
        {
          id: 33,
          class: "character",
          name: "doctor"
        },
        {
          id: 34,
          class: "character",
          name: "footballer"
        },
        {
          id: 35,
          class: "supplies",
          name: "stool"
        },
        {
          id: 36,
          class: "supplies",
          name: "tableware"
        },
        {
          id: 37,
          class: "supplies",
          name: "bed"
        },
        {
          id: 38,
          class: "supplies",
          name: "sofa"
        },
        {
          id: 39,
          class: "supplies",
          name: "wardrobe"
        },
        {
          id: 40,
          class: "supplies",
          name: "chair"
        },
        {
          id: 41,
          class: "supplies",
          name: "desk"
        },
        {
          id: 42,
          class: "supplies",
          name: "vase"
        },
        {
          id: 43,
          class: "food",
          name: "hamburger"
        },
        {
          id: 44,
          class: "food",
          name: "noodle"
        },
        {
          id: 45,
          class: "food",
          name: "steak"
        },
        {
          id: 46,
          class: "food",
          name: "chip"
        },
        {
          id: 47,
          class: "food",
          name: "cake"
        },
        {
          id: 48,
          class: "plant",
          name: "grass"
        },
        {
          id: 49,
          class: "plant",
          name: "flower"
        },
        {
          id: 50,
          class: "plant",
          name: "bouquet"
        },
        {
          id: 51,
          class: "plant",
          name: "bouquet2"
        },
        {
          id: 52,
          class: "plant",
          name: "tree"
        },
        {
          id: 53,
          class: "plant",
          name: "tree2"
        },
        {
          id: 54,
          class: "plant",
          name: "tree3"
        },
        {
          id: 55,
          class: "plant",
          name: "tree4"
        },
        {
          id: 56,
          class: "plant",
          name: "tree5"
        },
        {
          id: 57,
          class: "plant",
          name: "tree6"
        }
      ],
      sandBoxBlockList: [
        {
          type: '1',
          url: require("../assets/sandBoxBlocks/1.png")
        },
        {
          type: '0_0000',
          url: require("../assets/sandBoxBlocks/0_0000.png")
        },
        {
          type: '0_0001',
          url: require("../assets/sandBoxBlocks/0_0001.png")
        },
        {
          type: '0_0001',
          url: require("../assets/sandBoxBlocks/0_0001.png")
        },
        {
          type: '0_0010',
          url: require("../assets/sandBoxBlocks/0_0010.png")
        },
        {
          type: '0_0011',
          url: require("../assets/sandBoxBlocks/0_0011.png")
        },
        {
          type: '0_0100',
          url: require("../assets/sandBoxBlocks/0_0100.png")
        },
        {
          type: '0_0101',
          url: require("../assets/sandBoxBlocks/0_0101.png")
        },
        {
          type: '0_0110',
          url: require("../assets/sandBoxBlocks/0_0110.png")
        },
        {
          type: '0_0111',
          url: require("../assets/sandBoxBlocks/0_0111.png")
        },
        {
          type: '0_1000',
          url: require("../assets/sandBoxBlocks/0_1000.png")
        },
        {
          type: '0_1001',
          url: require("../assets/sandBoxBlocks/0_1001.png")
        },
        {
          type: '0_1010',
          url: require("../assets/sandBoxBlocks/0_1010.png")
        },
        {
          type: '0_1011',
          url: require("../assets/sandBoxBlocks/0_1011.png")
        },
        {
          type: '0_1100',
          url: require("../assets/sandBoxBlocks/0_1100.png")
        },
        {
          type: '0_1101',
          url: require("../assets/sandBoxBlocks/0_1101.png")
        },
        {
          type: '0_1110',
          url: require("../assets/sandBoxBlocks/0_1110.png")
        },
        {
          type: '0_1111',
          url: require("../assets/sandBoxBlocks/0_1111.png")
        }
      ],
    }
  },
  watch:{
    boxMovable(){
      if(this.boxMovable){
        this.fixButtonImage= require('../assets/first/editing1.png');
      }
      else{
        this.fixButtonImage= require('../assets/first/editing0.png');
      }
    }
  },
  computed: {
    url() {
      return url
    },
    MoveStatus(){
      if(this.boxMovable){
        return 'Editing'
      }
      else{
        return 'Locked'
      }
    }
  },
  created() {
    let blockSelectList = this.$store.state.blockSelectList;
    console.log(blockSelectList)
    // 初始化SandBoxBlockOrder
    for (let i = 0; i < blockSelectList.length; i++) {
      for (let j = 0; j < this.sandBoxBlockList.length; j++) {
        if (blockSelectList[i] == this.sandBoxBlockList[j].type) {
          this.sandBoxBlockOrder[i] = j;
          break;
        }
      }
    }
  },
  methods: {
    show() {
      this.bol = true;
    },
    changeBoxMovable(){
      this.boxMovable = !this.boxMovable;
      if (this.boxMovable) {
        ElMessage({
          message: '沙盘编辑已开启',
          type: 'success'
        });
        this.MoveStatus = 'Editing'
        document.getElementById( 'myImage').backgroundImage =' url(' + require('../assets/first/editing0.png') + ')';
      }
      else{
        ElMessage({
          message: '沙盘编辑已关闭',
          type: 'success'
        });
        this.MoveStatus = 'Locked'
      }
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
    // 在编辑图片时停止沙盘的拖拽指令
    stopCanvasDrag() {
      this.boxMovable = false;
    },
    // 在编辑图片完毕时恢复沙盘的拖拽指令
    resumeCanvasDrag() {
      this.boxMovable = true;
    },
    createNewImgRotate(toy) {
      //生成唯一ID并记录
      let newId = Symbol();
      this.toyActiveList.push({
        componentId: newId, //用于标识的唯一ID
        name: toy.name,
        class: toy.class,
        id: toy.id,
        transform: {}
      })
      this.toyActiveNumber++;
      let parent = document.getElementById(this.toyActiveNumber);
      let instance = h(ImgRotatable, {
        imgUrl: toy.name+'.png',
        componentId: newId,
        key: this.toyActiveNumber,
        onSavePos: this.savePosHandler,
        onRemoveToy: this.removeToyComponent,
        onEditStart: this.stopCanvasDrag,
        onEditEnd: this.resumeCanvasDrag
      },);
      instance.appContext = app._context;
      render(instance, parent);
      ElMessage({
        message: "成功添加 " + toy.name+" !",
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
    },
    editCanvasScale(e) {
      e.preventDefault();
      if (e.deltaY > 0) {
        // 缩小
        if (this.canvasScale <= 1) {
          return;
        }
        this.canvasScale -= 0.1;
      } else {
        // 放大
        this.canvasScale += 0.1;
      }
    }
  }
}
</script>
<style>
.sandBox {

  width: 100%;
  height: 100%;
}

.map {
  user-select:none;
  float: left;
  width: fit-content;
  height: 100%;
  padding: 30px;
  margin: 20px 20px 20px 30vw;
}

.gallery {
  box-shadow: 25px 25px 30px #b8b8b8,
  -25px -25px 30px #d1e1fe;
  --s: 80px; /* control the size */
  display: grid;
  grid: auto-flow var(--s) / repeat(3, var(--s));
  gap: 0px;
  place-items: center;
  margin: calc(var(--s) / 3);
  transform: rotate(35deg) skew(-10deg, -10deg);;
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

.canvasField {
  scale: v-bind(canvasScale);
  overflow-y: hidden;
  overflow-x: hidden;
}

.img-field {
  width: 60%;
  margin-left: 20%;
}

.img-item {
  width: 100%;
}

.button3 {
  position: absolute;
  left: 2%;
  top: 4%;
  width: 4vw;
  height: 4vw;
  border: none;
}

.button4 {
  position: absolute;
  left: 1.1%;
  top: 12%;
  width: 4vw;
  height: 4vw;
  border: none;
}

.button5 {
  position: absolute;
  left: 1.1%;
  top: 90%;
  width: 15vw;
  height: 4vw;
  border: none;
}

.button6 {
  position: absolute;
  /* 1秒内将right设为30%*/
  margin-left: 0%;
  top: 5%;
  width: 5vw;
  height: 5vw;
  border: none;
  transition: all 1s;
}

.img1 {
  position: absolute;
  margin-left: 4%;
  top: 0;
  width: 5vw;
  height: 100%;
  border: none;
}

.vue-drag-scroll-out-wrapper {
  /* &::-webkit-scrollbar { width: 0 !important } */
  overflow-x: hidden;
  overflow-y: hidden;
  width: fit-content;
  height: 100%;
  cursor: grab;
  padding: 10px;
}

.fix_button{
  position: absolute;
  left: 1.1%;
  top: 20%;
  width: 4vw;
  height: 4vw;
  border: none;
  background-repeat: no-repeat;
  background-size: cover;
  background-color: transparent;
  background-position: center;
}
#box {
  /*width: 100%;*/
  /*height: 100%;*/
  /*overflow: hidden;*/
  width: 100%;
  height: 100vh;
}
</style>