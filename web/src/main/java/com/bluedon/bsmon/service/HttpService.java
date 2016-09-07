package com.bluedon.bsmon.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluedon.bsmon.util.ConfUtil;
import com.bluedon.bsmon.util.HttpClientUtil;
@Service
public class HttpService {
	private HttpClientUtil util=new HttpClientUtil();
	
	/**
	 * 获取一个登陆过的工具类
	 * @param number
	 * @return
	 * @throws Exception
	 */
	public HttpClientUtil getHttpClientUtil(int number) throws Exception{
		if(number>0){
			try {
				Map<String,Integer> cache=new HashMap<String, Integer>();
				Map params=new HashMap();
				params.put("client_language", "zh-cn");
				params.put("finnger10", "");
				params.put("finnger9", "");
				params.put("login_type", "pwd");
				params.put("password", ConfUtil.getConfig("password"));
				params.put("template10	", "");
				params.put("template9", "");
				params.put("username", ConfUtil.getConfig("username"));
				String resultStr=util.doPostString(ConfUtil.getConfig("url")+"/accounts/login/",params);
				if(!"ok".equals(resultStr)){
					throw new RuntimeException(resultStr);
				}
				return util;
			}  catch (Exception e) {
				if(number!=1){
					return getHttpClientUtil(number-1);
				}else{
					throw e;
				}
			}
		}else{
			return util;
		}
		
		
	}
}
