package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Module;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private BaseDao<Module, String> moduleDao;

    public Pagination page(Integer pageNo, Integer pageSize) {
        return moduleDao.pageByHql("from Module",pageNo,pageSize);
    }

    public List<Module> findAll() {
        return moduleDao.getListByHQL("from Module");
    }

    public void save(Module module) {
         moduleDao.save(module);
    }

    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            moduleDao.deleteById(Module.class,id);
        }
    }

    public void update(Module module) {
              moduleDao.update(module);
    }

    @Override
    public List<Module> findListByCtype(Long ctype) {
        return moduleDao.getListByHQL("from Module m where m.ctype=?", ctype);
    }
}
