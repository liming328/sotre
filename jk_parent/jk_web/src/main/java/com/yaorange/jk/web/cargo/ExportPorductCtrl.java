package com.yaorange.jk.web.cargo;

import com.yaorange.jk.entity.ExportProduct;
import com.yaorange.jk.service.ExportProductService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargo/exportProduct")
public class ExportPorductCtrl {
    @Autowired
    private ExportProductService exportProductService;
    @GetMapping("/{exportId}")
    public Pagination list(Pagination page, @PathVariable("exportId")String exportId){
        return  exportProductService.findByPage(page,exportId);
    }
    @PutMapping
    public String update(@RequestBody ExportProduct exportProduct){
        exportProductService.update(exportProduct);
        return "1";
    }
}
