package com.yaorange.store.test;

import com.yaorange.store.orm.Product;
import com.yaorange.store.service.impl.ProductServiceImpl;
import org.junit.Test;

import java.util.List;

public class ProductServiceTest {
    @Test
    public void testProductService() throws Exception {
        List<Product> hotProductList = new ProductServiceImpl().findHotProductList();
        hotProductList.forEach(product -> {
            System.out.println(product.getPid()+"--"+product.getPname());
        });
    }
}
