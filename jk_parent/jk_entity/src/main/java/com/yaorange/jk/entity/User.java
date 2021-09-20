package com.yaorange.jk.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈用户类〉
 *
 * @author runmu
 * @since 1.0.0
 */
public class User extends BaseEntity {
    private String id;
    private String userName;
    private String password;
    private Long state;

    //所属部门(多对一)
    private Dept dept;
    //拓展信息(一对一)
    private UserInfo userInfo;

    //角色集合(多对多)
    private Set<Role> roleSet = new HashSet<>(0);

    //视图属性 (只用于展示)
    private String managerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getManagerId() {
        if(this.getUserInfo()!=null && this.getUserInfo().getManager()!=null)
        {
            managerId = this.getUserInfo().getManager().getId();
        }

        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

}
