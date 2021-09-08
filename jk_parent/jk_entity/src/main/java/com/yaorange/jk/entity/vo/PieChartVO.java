package com.yaorange.jk.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 *  ECharts的"饼图"实体
 * @author runmu
 * @version 1.0
 * @date 16:27 2019/5/6
 */
public class PieChartVO {
    private List<String> titles = new ArrayList<>();
    private List<Data> values = new ArrayList<>();

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<Data> getValues() {
        return values;
    }

    public void setValues(List<Data> values) {
        this.values = values;
    }

    public class Data{
        private String name;
        private Long value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }
}
