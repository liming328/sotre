<template>
    <div class="animated fadeIn">
    <Row>
        <Col span="6">
            模块：<Input v-model="keyWord" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button></Col>
    </Row>
    <br>
    <Row>
        <Button type="primary" icon="plus-round" @click="addModule()">新建</Button>
        <Button type="success" icon="wrench" @click="edit()">修改</Button>
        <Button type="error" icon="trash-a" @click="remove()">删除</Button>
    </Row>
    <br>
    <Row>
        <Table border :columns="columns1" :data="data1" @on-selection-change="change"></Table>
    </Row>
    <br>
    <Row>
        <Page :total="totalCount" :page-size="pageSize" :current="pageNo" @on-change="gopage"
              align="center"></Page>
    </Row>
    <br>
    <Modal
            v-model="updateModal"
            title="编辑模块"
            :mask-closable="false"
            @on-ok="update"
            @on-cancel="cancel" width="60%" :loading="loading">
        <Form ref="updateForm" :model="dataForm" :rules="formRule" :label-width="80">
            <Row>

                <Col span="14">
                    <FormItem label="等级" prop="ctype" >
                        <RadioGroup v-model="dataForm.ctype" @on-change="changeCType(dataForm.ctype)" >
                            <Radio label="0">一级菜单</Radio>
                            <Radio label="1">二级菜单</Radio>
                            <Radio label="2">三级菜单</Radio>
                           <!-- <Radio label="2">按钮</Radio>
                            <Radio label="3">链接</Radio>
                            <Radio label="4">状态</Radio>-->
                        </RadioGroup>
                    </FormItem>
                </Col>

            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="父模块" prop="parentId" >
                        <Select v-model="dataForm.parentId" filterable>
                            <Option v-for="item in moduleList" :value="item.id" :key="item.id">{{ item.name }}</Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="模块名" prop="name" >
                        <Input type="text" v-model="dataForm.name"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="层数" prop="layerNum" >
                        <Input type="text" v-model="dataForm.layerNum"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="权限标识" prop="cpermission" >
                        <Input type="text" v-model="dataForm.cpermission"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="链接" prop="curl" >
                        <Input type="text" v-model="dataForm.curl"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>


                <Col span="11">
                    <FormItem label="状态" prop="state" >
                        <Radio-group v-model="dataForm.state" type="button">
                            <Radio label="1" >启用</Radio>
                            <Radio label="0">停用</Radio>
                        </Radio-group>
                    </FormItem>
                </Col>
            </Row>


            <Row>
                <Col span="11">

                    <FormItem label="排序号" prop="orderNo" >
                        <Input type="text" v-model="dataForm.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="说明" prop="remark" >
                        <Input type="textarea" v-model="dataForm.remark"/>
                    </FormItem>
                </Col>
            </Row>

        </Form>
    </Modal>
    <Modal
            v-model="addModal"
            title="添加模块"
            :mask-closable="false"
            @on-ok="add"
            @on-cancel="cancel" width="60%" :loading="loading">
        <Form ref="addForm" :model="addForm" :rules="formRule" :label-width="80">
            <Row>

                <Col span="14">
                    <FormItem label="等级" prop="ctype" >
                        <RadioGroup v-model="addForm.ctype" @on-change="changeCType(addForm.ctype)" >
                            <Radio label="0">一级菜单</Radio>
                            <Radio label="1">二级菜单</Radio>
                            <Radio label="2">三级菜单</Radio>
                         <!--   <Radio label="2">按钮</Radio>
                            <Radio label="3">链接</Radio>
                            <Radio label="4">状态</Radio>-->
                        </RadioGroup>
                    </FormItem>
                </Col>

            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="父模块" prop="parentId" >
                        <Select v-model="addForm.parentId" filterable>
                            <Option v-for="item in moduleList" :value="item.id" :key="item.id">{{ item.name }}</Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="模块名" prop="name" >
                        <Input type="text" v-model="addForm.name"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="层数" prop="layerNum" >
                        <Input type="text" readonly v-model="addForm.layerNum"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="权限标识" prop="cpermission" >
                        <Input type="text" v-model="addForm.cpermission"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="链接" prop="curl" >
                        <Input type="text" v-model="addForm.curl"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>


                <Col span="11">
                    <FormItem label="状态" prop="state" >
                        <Radio-group v-model="addForm.state" type="button">
                            <Radio label="1" >启用</Radio>
                            <Radio label="0">停用</Radio>
                        </Radio-group>
                    </FormItem>
                </Col>
            </Row>


            <Row>
                <Col span="11">

                    <FormItem label="排序号" prop="orderNo" >
                        <Input type="text" v-model="addForm.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="说明" prop="remark" >
                        <Input type="textarea" v-model="addForm.remark"/>
                    </FormItem>
                </Col>
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
                loading:true,
                count: 0,
                gourpId: null,
                keyWord:"",
                pageSize:10,
                pageNo:1,
                totalPage:0,
                totalCount:0,
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '模块名',
                        key: 'name'
                    },
                    {
                        title: '层数',
                        key: 'layerNum'
                    },
                    {
                        title: '权限标识',
                        key: 'cpermission'
                    },
                    {
                        title: '链接',
                        key: 'curl'
                    },
                    {
                        title: '类型',
                        key: 'ctype',
                        //条件过滤器
                        filters: [
                            {
                                label: '一级菜单',
                                value: 0 //ctype=0
                            },
                            {
                                label: '二级菜单',
                                value: 1
                            },
                            {
                                label: '按钮',
                                value: 2
                            },
                            {
                                label: '链接',
                                value: 3
                            },
                            {
                                label: '状态',
                                value: 4
                            }
                        ],
                        filterMultiple: false,
                        filterMethod (value, row) {
                            if (value === 0) {
                                return row.ctype===0;
                            } else if (value === 1) {
                                return row.ctype===1;
                            }else if (value === 2) {
                                return row.ctype===2;
                            }else if (value === 3) {
                                return row.ctype===3;
                            }else if (value === 4) {
                                return row.ctype===4;
                            }
                        },
                        render: (h, params) => {
                            const task_status=parseInt(params.row.ctype);

                            if(task_status===0)
                                return h('div', [
                                    h('Tag', {
                                        props: {
                                            type: 'dot',
                                            color: "green"
                                        }
                                    }, "一级菜单"),
                                ]);

                            else if(task_status===1)
                                return h('div', [
                                    h('Tag', {
                                        props: {
                                            type: 'dot',
                                            color: "green"
                                        }
                                    }, "二级菜单"),
                                ]);
                            else if(task_status===2)
                                return h('div', [
                                    h('Tag', {
                                        props: {
                                            type: 'dot',
                                            color: "green"
                                        }
                                    }, "按钮"),
                                ]);
                            else if(task_status===3)
                                return h('div', [
                                    h('Tag', {
                                        props: {
                                            type: 'dot',
                                            color: "green"
                                        }
                                    }, "链接"),
                                ]);
                            else if(task_status===4)
                                return h('div', [
                                    h('Tag', {
                                        props: {
                                            type: 'dot',
                                            color: "green"
                                        }
                                    }, "状态"),
                                ]);
                        }
                    },
                    {
                        title: '状态',
                        ellipsis:'true',
                        key:'state',
                        filters: [
                            {
                                label: '启用',
                                value: 1
                            },
                            {
                                label: '禁用',
                                value: 0
                            }
                        ],
                        filterMultiple: false,
                        filterMethod (value, row) {
                            if (value === 1) {
                                return row.state===1;
                            } else if (value === 0) {
                                return row.state===0;
                            }
                        },
                        render: (h, params) => {
                            const task_status=parseInt(params.row.state);

                            if(task_status===1)
                                return h('div', [
                                    h('Tag', {
                                        props: {
                                            type: 'dot',
                                            color: "green"
                                        }
                                    }, "启用"),
                                ]);

                            else if(task_status===0)
                                return h('div', [

                                    h('Tag', {
                                        props: {
                                            type: 'dot',
                                            color: "red"
                                        }
                                    }, "禁用"),
                                ]);
                        }
                    }
                ],
                self: this,
                data1: [

                ],
                updateModal: false,
                addModal: false,
                addForm: {
                    "name":"",
                    "moduleName": "",
                    "layerNum": "3",
                    "cpermission": "",
                    "curl": "",
                    "ctype": "2",
                    "state": "1",
                    "remark": "",
                    "orderNo": "",
                    "parentId": ""
                },
               dataForm: {
                    "name":"",
                    "moduleName": "",
                    "layerNum": "1",
                    "cpermission": "",
                    "curl": "",
                    "ctype": "0",
                    "state": "1",
                    "remark": "",
                    "orderNo": "",
                    "parentId": ""
                },
                formRule: {
                    name: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    layerNum: [
                        {required: true, message:'层数不能为空',trigger:'blur'}
                    ]
                    ,
                    isLeaf: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    ico: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    cpermission: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    curl: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    ctype: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    state: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    belong: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    cwhich: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    remark: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    orderNo: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                },
                moduleList:{},
                degreeList:[
                    {
                        value: '0',
                        label: '超级管理员'
                    },
                    {
                        value: '1',
                        label: '跨部门跨人员'
                    },
                    {
                        value: '2',
                        label: '管理所有下属部门和人员'
                    },
                    {
                        value: '3',
                        label: '管理本部门'
                    },
                    {
                        value: '4',
                        label: '普通员工'
                    }
                ]
            }
        },
        methods: {
            change(e){
                this.count = e.length;
                if (e.length == 1) {
                    this.updateForm = e[0];
                }
                this.setGroupId(e);
            },
            setGroupId(e)
            {
                this.groupId = [];

                for (var i = 0; i < e.length; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            addModule(){
                this.addModal=true;
            },
            add(){
                const addForm = this.addForm;
                this.$refs['addForm'].validate((valid)=>{
                    if(valid) {
                        fetch({
                            url: '/system/module',
                            method: 'post',
                            data:addForm
                        }).then((resp) => {
                            if (resp.data=="1"){
                                this.$refs['addForm'].resetFields();
                                this.addModal=false;
                                this.gopage(this.pageNo);
                                this.$Message.success('新建模块成功');
                            } else {
                                this.$Message.error('新建模块失败');
                            }
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
            changeCType(type){
                this.dataForm.layerNum = (parseInt(type)+1)+"";
                this.addForm.layerNum = (parseInt(type)+1)+""
                fetch({
                    url: '/system/module/getParent/'+type,
                    method: 'get'
                }).then((result) => {
                    this.moduleList=result.data;
                });
            },
            edit (index) {

                if (this.groupId.length != 1) {
                    this.updateModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
                    this.updateModal = true;
                    var editModule =this.updateForm;

                    this.dataForm = editModule;
//radio组件lavle接受字符串，因此自己转换一下 int -> string
                    this.dataForm.ctype=editModule.ctype+"";
                    this.dataForm.state=editModule.state+"";
                    this.dataForm.layerNum=editModule.layerNum+"";
                    this.dataForm.orderNo=editModule.orderNo+"";
//初始化父菜单列表
                    this.changeCType(editModule.ctype);
                }



            },
            remove () {
                if (this.groupId.length>0){
                    if (confirm("确定要删除所选模块吗？")) {
                        fetch({
                            url: '/system/module',
                            method: 'delete',
                            data:this.groupId
                        }).then((result) => {
                            if(result.data=='1') {
                                this.groupId=[];
                                this.$Message.success('删除成功！!');
                                this.gopage(this.pageNo);
                                this.data1.splice(index, 1);
                            }else {
                                this.$Message.error('删除失败!');
                            }
                        });
                    }
                } else {
                    this.$Message.warning('请至少选择一项');
                }

            },
            ok () {
                this.updateModal = false,
                    this.$Message.info('点击了确定');
            },
            cancel () {
                this.$Message.info('点击了取消');
            },
            update () {
                this.$refs['updateForm'].validate((valid)=>{
                    if(valid)
                    {
                        const dataForm = this.dataForm;
                        fetch({
                            url: '/system/module',
                            method: 'put',
                            data:dataForm
                        }).then((resp) => {
                            if (resp.data=="1"){
                                this.gopage(this.pageNo);
                                this.updateModal=false;
                                this.dataForm={};
                                this.groupId = [];
                                this.$Message.success('修改部门成功!');
                            }else {
                                this.$Message.err('修改部门失败!');
                            }
                        });
                    }
                    else
                    {
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
            handleReset (moduleName) {
                this.$refs[moduleName].resetFields();
            },
            gopage(pageNo){
                const pageSize = this.pageSize;
                fetch({
                    url: '/system/module',
                    method: 'get',
                    params:{pageNo,pageSize}
                }).then((result) => {
                    this.data1=result.data.list;
                    this.pageNo=pageNo;
                    this.pageSize=pageSize;
                    this.totalCount=result.data.totalCount;
                });
            }
        },
        created:function(){
            this.gopage(this.pageNo);
        }
    }
</script>
