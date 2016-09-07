package com.bluedon.bsmon.util;


import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientUtil {
	private CloseableHttpClient client=null;
	private String charset="utf-8";
	private int connectTimeout=60000;
	private int socketTimeout=60000;
	public HttpClientUtil(){
		try {
			init();
		} catch (Exception e) {
			throw new RuntimeException("初始化失败");
		}
	}
	public void init() throws Exception{
		SSLContext sslContext = new SSLContextBuilder()
			.loadTrustMaterial(null, new TrustStrategy() {
				//信任所有
				public boolean isTrusted(X509Certificate[] chain,
		            String authType) throws CertificateException {
					return true;
				}
			}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		RequestConfig config=RequestConfig.custom().setConnectTimeout(this.connectTimeout).setSocketTimeout(this.socketTimeout).build();
		client=HttpClients.custom().setDefaultRequestConfig(config).setSSLSocketFactory(sslsf).build();
		
	}
	
	public HttpResponse doPost(String url) throws ClientProtocolException, IOException{
		return doPost(url, null,this.charset);
	}
	
	public HttpResponse doPost(String url,Map params) throws ClientProtocolException, IOException{
		return doPost(url, params,this.charset);
	}
	public HttpResponse doPost(String url,Map params,String charset) throws ClientProtocolException, IOException{
		HttpPost post=new HttpPost(url);
		if(params!=null&&params.size()!=0){
			Set keys=params.keySet();
			List<NameValuePair> paramlist=new ArrayList<NameValuePair>();
			for(Object key:keys){
				Object value=params.get(key);
				if(value==null){
					value="";
				}
				paramlist.add(new BasicNameValuePair(key+"", value+""));
			}
			post.setEntity(new UrlEncodedFormEntity(paramlist,charset));
			
		}
		return client.execute(post);
	}
	public HttpResponse doGet(String url) throws ClientProtocolException, IOException, URISyntaxException{
		return doGet(url, null, this.charset);
	}
	public HttpResponse doGet(String url,Map params) throws ClientProtocolException, IOException, URISyntaxException{
		return doGet(url, params,this.charset);
	}
	public HttpResponse doGet(String url,Map params,String charset) throws ClientProtocolException, IOException, URISyntaxException{
		if(params!=null&&params.size()!=0){
			Set keys=params.keySet();
			List<NameValuePair> paramlist=new ArrayList<NameValuePair>();
			for(Object key:keys){
				Object value=params.get(key);
				if(value==null){
					value="";
				}
				paramlist.add(new BasicNameValuePair(key+"", value+""));
			}
			url=url+"?"+URLEncodedUtils.format(paramlist, charset);
		}
		HttpGet get=new HttpGet(url);
		return client.execute(get);
		
		
	}
	public String doPostString(String url) throws ClientProtocolException, IOException, URISyntaxException{
		return doPostString(url, null, this.charset);
	}
	public String doPostString(String url,Map params) throws ClientProtocolException, IOException, URISyntaxException{
		return doPostString(url, params,this.charset);
	}
	public String doPostString(String url,Map params,String charset)throws ClientProtocolException, IOException, URISyntaxException{
		HttpResponse response=doPost(url, params, charset);
		int state=response.getStatusLine().getStatusCode();
		Header[] headers=response.getAllHeaders();
		if(state==200){
			InputStream is=null;
			Reader reader=null;
			StringWriter sWriter=null;
			try {
				is=response.getEntity().getContent();
				reader=new InputStreamReader(is,charset);
				sWriter=new StringWriter();
				copy(reader, sWriter);
				return sWriter.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeStream(reader);
				closeStream(is);
				closeStream(sWriter);
			}
		}
		return null;
	}
	public String doGetString(String url) throws ClientProtocolException, IOException, URISyntaxException{
		return doGetString(url, null, this.charset);
	}
	public String doGetString(String url,Map params) throws ClientProtocolException, IOException, URISyntaxException{
		return doGetString(url, params,this.charset);
	}
	
	public String doGetString(String url,Map params,String charset)throws ClientProtocolException, IOException, URISyntaxException{
		HttpResponse response=doGet(url, params, charset);
		int state=response.getStatusLine().getStatusCode();
		if(state==200){
			InputStream is=null;
			Reader reader=null;
			StringWriter sWriter=null;
			try {
				is=response.getEntity().getContent();
				reader=new InputStreamReader(is,charset);
				sWriter=new StringWriter();
				copy(reader, sWriter);
				return sWriter.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeStream(reader);
				closeStream(is);
				closeStream(sWriter);
			}
		}
		return null;
	}
	public void close(){
		try {
			if(client!=null){
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void closeStream(Closeable closeable){
		if(closeable!=null){
			try {
				closeable.close();
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 从输入流复制到输出流
	 * @param is
	 * @param os
	 * @return
	 * @throws IOException 
	 */
	private void copy(Reader reader,Writer writer) throws IOException{
		int len;
		char[] buff=new char[1024];
		while((len=reader.read(buff))!=-1){
			writer.write(buff, 0, len);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new HttpClientUtil().doGetString("https://www.baidu.com"));
		
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getSocketTimeout() {
		return socketTimeout;
	}
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	
}
