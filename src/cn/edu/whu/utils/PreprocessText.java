 
package cn.edu.whu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
}
