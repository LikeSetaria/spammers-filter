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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
					utils.deleteFile(saveTargetPath);
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
					utils.deleteFile(saveTargetPath);
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
					utils.deleteFile(saveTargetPath);
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
	   
	   /** 根据用户名提取用户ID
	    * @param userFilePath weibo_users.txt微博用户文件
	    * @param saveTargetPath 提取后的用户ID的保存路径
	    * @param userNameFilePath 微博用户名文件
	    * 
	    */
	   @SuppressWarnings("unchecked")
	public void selectUidByUserName(String userFilePath,String saveTargetPath,String userNameFilePath ){
		   File file =new File(userFilePath);
		   File userNameFile=new File(userNameFilePath);
		   //把所有用户的用户名、用户ID做一个映射，放到Map里面
		   Map<String ,String > map=new HashMap<String,String>();
		   //保存找到的用户ID
		   List<String> list=new LinkedList<String>();
		   LineIterator it=null;
		    Iterator userNameIt=map.entrySet().iterator();
	    	 String line=null; 
	    	 String line2=null;
	    	 String str=null;
	    	// Map.Entry<String,String> entry=(Map.Entry<String,String>)userNameIt.next(); 
	    	 try {
				it = FileUtils.lineIterator(file, "utf-8");
				userNameIt=FileUtils.lineIterator(userNameFile, "utf-8");
				while(it.hasNext()){
					 line=it.nextLine();
					 String [] strArray=line.split(","); 
					 if(strArray.length>5)
					 map.put(strArray[1],strArray[4]);
					 //System.out.println(strArray[1]+"   "+strArray[4]);
				}
				while(userNameIt.hasNext()){
					//entry=(Entry<String, String>) userNameIt.next();
					line2=(String) userNameIt.next();
					if(map.containsKey(line2)){ 
						str=map.get(line2);
						list.add(str);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 utils.saveResultByList(list, saveTargetPath);
		   
	   }
}
