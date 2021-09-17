<template>
    <div class="animated fadeIn">
    <Row>
        <Col span="6">角色：
            <Input v-model="name" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8">
            <Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button>
        </Col>
    </Row>
    <br>
    <Row>
        <Button type="primary" icon="plus-round" @click="addRole()">新建</Button>
        <Button type="success" icon="wrench" @click="edit()">修改</Button>
        <Button type="error" icon="trash-a" @click="remove()">删除</Button>
    </Row>
    <br>
    <Row>
        <Table border :columns="columns1" :data="data1" @on-selection-change="s=>{change(s)}"></Table>
    </Row>
    <br>
    <Row>
        <Page show-elevator align="center" :total="totalCount" :current="pageNo" :page-size="pageSize" @on-change="gopage" ></Page>
    </Row>

    <Modal
            v-model="addModal"
            title="添加角色"
            :mask-closable="false"
            :loading="loading"
            @on-ok="add"
            @on-cancel="cancel"
            width="60%">
        <Form ref="addForm" :model="addForm" :rules="ruleCustom" :label-width="80">
            <Row>
                <Col span="11">
                    <FormItem label="角色名字" prop="name">
                        <Input type="text" v-model="addForm.name"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="备注" prop="remark">
                        <Input type="text" v-model="addForm.remark"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="排序号" prop="orderNo">
                        <Input type="text" v-model="addForm.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
            </Row>
            <Row>
                <FormItem label="模块">
                    <!--<ul id="addTree" class="ztree" style="background-color: #3dbd7d;width: 90%">
                        <li v-for="module in moduleList" :value="module.id">
                            {{module.name}}
                        </li>
                    </ul>-->
                    <checkbox-group v-model="addForm.moduleIds">
                        <checkbox  v-for ="(module,index) in moduleList" :label="module.id">{{index+1}}-{{module.name}}</checkbox>
                    </checkbox-group>
                </FormItem>

            </Row>
        </Form>
    </Modal>

    <Modal
            v-model="updateModal"
            title="编辑角色"
            :mask-closable="false"
            :loading="loading"
            @on-ok="update"
            @on-cancel="cancel"
            width="60%">
        <Form ref="updateForm" :model="updateForm" :rules="ruleCustom" :label-width="80">

            <Row>
                <Col span="11">
                    <FormItem label="角色名字" prop="name">
                        <Input type="text" v-model="updateForm.name"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="备注" prop="remark">
                        <Input type="text" v-model="updateForm.remark"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="排序号" prop="orderNo">
                        <Input type="text" v-model="updateForm.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
            </Row>
            <Row>
             <FormItem label="模块">
                    <!--<ul id="addTree" class="ztree" style="background-color: #3dbd7d;width: 90%">
                        <li v-for="module in moduleList" :value="module.id">
                            {{module.name}}
                        </li>
                    </ul>-->
            <checkbox-group v-model="updateForm.moduleIds">
                <checkbox  v-for ="(module,index) in moduleList" :label="module.id">{{index+1}}-{{module.name}}</checkbox>
            </checkbox-group>
            </FormItem>
             </Row>
        </Form>
    </Modal>
</div>
</template>

<script type="text/ecmascript-6">
    import fetch from '../../utils/fetch';
    export default {
        data() {
            return {
                loading: true,
                count: 0,
                gourpId: null,
                pageSize: 5,
                pageNo: 1,
                totalPage: 0,
                totalCount: 0,
                name: "",
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '角色名字',
                        key: 'name'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                    {
                        title: '排序号',
                        key: 'orderNo'
                    }
                ],
                self: this,
                data1: [],
                updateModal: false,
                addModal: false,
                updateForm: {
                    id:"",
                    name: "",
                    remark: "",
                    orderNo: "",
                    moduleIds: []
                },
                addForm: {
                    name: "",
                    remark: "",
                    orderNo: "",
                    moduleIds:[]
                },
                moduleList: {},//角色可持有的模块
                ruleCustom: {
                    name: [
                        {required: true, message: '角色名字不能为空', trigger: 'blur'}
                    ]
                    ,
                    remark: [
                        {required: true, message: '备注不能为空', trigger: 'blur'}
                    ]
                    ,
                    orderNo: [
                        {required: true, message: '排序号不能为空', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            change(e) {
                if (e.length == 1) {
                    this.updateForm = e[0];
                }
                this.setGroupId(e);
            },
            setGroupId(e) {
                this.groupId = [];
                this.count = e.length;
                for (var i = 0; i < e.length; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            reset(form) {
                this.$refs[form].resetFields();
            },
            addRole() {
                this.addModal = true;
            },
            add(){
               /* const addForm = this.addForm;*/
                this.$refs['addForm'].validate((valid)=>{
                    if(valid) {
                        fetch({
                            url: '/system/role',
                            method: 'post',
                            data: this.addForm
                        }).then((resp) => {
                            if (resp.data=="1"){
                                this.$refs['addForm'].resetFields();
                                this.addModal=false;
                                this.gopage(this.pageNo);
                                //如果设置集合，不能清空
                                this.addForm.moduleIds = [];
                                this.$Message.success('新建角色成功');
                            } else {
                                this.$Message.error('新建角色失败');
                            }
                        }).catch(reason => {
                            this.$Message.error('系统故障！');
                        });
                    } else {
                        this.$Message.error("表单验证失败");
                        setTimeout(()=>{
                            this.loading=false;
                            this.$nextTick(()=>{
                                this.loading=true;
                            });
                        },1000);
                    }
                })
            },
            edit() {
                if (this.count != 1) {
                    this.updateModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
                    const editData = this.updateForm;
                    this.updateForm.moduleIds = [];
                    for (let i = 0; i <editData.moduleSet.length ; i++) {
                       this.updateForm.moduleIds[i] =editData.moduleSet[i].id;
                    }
//radio组件lavle接受字符串，因此自己转换一下 int -> string
                    this.updateForm.orderNo= this.updateForm.orderNo+"";
                    this.updateModal = true;
                }
            },
            update() {
                this.$refs['updateForm'].validate((valid) => {
                    if (valid) {
                        fetch({
                            url: '/system/role',
                            method: 'put',
                            data: this.updateForm
                        }).then((result) => {
                            if (result.data=="1"){
                                this.updateModal = false,
                                 this.$refs['updateForm'].resetFields();
                                this.updateForm.moduleIds = [];
                                this.updateForm.moduleSet = [];
                                this.$Message.success('修改角色成功!');
                                this.gopage(this.pageNo);
                            } else {
                                this.$Message.err('修改角色失败!');
                            }

                        });
                    }
                    else {
                        this.$Message.error("表单验证失败");
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                })
            },
            remove() {
                if (this.groupId != null && this.groupId != "") {
                    if (confirm("确定要删除所选角色吗？")) {
                        fetch({
                            url: '/system/role',
                            method: 'delete',
                            data: this.groupId
                        }).then((result) => {
                            if (result.data == '1') {
                                this.$Message.success('删除成功!');
                                this.gopage(this.pageNo);
                            }else {
                                this.$Message.err('删除失败!');
                            }
                        });
                    }
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            },
            gopage(pageNo) {
                this.pageNo = pageNo;
                fetch({
                    url:'/system/role',
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
            cancel() {
                this.$Message.info('点击了取消');
            }
        },
        mounted: function () {
            this.gopage();
            //加载的所有模块没有级次关系
            fetch({
                url:"/system/module/all",
                method:"get"
            }).then((resp)=>{
                this.moduleList = resp.data;
            })
        }
    }


</script>
