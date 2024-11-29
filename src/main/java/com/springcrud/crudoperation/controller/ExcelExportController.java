package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class ExcelExportController {
    @Autowired
    private ExcelExportService exportService;

    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
        byte[] excelFile= exportService.exportExcel();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename("user_data.xlsx").build());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(excelFile.length);

        return ResponseEntity.ok().headers(headers).body(excelFile);
    }
}
