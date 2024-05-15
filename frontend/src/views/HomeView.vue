<template>
  <el-container>

    <el-aside width="200px">
      <h1>懒得写导航</h1>
    </el-aside>

    <el-container>
      <el-header>
        <h1>懒得写标题</h1>
      </el-header>

      <el-main>
        <el-input placeholder="请输入姓名" prefix-icon="el-icon-search" v-model="input" class="short-input">
        </el-input>
        <el-button type="primary" icon="el-icon-search" @click="Search">搜索</el-button>
        <el-button type="primary" icon="el-icon-plus" @click="Plus">添加</el-button>
        <el-table :data="students" stripe style="width: 100%">
          <el-table-column prop="name" label="姓名" width="180">
          </el-table-column>
          <el-table-column prop="age" label="年龄" width="180">
          </el-table-column>
          <el-table-column prop="gender" label="性别" :formatter="formatGender" width="180">
          </el-table-column>
          <el-table-column prop="address" label="籍贯" width="180">
          </el-table-column>
          <el-table-column prop="className" label="班级" width="180">
          </el-table-column>
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button-group>
                <el-button type="primary" icon="el-icon-edit" @click="Edit(scope.row)"></el-button>
                <el-button type="primary" icon="el-icon-delete" @click="Delete(scope.row)"></el-button>
              </el-button-group>
            </template>
          </el-table-column>
        </el-table>
        <!-- 搜索 -->
        <el-dialog title="搜索结果" :visible.sync="showSearchWindow" width="30%" center>
          <div v-if="searchResult">
            <p>姓名：{{ searchResult.name }}</p>
            <p>年龄：{{ searchResult.age }}</p>
            <p>性别：{{ searchResult.gender == 1 ? "男" : "女" }}</p>
            <p>籍贯：{{ searchResult.address }}</p>
            <p>班级：{{ searchResult.className }}</p>
          </div>
          <div v-else>
            <p>没有找到相关数据</p>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="showSearchWindow = false">取 消</el-button>
            <el-button type="primary" @click="showSearchWindow = false">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 添加 -->
        <el-dialog title="填写信息" :visible.sync="showPlusWindow" width="30%" center @open="resetplusForm">
          <el-form :model="plusform" ref="plusform">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="plusform.name"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input v-model="plusform.age"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="plusform.gender">
                <el-radio :label="1">男</el-radio>
                <el-radio :label="0">女</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="籍贯" prop="address">
              <el-input v-model="plusform.address"></el-input>
            </el-form-item>
            <el-form-item label="班级" prop="clsaaName">
              <el-input v-model="plusform.className"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="showPlusWindow = false">取 消</el-button>
            <el-button type="primary" @click="submitplusForm">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 编辑 -->
        <el-dialog title="编辑信息" :visible.sync="showEditWindow" width="30%" center>
          <el-form :model="editform" ref="editform">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="editform.name"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input v-model="editform.age"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="editform.gender">
                <el-radio :label="1">男</el-radio>
                <el-radio :label="0">女</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="籍贯" prop="address">
              <el-input v-model="editform.address"></el-input>
            </el-form-item>
            <el-form-item label="班级" prop="clsaaName">
              <el-input v-model="editform.className"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="showEditWindow = false">取 消</el-button>
            <el-button type="primary" @click="submiteditForm">确 定</el-button>
          </span>
        </el-dialog>
      </el-main>

      <el-footer>
        <h1>不知道写什么好，就不写了</h1>
      </el-footer>
    </el-container>
  </el-container>


</template>

<script>
import axios from "axios";

export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      showSearchWindow: false,
      showPlusWindow: false,
      showEditWindow: false,
      input: '',
      students: [],
      searchResult: null,

      plusform: {
        name: '',
        age: null,
        gender: 0,
        address: '',
        className: '',
      },

      editform: {
        id: null,
        name: '',
        age: null,
        gender: 0,
        address: '',
        className: '',
      },
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios.get('http://localhost:8000/api/students')
        .then(response => {
          this.students = response.data;
        })
        .catch(error => {
          console.error('Error fetching student data:', error);
        });
    },
    formatGender(row, column, value) {
      return value === 1 ? '男' : '女';
    },

    async Search() {
      try {
        const response = await axios.get(`http://localhost:8000/api/students/${encodeURIComponent(this.input)}`);
        console.log(response.data);
        this.searchResult = response.data;
        this.showSearchWindow = true;
      } catch (error) {
        console.error(error);
      }
    },

    Plus() {
      this.showPlusWindow = true;
    },

    resetplusForm() {
      this.plusform = {
        name: '',
        age: null,
        gender: 0,
        address: '',
        className: '',
      };
    },

    submitplusForm() {
      this.$refs.plusform.validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8000/api/submit', this.plusform)
            .then(response => {
              console.log(response.data);
              this.showPlusWindow = false;
              window.location.reload();
            })
            .catch(error => {
              console.error(error);
            });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

submiteditForm() {
  this.$refs.editform.validate((valid) => {
    if (valid) {
      axios.post('http://localhost:8000/api/update', this.editform)
        .then(response => {
          console.log(response.data);
          this.showEditWindow = false;
          window.location.reload();
        })
        .catch(error => {
          console.error(error);
        });
    } else {
      console.log('error submit!!');
      return false;
    }
  });
},


    Edit(row) {
      console.log('编辑', row);
      this.editform = { ...row };
      this.showEditWindow = true;
    },

    Delete(row) {
      console.log('删除', row);
      const index = this.students.indexOf(row);
      if (index !== -1) {
        axios.delete(`http://localhost:8000/api/students/${row.id}`)
          .then(() => {
            this.students.splice(index, 1);
          })
          .catch(error => {
            console.error('Error deleting student:', error);
          });
      }
    },

  }
};
</script>

<style scoped>
.short-input {
  width: 200px;
}
</style>
