package com.yaorange.jk.web.cargo;

import com.yaorange.jk.entity.Export;
import com.yaorange.jk.service.ExportService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/cargo/export")
public class ExportCtrl {
    @Autowired
    private ExportService exportService;
    @GetMapping
    public Pagination page(Integer pageNo,Integer pageSize) {
        return exportService.page(pageNo, pageSize);
    }
    @PostMapping
    public String save(@RequestBody Export export) {
        exportService.save(export);
        return "1";
    }
    @GetMapping("/{exportId}")
    public Export findById(@PathVariable("exportId") String exportId){
        return exportService.findById(exportId);
    }
    @PutMapping
    public String update(@RequestBody Export export) {
        exportService.update(export);
        return "1";
    }
    @PutMapping("/submit")
    public String submit(@RequestBody String[] exportIds){
        exportService.updateState(exportIds,1L);
        return "1";
    }
    @PutMapping("/cancel")
    public String cancel(@RequestBody String[] exportIds){
        exportService.updateState(exportIds,0L);
        return "1";
    }
    @DeleteMapping
    public ResponseEntity<String> deleteByIds(@RequestBody String[] ids){
        try {
            exportService.deleteByIds(ids);
            return new ResponseEntity<String>("1", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
