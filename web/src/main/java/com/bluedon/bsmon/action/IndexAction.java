package com.bluedon.bsmon.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.codehaus.jackson.map.ObjectMapper;

import com.bluedon.bsmon.action.base.BaseAction;
import com.bluedon.bsmon.service.HttpService;
import com.bluedon.bsmon.util.ConfUtil;
import com.bluedon.bsmon.util.HttpClientUtil;
import com.bluedon.bsmon.util.Resolve;
import com.bluedon.bsmon.util.Solve;
import com.bluedon.bsmon.util.TimeUtil;

@Namespace("/index")
public class IndexAction extends BaseAction{
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月");
	private ObjectMapper mapper=new ObjectMapper();
	private String username;
	private String time;
	private String userid;
	/**
	 * 默认最多重试登陆次数
	 */
	private static int NUMBER=3;
	@Resource
	private HttpService httpService;
	@Action(value="index",results={
			@Result(location="/WEB-INF/view/index.ftl")
	})
	public String index() throws Exception {
		return SUCCESS;
	}
	@Action("getAllUser")
	public String getAllUser()throws Exception{
		try {
			HttpClientUtil util=httpService.getHttpClientUtil(NUMBER);
			
			List<Map> resultList=new ArrayList<Map>();
			addUser(util, username, 1, resultList);
			setJSON("userList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			setJSON("error", e.toString());
		}
		return JSON;
	}
	private void addUser(HttpClientUtil util,String username,int page,List<Map> resultList) throws Exception{
		Map params=new HashMap();
		params.put("mnp", 50);
		params.put("l", 15);
		params.put("o", "PIN,DeptID__code");
		params.put("q", username);
		if(page>1){
			params.put("p", page);
		}
		params.put("stamp", System.currentTimeMillis());
		String jsonString=util.doPostString(ConfUtil.getConfig("url")+"/data/personnel/Employee/", params);
		Pattern p=Pattern.compile("data:([\\s\\S]+),\nrecord_count");
		Matcher m= p.matcher(jsonString);
		if(m.find()){
			List list=mapper.readValue(m.group(1), List.class);
			for(int i=0;i<list.size();i++){
				Map resultMap=new HashMap();
				List childList=(List) list.get(i);
				resultMap.put("username", childList.get(2));
				resultMap.put("userid", childList.get(1));
				resultMap.put("department", childList.get(4));
				resultList.add(resultMap);
			}
			int count=getCount(jsonString);
			if(count>100){
				throw new RuntimeException("纪录数为"+count+"，数据过多，请选择精确的关键字");
			}
			if(resultList.size()<count){
				addUser( util, username, page+1, resultList);
			}
		}
	}
	private int getCount(String str){
		Pattern p=Pattern.compile("record_count:(\\d+),");
		Matcher m=p.matcher(str);
		if(m.find()){
			return Integer.parseInt(m.group(1));
		}
		return 0;
	}
	/**
	 * 从原始纪录表里读数据
	 * @return
	 * @throws Exception
	 */
	@Action(value="showDetailSource",results={
			@Result(location="/WEB-INF/view/xgcalendar.ftl")
	})
	public String showDetailSource()throws Exception {
		//编码的转换
		time=new String(time.getBytes("iso8859-1"),"utf-8");
		try {
			Date date=sdf.parse(time);
			HttpClientUtil util=httpService.getHttpClientUtil(NUMBER);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DATE, 1);
			String before=TimeUtil.dateFormat(calendar.getTime());
			calendar.add(calendar.MONTH, 1);
			String after=TimeUtil.dateFormat(calendar.getTime());
			Map params=new HashMap();
			params.put("UserID__PIN__contains", userid);
			params.put("o", "-TTime,UserID__PIN");
			params.put("stamp", new Date().getTime());
			params.put("TTime__gte", before);
			params.put("TTime__lte", after);
			params.put("l", 999);
			params.put("mnp", 50);
			String jsonString=util.doPostString(ConfUtil.getConfig("url")+"/data/iclock/Transaction/", params);
			Pattern p=Pattern.compile("data:([\\s\\S]+),\nrecord_count");
			Matcher m= p.matcher(jsonString);
			List<Long> longList=new ArrayList<Long>();
			if(m.find()){
				List list=mapper.readValue(m.group(1), List.class);
				for(int i=0;i<list.size();i++){
		    		List childList=(List) list.get(i);
		    		String timeStr=(String) childList.get(4);
		    		longList.add(TimeUtil.timeParse(timeStr).getTime());
				}
				Collections.sort(longList);
			}
			HttpServletRequest request=getRequest();
			request.setAttribute("longList", longList);
			request.setAttribute("resultList", new Solve(longList).getResult());
			request.setAttribute("time", time);
		} catch (Exception e) {
			throw e;
		}
		return SUCCESS;
		
	}
	/**
	 * 从统计报表里读数据（纪录有时会不准确）
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	@Action(value="showDetail",results={
			@Result(location="/WEB-INF/view/xgcalendar.ftl")
	})
	public String showDetail()throws Exception {
		//编码的转换
		time=new String(time.getBytes("iso8859-1"),"utf-8");
		try {
			Date date=sdf.parse(time);
			HttpClientUtil util=httpService.getHttpClientUtil(NUMBER);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DATE, 1);
			String before=TimeUtil.dateFormat(calendar.getTime());
			calendar.add(calendar.MONTH, 1);
			String after=TimeUtil.dateFormat(calendar.getTime());
			Map params=new HashMap();
			params.put("UserID__in", userid);
			params.put("all", "True");
			params.put("checktime__range", "(\""+before+"\",\""+after+"\")");
			params.put("fields", "UserID.PIN,UserID.EName,checktime,CheckType,NewType");
			params.put("filecode", "gb18030");
			params.put("format", "xls");
			params.put("model", "AttRecAbnormite");
			params.put("o", "-pk");
			params.put("reportname", "统计结果详情");
			params.put("stamp", System.currentTimeMillis());
			params.put("t", "1");
			params.put("txtviewname", "");
			HttpResponse response=util.doGet(ConfUtil.getConfig("url")+"/api/att/AttRecAbnormite/", params);
			InputStream is=response.getEntity().getContent();
			Resolve r=new Resolve(is);
			HttpServletRequest request=getRequest();
			request.setAttribute("longList", r.getList());
			request.setAttribute("time", time);
		} catch (Exception e) {
			throw e;
		}
		return SUCCESS;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
