package com.yaorange.jk.web.cargo;

import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购销合同控制器
 */
@RestController
@RequestMapping("/cargo/contract")
public class ContractCtrl {
    @Autowired
    private ContractService contractService;
    @GetMapping
    public Pagination page(Integer pageNo, Integer pageSize) {
        return contractService.page(pageNo, pageSize);
    }
    @GetMapping("/{state}")
    public Pagination page(@PathVariable("state")Long state, Integer pageNo, Integer pageSize) {
        return contractService.page(state,pageNo, pageSize);
    }
    @PostMapping
    public String save(@RequestBody Contract contract) {
        contractService.save(contract);
        return "1";
    }
    @PutMapping
    public String update(@RequestBody Contract contract) {
        contractService.update(contract);
        return "1";
    }
}
