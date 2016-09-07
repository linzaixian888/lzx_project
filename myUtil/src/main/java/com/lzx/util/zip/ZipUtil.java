package com.lzx.util.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {
	public static void main(String[] args) {
		
	}
	/**
	 * 解压，并关闭输入流
	 * @param is
	 * @param target
	 * @throws Exception
	 */
	public static void unZip(InputStream is,File target) throws Exception{
		ZipInputStream zis = new ZipInputStream(is);
		try {
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				File f = new File(target, entry.getName());
				//采用此种方式创建文件夹里因为有可能子文件在父文件夹的上面
				File parent = f.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				if (!entry.isDirectory()) {
					copy(zis, new FileOutputStream(f));
				}else if(!f.exists()){
					f.mkdirs();
				}
				zis.closeEntry();

			}
		} catch (Exception e) {
			throw e;
		}finally{
			is.close();
		}
		
		
	}
	/**
	 * 从输入流复制到输出流,并且不关掉输入流
	 * 
	 * @param is
	 * @param os
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	private static void copy(InputStream is, OutputStream os) throws IOException {
		try {
			int len;
			byte[] buff = new byte[1024];
			while ((len = is.read(buff)) != -1) {
				os.write(buff, 0, len);
			}
			os.close();
		} catch (IOException e) {
			throw e;
		}
		finally{
			os.close();
		}
	}

}
