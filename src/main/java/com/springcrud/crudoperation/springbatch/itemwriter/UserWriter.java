package com.springcrud.crudoperation.springbatch.itemwriter;


import com.springcrud.crudoperation.model.User;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class UserWriter implements ItemWriter<User> {

    private HttpServletResponse response;

    public UserWriter(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void write(Chunk<? extends User> users) throws Exception {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=user-data.xlsx");

        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet();

        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Row headerRow=sheet.createRow(0);
        String[] headers= {"Id", "Name", "Email", "CreatedAt", "UpdatedAt", "IsActive", "DeleteFlag"};

        for (int i=0;i<headers.length;i++){
            Cell cell=headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
        int rowNum=1;
        for (User userResponseDto : users){
            Row row=sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(userResponseDto.getId());
            row.createCell(1).setCellValue(userResponseDto.getName());
            row.createCell(2).setCellValue(userResponseDto.getEmail());
            row.createCell(3).setCellValue(userResponseDto.getCreatedAt());
            row.createCell(4).setCellValue(userResponseDto.getUpdatedAt());
            row.createCell(5).setCellValue(userResponseDto.isActive() ? "Yes" : "No");
            row.createCell(6).setCellValue(userResponseDto.isDeleteFlag() ? "Yes" : "No");
        }
        for (int i=0;i<headers.length;i++){
            sheet.autoSizeColumn(i);
        }
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
    }
}
