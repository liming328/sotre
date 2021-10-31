package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.*;
import com.yaorange.jk.service.ContractProductService;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.service.ExportService;
import com.yaorange.jk.utils.Pagination;
import com.yaorange.jk.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private BaseDao<Export, String> exportDao;
    @Autowired
    private ContractProductService contractProductService;
    @Autowired
    private ContractService contractService;

    //todo 处理电子报运
    @Override
    public Pagination page(Integer pageNo, Integer pageSize) {
        return exportDao.pageByHql("from Export order by inputDate desc",pageNo,pageSize);
    }

    @Override
    public List<Export> findAll() {
        return null;
    }

    @Override
    public void save(Export export) {
        //1.保存多个报运的货物（根据合同ids）
        //新增报运表-打断设计--新增报运货物表，附件同理
      /*  String contractIds = "'" + export.getContractIds().replaceAll(",", "','") + "'";
        System.out.println(contractIds);
        //2.获得待报运的货物
        List<ContractProduct> contractProductList = contractProductService.findListByContractIds(contractIds);
        //3.数据搬家--》将“合同货物”--》搬运到--》报运货物
        for (ContractProduct contractProduct : contractProductList) {
            ExportProduct exportProduct = new ExportProduct();
            exportProduct.setBoxNum(contractProduct.getBoxNum());
            exportProduct.setCnumber(contractProduct.getCnumber());
            exportProduct.setPrice(contractProduct.getPrice());
            exportProduct.setOrderNo(contractProduct.getOrderNo());
            exportProduct.setProductNo(contractProduct.getProductNo());
            exportProduct.setFactory(contractProduct.getFactory());
            //。。.其他根据项目需求（出口报运）需要搬运的货物属性
            //绑定和报运dan的“多对一”，通过“一方（报运dan）来维护”
            export.getExportProductSet().add(exportProduct);
            Set<ExtCproduct> extCproductSet = contractProduct.getExtCproductSet();//获得每个合同货物的附件

            //数据搬家--》将“合同附件”-->搬运到--》“报运附件”
            for (ExtCproduct extCproduct : extCproductSet) {
                //ExtC-->ExtE
                ExtEproduct extEproduct = new ExtEproduct();

                try {
                    BeanUtils.copyProperties(extEproduct,extCproduct);//将合同附件的所有属性一次性拷贝
                    exportProduct.setId(null);
                    //绑定和报运货物的“多对一”，通过“一方（报运货物）来维护”
                    exportProduct.getExtEproductSet().add(extEproduct);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        //5保存每个报运货物，级联保存该货物的每个报运附件
        exportDao.save(export);
        //将购销合同的状态改为2
        contractService.updateState(export.getContractIds().split(","), 2L);
    }
*/
        String contractIds = StringUtils.joinAroundStr(export.getContractIds(),"'");
        //将选中购销合同货物和附件 拷贝到 报运货物和附件表去
        // inputDate,exportProductSet
        export.setInputDate(new Date());
        //将contractProductSet --> exportProductSet
        List<ContractProduct> contractProductSet = contractProductService.findListByContractIds(contractIds);
        Set<ExportProduct> exportProductSet = new HashSet<>(contractProductSet.size());
        //循环遍历contractProductSet
        contractProductSet.forEach(cp -> {
            //1.cp --> ep(购销合同货物 拷贝到 报运货物)
            ExportProduct ep = new ExportProduct();
            try {
                BeanUtils.copyProperties(ep,cp);
                //避免hibernate 更新对象,将copy过来的id 字段清空
                ep.setId(null);
                //2.将购销合同货物附件extCproductSet 拷贝到 报运货物附件
                Set<ExtCproduct> extCproductSet = cp.getExtCproductSet();
                Set<ExtEproduct> extEproductSet = new HashSet<>(extCproductSet.size());
                //循环遍历销合同货物附件extCproductSet
                extCproductSet.forEach(ec->{
                    //ec(合同货物附件) --> ee(报运货物附件)
                    ExtEproduct ee = new ExtEproduct();
                    try {
                        BeanUtils.copyProperties(ee, ec);
                        ee.setId(null);
                        extEproductSet.add(ee);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
                //设置保运附件和报运货物关系
                ep.setExtEproductSet(extEproductSet);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            exportProductSet.add(ep);
        });
        //设置报运单和报运货物关系
        export.setExportProductSet(exportProductSet);
        exportDao.save(export);
        //将购销合同的状态改为2
        contractService.updateState(export.getContractIds().split(","), 2L);
    }
    @Override
    public Export findById(String id) {
        return exportDao.get(Export.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public void update(Export export) {
        //查询出数据库中的报运货物
        Export dbExport = findById(export.getId());
        //赋值
        export.setExportProductSet(dbExport.getExportProductSet());

        exportDao.merge(export);//hibernate执行事物的合并修改
    }

    @Override
    public void updateState(String[] exportIds, Long state) {
        for (String exportId : exportIds) {
            Export export = exportDao.get(Export.class, exportId);
            export.setState(state);
            exportDao.update(export);
        }

    }

    @Override
    public void deleteByIds(String[] ids) throws SQLException {
        for (String id : ids) {
            exportDao.deleteById(Export.class, id);
        }
    }

    @Override
    public Boolean export(String[] exportIds) {
        return null;
    }
}
