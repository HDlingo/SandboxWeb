<template>
  <el-button @click="myPost" class="full-rounded">
    <span>提交数据</span>
    <div class="border full-rounded"></div>
  </el-button>
</template>
<script>
import axios from 'axios';
import { ElButton, ElDialog } from 'element-plus';
export default {
  name: "postTest",
  setup(){
    let toy={name:"my name is my name!"};
    function myPost(){
      axios.post('http://localhost:8081/toy/testPost', {
        name: toy.name
        // lineId: lineId,
        // taskName: form.name,
        // assemblyPartNumber: form.part_num[0],
        // componentType:form.part_num[1],
        // componentPartNumber: form.part_num[2],
        // imagePath: form.imgPath,
        // reInspectionPercentage: form.percent,
        // face: form.face
      }).then(function (res) {
        // console.log("config submit res");
        console.log(res.data);
        // router.push('/task')

      }).catch(function (err) {
        // 提交错误处理 （还未完善，未进行测试
        alert("error!");
        console.log(err);
      })
    }

    return{
      myPost
    }
  }
}
</script>


<style scoped>
button:hover .border {
  display: block;
  width: 11em;
  height: 4em;
}

.full-rounded {
  border-radius: 2em;
}
</style>