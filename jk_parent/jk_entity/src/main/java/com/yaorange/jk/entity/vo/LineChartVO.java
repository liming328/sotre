package com.yaorange.jk.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * ECharts的"曲线图"实体
 * @author runmu
 * @version 1.0
 * @date 17:12 2019/5/6
 */
public class LineChartVO {
    private List<String> titles = new ArrayList<>();
    private List<Long> values = new ArrayList<>();

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<Long> getValues() {
        return values;
    }

    public void setValues(List<Long> values) {
        this.values = values;
    }
}
