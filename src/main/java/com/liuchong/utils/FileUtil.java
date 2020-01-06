package com.liuchong.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static String getExtName(String str) {
		if(StringUtil.isNull(str)) {
			return null;
		}
		if(!str.contains(".")) {
			throw new RuntimeException("无法获取文件扩展名");
		}
		return str.substring(str.indexOf("."));
	}
	
	public static void delete(File file) {
		File[] listFiles = file.listFiles();
		for(File theFile : listFiles) {
			if(theFile.isDirectory()) {
				delete(theFile);
				theFile.delete();
			}else {
				theFile.delete();
			}
			
		}
	}
	public static void delete(String pathname) {
		delete(new File(pathname));
	}

	public static String getSystemUserHome() {
		return System.getProperty("user.home");
	}
	
	public static String getSystemTempDirectory() {
		return System.getProperty("java.io.tmpdir");
	}
	
	public static String getFileSize(File file) {
		long length = file.length();
		double len = length/1024.0;
		return String.format("%.2f",len)+"kb";
	}
	
	public static String readTextFile(File file) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			do {
				String readLine = br.readLine();
				sb.append(readLine);
				sb.append("\r\n");
			}while(br.read()!=-1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			StreamUtil.close(br);
		}
		return sb.toString();
	}
	
	public static List<String> readTextFileToList(File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			do {
				String readLine = br.readLine();
				list.add(readLine);
			}while(br.read()!=-1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			StreamUtil.close(br);
		}
		return list;
	}
	
	public static List<String> readTextFileToList(String pathname){
		return readTextFileToList(new File(pathname));
	}
	
}
