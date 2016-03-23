 
package cn.edu.whu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * @author bczhang
 *预处理文本类
 *
 */
public class PreprocessText {
	/*
	 * 对文本进行预处理，去除所有的非中文字符
	 * 保存到
	 */
	private static String savePath="E:/temp/data/";
	public static void onlyChinese(String filePath){
		String[] tempsplit=filePath.split("/");
		String prefileName=savePath+tempsplit[tempsplit.length-1];
		//createFile(prefileName);
		File file=new File(filePath);
		File prefile=new File(prefileName);
		 LineIterator it=null;
    	 
    	 FileWriter filewriter=null;
		 PrintWriter pw=null;
    	 int count=0;
		  try {
			it = FileUtils.lineIterator(file, "UTF-8");
			filewriter=new FileWriter( prefile ,true);
			pw=new PrintWriter(filewriter);
			while(it.hasNext()){
				count++;
				String line=it.nextLine();
				if(line.matches("[\u4e00-\u9fa5]")){
					pw.print(line);
					if(count%100==0)
					pw.println();
				}
				pw.flush();
				filewriter.flush();
					
			}
			  
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} finally{
		   LineIterator.closeQuietly(it);
	}
		return  ;
	}
	
	
	public static  void main(String[] args){
		PreprocessText preText=new PreprocessText();
		String p="D:/DOWNLOAD/BaiduYunDownload/weibo_users_name.txt";
		onlyChinese(p);
	
	}
	 public static boolean createDir(String destDirName) {  
	        File dir = new File(destDirName);  
	        if (dir.exists()) {  
	            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
	            return false;  
	        }  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //创建目录  
	        if (dir.mkdirs()) {  
	            System.out.println("创建目录" + destDirName + "成功！");  
	            return true;  
	        } else {  
	            System.out.println("创建目录" + destDirName + "失败！");  
	            return false;  
	        }  
	    } 
	 public static boolean createFile(String destFileName) {  
	        File file = new File(destFileName);  
	        if(file.exists()) {  
	            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");  
	            return false;  
	        }  
	        if (destFileName.endsWith(File.separator)) {  
	            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");  
	            return false;  
	        }  
	        //判断目标文件所在的目录是否存在  
	        if(!file.getParentFile().exists()) {  
	            //如果目标文件所在的目录不存在，则创建父目录  
	            System.out.println("目标文件所在目录不存在，准备创建它！");  
	            if(!file.getParentFile().mkdirs()) {  
	                System.out.println("创建目标文件所在目录失败！");  
	                return false;  
	            }  
	        }  
	        //创建目标文件  
	        try {  
	            if (file.createNewFile()) {  
	                System.out.println("创建单个文件" + destFileName + "成功！");  
	                return true;  
	            } else {  
	                System.out.println("创建单个文件" + destFileName + "失败！");  
	                return false;  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());  
	            return false;  
	        }  
	    } 
}
