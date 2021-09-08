package com.yaorange.jk.entity.vo;

/**
 * 〈电子(海关)报运单结果〉
 *
 * @author runmu
 * @since 1.0.0
 */
public class ExportResult {
    private String exportId;
    private Integer state;
    private String remark;  /*  电子报运结果描述 */

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExportId() {
        return exportId;
    }

    public void setExportId(String exportId) {
        this.exportId = exportId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
