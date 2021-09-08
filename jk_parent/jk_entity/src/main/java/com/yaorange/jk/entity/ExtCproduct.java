package com.yaorange.jk.entity;

import java.util.Objects;

/**
 * 〈购销合同的货物附件〉
 *
 * @author runmu
 * @since 1.0.0
 */
public class ExtCproduct {
    private String id;
    private String factoryName;
    private String productNo;
    private String productImage;
    private String productDesc;
    private String packingUnit;
    private Long cnumber;
    private Long price;
    private Long amount;
    private String productRequest;
    private Long orderNo;

    /**
     * 多个附件对应一个货物
     */
    private ContractProduct contractProduct;

    /**
     * 多个合同附件对应一个工厂
     */
    private Factory factory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Long getCnumber() {
        return cnumber;
    }

    public void setCnumber(Long cnumber) {
        this.cnumber = cnumber;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(String productRequest) {
        this.productRequest = productRequest;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtCproduct that = (ExtCproduct) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(factoryName, that.factoryName) &&
                Objects.equals(productNo, that.productNo) &&
                Objects.equals(productImage, that.productImage) &&
                Objects.equals(productDesc, that.productDesc) &&
                Objects.equals(packingUnit, that.packingUnit) &&
                Objects.equals(cnumber, that.cnumber) &&
                Objects.equals(price, that.price) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(productRequest, that.productRequest) &&
                Objects.equals(orderNo, that.orderNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, factoryName, productNo, productImage, productDesc, packingUnit, cnumber, price, amount, productRequest, orderNo);
    }

    public ContractProduct getContractProduct() {
        return contractProduct;
    }

    public void setContractProduct(ContractProduct contractProduct) {
        this.contractProduct = contractProduct;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
