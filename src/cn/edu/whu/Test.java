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
 * @author 宝超
 *测试方法类
 */
public class Test {
	 private static String WEIBO_USER_FILEPATH="D:\\Whuer\\Major\\Refs4Spammers";
	 private final static Logger logger = Logger.getLogger(Test.class);
	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
		FileFilter filter=new FileFilter();
		/*//测试getFileList()方法
      
      Map<String, File> map=utils.getFileList(WEIBO_USER_FILEPATH);
      for(Iterator i = map.keySet().iterator(); i.hasNext();){
    	   Object obj = i.next();
    	   System.out.println(obj);
    	   System.out.println("key=" + obj + " value=" + map.get(obj));
      }
      for(Map.Entry<String, File> entry :map.entrySet()){
    	  System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }*/
		
		//测试copyFile()方法
		/*
		utils.copyFile("c:\\unintall.log", "d:\\unintall.log");
		*/
		
		
		//测试读取大文件
		/*
		logger.info("大文件测试");
	//	utils.readBigFile("D:\\Whuer\\Major\\weibo\\weibo_users.csv");
		*/
		//测试读取一般文件
		//utils.readFile("e:test.txt");
		
		//System.out.println(utils.writeToFile("e:\\test.txt", "hhhhhh"));
		//System.out.println(utils.readFile("e:\\test.txt"));
		//filter.Filter_V("D:\\Whuer\\Major\\weibo\\weibo_users.txt");
		filter.onlyChinese("D:/DOWNLOAD/BaiduYunDownload/weibo_users_name.txt","E:/temp/data/777.txt");
		 
				
	}
	

}
