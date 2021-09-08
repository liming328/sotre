package com.yaorange.jk.entity.vo;

/**
 * 〈ztree视图对象〉
 *
 * @author runmu
 * @since 1.0.0
 */
public class ZtreeVO {
//    { id:2, pId:0, name:"随意勾选 2", checked:true, open:true}
    /*  当前节点(当前模块) */
    private String id;

    /*  父节点(父模块) */
    private String pId;

    /*  当前模块名 */
    private String name;
    private Boolean checked;//true 选中  false  不选中
    private Boolean open;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
