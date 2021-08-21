package com.yaorange.store.orm;

import java.io.Serializable;

public class Category implements Serializable {
    private Long serialVersionUID = 2564656548855877L;
    private String cid;
    private String cname;

    public Category(Long serialVersionUID, String cid, String cname) {
        this.serialVersionUID = serialVersionUID;
        this.cid = cid;
        this.cname = cname;
    }

    public Category() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(Long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    @Override
    public String toString() {
        return "Category{" +
                "serialVersionUID=" + serialVersionUID +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
