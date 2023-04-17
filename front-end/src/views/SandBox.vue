<template>
  <div :style="background" class="sandBox">
    <div id="box" class="canvasField vue-drag-scroll-out-wrapper"
         @wheel="editCanvasScale">
      <!--         v-dragscroll="{'active':this.canvasDraggable}"-->
      <DDR :active=this.boxMovable :resizable="false" :rotatable="false" @dblclick="changeBoxMovable">
        <div v-for="i in this.toyActiveNumber+1" :key="i" :id="i">
        </div>
        <div class="map">
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
          <el-button :style="{
            backgroundImage: 'url('+this.fixButtonImage+')'
          }" id="myImage" class="fix_button" @click="this.boxMovable=!this.boxMovable"></el-button>
          <template v-if="bol">
            <course @ok="getData($event)" @cancel="close"></course>
          </template>
        </el-header>
        <el-container>
          <el-container>
            <el-drawer
                v-model="drawer"
                direction="rtl"
                style="user-select: none;"
            >
              <template #header style="margin-bottom: 0px">
                <el-autocomplete
                    v-model="selectInput"
                    class="w-50 m-2 mine_wrapper"
                    placeholder="请输入搜索的沙具名称"
                    :fetch-suggestions="querySearchAsync"
                >
                  <template #prefix>
                    <img
                        src="https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/utils%2Fsearch.png"
                        style="width: 20px; height: 20px; margin-right: 10px;"
                        @click="searchHandler"
                    >
                  </template>
                </el-autocomplete>
              </template>
                <div>
                  <el-row :gutter="5">
                    <el-col :span="4">
                      <el-tag round :effect="this.selectTagId===0?'dark':'light'" @click="this.selectHandle(0)">动物</el-tag>
                    </el-col>
                    <el-col :span="4">
                      <el-tag round :effect="this.selectTagId===1?'dark':'light'" @click="this.selectHandle(1)">建筑</el-tag>
                    </el-col>
                    <el-col :span="4">
                      <el-tag round :effect="this.selectTagId===2?'dark':'light'" @click="this.selectHandle(2)">交通</el-tag>
                    </el-col>
                    <el-col :span="4">
                      <el-tag round :effect="this.selectTagId===3?'dark':'light'" @click="this.selectHandle(3)">人物</el-tag>
                    </el-col>
                    <el-col :span="4">
                      <el-tag round :effect="this.selectTagId===4?'dark':'light'" @click="this.selectHandle(4)">食物</el-tag>
                    </el-col>
                    <el-col :span="4">
                      <el-tag round :effect="this.selectTagId===5?'dark':'light'" @click="this.selectHandle(5)">植物</el-tag>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 10px" :gutter="10">
                    <el-col :span="4">
                      <el-tag round :effect="this.selectTagId===6?'dark':'light'" @click="this.selectHandle(6)">生活用品</el-tag>
                    </el-col>

                    <el-col :span="8">
                      <el-tag round :effect="this.selectTagId===7?'dark':'light'" @click="this.selectHandle(7)">其他</el-tag>
                    </el-col>
                  </el-row>
                </div>
                <div>
                  <el-row v-for="(toy,index) in toyShowList" :key="index">
                    <el-col :span="12" v-if="index*2<toyShowList.length">
                      <div class="img-field" @click="createNewImgRotate(toyShowList[2*index])">
                        <img class="img-item" :src="CONSTANT.TOY_URL_PREFIX+toyShowList[2*index].name+'.png'">
                      </div>
                    </el-col>
                    <el-col :span="12" v-if="index*2+1 < toyShowList.length">
                      <div class="img-field" @click="createNewImgRotate(toyShowList[2*index+1])">
                        <img class="img-item" :src="CONSTANT.TOY_URL_PREFIX+toyShowList[2*index+1].name+'.png'">
                      </div>
                    </el-col>
                  </el-row>
                </div>
              <template #footer style="text-align: center;">
                <div style="display: inline-block">
                  <el-pagination
                      layout="prev, pager, next"
                      :total="this.toySelectList.length"
                      :page-size="this.pageSize"
                      @current-change="handleCurrentChange"
                      v-model:current-page="this.currentPage"
                  />
                </div>
              </template>
            </el-drawer>

            <el-footer>
              <el-button :style="button5" @click="submitBox()" class="button5"></el-button>
            </el-footer>
          </el-container>

          <el-aside width="200px">
            <el-button :style="button6" @click="drawer=!drawer" class="button6"></el-button>

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
import CONSTANT from "@/utils/Constant";
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
      fixButtonImage: require("../assets/first/editing0.png"),
      boxMovable: false,
      canvasDraggable: true,
      bol: true,
      str: '',
      canvasScale: 1,
      button3: {
        backgroundImage: 'url(https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/first/shapan_help.png)',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center'
      },
      button4: {
        backgroundImage: 'url(https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/first/shapan_setting.png)',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center'
      },
      button5: {
        backgroundImage: 'url(https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/first/shapan_submit.png)',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center'
      },
      button6: {
        backgroundImage: 'url(https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/box/shaju_open.png )',
        backgroundRepeat: 'no-repeat',
        // 背景图片大小
        backgroundSize: 'cover',
        // 背景颜色
        backgroundColor: 'transparent',
        // 背景图片位置
        backgroundPosition: 'center',
        right: this.drawer === false ? '30%' : '0px'
      },
      background: {
        // 背景图片地址
        backgroundImage: 'url(https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/first/shapan_bg.png)',
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
      test_toyActiveList:[
        {
          name: "horse",
          type: "animal",
          id: 10,
          transform:{
            height:100,
            rotation:0,
            width:100,
            x:656,
            y:61
          }
        },
        {
          name: "grass",
          type: "other",
          id: 2,
          transform:{
            height:100,
            rotation:0.66,
            width:100,
            x:33.5,
            y:23.5
          }
        }
      ],
      sandBoxBlockOrder: [
        2, 0, 0, 0, 1, 2, 1, 2, 0
      ],
      // Toy总列表
      toyList: [
        {
          id: 1,
          type: "animal",
          name: "chicken",
          zhName: "鸡"

        },
        {
          id: 2,
          type: "animal",
          name: "tiger",
          zhName: "老虎"
        },
        {
          id: 3,
          type: "animal",
          name: "horse",
          zhName: "马"
        },
        {
          id: 5,
          type: "animal",
          name: "sheep",
          zhName: "绵羊"
        },
        {
          id: 6,
          type: "animal",
          name: "cow",
          zhName: "奶牛"
        },
        {
          id: 7,
          type: "animal",
          name: "rabbit",
          zhName: "兔子"
        },
        {
          id: 8,
          type: "animal",
          name: "dog",
          zhName: "狗"
        },
        {
          id: 9,
          type: "animal",
          name: "duck",
          zhName: "鸭子"
        },
        {
          id: 10,
          type: "animal",
          name: "giraffe",
          zhName: "长颈鹿"
        },
        {
          id: 11,
          type: "animal",
          name: "pig",
          zhName: "猪"
        },
        {
          id: 12,
          type: "building",
          name: "castle",
          zhName: "城堡"
        },
        {
          id: 13,
          type: "building",
          name: "house",
          zhName: "房子"
        },
        {
          id: 14,
          type: "building",
          name: "house2",
          zhName: "房子2"
        },
        {
          id: 15,
          type: "building",
          name: "bridge",
          zhName: "桥"
        },
        {
          id: 16,
          type: "building",
          name: "tower",
          zhName: "塔"
        },
        {
          id: 17,
          type: "building",
          name: "library",
          zhName: "图书馆"
        },
        {
          id: 18,
          type: "traffic",
          name: "car",
          zhName: "汽车"
        },
        {
          id: 19,
          type: "traffic",
          name: "plane",
          zhName: "飞机"
        },
        {
          id: 20,
          type: "traffic",
          name: "plane2",
          zhName: "飞机2"
        },
        {
          id: 21,
          type: "traffic",
          name: "plane3",
          zhName: "飞机3"
        },
        {
          id: 22,
          type: "traffic",
          name: "plane4",
          zhName: "飞机4"
        },
        {
          id: 23,
          type: "other",
          name: "shell",
          zhName: "贝壳"
        },
        {
          id: 24,
          type: "other",
          name: "stone",
          zhName: "石头"
        },
        {
          id: 25,
          type: "other",
          name: "stone2",
          zhName: "石头2"
        },
        {
          id: 26,
          type: "other",
          name: "fence",
          zhName: "围栏"
        },
        {
          id: 27,
          type: "character",
          name: "chef",
          zhName: "厨师"
        },
        {
          id: 28,
          type: "character",
          name: "teacher",
          zhName: "老师"
        },
        {
          id: 29,
          type: "character",
          name: "elder",
          zhName: "老人"
        },
        {
          id: 30,
          type: "character",
          name: "fairy",
          zhName: "仙女"
        },
        {
          id: 31,
          type: "character",
          name: "girl",
          zhName: "女孩"
        },
        {
          id: 32,
          type: "character",
          name: "pupil",
          zhName: "小学生"
        },
        {
          id: 33,
          type: "character",
          name: "doctor",
          zhName: "医生"
        },
        {
          id: 34,
          type: "character",
          name: "footballer",
          zhName: "足球运动员"
        },
        {
          id: 35,
          type: "supplies",
          name: "stool",
          zhName: "凳子"
        },
        {
          id: 36,
          type: "supplies",
          name: "tableware",
          zhName: "餐具"
        },
        {
          id: 37,
          type: "supplies",
          name: "bed",
          zhName: "床"
        },
        {
          id: 38,
          type: "supplies",
          name: "sofa",
          zhName: "沙发"
        },
        {
          id: 39,
          type: "supplies",
          name: "wardrobe",
          zhName: "衣柜"
        },
        {
          id: 40,
          type: "supplies",
          name: "chair",
          zhName: "椅子"
        },
        {
          id: 41,
          type: "supplies",
          name: "desk",
          zhName: "桌子"
        },
        {
          id: 42,
          type: "supplies",
          name: "vase",
          zhName: "花瓶"
        },
        {
          id: 43,
          type: "food",
          name: "hamburger",
          zhName: "汉堡包"
        },
        {
          id: 44,
          type: "food",
          name: "noodle",
          zhName: "面条"
        },
        {
          id: 45,
          type: "food",
          name: "steak",
          zhName: "牛排"
        },
        {
          id: 46,
          type: "food",
          name: "chip",
          zhName: "薯条"
        },
        {
          id: 47,
          type: "food",
          name: "cake",
          zhName: "蛋糕"
        },
        {
          id: 48,
          type: "plant",
          name: "grass",
          zhName: "草"
        },
        {
          id: 49,
          type: "plant",
          name: "flower",
          zhName: "花"
        },
        {
          id: 50,
          type: "plant",
          name: "bouquet",
          zhName: "花束"
        },
        {
          id: 51,
          type: "plant",
          name: "bouquet2",
          zhName: "花束2"
        },
        {
          id: 52,
          type: "plant",
          name: "tree",
          zhName: "树"
        },
        {
          id: 53,
          type: "plant",
          name: "tree2",
          zhName: "树2"
        },
        {
          id: 54,
          type: "plant",
          name: "tree3",
          zhName: "树3"
        },
        {
          id: 55,
          type: "plant",
          name: "tree4",
          zhName: "树4"
        },
        {
          id: 56,
          type: "plant",
          name: "tree5",
          zhName: "树5"
        },
        {
          id: 57,
          type: "plant",
          name: "tree6",
          zhName: "树6"
        }
      ],
      toyZhName:[],
      // 被筛选中的Toy列表
      toySelectList: [],
      // 每一页Toy的列表
      toyShowList: [],
      currentPage: 1,
      pageSize: 8,
      sandBoxBlockList: [
        {
          type: '1',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F1.png'
        },
        {
          type: '0_0000',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0000.png'
        },
        {
          type: '0_0001',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0001.png'
        },
        {
          type: '0_0010',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0010.png'
        },
        {
          type: '0_0011',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0011.png'
        },
        {
          type: '0_0100',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0100.png'
        },
        {
          type: '0_0101',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0101.png'
        },
        {
          type: '0_0110',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0110.png'
        },
        {
          type: '0_0111',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_0111.png'
        },
        {
          type: '0_1000',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1000.png'
        },
        {
          type: '0_1001',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1001.png'
        },
        {
          type: '0_1010',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1010.png'
        },
        {
          type: '0_1011',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1011.png'
        },
        {
          type: '0_1100',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1100.png'
        },
        {
          type: '0_1101',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1101.png'
        },
        {
          type: '0_1110',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1110.png'
        },
        {
          type: '0_1111',
          url: 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/sandBoxBlocks%2F0_1111.png'
        }
      ],
      TagList:[
        {
          id: 0,
          name: "animal"
        },
        {
          id: 1,
          name: "building"
        },
        {
          id: 2,
          name: "traffic"
        },
        {
          id: 3,
          name: "character"
        },
        {
          id: 4,
          name: "food"
        },        {
          id: 5,
          name: "plant"
        },
        {
          id: 6,
          name: "supplies"
        },
        {
          id: 7,
          name: "other"
        },
      ],
      selectTagId: -1,
      selectInput: '',
      movableRecording: false,
    }
  },
  watch: {
    boxMovable() {
      if (this.boxMovable) {
        this.fixButtonImage = 'https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/first/editing1.png ';
      } else {
        this.fixButtonImage ='https://websandbox-1314584991.cos.ap-shanghai.myqcloud.com/first%2Fediting0.png';
      }
    }
  },
  computed: {
    CONSTANT() {
      return CONSTANT
    },
    url() {
      return url
    },
    MoveStatus() {
      if (this.boxMovable) {
        return 'Editing'
      } else {
        return 'Locked'
      }
    }
  },
  created() {
    let blockSelectList = this.$store.state.blockSelectList;
    // 初始化SandBoxBlockOrder
    for (let i = 0; i < blockSelectList.length; i++) {
      for (let j = 0; j < this.sandBoxBlockList.length; j++) {
        if (blockSelectList[i] == this.sandBoxBlockList[j].type) {
          this.sandBoxBlockOrder[i] = j;
          break;
        }
      }
    }
    for(let i = 0; i < this.toyList.length; i++){
      this.toyZhName.push(this.toyList[i].zhName);
    }
    // 初始化ToySelectList
    this.toySelectList = [].concat(this.toyList);
    // 初始化ToyShowList
    this.toyShowList = this.toySelectList.slice((this.currentPage - 1) * this.pageSize, this.pageSize);
  },
  methods: {
    searchHandler(){
      if(this.selectInput === ''){
        ElMessage({
          message: '默认显示全部沙具',
          type: 'info',
          duration: 2000
        });
        this.currentPage = 1;
        this.toySelectList = [].concat(this.toyList);
        this.toyShowList = this.toySelectList.slice((this.currentPage - 1) * this.pageSize, this.pageSize);
        this.selectTagId = -1;
        return;
      }
      for(let i = 0; i < this.toyList.length; i++){
        if(this.toyList[i].zhName === this.selectInput){
          this.toySelectList = [this.toyList[i]];
          this.toyShowList = this.toySelectList.slice((this.currentPage - 1) * this.pageSize, this.pageSize);
          ElMessage({
            message: '搜索成功',
            type: 'success'
          });
          this.selectTagId = -1;
          return;
        }
      }
      ElMessage({
        message: '未找到该沙具',
        type: 'error',
        duration: 2000
      });
    },
    querySearchAsync(queryString, cb) {
      let toyZhName = this.toyZhName;
      // 将toyZhName中包含queryString的name筛选出来，组成数组
      let results = toyZhName.filter((toyZhName) => {
        return toyZhName.toLowerCase().indexOf(queryString.toLowerCase()) !== -1;
      });
      // 将results改为对象数组，以便于在下拉列表中显示
      results = results.map((item) => {
        return {value: item};
      });
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    selectHandle(tagId){
      if(tagId === this.selectTagId){
        this.selectTagId = -1;
        this.currentPage = 1;
        this.toySelectList = [].concat(this.toyList);
        this.toyShowList = this.toySelectList.slice((this.currentPage - 1) * this.pageSize, this.pageSize);
      }else{
        this.selectTagId = tagId;
        this.currentPage = 1;
        this.toySelectList = this.toyList.filter((item)=>{
          return item.type === this.TagList[tagId].name;
        });
        this.toyShowList = this.toySelectList.slice((this.currentPage - 1) * this.pageSize, this.pageSize);
      }
    },
    show() {
      this.bol = true;
    },
    changeBoxMovable() {
      this.boxMovable = !this.boxMovable;
      if (this.boxMovable) {
        ElMessage({
          message: '沙盘编辑已开启',
          type: 'success'
        });
        this.MoveStatus = 'Editing'
        document.getElementById('myImage').backgroundImage = ' url(' + require('../assets/first/editing0.png') + ')';
      } else {
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
      this.movableRecording= this.boxMovable;
      this.boxMovable = false;
    },
    // 在编辑图片完毕时恢复沙盘的拖拽指令
    resumeCanvasDrag() {
      this.boxMovable = this.movableRecording;
    },
    createNewImgRotate(toy) {
      //生成唯一ID并记录
      let newId = Symbol();
      this.toyActiveList.push({
        componentId: newId, //用于标识的唯一ID
        name: toy.name,
        type: toy.type,
        id: toy.id,
        transform: {}
      })
      this.toyActiveNumber++;
      let parent = document.getElementById(this.toyActiveNumber);
      let instance = h(ImgRotatable, {
        imgUrl: toy.name + '.png',
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
        message: "成功添加 " + toy.name + " !",
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
      axios.post('http://localhost:8081/SandBoxInstance/list', {
        phoneNumber: 2054305,
        newSandBoxInstances: this.toyActiveList
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
    },
    handleCurrentChange(e) {
      this.currentPage = e;
      this.toyShowList = this.toySelectList.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
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
  user-select: none;
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
  left: 20px;
  top: 4%;
  width: 4vw;
  height: 4vw;
  border: none;
}

.button5 {
  position: absolute;
  left: 20px;
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


.vue-drag-scroll-out-wrapper {
  /* &::-webkit-scrollbar { width: 0 !important } */
  overflow-x: hidden;
  overflow-y: hidden;
  width: fit-content;
  height: 100%;
  cursor: grab;
  padding: 10px;
}

.fix_button {
  position: absolute;
  left: 5px;
  top: 20%;
  width: 4vw;
  height: 4vw;
  border: none;
  background-repeat: no-repeat;
  background-size: cover;
  background-color: transparent;
  background-position: center;
}

.el-input {
  /*--el-input-border-color: #a0cfff;*/
  --el-input-bg-color: #ffffff00;
  --el-input-hover-border-color: #d7d6f8;
  --el-input-focus-border-color: #827fe9;
}
.el-drawer__header {
  margin-bottom: 0;
}
.el-drawer__footer{
  text-align: center;
}

#box {
  /*width: 100%;*/
  /*height: 100%;*/
  /*overflow: hidden;*/
  width: 100%;
  height: 100vh;
}
</style>