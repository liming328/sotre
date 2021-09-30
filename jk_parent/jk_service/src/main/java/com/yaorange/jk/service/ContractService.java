package com.yaorange.jk.service;

import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

public interface ContractService {
    /**
     * 分页查询所有合同
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination page(Integer pageNo, Integer pageSize);
    /**
     * 根据状态分页查询所有合同
     * @param state
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination page(Long state,Integer pageNo, Integer pageSize);

    List<Contract> findAll();

    void save(Contract contract);

    void update(Contract contract);

    void deleteByIds(String[] ids);

    /**
     * 根据id查询某一个合同
     * @param contractId
     * @return
     */
    Contract findById(String contractId);

    /**
     * （批量）更新合同的状态
     * @param groupId
     * @param newState
     */
    void updateState(String groupId, Long newState);
}
