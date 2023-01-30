<template>
  <div class="common-layout">
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
    <el-container>
      <el-aside width="200px" id="aside">
        <div v-for="i in this.toyActiveNumber+1" :key="i" :id="i">
        </div>
      </el-aside>
      <el-main id="main">
        <el-button type="primary" @click="drawer=!drawer">
          Click Me!
        </el-button>
        <el-button type="primary" @click="submitBox()">
          Submit
        </el-button>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import ImgRotatable from "@/components/ImgRotatable";
import {render,h} from "vue";
import { ElMessage } from 'element-plus'
export default {
  components: {
    ImgRotatable
  },
  data() {
    return {
      drawer: false,
      toyActiveNumber:0,
      toyActiveList:[],
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
      ]
    }
  },
  methods:{
    createNewImgRotate(name){
      //生成唯一ID并记录
      let newId = Symbol();
      this.toyActiveList.push({
        componentId:newId, //用于标识的唯一ID
        name:name,
        transform:{}
      })
      this.toyActiveNumber++;
      let parent = document.getElementById(this.toyActiveNumber);
      let instance = h(ImgRotatable,{
        imgUrl : name,
        componentId : newId,
        key : this.toyActiveNumber,
        onSavePos : this.savePosHandler,
        onRemoveToy : this.removeToyComponent,
      },);
      instance.appContext=app._context;
      render(instance,parent);
      ElMessage({
        message: "成功添加 "+name.split('.')[0]+" !",
        type: 'success',
        duration: 2000,
        showClose: true
      })
    },
    savePosHandler(e){
      let {componentId, transform} = e;
      for(let i=0;i<this.toyActiveList.length;++i){
        if(this.toyActiveList[i].componentId===componentId){
          this.toyActiveList[i].transform=transform;
          return
        }
      }
    },
    removeToyComponent(e){
      let {componentId} = e;
      for(let i=0;i<this.toyActiveList.length;++i){
        // 删除对应id元素
        if(this.toyActiveList[i].componentId===componentId){
          let name=this.toyActiveList[i].name;
          this.toyActiveList.splice(i,1);
          ElMessage({
            message: "成功删除 "+name.split('.')[0]+" !",
            type: 'success',
            duration: 2000,
            showClose: true
          })
          return
        }
      }
    },
    submitBox(){
      console.log(this.toyActiveList)
    }
  }
}
</script>
<style>
#aside {
  background-color: aquamarine;
}

#main {
  background-color: antiquewhite;
}

.img-field {
  width: 60%;
  margin-left: 20%;
}

.img-item {
  width: 100%;
}

</style>