package com.computec.computec.service.impl;

import com.computec.computec.dao.IDetalleCompraDao;
import com.computec.computec.model.Compra;
import com.computec.computec.model.DetalleCompra;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DetalleCompraExcelService {

    @Autowired
    private IDetalleCompraDao detalleCompraRepository;

    public ResponseEntity<byte[]> generateExcelFileForOrder(Integer ordenId) throws IOException {
        List<DetalleCompra> detalles = detalleCompraRepository.findByOrdenId(ordenId);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Detalles de Compra");

        String[] columns = {"ID", "Nombre", "Cantidad", "Precio", "Total", "Imagen"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(createHeaderCellStyle(workbook));
        }

        int rowNum = 1;
        for (DetalleCompra detalle : detalles) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(detalle.getId());
            row.createCell(1).setCellValue(detalle.getNombre());
            row.createCell(2).setCellValue(detalle.getCantidad());
            row.createCell(3).setCellValue(detalle.getPrecio().doubleValue());
            row.createCell(4).setCellValue(detalle.getTotal().doubleValue());
            row.createCell(5).setCellValue(detalle.getImg());
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "DetalleCompra.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(outputStream.toByteArray());
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}
