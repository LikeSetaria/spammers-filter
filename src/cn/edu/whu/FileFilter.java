/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.chainsaw.Main;

import cn.edu.whu.utils.Utils;

/**
 * @author 宝超
 *过滤weibo_users.csv中的微博认证用户，包括蓝V、黄V、媒体认证V
 */
public class FileFilter {
	 private static String RESULT_PATH="D:\\Whuer\\Major\\weibo\\RESULT\\weibo_users.txt";
	Utils utils=new Utils();
	
	   @SuppressWarnings("resource")
	public   void Filter_V(String filePath){
	    	 File file=new File(filePath);
	    	 LineIterator it=null;
	    	 String line=null;
	    	// List<String> results=new LinkedList<>();
	    	 
	    	 FileWriter filewriter=null;
			 PrintWriter pw=null;
				  try {
					it = FileUtils.lineIterator(file, "UTF-8");
					Utils.deleteFile(RESULT_PATH);
					filewriter=new FileWriter(new File(RESULT_PATH),true);
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
}
