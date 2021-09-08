package com.yaorange.jk.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 *  ECharts的"柱状图"实体
 * @author runmu
 * @version 1.0
 * @date 14:43 2019/5/6
 */
public class BarChartVO {
    private List<String> titles=new ArrayList<>();
    private List<Integer> values=new ArrayList<>();

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
