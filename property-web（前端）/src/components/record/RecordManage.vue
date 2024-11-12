<template>
  <div>
    <div style="margin-bottom: 5px">
      <el-input v-model="name" style="width: 200px" placeholder="请输入物品名" suffix-icon="el-icon-search"
        @keyup.enter.n.native="loadPost"></el-input>
      <el-select v-model="storage" placeholder="请选择资产" style="margin-left: 5px">
        <el-option v-for="item in storageData" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="goodstype" placeholder="请选择分部" style="margin-left: 5px">
        <el-option v-for="item in goodstypeData" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="status" filterable placeholder="请选择出入库状态" style="margin-left: 5px">
        <el-option v-for="item in stat" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">搜索</el-button>
      <el-button type="danger" style="margin-left: 5px" @click="resetParam">重置</el-button>
    </div>
    <el-table :data="tableData" :header-cell-style="{ background: '#f2f5fc', color: '#555555' }" border>
      <el-table-column prop="id" label="ID" width="60"> </el-table-column>
      <el-table-column prop="goodsname" label="物品名" width="100">
      </el-table-column>
      <el-table-column prop="storagename" label="资产名" width="100">
      </el-table-column>
      <el-table-column prop="goodstypename" label="分部" width="100">
      </el-table-column>

      <el-table-column prop="status" label="审批状态" width="100">
        <template slot-scope="scope">
          <!-- <el-tag :type="scope.row.status === 'S' ? 'danger' : (scope.row.status === 'N' ? 'primary' : 'success')"
            disable-transitions>{{ scope.row.status
              === 'S' ? '审批拒绝' : scope.row.status === 'N' ? '未审核' : '审批通过' }}</el-tag> -->
          <el-tag :type="scope.row.status === 'Y' ? 'success' : scope.row.status === 'N' ? 'primary' : 'danger'"
            disable-transitions>{{
              scope.row.status === 'Y' ? '审批通过' : scope.row.status === 'N' ? '未审核' : '审批拒绝' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="adminname" label="审批人" width="100">
      </el-table-column>
      <el-table-column prop="username" label="申请人" width="100">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="50"> </el-table-column>
      <el-table-column prop="createtime" label="申请时间" width="180" :formatter="formatTime">
      </el-table-column>
      <el-table-column prop="recordtime" label="操作时间" width="180">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 'N' ? 'danger' : 'success'" disable-transitions>{{
            scope.row.status === 'N' ? '未审核' : formatDate(scope.row.recordtime) }}</el-tag>
        </template>

      </el-table-column>
      <el-table-column prop="remark" label="备注"> </el-table-column>
      <el-table-column prop="operate" label="操作" width="300" v-if="user.roleId != 2">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status == 'N' ? true : false" size="small" type="success"
            @click="modStatus(scope.row)">
            审批同意</el-button>
          <el-button v-if="scope.row.status == 'N' ? true : false" size="small" type="danger"
            @click="reStatus(scope.row)">
            审批拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
      :page-sizes="[5, 10, 20, 30]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "RecordManage",
  data() {
    return {
      goodstypeData: [],
      storageData: [],
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: "",
      storage: "",
      goodstype: "",
      userid: "",
      adminId: "",
      user: JSON.parse(sessionStorage.getItem("CurUser")),
      centerDialogVisible: false,
      form: {
        id: "",
        name: "",
        storage: "",
        goodstype: "",
        count: "",
        remark: "",
      },

      status: "",
      stat: [
        {
          value: "Y",
          label: "审批通过",
        },
        {
          value: "N",
          label: "未审核",
        },
        {
          value: "S",
          label: "审批拒绝",
        },
      ],
    };
  },
  methods: {
    formatStorage(row) {
      let temp = this.storageData.find((item) => {
        return item.id == row.storage;
      });
      return temp && temp.name;
    },
    formatGoodstype(row) {
      let temp = this.goodstypeData.find((item) => {
        return item.id == row.goodstype;
      });
      return temp && temp.name;
    },
    formatTime(row, column) {
      let data = row[column.property];
      if (data == null) {
        return null;
      }
      let dt = new Date(data);
      return (
        dt.getFullYear() +
        "-" +
        (dt.getMonth() + 1) +
        "-" +
        dt.getDate() +
        " " +
        dt.getHours() +
        ":" +
        dt.getMinutes() +
        ":" +
        dt.getSeconds()
      );
    },
    modStatus(row) {
      console.log(row);
      (this.form.id = row.id),
        (this.form.goods = row.goods),
        (this.form.count = row.count);
      this.form.adminId = this.user.id;
      this.$axios
        .post(this.$httpUrl + "/record/mod", this.form)
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.$message({
              message: "审批成功！",
              type: "success",
            });
            this.inDialogVisible = false;
            this.loadPost();
          } else {
            this.$message({
              message: res.msg,
              type: "error",
            });
          }
        });
    },
    reStatus(row) {
      console.log(row);
      (this.form.id = row.id),
        (this.form.goods = row.goods),
        (this.form.count = row.count);
      this.form.adminId = this.user.id;
      this.$axios
        .post(this.$httpUrl + "/record/refuse", this.form)
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.$message({
              message: "拒绝成功！",
              type: "success",
            });
            this.inDialogVisible = false;
            this.loadPost();
          } else {
            this.$message({
              message: res.msg,
              type: "error",
            });
          }
        });
    },
    formatDate(data) {
      let dt = new Date(data);
      return (
        dt.getFullYear() +
        "-" +
        (dt.getMonth() + 1) +
        "-" +
        dt.getDate() +
        " " +
        dt.getHours() +
        ":" +
        dt.getMinutes() +
        ":" +
        dt.getSeconds()
      );
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadPost();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadPost();
    },
    loadPost() {
      if (this.user.roleId == 2) {
        this.$axios
          .post(this.$httpUrl + "/record/listPage", {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              name: this.name,
              storage: this.storage + "",
              goodstype: this.goodstype + "",
              userid: this.user.id + "",
              status: this.status,
              adminId: "",
            },
          })
          .then((res) => res.data)
          .then((res) => {
            console.log(res);
            if (res.code == 200) {
              this.tableData = res.data;
              this.total = res.total;
            } else {
              alert("获取数据失败");
            }
          });
      } else if (this.user.roleId == 1) {
        this.$axios
          .post(this.$httpUrl + "/record/listPage", {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              name: this.name,
              storage: this.storage + "",
              goodstype: this.goodstype + "",

              status: this.status,
              userid: "",
              adminId: this.user.id + "",
            },
          })
          .then((res) => res.data)
          .then((res) => {
            console.log(res);
            if (res.code == 200) {
              this.tableData = res.data;
              console.log(this.tableData)
              this.total = res.total;
            } else {
              alert("获取数据失败");
            }
          });
      } else {
        this.$axios
          .post(this.$httpUrl + "/record/listPage", {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              name: this.name,
              storage: this.storage + "",
              goodstype: this.goodstype + "",

              status: this.status,
              userid: "",
              adminId: "",
            },
          })
          .then((res) => res.data)
          .then((res) => {
            console.log(res);
            if (res.code == 200) {
              this.tableData = res.data;
              this.total = res.total;
            } else {
              alert("获取数据失败");
            }
          });
      }
    },
    loadStorage() {
      this.$axios
        .get(this.$httpUrl + "/storage/list")
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.storageData = res.data;
          } else {
            alert("获取数据失败");
          }
        });
    },
    loadGoodstype() {
      this.$axios
        .get(this.$httpUrl + "/goodstype/list")
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.goodstypeData = res.data;
          } else {
            alert("获取数据失败");
          }
        });
    },
    resetParam() {
      this.name = "";
      this.storage = "";
      this.status = "";
      this.goodstype = "";
    },
  },
  beforeMount() {
    this.loadStorage();
    this.loadGoodstype();
    this.loadPost();
  },
};
</script>

<style scoped></style>