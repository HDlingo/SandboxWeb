<template>
  <div class="sandBox">
    <el-drawer
        v-model="drawer"
        direction="ltr"
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
      <el-row style="font-size: 0;">
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[0]].url+')'}"
               class="sandbox-block">
          </div>
        </el-col>
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[1]].url+')'}"
               class="sandbox-block">
            <el-button type="primary" @click="drawer=!drawer">
              Click Me!
            </el-button>
            <el-button type="primary" @click="submitBox()">
              Submit
            </el-button>
          </div>
        </el-col>
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[2]].url+')'}"
               class="sandbox-block"></div>
        </el-col>
      </el-row>
      <el-row style="font-size: 0;">
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[3]].url+')'}"
               class="sandbox-block"></div>
        </el-col>
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[4]].url+')'}"
               class="sandbox-block"></div>
        </el-col>
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[5]].url+')'}"
               class="sandbox-block"></div>
        </el-col>
      </el-row>
      <el-row style="font-size: 0;">
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[6]].url+')'}"
               class="sandbox-block"></div>
        </el-col>
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[7]].url+')'}"
               class="sandbox-block"></div>
        </el-col>
        <el-col :span="8">
          <div :style="{'background-image':'url('+this.sandBoxBlockList[this.sandBoxBlockOrder[8]].url+')'}"
               class="sandbox-block"></div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import ImgRotatable from "@/components/ImgRotatable";
import {render, h} from "vue";
import {ElMessage} from 'element-plus'

export default {
  components: {
    ImgRotatable
  },
  data() {
    return {
      drawer: false,
      toyActiveNumber: 0,
      toyActiveList: [],
      sandBoxBlockOrder: [
        0, 0, 1, 0, 1, 1, 1, 0, 0, 0
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
          url: require("../assets/grass.jpg")
        },
        {
          url: require("../assets/ground.jpg")
        }
      ],
    }
  },
  methods: {
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
<style>
.sandBox {
  width: 100vw;
}

.sandbox-block {
  font-size: 0;
  height: 33.33vh;
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.img-field {
  width: 60%;
  margin-left: 20%;
}

.img-item {
  width: 100%;
}

</style>