package com.yaorange.jk.service;

import com.yaorange.jk.entity.Export;
import com.yaorange.jk.utils.Pagination;
import org.springframework.security.access.method.P;

import java.sql.SQLException;
import java.util.List;

/**
 * 报运业务
 */
public interface ExportService {
    /**
     * 分页查询所有保运
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination page(Integer pageNo, Integer pageSize);

    /**
     * 不分页查询所有保运按日期降序
     * @return
     */
    List<Export> findAll();

    /**
     * 添加报运
     * @param export
     */
    void save(Export export);

    /**
     * 根据id查询报运单
     * @param id
     * @return
     */
    Export findById(String id);

    /**
     * 修改报运单
     * @param export
     */
    void update(Export export);

    /**
     * 修改报运单状态
     * @param exportIds
     * @param state
     */
    void updateState(String[] exportIds, Long state);

    /**
     * 删除报运单
     * @param ids
     */
    void deleteByIds(String[] ids)throws SQLException;

    /**
     * 电子报运
     * @param exportIds
     * @return
     */
    Boolean export(String[] exportIds);
}
