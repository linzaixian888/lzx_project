package com.lzx.demo.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


public class HdfsDemo {
	private static String HDFS="hdfs://172.16.2.57:9000";
	private static String name="HTML.zip";
	private static int length=100;
	private static String source="d:/"+name;
	private static String target=HDFS+"/user/root/test/"+name;
	public static void main(String[] args) {
		try {
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void  test() throws Exception{
		long one=new File(source).length();
		System.out.println("单个文件大小(字节):"+one);
		long all=one*length;
		double allMB=all/1024.0/1024.0;
		System.out.println("所有文件大小(MB):"+allMB);
		OutputStream os=null;
		 FileInputStream fis=null;
		 Configuration conf = new Configuration();
		 conf.set("fs.default.name", HDFS);
		 conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER"); 
		 conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true"); 
		 conf.setBoolean("dfs.support.append", true);
		 FileSystem fs = FileSystem.get(conf);
		 fs.delete(new Path(target), true);
		 os=fs.create(new Path(target));
		 os.close();
		 long before=System.currentTimeMillis();
		 for(int i=0;i<length;i++){
			 os=fs.append(new Path(target));
			 fis=new FileInputStream(source);
			 IOUtils.copyBytes(fis, os, 4096,true);
		 }
		 long after=System.currentTimeMillis();
		 long diff=after-before;
		 System.out.println("上传["+length+"个文件]耗时:(豪秒):"+diff);
		 double sec=diff/1000.0;
		 System.out.println("上传["+length+"个文件]耗时:(秒):"+sec);
		 System.out.println("每秒传输(B/s):"+all/sec);
		 System.out.println("每秒传输(MB/s):"+allMB/sec);
	}
}
