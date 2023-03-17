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
          <el-button :style="fix_button" id="myImage" class="fix_button" @click="this.boxMovable=!this.boxMovable" >{{ MoveStatus }}</el-button>
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
import {dragscroll} from "vue-dragscroll";
import 'yoyoo-ddr-vue3/dist/yoyoo-ddr-vue3.css'
import DDR from 'yoyoo-ddr-vue3'
import axios from "axios";
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
        backgroundPosition: 'center'
      },
      fix_button: {
        backgroundImage: 'url(' + require('../assets/first/editing1.png') + ')',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center'
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
      test_toySubmitList: [
        {
          toyId: 1,
          x:5.22,
          y:6.33,
          rotation:0.5
        },
        {
          toyId: 2,
          x:6,
          y:10,
          rotation: 0
        }
      ],
      sandBoxBlockOrder: [
        2, 0, 0, 0, 1, 2, 1, 2, 0
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

  computed: {
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
        onEditStart: this.stopCanvasDrag,
        onEditEnd: this.resumeCanvasDrag
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
      axios.post('http://localhost:8081/SandBoxInstance/test/list', {
        userId: 2054305,
        sandBoxInstances: this.test_toySubmitList
      }).then(function (res) {
        console.log("config submit res");
        console.log(res.data);

      }).catch(function (err) {
        // 提交错误处理 （还未完善，未进行测试
        alert("error!");
        console.log(err);
      })
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
  margin-left: 0%;
  top: 5%;
  width: 5vw;
  height: 5vw;
  border: none;
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
}
#box {
  /*width: 100%;*/
  /*height: 100%;*/
  /*overflow: hidden;*/
  width: 100%;
  height: 100vh;
}
</style>