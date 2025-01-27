package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.ExcelExportService;
import com.springcrud.crudoperation.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Autowired
    private UserService userService;

    @Override
    public byte[] exportExcel() throws IOException {

        try {
            SuccessResponse<List<UserResponseDto>> users = userService.getAllUsers();
            if (users == null || users.getData() == null) {
                throw new RuntimeException("No user data available for export.");
            }

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("User-Data");

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);


            Row headerRow = sheet.createRow(0);
            String[] headers = {"Id", "Name", "Email", "CreatedAt", "UpdatedAt", "IsActive", "DeleteFlag"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            int rowNum=1;
            for (UserResponseDto userResponseDto : users.getData()){
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
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to Excel",e);
        }
    }
}
