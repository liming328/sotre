<template>
    <div class="animated fadeIn">
    <Row>
        <Col span="6">用户名：
            <Input v-model="keyWord" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8">
            <Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button>
        </Col>
    </Row>
    <br>
    <Row>
        <Button type="primary" icon="plus-round" @click="addUser()">新建</Button>
        <Button type="success" icon="wrench" @click="edit()">修改</Button>
        <Button type="error" icon="trash-a" @click="remove()">删除</Button>
        <Button type="success" icon="wrench" @click="role()">修改角色</Button>
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

    <Modal
            v-model="updateModal"
            title="编辑用户"
            width="60%"
            :mask-closable="false"
            :loading="loading"
            @on-ok="update"
            @on-cancel="cancel">
        <Form ref="updateForm" :model="updateForm" :rules="ruleCustom" :label-width="80">
            <Row>
                <Col span="11">
                    <FormItem label="所在部门" prop="dept.id">
                        <Select v-model="updateForm.dept.id" filterable>
                            <Option v-for="item in deptList" :value="item.id" :key="item.id">{{ item.deptName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="直属领导" prop="userInfo.manager.id">
                        <Select v-model="updateForm.managerId">
                            <Option v-for="item in userList" :value="item.id" :disabled="item.id==updateForm.id"
                                    :key="item.id">{{ item.userName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>


            <Row>
                <Col span="11">
                    <FormItem label="登录名" prop="userName">
                        <Input type="text" readonly v-model="updateForm.userName"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="姓名" prop="userInfo.name">
                        <Input type="text" v-model="updateForm.userInfo.name"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="入职日期" prop="userInfo.joinDate">
                        <DatePicker v-model="updateForm.userInfo.joinDate" format="yyyy-MM-dd" type="date"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="薪水" prop="userInfo.salary">
                        <Input type="text" v-model="updateForm.userInfo.salary"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="等级" prop="userInfo.degree">
                        <Select v-model="updateForm.userInfo.degree" filterable>
                            <Option v-for="item in degreeList" :value="item.value" :key="item.value">{{ item.label
                                }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="性别" prop="userInfo.gender">
                        <RadioGroup v-model="updateForm.userInfo.gender" type="button">
                            <Radio label="1">男</Radio>
                            <Radio label="0">女</Radio>
                        </RadioGroup>
                    </FormItem>
                </Col>
                <Col span="11">
                    <FormItem label="状态" prop="state">
                        <Radio-group v-model="updateForm.state" type="button">
                            <Radio label="1">启用</Radio>
                            <Radio label="0">停用</Radio>
                        </Radio-group>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="岗位" prop="userInfo.station">
                        <Input type="text" v-model="updateForm.userInfo.station"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="电话" prop="userInfo.telephone">
                        <Input type="text" v-model="updateForm.userInfo.telephone"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="邮箱" prop="userInfo.email">
                        <Input type="text" v-model="updateForm.userInfo.email"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="出生年月" prop="userInfo.birthday">
                        <DatePicker type="date" v-model="updateForm.userInfo.birthday" format="yyyy-MM-dd"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">

                    <FormItem label="排序号" prop="userInfo.orderNo">
                        <Input type="text" v-model="updateForm.userInfo.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="说明" prop="userInfo.remark">
                        <Input type="textarea" v-model="updateForm.userInfo.remark"/>
                    </FormItem>
                </Col>
            </Row>

        </Form>
    </Modal>
    <Modal
            v-model="addModal"
            title="添加用户"
            :mask-closable="false"
            width="60%"
            @on-ok="add"
            @on-cancel="cancel"
            :loading="loading">
        <Form ref="addForm" :model="addForm" :rules="ruleCustom" :label-width="80">
            <Row>
                <Col span="11">
                    <FormItem label="所在部门" prop="dept.id">
                        <Select v-model="addForm.dept.id" filterable>
                            <Option v-for="item in deptList" :value="item.id" :key="item.id">{{ item.deptName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="直属领导" prop="userInfo.manager.id">
                        <Select v-model="addForm.managerId" filterable>
                            <Option v-for="item in userList" :value="item.id" :key="item.id">{{ item.userName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>


            <Row>
                <Col span="11">
                    <FormItem label="登录名" prop="userName">
                        <Input type="text" v-model="addForm.userName"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="姓名" prop="userInfo.name">
                        <Input type="text" v-model="addForm.userInfo.name"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="入职日期" prop="userInfo.joinDate">
                        <DatePicker v-model="addForm.userInfo.joinDate" type="date" placeholder="Select date"
                                    style="width: 200px"></DatePicker>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="薪水" prop="userInfo.salary">
                        <Input type="text" v-model="addForm.userInfo.salary"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="等级" prop="userInfo.degree">
                        <Select v-model="addForm.userInfo.degree" filterable>
                            <Option v-for="item in degreeList" :value="item.value" :key="item.value">{{ item.label
                                }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="性别" prop="userInfo.gender">
                        <RadioGroup v-model="addForm.userInfo.gender" type="button">
                            <Radio label="1">男</Radio>
                            <Radio label="0">女</Radio>
                        </RadioGroup>
                    </FormItem>
                </Col>
                <Col span="11">
                    <FormItem label="状态" prop="state">
                        <Radio-group v-model="addForm.state" type="button">
                            <Radio label="1">启用</Radio>
                            <Radio label="0">停用</Radio>
                        </Radio-group>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="岗位" prop="userInfo.station">
                        <Input type="text" v-model="addForm.userInfo.station"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="电话" prop="userInfo.telephone">
                        <Input type="text" v-model="addForm.userInfo.telephone"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="邮箱" prop="userInfo.email">
                        <Input type="text" v-model="addForm.userInfo.email"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="出生年月" prop="userInfo.birthday">
                        <DatePicker type="date" v-model="addForm.userInfo.birthday" placeholder="Select date"
                                    style="width: 200px"></DatePicker>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">

                    <FormItem label="排序号" prop="userInfo.orderNo">
                        <Input type="text" v-model="addForm.userInfo.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="说明" prop="userInfo.remark">
                        <Input type="textarea" v-model="addForm.userInfo.remark"/>
                    </FormItem>
                </Col>
            </Row>

        </Form>
    </Modal>

    <Modal
            v-model="roleModal"
            title="分配角色"
            @on-ok="updateRole"
            @on-cancel="cancel" width="60%">
        <Form ref="roleForm" :model="roleForm" :label-width="80">

            <Row>
                <Col span="11">
                    <FormItem label="登录名" prop="userName">
                        <Input type="text" v-model="roleForm.userName"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <FormItem label="角色" prop="roleIds">
                    <CheckboxGroup v-model="roleForm.roleIds">
                        <Checkbox v-for="(role,key) in roleList" :key="role.id" :label="role.id">{{role.name}}
                        </Checkbox>
                    </CheckboxGroup>
                </FormItem>
            </Row>

        </Form>
    </Modal>
</div>
</template>

<script type="text/ecmascript-6">
    import fetch from '../../utils/fetch';
    import formatDate from '../../utils/date';

    export default {
        data() {
            return {
                loading: true,
                count: 0,
                gourpId: null,
                pageSize: 10,
                pageNo: 1,
                totalPage: 0,
                totalCount: 0,
                keyWord: "",
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '用户名',
                        key: 'userName'
                    },
                    {
                        title: '状态',
                        key: 'state',
                        render: (h, params) => {
                            const state = parseInt(params.row.state);
                            if (state === 0) {
                                return "不可用";
                            }
                            else if (state === 1) {
                                return "可用";
                            }
                            else {
                                return "unkown"
                            }
                        }
                    }
                ],
                self: this,
                data1: [],
                formRule: {
                    userName: [
                        {required: true, message: '不能重复,可为中文不能为空', trigger: 'blur'}
                    ]
                },
                addModal: false,
                updateModal: false,
                roleModal: false,
                addForm: {
                    "dept": {
                        id: ""
                    },
                    "userName": "",
                    "state": "1",
                    "userInfo": {
                        "name": "",
                        "manager": {
                            id: ""
                        },
                        "joinDate": "",
                        "salary": "",
                        "degree": "",
                        "gender": "1",
                        "station": "",
                        "telephone": "",
                        "email": "",
                        "birthday": "",
                        "orderNo": "",
                        "remark": ""
                    }
                },
                updateForm: {
                    id: "",
                    "dept": {
                        id: ""
                    },
                    "userName": "",
                    "state": "",
                    "userInfo": {
                        "name": "",
                        "manager": {
                            id: ""
                        },
                        "joinDate": "",
                        "salary": "",
                        "degree": "",
                        "gender": "",
                        "station": "",
                        "telephone": "",
                        "email": "",
                        "birthday": "",
                        "orderNo": "",
                        "remark": ""
                    }
                },
                roleForm: {
                    id: "",
                    userName: "",
                    roleIds: {}
                },
                deptList: {},
                userList:{},
                roleList:{},
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
                ],
                ruleCustom: {
                    userName: [
                        { required: true,message:'cannot be empty', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            change(e) {
                if (e.length == 1) {
                    var editUser =e[0];

// 手动赋值的原因：userInfo中的manager不会转换成json，单独传了一个managerId过来
                    if(editUser.managerId!=undefined)
                    {
                        this.updateForm.userInfo.manager.id= editUser.managerId;
                    }
                    if(editUser.dept!=undefined)
                    {
                        this.updateForm.dept.id=editUser.dept.id;
                    }
                    if(editUser.userInfo != undefined) {
                        this.updateForm.userName=editUser.userName;
                        this.updateForm.id=editUser.id;
                        this.updateForm.userInfo.name=editUser.userInfo.name;
                        this.updateForm.userInfo.salary=editUser.userInfo.salary;

                        this.updateForm.userInfo.station=editUser.userInfo.station;
                        this.updateForm.userInfo.telephone=editUser.userInfo.telephone;
                        this.updateForm.userInfo.email=editUser.userInfo.email;
                        this.updateForm.userInfo.orderNo=editUser.userInfo.orderNo;
                        this.updateForm.userInfo.remark=editUser.userInfo.remark;
                        this.updateForm.roleSet=editUser.roleSet;
//数字转字符
                        this.updateForm.state=editUser.state+"";
                        this.updateForm.userInfo.degree=editUser.userInfo.degree+"";
                        this.updateForm.userInfo.gender=editUser.userInfo.gender+"";
//日期转字符
                        this.updateForm.userInfo.joinDate=formatDate(editUser.userInfo.joinDate);
                        this.updateForm.userInfo.birthday=formatDate(editUser.userInfo.birthday);
                    }
                    //使有数据为空时，不会用上次选中的数据
                    if (this.updateForm.id !=e[0].id){
                        this.$refs['updateForm'].resetFields();
                        this.$refs['roleForm'].resetFields();
                        this.updateForm.roleSet = [];
                    }
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
//显示新建用户对话框
            addUser() {
                this.addModal = true;
            },
//新建用户
            add() {
                this.$refs['addForm'].validate((valid) => {
                    if (valid) {
                        fetch({
                            url: '/system/user',
                            method: 'post',
                            data: this.addForm
                        }).then((result) => {
                            if (result.data=="1"){
                                this.gopage(this.pageNo);
                                this.$refs['addForm'].resetFields();
                                this.$Message.success('新建用户成功!');
                                this.addModal = false;
                            }else {
                                this.$Message.err('新建用户失败!');
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
//显示修改用户对话框
            edit() {
                if (this.count != 1) {
                    this.updateModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
                    this.updateModal = true;
                }
            },
//更新用户
            update() {
                this.$refs['updateForm'].validate((valid) => {
                    if (valid) {
                        fetch({
                            url: '/system/user',
                            method: 'put',
                            data: this.updateForm,
                        }).then((result) => {
                            if (result.data=="1"){
                                this.gopage(this.pageNo);
                                this.$refs['updateForm'].resetFields();
                                this.gourpId = [];
                                this.count = 0;
                                this.$Message.success('修改用户成功!');
                                this.updateModal = false;
                            }else {
                                this.$Message.err('修改用户失败!');
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
//删除用户
            remove() {
                if (this.groupId != null && this.groupId != "") {
                    if (confirm("确定要删除吗？")){
                        fetch({
                            url: '/system/user',
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
                const pageSize = this.pageSize;
                const keyWord = this.keyWord;
                fetch({
                    url: '/system/user',
                    method: 'get',
                    params: {pageNo, pageSize, keyWord}
                }).then((result) => {
                    this.data1 = result.data.list;
                    this.pageNo = pageNo;
                    this.pageSize = pageSize;
                    this.totalCount = result.data.totalCount;
                });
            },
            cancel() {
                this.$Message.info('点击了取消');
            },
//显示修改角色对话框
            role () {
                if (this.count != 1) {
                    this.roleModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
//获取到需要修改的用户信息
                    const editData = this.updateForm;
                    this.roleForm.id = editData.id;
                    this.roleForm.userName = editData.userName;

                    this.roleForm.roleIds = new Array();//clean 之前的数据

//将用户中的userSet 转成 roleIds，用于确定哪些角色已经被选中
                    for(let i = 0 ;i < this.updateForm.roleSet.length;i++)
                    {
                        this.roleForm.roleIds[i] = this.updateForm.roleSet[i].id;
                    }
//显示角色对话框
                    this.roleModal = true;
                }

            },
//更新角色
            updateRole(){
                const roleForm = this.roleForm;
                fetch({
                    url: '/system/user/role',
                    method: 'put',
                    data:roleForm
                }).then((result) => {
                    if (result.data=='1'){
                        this.$Message.success('角色分配成功!');
                        this.$refs['roleForm'].resetFields();
                        this.roleForm.roleIds = [];
                        this.groupId = [];
                        this.count = 0;
                        this.gopage(this.pageNo);
                        r
                    }else {
                        this.$Message.err('角色分配失败!');
                    }
                });
            },
        },
        mounted: function () {
            this.gopage(1);

//加载所有部门
            fetch({
                url: '/system/dept/all',
                method: 'get',

            }).then((result) => {
                this.deptList=result.data;
            });

//加载所有员工（直属领导）
            fetch({
                url: '/system/user/all',
                method: 'get'
            }).then((result) => {
                this.userList=result.data;
            });
//加载角色列表
            fetch({
                url: '/system/role/all',
                method: 'get'
            }).then((result) => {
                this.roleList = result.data;
            });
        }
    }

</script>
