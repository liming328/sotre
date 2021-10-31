package com.yaorange.jk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 〈报运单〉
 *
 * @author runmu
 * @since 1.0.0
 */
@JsonIgnoreProperties({"handler","hibernateLazyInitializer","exportProductSet"})
public class Export extends BaseEntity{
    private String id;

    /*  制单日期 */
    private Date inputDate = new Date();

    private String contractIds;
    private String customerContract;
    private String lcno;
    private String consignee;
    private String marks;
    private String shipmentPort;
    private String destinationPort;
    private String transportMode;
    private String priceCondition;
    private String remark;
    private Long boxNums;
    private Long grossWeights;
    private Long measurements;
    /*0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务  6-已申报*/
    private Long state;

    /*一个报运单下有多个报运货物*/
    private Set<ExportProduct> exportProductSet = new HashSet<>(0);


    /*视图属性  货物数/附件数*/
    private String  nums;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getContractIds() {
        return contractIds;
    }

    public void setContractIds(String contractIds) {
        this.contractIds = contractIds;
    }

    public String getCustomerContract() {
        return customerContract;
    }

    public void setCustomerContract(String customerContract) {
        this.customerContract = customerContract;
    }

    public String getLcno() {
        return lcno;
    }

    public void setLcno(String lcno) {
        this.lcno = lcno;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getPriceCondition() {
        return priceCondition;
    }

    public void setPriceCondition(String priceCondition) {
        this.priceCondition = priceCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getBoxNums() {
        return boxNums;
    }

    public void setBoxNums(Long boxNums) {
        this.boxNums = boxNums;
    }

    public Long getGrossWeights() {
        return grossWeights;
    }

    public void setGrossWeights(Long grossWeights) {
        this.grossWeights = grossWeights;
    }

    public Long getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Long measurements) {
        this.measurements = measurements;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Set<ExportProduct> getExportProductSet() {
        return exportProductSet;
    }

    public void setExportProductSet(Set<ExportProduct> exportProductSet) {
        this.exportProductSet = exportProductSet;
    }

    public String getNums() {
        Integer  epNums = getExportProductSet().size();
        Integer  eeNums = 0;
        for(ExportProduct ep :getExportProductSet())
        {
            eeNums+=ep.getExtEproductSet().size();
        }
        nums = epNums+"/"+eeNums;
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }
}
