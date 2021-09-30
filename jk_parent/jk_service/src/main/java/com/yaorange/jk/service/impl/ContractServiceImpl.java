package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private BaseDao<Contract, String> contractDao;

    @Override
    public Pagination page(Integer pageNo, Integer pageSize) {
        return contractDao.pageByHql("from Contract order by createTime desc",pageNo,pageSize);
    }

    @Override
    public Pagination page(Long state, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public List<Contract> findAll() {
        return null;
    }

    @Override
    public void save(Contract contract) {
          contractDao.save(contract);
    }

    @Override
    public void update(Contract contract) {
           contractDao.update(contract);
    }

    @Override
    public void deleteByIds(String[] ids) {

    }

    @Override
    public Contract findById(String contractId) {
        return null;
    }

    @Override
    public void updateState(String groupId, Long newState) {

    }
}
