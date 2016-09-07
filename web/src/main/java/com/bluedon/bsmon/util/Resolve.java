package com.bluedon.bsmon.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.steps.sortSkipAndLimit;

public class Resolve {
	private InputStream is;

	public Resolve(InputStream is) {
		this.is = is;
	}
	public List<Long> getList(){
		List<Long> longList=new ArrayList<Long>();
		try {
			HSSFWorkbook workbook=new HSSFWorkbook(is);
			HSSFSheet sheet=workbook.getSheetAt(0);
			int length=sheet.getLastRowNum();
			for(int i=1;i<=length;i++){
				HSSFRow row=sheet.getRow(i);
				String timeStr=row.getCell(2).getStringCellValue();
				longList.add(TimeUtil.timeParse(timeStr).getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//进行排序操作
		Collections.sort(longList);;
		return longList;
	}
	
}
