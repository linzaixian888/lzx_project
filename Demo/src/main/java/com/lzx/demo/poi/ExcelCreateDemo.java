package com.lzx.demo.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCreateDemo {
	public static void main(String[] args) throws Exception {
		
		create2007();
	}
	public static void create2003() throws Exception{
		FileOutputStream fos=new FileOutputStream("d:/test.xls");
		//创建文档
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建工作表
		HSSFSheet sheet=workbook.createSheet("工作表1");
		//创建行,从0开始
		HSSFRow row=sheet.createRow(0);
		//创建单元格，从0开始
		HSSFCell cell=row.createCell(0);
		cell.setCellValue(true);
		//输出到流
		workbook.write(fos);
		fos.close();
	}
	
	public static void create2007() throws Exception{
		FileOutputStream fos=new FileOutputStream("d:/test.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("工作表1");
		XSSFRow row=sheet.createRow(0);
		XSSFCell cell=row.createCell(0);
		cell.setCellValue(true);
		workbook.write(fos);
		fos.close();
	}
}
