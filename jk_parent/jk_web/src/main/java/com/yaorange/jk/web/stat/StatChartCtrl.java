package com.yaorange.jk.web.stat;

import com.yaorange.jk.entity.vo.BarChartVO;
import com.yaorange.jk.entity.vo.LineChartVO;
import com.yaorange.jk.entity.vo.PieChartVO;
import com.yaorange.jk.service.ContractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 工厂销售（合同货物）统计控制器
 */
@RestController
@RequestMapping("/stat")
public class StatChartCtrl {

    @Autowired
    private ContractProductService contractProductService;
    @GetMapping("/factorysale")
    public BarChartVO factorySale() {
        List<Object[]> list = contractProductService.findFactorySale();
        BarChartVO barChartVO = new BarChartVO();
        for (Object[] objects : list) {
            String title = (String) objects[0];
            BigDecimal value = (BigDecimal) objects[1];
            barChartVO.getTitles().add(title);
            barChartVO.getValues().add(value.longValue());
        }
        return barChartVO;
    }
    @GetMapping("/productSale")
    public PieChartVO productsale(){
        List<Object[]> productSaleList = contractProductService.findProductSaleList();
        PieChartVO pieChartVO = new PieChartVO();
        for (Object[] objects : productSaleList) {
            String title = (String) objects[0];
            BigDecimal value = (BigDecimal) objects[1];
            pieChartVO.getTitles().add(title);
            PieChartVO.Data data = pieChartVO.new Data();
            data.setName(title);
            data.setValue(value.longValue());
            pieChartVO.getValues().add(data);
        }
        return pieChartVO;
    }
    @GetMapping("/onlineInfo")
    public LineChartVO onlineInfo(){
        List<Object[]> onlineInfoList = contractProductService.findOnlineInfoList();
        LineChartVO lineChartVO = new LineChartVO();
        for (Object[] objects : onlineInfoList) {
            String title = (String) objects[0];
            BigDecimal value = (BigDecimal) objects[1];
            lineChartVO.getTitles().add(title);
            lineChartVO.getValues().add(value.longValue());
        }
        return lineChartVO;
    }
}
