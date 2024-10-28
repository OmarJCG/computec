package com.computec.computec.controller;

import com.computec.computec.model.Compra;
import com.computec.computec.service.impl.DetalleCompraExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private DetalleCompraExcelService detalleCompraExcelService;



    @GetMapping("/downloadExcel/{ordenId}")
    public ResponseEntity<byte[]> downloadExcel(@PathVariable Integer ordenId) throws IOException {
        return detalleCompraExcelService.generateExcelFileForOrder(ordenId);
    }
}
