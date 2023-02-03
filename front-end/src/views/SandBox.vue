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
      <el-button type="primary" @click="drawer=!drawer">
        Click Me!
      </el-button>
      <el-button type="primary" @click="submitBox()">
        Submit
      </el-button>
      <div class="map">
        <div class="gallery" >
          <img :src="this.sandBoxBlockList[blockIndex].url" alt="a sandbox map block" v-for="blockIndex in this.sandBoxBlockOrder" :key="blockIndex">
        </div>
      </div>
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
        0, 0, 0, 0, 1, 1, 1, 0, 1
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
  margin-top: 1vh;
}
.map{
  margin: 0;
  display: grid;
  place-content: center;
}
.gallery {
  box-shadow:  25px 25px 30px #b8b8b8,
  -25px -25px 30px #ffffff;
  --s: 200px; /* control the size */
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

</style>