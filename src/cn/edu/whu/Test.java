/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.whu.utils.Utils;

/**
 * @author ����
 *���Է�����
 */
public class Test {
	 private static String WEIBO_USER_FILEPATH="D:\\Whuer\\Major\\Refs4Spammers";
	 private final static Logger logger = Logger.getLogger(Test.class);
	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
		FileFilter filter=new FileFilter();
		/*//����getFileList()����
      
      Map<String, File> map=utils.getFileList(WEIBO_USER_FILEPATH);
      for(Iterator i = map.keySet().iterator(); i.hasNext();){
    	   Object obj = i.next();
    	   System.out.println(obj);
    	   System.out.println("key=" + obj + " value=" + map.get(obj));
      }
      for(Map.Entry<String, File> entry :map.entrySet()){
    	  System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }*/
		
		//����copyFile()����
		/*
		utils.copyFile("c:\\unintall.log", "d:\\unintall.log");
		*/
		
		
		//���Զ�ȡ���ļ�
		/*
		logger.info("���ļ�����");
	//	utils.readBigFile("D:\\Whuer\\Major\\weibo\\weibo_users.csv");
		*/
		//���Զ�ȡһ���ļ�
		//utils.readFile("e:test.txt");
		
		//System.out.println(utils.writeToFile("e:\\test.txt", "hhhhhh"));
		//System.out.println(utils.readFile("e:\\test.txt"));
		//filter.Filter_V("D:\\Whuer\\Major\\weibo\\weibo_users.txt");
		filter.onlyChinese("D:/DOWNLOAD/BaiduYunDownload/weibo_users_name.txt","E:/temp/data/777.txt");
		 
				
	}
	

}
