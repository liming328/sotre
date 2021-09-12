<template>
    <div class="animated fadeIn">
        <Card>
      <row>
          部门名字:<input  placeholer="" style="width: 150px; margin-left: 10px; margin-right: 20px"/>
          <Button type="primary" shape="circle"icon="ios-search">搜索</Button>
      </row>
        <br/>
       <row>
           <Button type="primary" icon="plus" @click="addDeptUI">新建</Button>
           <Button type="success" icon="wrench" @click="editDeptUI">修改</Button>
           <Button type="error" icon="trash-a" @click="deleteDept">删除</Button>
       </row>
        <br/>
        <row>
            <Table border ref="selection" :columns="columns3" :data="data1" @on-selection-change="changeIds">
            </Table>
        </row>
        <br/>
        <row>
            <Page show-elevator align="center" :total="totalCount" :current="pageNo" :page-size="pageSize" @on-change="gopage" ></Page>
        </row>
            <!--添加部门-->
            <Modal title="添加部门" :mask-closable="false" v-model="addModal" @on-ok="addDept"  @on-cancel="cancel" :loading="loading" width="60%">
                <Form ref="addForm"  :model="addForm" :rules="ruleValidate" :label-width="100">
                            <FormItem label="部门名字" prop="deptName">
                                <Input type="text" v-model="addForm.deptName" placeholder="请输入部门名"></Input>
                            </FormItem>
                            <FormItem label="父部门" prop="=parentId">
                                <Select v-model="addForm.parentId" placeholder="请选择父部门">
                                    <Option v-for="dept in deptList"  :value="dept.id">{{dept.deptName}}</Option>
                                </Select>
                            </FormItem>
                             <FormItem label="状态" prop="state">
                                <RadioGroup v-model="addForm.state">
                                    <Radio label="1">可用</Radio>
                                    <Radio label="0">不可用</Radio>
                                </RadioGroup>
                             </FormItem>
                </Form>
            </Modal>
            <!--编辑部门-->
            <Modal title="编辑部门" v-model="editModal" :mask-closable="false" @on-ok="editDept"  @on-cancel="cancel" :loading="loading" width="60%">
                <Form ref="editForm"  :model="editForm" :rules="ruleValidate" :label-width="100">
                    <FormItem label="部门名字" prop="deptName">
                        <Input type="text" v-model="editForm.deptName" ></Input>
                    </FormItem>
                    <FormItem label="父部门" prop="=parentId">
                        <Select v-model="editForm.parentId" placeholder="请选择父部门">
                            <Option v-for="dept in deptList"  :value="dept.id">{{dept.deptName}}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="状态" prop="state">
                        <RadioGroup v-model="editForm.state">
                            <Radio label="1">可用</Radio>
                            <Radio label="0">不可用</Radio>
                        </RadioGroup>
                    </FormItem>
                </Form>
            </Modal>
        </Card>
    </div>
