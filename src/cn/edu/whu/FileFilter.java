/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.chainsaw.Main;

import cn.edu.whu.utils.Utils;
 
/**
 * @author 宝超
 *过滤weibo_users.csv中的微博认证用户，包括蓝V、黄V、媒体认证V
 */
public class FileFilter {
	 
	Utils utils=new Utils();
	
	   @SuppressWarnings("resource")
	public   void Filter_V(String sourceFilePath,String saveTargetPath){
	    	 File file=new File(sourceFilePath);
	    	 LineIterator it=null;
	    	 String line=null;
	    	// List<String> results=new LinkedList<>();
	    	 
	    	 FileWriter filewriter=null;
			 PrintWriter pw=null;
				  try {
					it = FileUtils.lineIterator(file, "UTF-8");
					Utils.deleteFile(saveTargetPath);
					filewriter=new FileWriter(new File(saveTargetPath),true);
					pw=new PrintWriter(filewriter);
					while(it.hasNext()){
						 line=it.nextLine();
						 String [] strArray=line.split(",");
						if(strArray[0].equals("false")){
							//System.out.println(strArray[0]);
							pw.print(line);
							pw.println();
							//results.add(line);						}
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
	    	 return ;
	     }
	   /*
	    * 对文本文件进行预处理
	    * 逐行去除非中文字符，对于非中文字符用空字符串进行替换
	    * 
	    */
		public   void onlyChinese(String sourceFilePath,String saveTargetPath){
	    	 File file=new File(sourceFilePath);
	    	 LineIterator it=null;
	    	 String line=null;
	    	 FileWriter filewriter=null;
			 PrintWriter pw=null;
			 int count =1;
		 	 try {
					it = FileUtils.lineIterator(file, "UTF-8");
					Utils.deleteFile(saveTargetPath);
					filewriter=new FileWriter(new File(saveTargetPath),true);
					pw=new PrintWriter(filewriter);
					while(it.hasNext()){
						 //逐行读取进行处理
						count++;
						 line=it.nextLine(); 
						 //非中文字符用空字符串替换
						pw.print(line.replaceAll("[^\u4e00-\u9fa5]", "")+"   ");
						 if(count%10==0)
							 pw.println();
						 
						
				}  pw.flush();
		             filewriter.flush();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} finally{
				   LineIterator.closeQuietly(it);
			}			  
	    	 return ;
	     }
	   
	   
	   /**
	    * 提取所有的用户名
	    */
	   @SuppressWarnings("resource")
	public void  extractUserName(String sourceFilePath,String saveTargetPath){
		   File file=new File(sourceFilePath);
	    	 LineIterator it=null;
	    	 String line=null;
	    	// List<String> results=new LinkedList<>();
	    	 
	    	 FileWriter filewriter=null;
			 PrintWriter pw=null;
				  try {
					it = FileUtils.lineIterator(file, "GBK");
					Utils.deleteFile(saveTargetPath);
					filewriter=new FileWriter(new File(saveTargetPath),true);
					pw=new PrintWriter(filewriter);
					while(it.hasNext()){
						 line=it.nextLine();
						 String [] strArray=line.split(","); 
						 System.out.println(strArray[1]);
						// System.out.println(line);
						 pw.print(strArray[1]);
					     pw.println(); 	 					
					}
					 
					pw.flush();
					filewriter.flush();
				
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} finally{
				   LineIterator.closeQuietly(it);
			}			  
	    	 return ;
	   }
}
