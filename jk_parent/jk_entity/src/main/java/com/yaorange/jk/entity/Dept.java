package com.yaorange.jk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 〈部门类〉
 *
 * @author runmu
 * @since 1.0.0
 */
//告诉jackson忽略哪些属性
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class Dept {

    private String id;

    private String deptName;

    private Long state;

    private String parentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