</template>

  <script>
    import fetch from '../../utils/fetch'
   export default {
       data(){
            return{
              columns3:[
                  {
                      type:'selection',
                      width:60,
                      align:'center'
                  },
                  {
                      title:'部门名字',
                      key:'deptName'
                  },
                  {
                      title:'状态',
                      key:'state',
                      render: (h,params)=>{
                          //h用来画标签<div><strong>
                          //params 获取row等
                          return h('div',
                          [
                              h('strong',params.row.state==1 ? '可用':'不可用')
                          ]);
                      }
                  }
              ],
                data1:[],//当前部门的集合
                totalCount:0,
                pageNo:1,
                pageSize:3,
                //控制"添加部门"
                addModal:false,
                deptList:[],
                addForm:{
                  deptName:'',
                  parentId:null,
                  state:1
                },
                //编辑部门"
                editModal:false,
                editForm:{
                    id:'',
                    deptName:'',
                    parentId:null,
                    state:1
                },
                //用于修改和删除
                ids:[],
                //表单校验器
                ruleValidate:{
                  deptName:[
                      {required:true,message:'部门名字必须输入',trigger:'blur'}
                  ]
                },
                loading:true
            };
        },
        methods: {
            gopage: function (pageNo) {
                this.pageNo = pageNo;
                fetch({
                    url:'/system/dept',
                    method:'get',
                    params:{
                        pageNo:pageNo,
                        pageSize: this.pageSize
                    }
                }).then((resp)=>{
                    this.data1=resp.data.list;
                    this.totalCount = resp.data.totalCount;
                });
            },
            addDeptUI: function(){
                this.addModal=true;
                fetch({
                    url: '/system/dept/all',
                    method: 'get',
                }).then((resp)=>{
                    this.deptList = resp.data;
                })
            },
            editDeptUI: function(){
                if (this.ids.length==1){
                    this.editModal=true;
                    fetch({
                        url: '/system/dept/all',
                        method: 'get',
                    }).then((resp)=>{
                        this.deptList = resp.data;
                        //将自己部门从修改界面的父部门列表中删除
                        for (let i = 0; i < this.deptList.length; i++) {
                            if (this.editForm.id==this.deptList[i].id){
                                this.deptList.splice(i,1)
                            }
                        }
                    });
                }else if (this.ids.length>1){
                    this.$Message.warning('单次只能对一个部门进行修改!');
                }else {
                    this.$Message.warning('未选中任何部门');
                }

            },
            addDept:function () {
                this.$refs['addForm'].validate((valid)=>{
                    if (valid){
                        fetch({
                            url:'/system/dept',
                            method:'post',
                            data:this.addForm
                        }).then((resp)=>{
                            if (resp.data=='1'){
                                this.$Message.success('新建部门成功!');
                                this.gopage(this.pageNo);
                                this.addModal = false;
                            }else {
                                this.$Message.success('新建部门失败!');
                            }
                        });
                    }else {
                        this.$Message.error('表单校验失败');
                        setTimeout(()=>{
                            this.loading=false;//不要加载了
                            //dom刷新重新加载
                            this.$nextTick(()=>{
                                this.loading = true;
                            });
                        },2000);
                    }
                });
            },
            editDept:function () {
                this.$refs['editForm'].validate((valid)=>{
                    if (valid){
                        fetch({
                            url:'/system/dept',
                            method:'put',
                            data:this.editForm
                        }).then((resp)=>{
                            if (resp.data=='1'){
                                this.$Message.success('修改部门成功!');
                                this.gopage(this.pageNo);
                                this.editModal = false;
                            }else {
                                this.$Message.success('修改部门失败!');
                            }
                        });
                    }else {
                        this.$Message.error('表单校验失败');
                        setTimeout(()=>{
                            this.loading=false;//不要加载了
                            //dom刷新重新加载
                            this.$nextTick(()=>{
                                this.loading = true;
                            });
                        },2000);
                    }
                });
            },
            //选取复选框
            changeIds:function (selection) {
                //先去清空之前选中的ids
                this.ids = [];
                for (let i = 0; i < selection.length; i++) {
                    this.ids.push(selection[i].id)
                }
                //当单选
                if (selection.length==1){
                    this.editForm = selection[0];
                }
            },
            //删除部门
            deleteDept:function () {
                if (this.ids.length>=1){
                    if (confirm("确定要删除吗？")) {
                        fetch({
                            url: '/system/dept',
                            method: 'delete',
                            data:this.ids
                        }).then((resp)=>{
                            if (resp.data=='1'){
                                this.$Message.success('删除成功！');
                                this.gopage(this.pageNo)
                                this.ids = [];
                            }else {
                                this.$Message.error('删除失败！');
                            }

                        });
                    }
                }else {
                    this.$Message.error('请选择需要删除的部门！');
                }

            }
        },
        mounted(){
            //网页一加载完毕就显示第一页的部门集合-->Table
            this.gopage(1);
        }
    };
</script>

<style type="text/css">

</style>