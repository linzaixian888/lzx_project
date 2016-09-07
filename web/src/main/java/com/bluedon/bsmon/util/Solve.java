package com.bluedon.bsmon.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solve {
	private List<Long> longList;

	public Solve(List<Long> longList) {
		this.longList = longList;
	}
	public List<Map> getResult(){
		List<Long> tempList=new ArrayList<Long>();
		List<Map> resultList=new ArrayList<Map>();
		Long last=null;
		for(Long l:longList){
			if(last!=null){
				if(!isSameDay(l, last)){
					handleOneDay(tempList,resultList);
					tempList.clear();
				}
			}
			tempList.add(l);
			last=l;
			
		}
		if(tempList.size()>0){
			handleOneDay(tempList,resultList);
			tempList.clear();
		}
		return resultList;
	}
	public void handleOneDay(List<Long> longList,List<Map> resultList){
		
		ArrayList<Map> moringList=new ArrayList<Map>();
		ArrayList<Map> nooningList=new ArrayList<Map>();
		ArrayList<Map> afternoonList=new ArrayList<Map>();
		for(Long l:longList){
			Map map=new HashMap();
			map.put("time", l);
			if(isMoring(l)){
				map.put("title", "早上打卡");
				map.put("color", 9);
			}else if(isNooning(l)){
				map.put("title", "中午打卡");
				map.put("color", 9);
			}else if(isAfternoon(l)){
				map.put("title", "下午打卡");
				map.put("color", 9);
			}else{
				map.put("title", "异常打卡");
				map.put("color", 0);
			}
			resultList.add(map);
		}
		
	}
	public boolean isMoring(long time){
		Date date=new Date(time);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE,30);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if(calendar.getTime().after(date)){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean isNooning(long time){
		Date date=new Date(time);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if(calendar.getTime().before(date)){
			calendar.set(Calendar.HOUR_OF_DAY, 13);
			calendar.set(Calendar.MINUTE,30);
			if(calendar.getTime().after(date)){
				return true;
			}
		}
		return false;
	}
	public boolean isAfternoon(long time){
		Date date=new Date(time);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if(calendar.getTime().before(date)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断是否同一天
	 * @param one
	 * @param two
	 * @return
	 */
	public boolean isSameDay(long one,long two){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date(one));
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(new Date(two));
		if(day==calendar.get(calendar.DAY_OF_MONTH)){
			return true;
		}else{
			return false;
		}
		
	}
	public static void main(String[] args) {
		
	}
}
