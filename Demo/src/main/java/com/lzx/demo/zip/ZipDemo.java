package com.lzx.demo.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipDemo {
	public static void main(String[] args) throws Exception {
		// FileUtil fu = new FileUtil();
		// ZipFile file = new ZipFile("e:/BDWeb.war");
		// Enumeration<ZipEntry> all = (Enumeration<ZipEntry>) file.entries();
		// while (all.hasMoreElements()) {
		// ZipEntry entry = all.nextElement();
		// System.out.println(entry.getName());
		// InputStream is = file.getInputStream(entry);
		// File f = new File("e:/BDWeb", entry.getName());
		// File parent = f.getParentFile();
		// if (!parent.exists()) {
		// System.out.println(f.getName()+"-------"+parent.getName());
		// parent.mkdirs();
		// }
		// if(!entry.isDirectory()){
		// fu.copy(is, new FileOutputStream(f));
		// }else{
		// f.mkdirs();
		// }
		// }
		FileInputStream fis = new FileInputStream("e:/BDWeb.war");
		unZip(fis, "e:/BDWeb");
		// ZipInputStream zis=new ZipInputStream(fis);
	}

	public static void unZip(InputStream is, String target) throws Exception {
		ZipInputStream zis = new ZipInputStream(is);
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {
			File f = new File(target, entry.getName());
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
		is.close();
	}

	/**
	 * 从输入流复制到输出流
	 * 
	 * @param is
	 * @param os
	 * @return
	 * @throws Exception
	 */
	public static void copy(InputStream is, OutputStream os) throws Exception {
		int len;
		byte[] buff = new byte[1024];
		while ((len = is.read(buff)) != -1) {
			os.write(buff, 0, len);
		}
		os.close();
	}

}
