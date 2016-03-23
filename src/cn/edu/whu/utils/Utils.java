/**
 * 
 */
package cn.edu.whu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

 
/**
 * @author 宝超
 *工具类
 */
public class Utils {
	  /**
     * 根据路径取出文件列表
     * @param path
     * @return
     */
     public static Map<String, File> getFileList(String filePath)
     {
    	 Map<String, File> map=new HashMap<>();
    	 File file=new File(filePath);
    	 int i=0;
    	 if(file.isDirectory()){
    		 File [] f=file.listFiles();
    		 for(File tempFile :f){
    			 if (tempFile.isFile()) {
    				 String fileName=tempFile.getName();
    				// String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
    				 map.put(fileName,tempFile);
    				 //i++;
					
				}
    		 }
    	 }
		return map;
    	 
     }
     /**
      * 读取一般文件（文件大小一般小于1Gb大小）的内容
      * 利用 org.apache.commons.io.FileUtils快速读写文件
      * @return 把文件返回为字符串
      */
     public static String readFile(String filePath)  {
    	 File file =new File(filePath);
    	 String fileContent="";
    	 try {
			fileContent=org.apache.commons.io.FileUtils.readFileToString(file,"utf-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return fileContent;
     }
     /**
      * 读取大文件（大于1Gb大小的文件）的内容
      * 使用apache commons包中的IO工具类读取大文件
      * 在方法中需要进行相应的业务处理
      */
     public  static void readBigFile(String filePath){
    	 File file=new File(filePath);
    	 LineIterator it=null;
    	 
			  try {
				it = FileUtils.lineIterator(file, "UTF-8");
				while(it.hasNext()){
					String line=it.nextLine();
					//System.out.println(line);
					//。。。。对每一行进行相应操作
				}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} finally{
			   LineIterator.closeQuietly(it);
		}
    	 return ;
     }
     
     /**
      * 换行，追加字符串到文件尾部
     * @return 
      */
     public static boolean writeToFile(  String filePath ,String content){
    	  File file=new File(filePath);
    	  try { 
    	   org.apache.commons.io.FileUtils.writeStringToFile(file,"\n"+ content, "utf-8"); 
    	  } catch (IOException e) { 
    	   e.printStackTrace(); 
    	  } 
    	 return true;
     }
     /**
      * 复制单个文件
      * @param fileFrom String 原文件路径 如：c:/abc.txt
      * @param fileTo String 复制后路径 如：d:/abc.txt
      * @return boolean
      */
     public static boolean copyFile(String fileFrom,String fileTo){
    	 try{
    		 FileInputStream in =new java.io.FileInputStream(fileFrom);
    		 File file=new File(fileTo);//如果目标路径不存在则创建目录
    		 if(!file.exists()){
    			 file.getParentFile().mkdirs();
    		 }
    		 FileOutputStream out =new FileOutputStream(fileTo);
    		 byte [] bt =new byte[1024];
    		 int count;
    		 while((count=in.read(bt))>0){
    			 out.write(bt, 0, count);
    			 
    		 }
    		 in.close();
    		 out.close();
    		 return true;
    	 }catch(IOException ex){
    	 return false;
    	 }
     }
     /**
      * 删除文件
      */
     public static void deleteFile(String filePath) {
         File file = new File(filePath);
         if (file.exists()) {
             file.delete();
         }
     }

     public static void deleteByFolder(String folderPath) {
         try {
             deleteAllFile(folderPath); //删除完里面所有内容
             String filePath = folderPath;
             filePath = filePath.toString();
             java.io.File myFilePath = new java.io.File(filePath);
             myFilePath.delete(); //删除空文件夹
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     public static boolean deleteAllFile(String path) {
         boolean flag = false;
         File file = new File(path);
         if (!file.exists()) {
             return flag;
         }
         if (!file.isDirectory()) {
             return flag;
         }
         String[] tempList = file.list();
         File temp = null;
         for (int i = 0; i < tempList.length; i++) {
             if (path.endsWith(File.separator)) {
                 temp = new File(path + tempList[i]);
             } else {
                 temp = new File(path + File.separator + tempList[i]);
             }
             if (temp.isFile()) {
                 temp.delete();
             }
             if (temp.isDirectory()) {
                 deleteAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                 deleteByFolder(path + "/" + tempList[i]);//再删除空文件夹
                 flag = true;
             }
         }
         return flag;
     }
     /**保存链表到磁盘
      *@author bczhang
      *@param d 待保存链表
      * @param saveFilePath 保存到的目录地址
      */
     public void saveResultByList(List<String> d,String saveFilePath){
     	File file=new File(saveFilePath);
     	StringBuffer str=new StringBuffer();
     	int cou=0;
     	for(String res:d){
     		cou++;
     		str.append(res.toString());
     		if(cou%10==0)
     			str.append("\n");
     	}
     	String content=str.toString();
     	try {
 			org.apache.commons.io.FileUtils.writeStringToFile(file,"\n"+ content, "utf-8");
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
     	
     	
     }
     /**保存Map数据到磁盘
      *@author bczhang
      *@param d 待保存map
      * @param saveFilePath 保存到的目录地址
      */
     public static void saveResultByHashMap(HashMap<String,Integer> map,String saveFilePath){
    	 File file=new File(saveFilePath);
      	 StringBuffer str=new StringBuffer();
      	 int cou=0;
         Iterator<?> it =map.entrySet().iterator();
         while(it.hasNext()){
        	 cou++;
        	 @SuppressWarnings("rawtypes")
			Map.Entry entry=(Map.Entry) it.next();
        	 Object key=entry.getKey();
        	 str.append(key.toString()+" ");
        	 Object val=entry.getValue();
        	 str.append(val.toString()+"  ");
        	 if(cou%30==0)
        		 str.append("\n");
        	
         }
         try {
			FileUtils.writeStringToFile(file, str.toString(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //System.out.println(str.toString());
     }
     
}
