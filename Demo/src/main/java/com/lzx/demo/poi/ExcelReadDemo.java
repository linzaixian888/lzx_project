package com.lzx.demo.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;





public class ExcelReadDemo {
	private static String filePath="d:/高志林-6月.xls";
	public static void main(String[] args) {
		HSSFWorkbook wb=new ExcelReadDemo().getHssfWorkbook(filePath);
		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row=sheet.getRow(0);
		HSSFCell cell=row.getCell(0);
	}
	public HSSFWorkbook getHssfWorkbook(String path){
		FileInputStream is=null;
		try {
			is=new FileInputStream(path);
			return new HSSFWorkbook(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
