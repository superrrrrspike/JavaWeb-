<template>
    <el-menu style="min-height: 100%; overflow-x: hidden;border-right-color: transparent"
             background-color="rgb(48, 65, 86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             default-active="/Home"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
    >
      <div style="height: 60px; line-height: 60px; text-align: center">
        <img src="../assets/xiaohui.jpeg" alt="" style="width: 25px;position: relative;top: 6px;margin-right: 0px">
        <b style="color: white" v-show="!isCollapse">企业资产管理系统</b>
      </div>
      <el-menu-item index="/Home">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item :index="'/'+item.menuclick" v-for="(item,i) in menu" :key="i">
        <i :class="item.menuicon"></i>
        <span slot="title">{{item.menuname}}</span>
      </el-menu-item>
    </el-menu>

</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Aside",
  data(){
    return{
      /*menu:[
        {
          menuClick:'Admin',
          menuName:'管理员管理',
          menuIcon:'el-icon-s-custom'
        },{
          menuClick:'User',
          menuName:'用户管理',
          menuIcon:'el-icon-user-solid'
        }
      ]*/
    }
  },
  computed:{
      "menu":{
        get(){
          return this.$store.state.menu
        }
      }
  },
  props:{
    isCollapse:Boolean
  }
}
</script>

<style scoped>

</style>