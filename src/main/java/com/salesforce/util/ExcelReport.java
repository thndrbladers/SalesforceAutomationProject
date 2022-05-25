package com.salesforce.util;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReport {

	public static XSSFWorkbook workbook = new XSSFWorkbook();
	public static XSSFSheet sheet = workbook.createSheet(java.time.LocalDate.now().toString());
	public static FileOutputStream fileOutputStream;
	public static int i = 0;

	public ExcelReport() {

	}

	public static void result(String methodName, String parameters, String result, String exception) {
		if (i != 0) {
			sheet.createRow(i);
			sheet.getRow(i).createCell(0).setCellValue(i + 1);
			sheet.getRow(i).createCell(1).setCellValue(methodName);
			sheet.getRow(i).createCell(2).setCellValue(parameters.toString());
			sheet.getRow(i).createCell(3).setCellValue(result);
			sheet.getRow(i).createCell(4).setCellValue(exception);
			i++;
		} else {
			sheet.createRow(i);
			sheet.getRow(i).createCell(0).setCellValue(i );
			sheet.getRow(i).createCell(1).setCellValue("Test Cases");
			sheet.getRow(i).createCell(2).setCellValue("Parameters");
			sheet.getRow(i).createCell(3).setCellValue("Result");
			sheet.getRow(i).createCell(4).setCellValue("Exception");
			i++;
		}
	}

	public static void write() {
		try {
			fileOutputStream = new FileOutputStream("C:\\Users\\thndr\\Desktop\\excelResult.xlsx");
			workbook.write(fileOutputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
