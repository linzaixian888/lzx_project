package com.lzx.demo.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class AlipayDemo {
	private static String targetPath="d:/bbb.xls";
	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) throws Exception {
		HSSFWorkbook workbook=getHssfWorkbook();
		HSSFSheet sheet=workbook.getSheetAt(0);
		int rowNum=sheet.getLastRowNum();
		HSSFWorkbook templateWorkbook=getTemplate();
		HSSFSheet templateSheet=templateWorkbook.getSheetAt(0);
		for(int i=0;i<=rowNum;i++){
			HSSFRow row=sheet.getRow(i);
			String time=dateFormat.format(row.getCell(0).getDateCellValue())+" 12:00:00";
			double value=Double.parseDouble(row.getCell(1).getStringCellValue().replace(" 元", ""));
			initDefault(templateSheet.createRow(i+1), time, value);
		}
		FileOutputStream fos=new FileOutputStream(targetPath);
		templateWorkbook.write(fos);
		fos.close();
		
		
	}
	public static HSSFWorkbook getHssfWorkbook() throws IOException{
			return new HSSFWorkbook(AlipayDemo.class.getResourceAsStream("AlipayDemo.xls"));
	}
	public static HSSFWorkbook getTemplate() throws IOException{
		return new HSSFWorkbook(AlipayDemo.class.getResourceAsStream("myMoney.xls"));
	}
	public static HSSFRow  initDefault(HSSFRow row,String date,double value){
		row.createCell(0).setCellValue("收入");;
		row.createCell(1).setCellValue(date);;
		row.createCell(2).setCellValue("职业收入");;
		row.createCell(3).setCellValue("投资收入");;
		row.createCell(4).setCellValue("支付宝");;
		row.createCell(6).setCellValue(value);;
		row.createCell(7).setCellValue("本人");;
		row.createCell(9).setCellValue("投资");;
		row.createCell(10).setCellValue("余额宝收益");;
		return row;
	}
}
