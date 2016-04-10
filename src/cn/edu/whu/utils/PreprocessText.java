 
package cn.edu.whu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * @author bczhang
 *Ԥ�����ı���
 *
 */
public class PreprocessText {
	/*
	 * ���ı�����Ԥ����ȥ�����еķ������ַ�
	 * ���浽
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
	            System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�Ŀ��Ŀ¼�Ѿ�����");  
	            return false;  
	        }  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //����Ŀ¼  
	        if (dir.mkdirs()) {  
	            System.out.println("����Ŀ¼" + destDirName + "�ɹ���");  
	            return true;  
	        } else {  
	            System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�");  
	            return false;  
	        }  
	    } 
	 public static boolean createFile(String destFileName) {  
	        File file = new File(destFileName);  
	        if(file.exists()) {  
	            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");  
	            return false;  
	        }  
	        if (destFileName.endsWith(File.separator)) {  
	            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ�����ΪĿ¼��");  
	            return false;  
	        }  
	        //�ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����  
	        if(!file.getParentFile().exists()) {  
	            //���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼  
	            System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");  
	            if(!file.getParentFile().mkdirs()) {  
	                System.out.println("����Ŀ���ļ�����Ŀ¼ʧ�ܣ�");  
	                return false;  
	            }  
	        }  
	        //����Ŀ���ļ�  
	        try {  
	            if (file.createNewFile()) {  
	                System.out.println("���������ļ�" + destFileName + "�ɹ���");  
	                return true;  
	            } else {  
	                System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�");  
	                return false;  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�" + e.getMessage());  
	            return false;  
	        }  
	    }
	 /**
	  * 预处理follows_user.csv文件，从中提取潜在垃圾用户ID所有对应的 关系
	  * @param filePath
	  */
	 
     public    void readBigFile(String followsFilePath,String uidFilePath,String resultFilePath){
    	 File followFile=new File(followsFilePath);
    	 File uidFile =new File(uidFilePath);
    	 LineIterator it=null;
    	 LineIterator iter=null;
    	//这里必须使用Hash一类的，因为对于follow_user.csv中的一行，都要去这个集合中查找是否出现，
    	 //一开始我使用的是List 使用contain（）方法， 可想而知速度奇慢无比，每条比较都要遍历二百多万条数据，而follow文件有8Gb大小
    	 //后来使用HashSet速度提高了百万倍 ，体会Hash原理，对于查找的意义，以及为什么会这么快
    	  HashSet<String> set=new HashSet<String>();
    	  StringBuilder str1=null;
    	  StringBuilder str2=new StringBuilder();
    	 StringBuilder temp=new StringBuilder();
    	 FileWriter fw=null;
    	  Map<String,LinkedList<String>> map=new HashMap<String,LinkedList<String>>(); 
			  try {
			    fw = new FileWriter(resultFilePath,true); 
				it = FileUtils.lineIterator(followFile, "UTF-8");
				iter = FileUtils.lineIterator(uidFile, "UTF-8");
				while(iter.hasNext()){
					str1=new StringBuilder(iter.nextLine());
					set.add(str1.toString());
				}
				while(it.hasNext()){
					str2=new StringBuilder(it.nextLine());
					String[] arr=str2.toString().split(",");
					if(set.contains(arr[0])||set.contains(arr[1])){
//						temp.append(str2);
//						temp.append("\n");
						fw.write(str2.toString());
						fw.write("\n");
						//System.out.println(str2);
						
					}
					fw.flush();
				
				}
				
					 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} finally{
			   LineIterator.closeQuietly(it);
			   if (fw != null)  
	                try {  
	                    fw.close();  
	                } catch (IOException e) {  
	                    throw new RuntimeException("关闭失败！");  
	                }  
		}
    	 return ;
     }
}
