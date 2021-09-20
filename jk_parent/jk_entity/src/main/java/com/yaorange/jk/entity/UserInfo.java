package com.yaorange.jk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 〈用户附加信息类〉
 *
 * @author runmu
 * @since 1.0.0
 */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","user","manager"})
public class UserInfo extends BaseEntity{
    private String id;
    private String name;
    private Date joinDate;
    private Long salary;
    private Date birthday;
    private String gender;
    private String station;
    private String telephone;
    private Long degree;
    private String remark;
    private Long orderNo;
    private String email;

    //直属领导(多对一)
    private User manager;

    //所属用户(一对一)
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getDegree() {
        return degree;
    }

    public void setDegree(Long degree) {
        this.degree = degree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", degree=" + degree +
                ", remark='" + remark + '\'' +
                ", orderNo=" + orderNo +
                ", email='" + email + '\'' +
                ", manager=" + manager +
                ", user=" + user +
                '}';
    }
}
