package com.lzx.demo.poi;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class YiRenDaiDemo {
	private static String targetPath="d:/abb.xls";
	private static int index=1;
	public static void main(String[] args) throws Exception {
		HSSFWorkbook workbook=new HSSFWorkbook(YiRenDaiDemo.class.getResourceAsStream("YiRenDaiDemo.xls"));
		HSSFSheet sheet=workbook.getSheetAt(0);
		HSSFWorkbook templateWorkbook=getTemplate();
		HSSFSheet templateSheet=templateWorkbook.getSheetAt(0);
		int rowNum=sheet.getLastRowNum();
		for(int i=0;i<=rowNum;i++){
			HSSFRow row=sheet.getRow(i);
			HSSFCell cell=row.getCell(1);
			if(cell==null){
				continue;
			}
			judgeCell(cell,row,templateSheet);
//			System.out.println(cell.getStringCellValue());
		}
		FileOutputStream fos=new FileOutputStream(targetPath);
		templateWorkbook.write(fos);
		fos.close();
	}
	public static HSSFRow judgeCell(HSSFCell cell,HSSFRow row,HSSFSheet templateSheet){
		HSSFRow templateRow=templateSheet.createRow(index);
		if(exist(cell, "活动奖励","回款利息")){
			HSSFCell dateCell=row.getCell(0);
			HSSFCell textCell=row.getCell(2);
			HSSFCell valueCell=row.getCell(3);
			Double valueDouble=valueCell.getNumericCellValue();
			initShouRu(templateRow, dateCell.getStringCellValue(), valueDouble, textCell.getStringCellValue());
		}else if(exist(cell, "提现手续费","担保服务费","服务费")){
			HSSFCell dateCell=row.getCell(0);
			HSSFCell textCell=row.getCell(2);
			HSSFCell valueCell=row.getCell(4);
			Double valueDouble=-valueCell.getNumericCellValue();
			initZhichu(templateRow, dateCell.getStringCellValue(), valueDouble, textCell.getStringCellValue());
		}else if(exist(cell, "提现")){
			HSSFCell dateCell=row.getCell(0);
			HSSFCell textCell=row.getCell(2);
			HSSFCell valueCell=row.getCell(4);
			Double valueDouble=-valueCell.getNumericCellValue();
			initZhuanZhang(templateRow, dateCell.getStringCellValue(), valueDouble, textCell.getStringCellValue(),"宜人贷","民生借记卡3420");
		}else if(exist(cell, "充值")){
			HSSFCell dateCell=row.getCell(0);
			HSSFCell textCell=row.getCell(2);
			HSSFCell valueCell=row.getCell(3);
			Double valueDouble=valueCell.getNumericCellValue();
			initZhuanZhang(templateRow, dateCell.getStringCellValue(), valueDouble, textCell.getStringCellValue(),"招行一卡通0195","宜人贷");
		}else if(exist(cell, "回款本金","资金转至资金账户")){
			HSSFCell dateCell=row.getCell(0);
			HSSFCell textCell=row.getCell(2);
			HSSFCell valueCell=row.getCell(3);
			Double valueDouble=valueCell.getNumericCellValue();
			initZhuanZhang(templateRow, dateCell.getStringCellValue(), valueDouble, textCell.getStringCellValue(),"应收款项","宜人贷");
		}else if(exist(cell, "投标","转成待放款","资金转出")){
			HSSFCell dateCell=row.getCell(0);
			HSSFCell textCell=row.getCell(2);
			HSSFCell valueCell=row.getCell(4);
			Double valueDouble=-valueCell.getNumericCellValue();
			initZhuanZhang(templateRow, dateCell.getStringCellValue(), valueDouble, textCell.getStringCellValue(),"宜人贷","应收款项");
		}else{
			System.out.println("不处理的类型："+cell.getStringCellValue());
			templateSheet.removeRow(templateRow);
			return null;
		}
		index++;
		return row;
	}
	
	public static HSSFRow  initShouRu(HSSFRow row,String date,double value,String text){
		row.createCell(0).setCellValue("收入");
		row.createCell(1).setCellValue(date.replace(".", "-"));;
		row.createCell(2).setCellValue("职业收入");
		row.createCell(3).setCellValue("投资收入");
		row.createCell(4).setCellValue("宜人贷");
		row.createCell(6).setCellValue(value);
		row.createCell(7).setCellValue("本人");
		row.createCell(9).setCellValue("投资");
		row.createCell(10).setCellValue(text);
		return row;
	}
	public static HSSFRow initZhuanZhang(HSSFRow row,String date,double value,String text,String user1,String user2){
		row.createCell(0).setCellValue("转帐");
		row.createCell(1).setCellValue(date.replace(".", "-"));;
		row.createCell(4).setCellValue(user1);
		row.createCell(5).setCellValue(user2);
		row.createCell(6).setCellValue(value);
		row.createCell(9).setCellValue("转帐");
		row.createCell(10).setCellValue(text);
		return row;
	}
	public static HSSFRow initZhichu(HSSFRow row,String date,double value,String text){
		row.createCell(0).setCellValue("支出");
		row.createCell(1).setCellValue(date.replace(".", "-"));
		row.createCell(2).setCellValue("金融保险");
		row.createCell(3).setCellValue("服务手续");
		row.createCell(4).setCellValue("宜人贷");
		row.createCell(6).setCellValue(value);
		row.createCell(7).setCellValue("本人");
		row.createCell(8).setCellValue("宜人贷");
		row.createCell(9).setCellValue("投资");
		
		row.createCell(10).setCellValue(text);
		return row;
	}
	public static boolean exist(HSSFCell cell,String... strs){
		for(String str:strs){
			if(str.equals(cell.getStringCellValue())){
				return true;
			}
		}
		return false;
	}
	public static HSSFWorkbook getTemplate() throws IOException{
		return new HSSFWorkbook(AlipayDemo.class.getResourceAsStream("myMoney.xls"));
	}
	
}
