package com.yaorange.jk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 〈购销合同〉
 *
 * @author runmu
 * @since 1.0.0
 */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","contractProductSet"})
public class Contract extends BaseEntity {
    private String id;
    private String offeror;
    private String contractNo;
    private Date signingDate;
    private String inputBy;
    private String checkBy;
    private String inspector;

    /*  总金额 */
    private Long totalAmount;

    private String crequest;
    private String customName;
    private Date shipTime;
    private Long importNum;
    private Date deliveryPeriod;
    private Long oldState;
    private Long outState;
    private String tradeTerms;
    private String printStyle;
    private String remark;

    /*0草稿 1已上报待报运  2已报运*/
    private Long state;

    //用于前端显示"货物数/附件数"
    private String nums;

    public String getNums() {
        //获取当前合同货物数
        Integer contractProductTotal = this.contractProductSet.size();

        //定义当前合同的附件总数=多个货物下的附件总数累积之和
        Integer extTotal = 0;
        for (ContractProduct contractProduct : this.contractProductSet) {
            Set<ExtCproduct> extCproductSet = contractProduct.getExtCproductSet();
            extTotal += extCproductSet.size();
        }

        return contractProductTotal + "/" + extTotal;
    }
    public void setNums(String nums) {
        this.nums = nums;
    }

    /**
     * 一个购销合同有多个合同货物
     */
    private Set<ContractProduct> contractProductSet = new HashSet<>(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public String getInputBy() {
        return inputBy;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCrequest() {
        return crequest;
    }

    public void setCrequest(String crequest) {
        this.crequest = crequest;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Long getImportNum() {
        return importNum;
    }

    public void setImportNum(Long importNum) {
        this.importNum = importNum;
    }

    public Date getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(Date deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public Long getOldState() {
        return oldState;
    }

    public void setOldState(Long oldState) {
        this.oldState = oldState;
    }

    public Long getOutState() {
        return outState;
    }

    public void setOutState(Long outState) {
        this.outState = outState;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public String getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(String printStyle) {
        this.printStyle = printStyle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Set<ContractProduct> getContractProductSet() {
        return contractProductSet;
    }

    public void setContractProductSet(Set<ContractProduct> contractProductSet) {
        this.contractProductSet = contractProductSet;
    }
}
