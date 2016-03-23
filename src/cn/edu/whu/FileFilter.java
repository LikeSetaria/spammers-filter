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
 * @author ����
 *����weibo_users.csv�е�΢����֤�û���������V����V��ý����֤V
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
	    * ���ı��ļ�����Ԥ����
	    * ����ȥ���������ַ������ڷ������ַ��ÿ��ַ��������滻
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
						 //���ж�ȡ���д���
						count++;
						 line=it.nextLine(); 
						 //�������ַ��ÿ��ַ����滻
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
	    * ��ȡ���е��û���
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
