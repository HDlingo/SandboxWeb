"use strict";(self["webpackChunksandbox"]=self["webpackChunksandbox"]||[]).push([[443,509],{8036:function(e,t,o){o.r(t),o.d(t,{default:function(){return g}});var n=o(3396),l=o(7139);const a={class:"common-layout"};function r(e,t,o,r,s,i){const u=(0,n.up)("el-header"),c=(0,n.up)("el-aside"),d=(0,n.up)("el-button"),g=(0,n.up)("tips"),m=(0,n.up)("el-main"),p=(0,n.up)("el-container");return(0,n.wg)(),(0,n.iD)("div",{style:(0,l.j5)(s.background),class:"bgBackground"},[(0,n._)("div",a,[(0,n.Wm)(p,null,{default:(0,n.w5)((()=>[(0,n.Wm)(u,{height:"365px"}),(0,n.Wm)(p,null,{default:(0,n.w5)((()=>[(0,n.Wm)(c,{width:"0px"}),(0,n.Wm)(m,null,{default:(0,n.w5)((()=>[(0,n.Wm)(d,{style:(0,l.j5)(s.button1),onClick:i.show,class:"button1"},null,8,["style","onClick"]),s.bol?((0,n.wg)(),(0,n.j4)(g,{key:0,onOk:t[0]||(t[0]=e=>i.getData(e)),onCancel:i.close},null,8,["onCancel"])):(0,n.kq)("",!0)])),_:1})])),_:1})])),_:1})])],4)}var s=o(7063),i=(o(1120),o(3035)),u={name:"FirstPage",data(){return{bol:!1,str:"",button1:{backgroundImage:"url("+o(9302)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"},background:{backgroundImage:"url("+o(936)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"}}},components:{tips:i["default"],ImgRotatable:s.Z},methods:{show(){this.bol=!0},getData(e){this.bol=!1,console.log(e),this.str=e},close(){this.bol=!1}}},c=o(89);const d=(0,c.Z)(u,[["render",r],["__scopeId","data-v-e732857e"]]);var g=d},5308:function(e,t,o){o.r(t),o.d(t,{default:function(){return W}});var n=o(3396),l=o(7139);const a={class:"common-layout"},r=(0,n._)("h3",null," Select ",-1),s=["onClick"],i=["src"],u=["onClick"],c=["src"],d=["id"],g={class:"map"},m={class:"gallery"},p=["src"],b=["src"];function k(e,t,k,h,y,v){const f=(0,n.up)("el-button"),w=(0,n.up)("course"),_=(0,n.up)("el-header"),C=(0,n.up)("el-col"),W=(0,n.up)("el-row"),x=(0,n.up)("el-scrollbar"),I=(0,n.up)("el-drawer"),L=(0,n.up)("el-main"),z=(0,n.up)("el-footer"),A=(0,n.up)("el-container"),j=(0,n.up)("el-aside");return(0,n.wg)(),(0,n.iD)("div",{style:(0,l.j5)(y.background),class:"sandBox"},[(0,n._)("div",a,[(0,n.Wm)(A,null,{default:(0,n.w5)((()=>[(0,n.Wm)(_,{height:"100px"},{default:(0,n.w5)((()=>[(0,n.Wm)(f,{style:(0,l.j5)(y.button3),onClick:v.show,class:"button3"},null,8,["style","onClick"]),(0,n.Wm)(f,{style:(0,l.j5)(y.button4),onClick:v.show,class:"button4"},null,8,["style","onClick"]),y.bol?((0,n.wg)(),(0,n.j4)(w,{key:0,onOk:t[0]||(t[0]=e=>v.getData(e)),onCancel:v.close},null,8,["onCancel"])):(0,n.kq)("",!0)])),_:1}),(0,n.Wm)(A,null,{default:(0,n.w5)((()=>[(0,n.Wm)(A,null,{default:(0,n.w5)((()=>[(0,n.Wm)(L,null,{default:(0,n.w5)((()=>[(0,n.Wm)(I,{modelValue:y.drawer,"onUpdate:modelValue":t[1]||(t[1]=e=>y.drawer=e),direction:"rtl"},{header:(0,n.w5)((()=>[r])),default:(0,n.w5)((()=>[(0,n.Wm)(x,null,{default:(0,n.w5)((()=>[(0,n._)("div",null,[((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(y.toyList,((e,t)=>((0,n.wg)(),(0,n.j4)(W,{key:t},{default:(0,n.w5)((()=>[2*t<y.toyList.length?((0,n.wg)(),(0,n.j4)(C,{key:0,span:12},{default:(0,n.w5)((()=>[(0,n._)("div",{class:"img-field",onClick:e=>v.createNewImgRotate(y.toyList[2*t].name)},[(0,n._)("img",{class:"img-item",src:o(7173)("./"+y.toyList[2*t].name)},null,8,i)],8,s)])),_:2},1024)):(0,n.kq)("",!0),2*t+1<y.toyList.length?((0,n.wg)(),(0,n.j4)(C,{key:1,span:12},{default:(0,n.w5)((()=>[(0,n._)("div",{class:"img-field",onClick:e=>v.createNewImgRotate(y.toyList[2*t+1].name)},[(0,n._)("img",{class:"img-item",src:o(7173)("./"+y.toyList[2*t+1].name)},null,8,c)],8,u)])),_:2},1024)):(0,n.kq)("",!0)])),_:2},1024)))),128))])])),_:1})])),_:1},8,["modelValue"]),(0,n._)("div",null,[((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(this.toyActiveNumber+1,(e=>((0,n.wg)(),(0,n.iD)("div",{key:e,id:e},null,8,d)))),128)),(0,n._)("div",g,[(0,n._)("div",m,[((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(this.sandBoxBlockOrder,(e=>((0,n.wg)(),(0,n.iD)("img",{src:this.sandBoxBlockList[e].url,alt:"a sandbox map block",key:e},null,8,p)))),128))])])])])),_:1}),(0,n.Wm)(z,null,{default:(0,n.w5)((()=>[(0,n.Wm)(f,{style:(0,l.j5)(y.button5),onClick:t[2]||(t[2]=e=>v.submitBox()),class:"button5"},null,8,["style"])])),_:1})])),_:1}),(0,n.Wm)(j,{width:"200px"},{default:(0,n.w5)((()=>[(0,n.Wm)(f,{style:(0,l.j5)(y.button6),onClick:t[3]||(t[3]=e=>y.drawer=!y.drawer),class:"button6"},null,8,["style"]),(0,n._)("img",{class:"img1",src:o(9311)},null,8,b)])),_:1})])),_:1})])),_:1})])],4)}o(7658);var h=o(7063),y=o(9242),v=o(7178),f=o(1309),w={components:{course:f["default"],ImgRotatable:h.Z},data(){return{bol:!0,str:"",button3:{backgroundImage:"url("+o(7526)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"},button4:{backgroundImage:"url("+o(3106)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"},button5:{backgroundImage:"url("+o(7258)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"},button6:{backgroundImage:"url("+o(7724)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"},background:{backgroundImage:"url("+o(9681)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"},drawer:!1,toyActiveNumber:0,toyActiveList:[],sandBoxBlockOrder:[0,0,0,0,1,1,1,0,1],toyList:[{name:"logo.png"},{name:"morty.png"},{name:"3.png"},{name:"4.gif"},{name:"5.png"},{name:"6.png"},{name:"7.png"},{name:"8.jpg"},{name:"9.png"},{name:"10.png"},{name:"11.gif"},{name:"12.png"},{name:"13.png"}],sandBoxBlockList:[{url:o(7942)},{url:o(3808)}]}},methods:{show(){this.bol=!0},getData(e){this.bol=!1,console.log(e),this.str=e},close(){this.bol=!1},createNewImgRotate(e){let t=Symbol();this.toyActiveList.push({componentId:t,name:e,transform:{}}),this.toyActiveNumber++;let o=document.getElementById(this.toyActiveNumber),l=(0,n.h)(h.Z,{imgUrl:e,componentId:t,key:this.toyActiveNumber,onSavePos:this.savePosHandler,onRemoveToy:this.removeToyComponent});l.appContext=app._context,(0,y.sY)(l,o),(0,v.z8)({message:"成功添加 "+e.split(".")[0]+" !",type:"success",duration:2e3,showClose:!0})},savePosHandler(e){let{componentId:t,transform:o}=e;for(let n=0;n<this.toyActiveList.length;++n)if(this.toyActiveList[n].componentId===t)return void(this.toyActiveList[n].transform=o)},removeToyComponent(e){let{componentId:t}=e;for(let o=0;o<this.toyActiveList.length;++o)if(this.toyActiveList[o].componentId===t){let e=this.toyActiveList[o].name;return this.toyActiveList.splice(o,1),void(0,v.z8)({message:"成功删除 "+e.split(".")[0]+" !",type:"success",duration:2e3,showClose:!0})}},submitBox(){console.log(this.toyActiveList)}}},_=o(89);const C=(0,_.Z)(w,[["render",k]]);var W=C},1309:function(e,t,o){o.r(t),o.d(t,{default:function(){return f}});var n=o(3396),l=o(7139);const a=e=>((0,n.dD)("data-v-5a0ee02c"),e=e(),(0,n.Cn)(),e),r={class:"mark"},s={class:"alert"},i={class:"common-layout"},u=a((()=>(0,n._)("div",{style:{color:"BLACK","font-size":"250%","line-height":"2em","margin-left":"50px"}},"操作教程",-1))),c=a((()=>(0,n._)("div",{style:{"line-height":"2em","font-size":"20px"}},"涉及到右侧抽屉的拖拽、鼠标左中右键选择(删除会上初步说是双击右键)",-1))),d=a((()=>(0,n._)("div",{style:{"line-height":"2em","font-size":"20px"}},"设置里是控制bgm吗",-1))),g=a((()=>(0,n._)("div",{style:{"line-height":"2em","font-size":"20px"}},"具体再议，主要是先放这个面板",-1))),m=a((()=>(0,n._)("div",{style:{"line-height":"2em","font-size":"20px"}},"面板内容不需要点击，后续替换即可",-1))),p=a((()=>(0,n._)("div",{style:{"line-height":"2em","font-size":"20px"}},"初次进入点击“开始探索”关闭面板",-1))),b=a((()=>(0,n._)("div",{style:{"line-height":"2em","font-size":"20px"}},"单击",-1)));function k(e,t,o,a,k,h){const y=(0,n.up)("el-button"),v=(0,n.up)("el-header"),f=(0,n.up)("el-main"),w=(0,n.up)("el-footer"),_=(0,n.up)("el-container");return(0,n.wg)(),(0,n.iD)("div",r,[(0,n._)("div",s,[(0,n._)("div",i,[(0,n.Wm)(_,null,{default:(0,n.w5)((()=>[(0,n.Wm)(v,null,{default:(0,n.w5)((()=>[(0,n.Wm)(y,{style:(0,l.j5)(k.button2),onClick:h.cancel,class:"button2"},null,8,["style","onClick"]),u])),_:1}),(0,n.Wm)(f,null,{default:(0,n.w5)((()=>[c,d,g,m,p,b])),_:1}),(0,n.Wm)(w,null,{default:(0,n.w5)((()=>[(0,n.Wm)(y,{style:(0,l.j5)(k.button1),onClick:h.sure,class:"button1"},null,8,["style","onClick"])])),_:1})])),_:1})])])])}o(1120);var h={data(){return{button1:{backgroundImage:"url("+o(4981)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"},button2:{backgroundImage:"url("+o(6219)+")",backgroundRepeat:"no-repeat",backgroundSize:"cover",backgroundColor:"transparent",backgroundPosition:"center top"}}},methods:{sure(){this.$emit("cancel")},cancel(){this.$emit("cancel")}}},y=o(89);const v=(0,y.Z)(h,[["render",k],["__scopeId","data-v-5a0ee02c"]]);var f=v}}]);
//# sourceMappingURL=about.b9d6ca85.js.map