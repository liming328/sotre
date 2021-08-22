package com.yaorange.store.entity;

/**
 * 购物条目
 */
public class CartItem {
    private Product product;
    private int count;//该商品对应的条数
    private Double subtotal;//该条目销售小计

    public CartItem(Product product, int count, Double subtotal) {
        this.product = product;
        this.count = count;
        this.subtotal = subtotal;
    }

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return this.product.getShop_price()*this.count;
    }

  /*  public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }*/
}
