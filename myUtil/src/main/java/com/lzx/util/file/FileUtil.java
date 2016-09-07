package com.lzx.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public final static String DESKTOP="C:/Users/Administrator/Desktop";
	public final static String NEWLINE=System.getProperty("line.separator");
	private final static String MD5="MD5";
	private final static String SHA1="SHA1";
	private static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private final static String[] charTypes={".txt",".html",".htm",".js",".aspx",".css",".xml",".java"};
	
	/**
	 * 判断该字符串的扩展名是否为可用字符型复制的后缀扩展名
	 * @param str
	 * @return
	 */
	public boolean isCharType(String str){
		for(String temp:charTypes){
			if(str.endsWith(temp)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 通过自定义编 码方式获取指定文件内容
	 * @param path
	 * @param language
	 * @return
	 * @throws IOException 
	 */
	public String getContent(String path,String language) throws IOException{
		return getContent(new File(path), language);
	}
	
	/**
	 * 通过自定义编 码方式获取指定文件内容
	 * @param sourceFile
	 * @param language
	 * @return
	 * @throws IOException 
	 */
	public String getContent(File sourceFile,String language) throws IOException{
		StringBuffer sb=new StringBuffer();
		FileInputStream fis=null;
		Reader reader=null;
		try {
			fis=new FileInputStream(sourceFile);
			reader=new InputStreamReader(fis,language);
			int len;
			char[] buff=new char[1024];
			while((len=reader.read(buff))!=-1){
				sb.append(buff,0,len);
			}
		} catch (IOException e) {
			throw e;
		}finally{
			if(reader!=null){
				reader.close();
			}
			if(fis!=null){
				fis.close();
			}
		}
		return sb.toString();
	}
	/**
	 * 获取文件内容
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public String getContent(String path) throws IOException{
		return getContent(new File(path));
	}
	/**
	 * 获取文件内容
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	public String getContent(File sourceFile) throws IOException{
		StringBuffer sb=new StringBuffer();
		Reader reader=null;
		try {
			reader=new FileReader(sourceFile);
			int len;
			char[] buff=new char[1024];
			while((len=reader.read(buff))!=-1){
				sb.append(buff,0,len);
			}
		} catch (IOException e) {
			throw e;
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
		return sb.toString();
	}
	/**
	 * 通过自定义编码方式保存文件
	 * @param content
	 * @param path
	 * @param language
	 * @return
	 * @throws IOException 
	 */
	public void saveContent(String content,String path,String language) throws IOException{
		 saveContent(content, new File(path), language);
	}
	/**
	 * 保存文件
	 * @param content
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public void saveContent(String content,String path) throws IOException{
		 saveContent(content, new File(path));
	}
	/**
	 * 保存文件
	 * @param content
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public void saveContent(String content,File targetFile) throws IOException{
		Writer writer=null;
		try {
			 writer=new FileWriter(targetFile);
			writer.write(content);
		} catch (IOException e) {
			throw e;
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
	}
	/**
	 * 通过自定义编码方式保存文件
	 * @param content
	 * @param targetFile
	 * @param language
	 * @return
	 * @throws IOException 
	 */
	public void saveContent(String content,File targetFile,String language) throws IOException{
		FileOutputStream fos=null;
		Writer writer=null;
		try {
			fos=new FileOutputStream(targetFile);
			writer=new OutputStreamWriter(fos,language);
			writer.write(content);
		} catch (IOException e) {
			throw e;
		}finally{
			if(writer!=null){
				writer.close();
			}
			if(fos!=null){
				fos.close();
			}
		}
	}
	
	
	/**
	 * 将集合中的文件以字节流方式复制到指定的文件夹中
	 * @param list
	 * @param targetPath
	 * @throws IOException 
	 */
	public void saveFileByByte(List<File> list,String targetPath) throws IOException{
		saveFileByByte(list, new File(targetPath));
	}
	/**
	 * 将集合中的文件以字节流方式复制到指定的文件夹中
	 * @param list
	 * @param targetFile
	 * @throws IOException 
	 */
	public void saveFileByByte(List<File> list,File targetFile) throws IOException{
		if(!targetFile.isDirectory()){
			throw new RuntimeException("该目标地址不是文件夹");
		}
		String folderPath=createFolder(targetFile);
		for(File f:list){
			String temp=folderPath+File.separator+f.getName();
			File tempFile=new File(temp);
			if(tempFile.exists()){
				throw new RuntimeException("该文件已存在，可能存在重命名冲突，请及时解决");
			}else{
				copyFileByByte(f, tempFile);
			}
		}
	}
	/**
	 * 将集合中的文件以字符流方式复制到指定的文件夹中
	 * @param list
	 * @param targetPath
	 * @throws IOException 
	 */
	public void saveFileByChar(List<File> list,String targetPath) throws IOException{
		saveFileByChar(list, new File(targetPath));
	}
	/**
	 * 将集合中的文件以字符流方式复制到指定的文件夹中
	 * @param list
	 * @param targetFile
	 * @throws IOException 
	 */
	public void saveFileByChar(List<File> list,File targetFile) throws IOException{
		if(!targetFile.isDirectory()){
			throw new RuntimeException("该目标地址不是文件夹");
		}
		String folderPath=createFolder(targetFile);
		for(File f:list){
			String temp=folderPath+File.separator+f.getName();
			File tempFile=new File(temp);
			if(tempFile.exists()){
				throw new RuntimeException("该文件已存在，可能存在重命名冲突，请及时解决");
			}else{
				copyFileByChar(f, tempFile);
			}
		}
	}
	
	
	/**
	 * 通过字节流进行文件复制
	 * @param source
	 * @param target
	 * @return
	 * @throws IOException 
	 */
	public  void copyFileByByte(String source,String target) throws IOException{
		 copyFileByByte(new File(source), new File(target));
		
	}
	
	/**
	 * 通过字节流进行文件复制
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public  void copyFileByByte(File sourceFile,File targetFile) throws IOException{
			File parent=targetFile.getParentFile();
			targetFile=new File(parent,changeFileTitle(targetFile,targetFile.getName()));
			FileInputStream fis=new FileInputStream(sourceFile);
			FileOutputStream fos=new FileOutputStream(targetFile);
			copy(fis, fos);
			
	}
	
	
	
	
	/**
	 * 通过字符流进行文件复制
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public  void copyFileByChar(File sourceFile,File targetFile) throws IOException{
		 copyFileByLanguage(sourceFile, targetFile, null, null);
	}
	/**
	 * 通过字符流进行文件复制
	 * @param source
	 * @param target
	 * @return
	 * @throws IOException 
	 */
	public  void copyFileByChar(String source,String target) throws IOException{
		 copyFileByChar(new File(source), new File(target));
		
	}
	
	
	/**
	 * 自定义编码方式进行字符流复制文件
	 * @param source
	 * @param target
	 * @param inputLanguage
	 * @param outputLanguage
	 * @return
	 * @throws IOException 
	 */
	public void copyFileByChar(String source,String target,String inputLanguage,String outputLanguage) throws IOException{
		 copyFileByChar(new File(source), new File(target), inputLanguage, outputLanguage);
	}
	/**
	 * 自定义编码方式进行字符流复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @param inputLanguage
	 * @param outputLanguage
	 * @return
	 * @throws IOException 
	 */
	public void copyFileByChar(File sourceFile,File targetFile,String inputLanguage,String outputLanguage) throws IOException{
		 copyFileByLanguage(sourceFile, targetFile, inputLanguage, outputLanguage);
	}
	/**
	 * 自定义编码方式进行字符流复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @param inputLanguage
	 * @return
	 * @throws IOException 
	 */
	public void copyFileByCharInputLanguage(File sourceFile,File targetFile,String inputLanguage) throws IOException{
		 copyFileByLanguage(sourceFile, targetFile, inputLanguage, null);
	}
	/**
	 * 自定义编码方式进行字符流复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @param inputLanguage
	 * @param outputLanguage
	 * @return
	 */
	/**
	 * 自定义编码方式进行字符流复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @param outputLanguage
	 * @return
	 * @throws IOException 
	 */
	public void copyFileByCharOutputLanguage(File sourceFile,File targetFile,String outputLanguage) throws IOException{
		 copyFileByLanguage(sourceFile, targetFile, null, outputLanguage);
	}
	/**
	 * 自定义编码方式进行字符流复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @param inputLanguage
	 * @param outputLanguage
	 * @return
	 * @throws IOException 
	 */
	public void copyFileByLanguage(File sourceFile,File targetFile,String inputLanguage,String outputLanguage) throws IOException{
		FileInputStream fis=null;
		FileOutputStream fos=null;
		Reader reader=null;
		Writer writer=null;
		try {
			File parent=targetFile.getParentFile();
			targetFile=new File(parent,changeFileTitle(targetFile,targetFile.getName()));
			StringBuilder sb=new StringBuilder();
			 fis=new FileInputStream(sourceFile);
			 fos=new FileOutputStream(targetFile);
			 reader=null;
			if(inputLanguage!=null){
				reader=new InputStreamReader(fis, inputLanguage);
			}else{
				reader=new InputStreamReader(fis);
			}
			 writer=null;
			if(outputLanguage!=null){
				writer=new OutputStreamWriter(fos,outputLanguage);
			}else{
				writer=new OutputStreamWriter(fos);
			}
			int len;
			char[] buff=new char[1024];
			while((len=reader.read(buff))!=-1){
				sb.append(buff,0,len);
			}
			writer.write(changeContent(sb.toString()));
		} catch (IOException e) {
			throw e;
		}finally{
			if(reader!=null){
				reader.close();
			}
			if(writer!=null){
				writer.close();
			}
			if(fis!=null){
				fis.close();
			}
			if(fos!=null){
				fos.close();
			}
		}
	}
	
	
	/**
	 * 默认是字节流复制
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public void copyFileSwitch(File sourceFile,File targetFile) throws IOException{
		if(isCharType(targetFile.getName())){
			 copyFileByChar(sourceFile, targetFile);
		}else{
			 copyFileByByte(sourceFile, targetFile);
		}
		
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 字节流进行文件夹复制
	 * @param source
	 * @param target
	 * @return
	 * @throws IOException 
	 */
	public  void copyFolderByByte(String source,String target) throws IOException{
		 copyFolderByByte(new File(source), new File(target));
	}
	/**
	 * 字节流进行文件夹复制 
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public  void copyFolderByByte(File sourceFile,File targetFile) throws IOException{
		try {
			String foldPath=createFolder(targetFile);
			File[] files=sourceFile.listFiles();
			for(File f:files){
				String name=f.getName();
				File temp=new File(foldPath+File.separator+name);
				if(f.isDirectory()){
					copyFolderByByte(f, temp);
				}else{
					copyFileByByte(f, temp);
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}
	/**
	 * 字符流进行文件夹复制
	 * @param source
	 * @param target
	 * @return
	 * @throws IOException 
	 */
	public  void copyFolderByChar(String source,String target) throws IOException{
		 copyFolderByChar(new File(source), new File(target));
	}
	/**
	 * 字符流进行文件夹复制
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public  void copyFolderByChar(File sourceFile,File targetFile) throws IOException{ 
		try {
			String foldPath=createFolder(targetFile);
			File[] files=sourceFile.listFiles();
			for(File f:files){
				String name=f.getName();
				File temp=new File(foldPath+File.separator+name);
				if(f.isDirectory()){
					copyFolderByChar(f, temp);
				}else{
					copyFileByChar(f, temp);
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 *复制文件夹
	 * @param sourcePath
	 * @param targetPath
	 * @return
	 * @throws IOException 
	 */
	public void copyFolderSwitch(String sourcePath,String targetPath) throws IOException{
		 copyFolderSwitch(new File(sourcePath), new File(targetPath));
	}
	/**
	 * 复制文件夹
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public void copyFolderSwitch(File sourceFile,File targetFile) throws IOException{
		try {
			String foldPath=createFolder(targetFile);
			File[] files=sourceFile.listFiles();
			for(File f:files){
				String name=f.getName();
				File temp=new File(foldPath+File.separator+name);
				if(f.isDirectory()){
					copyFolderSwitch(f, temp);
				}else{
					copyFileSwitch(f, temp);
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 将某个文件或文件夹内的文件的编码进行转换
	 * @param path
	 * @param inputLanguage
	 * @param outputLanguage
	 * @throws IOException 
	 */
	public void changeLanguage(String path,String inputLanguage,String outputLanguage) throws IOException{
		changeLanguage(new File(path), inputLanguage, outputLanguage);
		
	}
	
	/**
	 * 将某个文件或文件夹内的文件的编码进行转换
	 * @param file
	 * @param inputLanguage
	 * @param outputLanguage
	 * @throws IOException 
	 */
	public void changeLanguage(File file,String inputLanguage,String outputLanguage) throws IOException{
		if(file.isDirectory()){
			File[] files=file.listFiles();
			for(File f:files){
				changeLanguage(f, inputLanguage, outputLanguage);
			}
		}else{
			String name=file.getName();
			if(isCharType(name)){
				String temp=getContent(file, inputLanguage);
				saveContent(temp, file, outputLanguage);
			}else{
				System.out.println(name+"不符合当前字符流类型库的后缀，跳过不转换");
			}
			
		}
		
	}
	
	
	
	
	/**
	 * 获得文件夹及子文件夹的所有文件
	 * @param path
	 * @return
	 */
	public List<File> getFileList(String path){
		return getFileList(new File(path));
	}
	/**
	 * 获得文件夹及子文件夹的所有文件
	 * @param targetFile
	 * @return
	 */
	public List<File> getFileList(File targetFile){
		List<File> list=new ArrayList<File>();
		if(targetFile.isDirectory()){
			getFileList(targetFile, list);
		}else {
			list.add(targetFile);
		}
		return list;
	}
	/**
	 * 将某个文件夹的所有文件全部放入一个list集合(不包括该文件夹)
	 * @param folder
	 * @param list
	 */
	private void getFileList(File folder,List<File> list){
		File[] files=folder.listFiles();
		for(File f:files){
			if(f.isDirectory()){
				getFileList(f, list);
			}else{
				list.add(f);
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 * 改变文件夹标题
	 * @param sourceFile
	 * @param allPath
	 * @return
	 */
	public  String changeFolderTitle(File sourceFile,String allPath){
		return allPath;
	}
	/**
	 * 改变文件标题
	 * @param sourceFile
	 * @param allPath
	 * @return
	 */
	public String changeFileTitle(File sourceFile,String title){
		return title;
	}
	/**
	 * 改变文件内容
	 * @param content
	 * @return
	 */
	public  String changeContent(String content){
		return content;
	}
	/**
	 * 创建目标文件夹
	 * @param targetFile
	 * @return
	 */
	private String createFolder(File targetFile){
		String path=changeFolderTitle(targetFile,targetFile.getAbsolutePath());
		File folder=new File(path);
		if(!folder.exists()){
			folder.mkdirs();
		}
		return path;
		
	}
	
	/**
	 * 删除指定文件夹或文件
	 * @param path
	 * @return
	 */
	public  boolean del(String path){
		return del(new File(path));
	}
	/**
	 * 删除指定文件夹或文件
	 * @param target
	 * @return
	 */
	public  boolean del(File target){
		boolean flag=false;
		try {
			if(target.isDirectory()){
				File[] files=target.listFiles();
				for(File f:files){
					del(f);
				}
				flag=target.delete();
			}else{
				flag=target.delete();;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 将集合中的字符串写到指定文件中
	 * @param list
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public void write(List<String> list,File targetFile) throws IOException{
		FileWriter fw=null;
		BufferedWriter bfw=null;
		try {
			fw=new FileWriter(targetFile);
			bfw=new BufferedWriter(fw);
			for(String temp:list){
				bfw.write(temp);
				bfw.newLine();
			}
		} catch (IOException e) {
			throw e;
		}finally{
			try {
				if(bfw!=null){
					bfw.close();
				}
				if(fw!=null){
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 将集合中的字符串写到指定文件中
	 * @param list
	 * @param targetPath
	 * @return
	 * @throws IOException 
	 */
	public void write(List<String> list,String targetPath) throws IOException{
		 write(list, new File(targetPath));
	}
	/**
	 * 将指定的文件里的内容以分隔符分离成一个数组
	 * @param targetPath
	 * @param separator
	 * @return
	 * @throws IOException 
	 */
	public String[] readToArray(String targetPath,String separator) throws IOException{
		return getContent(targetPath).split(separator);
	}
	/**
	 * 将指定的文件里的内容以换行符分离成一个数组
	 * @param targetPath
	 * @return
	 * @throws IOException 
	 */
	public String[] readToArray(String targetPath) throws IOException{
		return readToArray(targetPath, NEWLINE);
	}
	/**
	 * 流的复制，并关掉所有输入输出流
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	private void copy(InputStream is,OutputStream os) throws IOException{
		try {
			int len=0;
			byte[] buff=new byte[1024];
			while((len=is.read(buff))!=-1){
				os.write(buff, 0, len);
			}
		} catch (IOException e) {
			throw e;
		}finally{
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
		}
	}
	/**
	 * 数据的加密
	 * @param file
	 * @param hashType
	 * @return
	 * @throws Exception
	 */
	private static String getHash(File file, String hashType)
			throws Exception {
		InputStream fis;
		fis = new FileInputStream(file);
		MessageDigest md5;
		try {
			byte[] buffer = new byte[1024];
			md5 = MessageDigest.getInstance(hashType);
			int numRead = 0;
			while ((numRead = fis.read(buffer)) > 0) {
				md5.update(buffer, 0, numRead);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			fis.close();
		}
		return toHexString(md5.digest());
	}
	/**
	 * 字节数组加密成字符
	 * @param b
	 * @return
	 */
	private static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}
	/**
	 * 获得某个文件的MD5值
	 * @param targetFile
	 * @return
	 * @throws Exception 
	 */
	public String getMD5(File targetFile) throws Exception{
			return getHash(targetFile, MD5);
	}
	
	/**
	 * 获得某个文件的SHA1值
	 * @param targetFile
	 * @return
	 * @throws Exception 
	 */
	public String getSHA1(File targetFile) throws Exception{
			return getHash(targetFile, SHA1);
	}
	public static void main(String[] args) throws Exception {
//		List<String> list=new ArrayList<String>();
//		for(int i=1;i<=24;i++){
//			list.add("COMMENT ON COLUMN tb_serviceability.v"+i+" IS '"+(i-1)+"点的可用性';");
//		}
//		new FileUtil().write(list, "d:/temp.txt");
		FileUtil util=new FileUtil();
		for(int i=2;i<=100;i++){
			util.copyFileByByte("d:/test/root1.zip", "d:/test/root"+i+".zip");
		}
	}
}
